package engine.render;

import org.joml.Matrix4f;
import org.joml.Vector4f;
import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL43.GL_COMPUTE_SHADER;

/**
 * The Shader class is responsible for managing and handling shader programs in OpenGL.
 */
/**
 * The Shader class is responsible for managing and handling shader programs in OpenGL.
 */
public class Shader {

    private final int programID;
    private int vertexShaderID;
    private int fragmentShaderID;
    private final Map<String, Integer> uniforms;

    /**
     * Constructs a new Shader and initializes the shader program.
     *
     * @throws Exception If a shader program cannot be created.
     */
    public Shader() throws Exception {
        uniforms = new HashMap<>();
        programID = glCreateProgram();

        if (programID == 0) {
            throw new Exception("Cannot create shader program");
        }
    }

    /**
     * Creates a shader of the specified type using the provided shader resource.
     *
     * @param shaderResource The shader resource containing the shader code.
     * @param shaderType     The type of shader, such as GL_VERTEX_SHADER or GL_FRAGMENT_SHADER.
     * @return The ID of the created shader.
     * @throws Exception If a shader cannot be created or compiled.
     */
    private int createShader(String shaderResource, int shaderType) throws Exception {
        int shaderID = glCreateShader(shaderType);

        if (shaderID == 0) {
            throw new Exception("Cannot create shader: '" + shaderType + "'");
        }

        glShaderSource(shaderID, shaderResource);
        glCompileShader(shaderID);

        if(glGetShaderi(shaderID, GL_COMPILE_STATUS) == 0) {
            throw new Exception("Cannot compile shader: " + glGetShaderInfoLog(shaderID,1024));
        }

        glAttachShader(programID, shaderID);

        return shaderID;
    }

    public void createVertexShader(String shaderResource) throws Exception {
        vertexShaderID = createShader(shaderResource, GL_VERTEX_SHADER);
    }

    public void createFragmentShader(String shaderResource) throws Exception {
        fragmentShaderID = createShader(shaderResource, GL_FRAGMENT_SHADER);
    }

    public void createComputeShader(String shaderResource) throws Exception {
        vertexShaderID = createShader(shaderResource, GL_COMPUTE_SHADER);
    }

    public void link() throws Exception {
        glLinkProgram(programID);

        if (glGetProgrami(programID, GL_LINK_STATUS) == 0) {
            throw new Exception("Cannot link program: "+ glGetProgramInfoLog(programID,1024));
        }

        if (vertexShaderID != 0) {
            glDetachShader(programID, vertexShaderID);
        }

        if (fragmentShaderID != 0) {
            glDetachShader(programID, fragmentShaderID);
        }

        glValidateProgram(programID);
    }

    public void createUniforms(String uniformName) throws Exception {
        int uniformLocation = glGetUniformLocation(programID, uniformName);

        if (uniformLocation < 0) {
            throw new Exception("Cannot find uniform in shader: " + uniformName);
        }

        uniforms.put(uniformName, uniformLocation);
    }

    public void setUniform(String name, Matrix4f value) {
        try(MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer buffer = stack.mallocFloat(16);
            value.get(buffer);
            glUniformMatrix4fv(uniforms.get(name), false, buffer);
        }
    }

    public void setUniform(String name, int value) {
        glUniform1i(uniforms.get(name), value);
    }

    public void setUniform(String name, float value) {
        glUniform1f(uniforms.get(name), value);
    }

    public void setUniform(String name, Vector4f value){
        glUniform4f(uniforms.get(name),value.x,value.y,value.z,value.w);
    }

    public void bind() {
        glUseProgram(programID);
    }

    public void unbind() {
        glUseProgram(0);
    }
}