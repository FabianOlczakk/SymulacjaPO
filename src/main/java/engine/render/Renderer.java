package engine.render;

import engine.launcher.Parameters;
import engine.launcher.SpawnMode;
import engine.objects.PlaneObject;
import engine.utils.ResourceLoader;
import engine.utils.Transformation;
import engine.window.Window;
import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_DYNAMIC_COPY;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL42.GL_SHADER_IMAGE_ACCESS_BARRIER_BIT;
import static org.lwjgl.opengl.GL42.glMemoryBarrier;
import static org.lwjgl.opengl.GL43.GL_SHADER_STORAGE_BUFFER;
import static org.lwjgl.opengl.GL43.glDispatchCompute;

public class Renderer {
    private int width;
    private int height;
    private final Transformation transformation;
    private final Parameters simulationSettings;
    private Shader renderShader;
    private Shader computeShader;
    private Shader postProcessShader;

    public Renderer(Parameters simulationSettings) {
        this.transformation = new Transformation();
        this.simulationSettings = simulationSettings;
    }

    public void init() throws Exception {
        width = simulationSettings.getWIDTH();
        height = simulationSettings.getHEIGHT();

        renderShader = new Shader();
        renderShader.createVertexShader(ResourceLoader.loadResource("/shaders/vertex.glsl"));
        renderShader.createFragmentShader(ResourceLoader.loadResource("/shaders/fragment.glsl"));
        renderShader.link();
        renderShader.createUniforms("modelViewMatrix");
        renderShader.createUniforms("texture_sampler");

        computeShader = new Shader();
        computeShader.createComputeShader(ResourceLoader.loadResource("/shaders/compute.glsl"));
        computeShader.link();
        computeShader.createUniforms("AColor");
        computeShader.createUniforms("sensorAngleSpacingA");
        computeShader.createUniforms("turnSpeedA");
        computeShader.createUniforms("sensorOffsetDistA");
        computeShader.createUniforms("sensorSizeA");
        computeShader.createUniforms("BColor");
        computeShader.createUniforms("sensorAngleSpacingB");
        computeShader.createUniforms("turnSpeedB");
        computeShader.createUniforms("sensorOffsetDistB");
        computeShader.createUniforms("sensorSizeB");
        computeShader.createUniforms("deltaTime");
        computeShader.createUniforms("height");
        computeShader.createUniforms("width");

        int ssbo = glGenBuffers();
        glBindBuffer(GL_SHADER_STORAGE_BUFFER, ssbo);
        float[] agents = createAgents(simulationSettings.getAGENTS_COUNT());
        glBindBufferBase(GL_SHADER_STORAGE_BUFFER, 2, ssbo);
        glBufferData(GL_SHADER_STORAGE_BUFFER, agents, GL_DYNAMIC_COPY);
        glBindBuffer(GL_SHADER_STORAGE_BUFFER, 0);

        postProcessShader = new Shader();
        postProcessShader.createComputeShader(ResourceLoader.loadResource("/shaders/postProcess.glsl"));
        postProcessShader.link();
        postProcessShader.createUniforms("width");
        postProcessShader.createUniforms("height");
        postProcessShader.createUniforms("deltaTime");
        postProcessShader.createUniforms("diffuseSpeed");
        postProcessShader.createUniforms("evaporateSpeed");
    }

    private float[] createAgents(int numberOfAgents) {
        float[] result = new float[numberOfAgents * 4];

        for (int i = 0; i < numberOfAgents; i++) {
            float randomAngle = (float) (Math.random() * Math.PI * 2);

            if (simulationSettings.getSPAWN_MODE() == SpawnMode.POINT) {
                result[i * 4] = (float) (width /2);
                result[i * 4 + 1] = (float) (height /2);
                result[i * 4 + 2] = (randomAngle);

            } else if (simulationSettings.getSPAWN_MODE() == SpawnMode.RANDOM) {
                result[i * 4] = (float) (width * Math.random());
                result[i * 4 + 1] = (float) (height * Math.random());
                result[i * 4 + 2] = (randomAngle);

            } else if (simulationSettings.getSPAWN_MODE() == SpawnMode.INWARD_CIRCLE) {
                float x = result[i * 4] = (float) (width /2 + 100 * Math.cos(randomAngle));
                float y = result[i * 4 + 1] = (float) (height /2 + 100 * Math.sin(randomAngle));
                result[i * 4 + 2] = (float) Math.atan2(
                        ((float) height /2 - y) / simulationSettings.getHEIGHT(),
                        ((float) width /2 - x) / simulationSettings.getWIDTH()
                );

            } else if (simulationSettings.getSPAWN_MODE() == SpawnMode.RANDOM_CIRCLE) {
                result[i * 4] = (float) (width /2 + (Math.random() * (height/4)) * Math.cos(randomAngle));
                result[i * 4 + 1] = (float) (height /2 + (Math.random() * (height/4)) * Math.sin(randomAngle));
                result[i * 4 + 2] = randomAngle;
            }

            result[i * 4 + 3] = Math.random() > simulationSettings.getCELL_B_PROBABILITY() ? 0 : 1;
        }

        return result;
    }

    public void render(Window window, PlaneObject plane) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT | GL_STENCIL_BUFFER_BIT);

        glViewport(0, 0, window.getWindowWidth(), window.getWindowHeight());

        glClear(GL_COLOR_BUFFER_BIT);
        glBindFramebuffer(GL_FRAMEBUFFER, 0);

        computeShader.bind();
        computeShader.setUniform("AColor", simulationSettings.getCELL_COLOR_A());
        computeShader.setUniform("sensorAngleSpacingA", simulationSettings.getSENSOR_ANGLE_SPACING_A());
        computeShader.setUniform("turnSpeedA", simulationSettings.getTURN_SPEED_A());
        computeShader.setUniform("sensorOffsetDistA", simulationSettings.getSENSOR_OFFSET_DISTANCE_A());
        computeShader.setUniform("sensorSizeA", simulationSettings.getSENSOR_SIZE_A());
        computeShader.setUniform("BColor", simulationSettings.getCELL_COLOR_B());
        computeShader.setUniform("sensorAngleSpacingB", simulationSettings.getSENSOR_ANGLE_SPACING_B());
        computeShader.setUniform("turnSpeedB", simulationSettings.getTURN_SPEED_B());
        computeShader.setUniform("sensorOffsetDistB", simulationSettings.getSENSOR_OFFSET_DISTANCE_B());
        computeShader.setUniform("sensorSizeB", simulationSettings.getSENSOR_SIZE_B());
        computeShader.setUniform("deltaTime", 1);
        computeShader.setUniform("width", width);
        computeShader.setUniform("height", height);

        glDispatchCompute(32, 16, 16);
        glMemoryBarrier(GL_SHADER_IMAGE_ACCESS_BARRIER_BIT);

        postProcessShader.bind();
        postProcessShader.setUniform("width", width);
        postProcessShader.setUniform("height", height);
        postProcessShader.setUniform("deltaTime", 1);
        postProcessShader.setUniform("diffuseSpeed", simulationSettings.getDIFFUSE_SPEED());
        postProcessShader.setUniform("evaporateSpeed", simulationSettings.getEVAPORATE_SPEED());

        int postProcessingGroupSize = 16;
        glDispatchCompute(width/ postProcessingGroupSize,height/ postProcessingGroupSize,1);
        glMemoryBarrier(GL_SHADER_IMAGE_ACCESS_BARRIER_BIT);

        renderShader.bind();
        Matrix4f orthographic = transformation.getOrtho2DProjectionMatrix(0,1,1,0,0,1);
        transformation.buildOrtoProjModelMatrix(plane, orthographic);
        renderShader.setUniform("modelViewMatrix", transformation.buildOrtoProjModelMatrix(plane, orthographic));
        renderShader.setUniform("texture_sampler",0);
        plane.render();
        renderShader.unbind();
    }
}

