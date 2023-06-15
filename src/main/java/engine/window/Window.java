package engine.window;

import engine.components.Texture;
import engine.launcher.Parameters;
import engine.objects.PlaneObject;
import engine.render.Renderer;
import engine.utils.KeyboardInput;
import engine.utils.Timer;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL32.glFramebufferTexture;
//import static org.lwjgl.opengl.GL42.glBindImageTexture;
//import static org.lwjgl.opengl.GL43.GL_SHADER_STORAGE_BUFFER;
import static org.lwjgl.opengl.GL42.glBindImageTexture;
import static org.lwjgl.opengl.GL43.GL_SHADER_STORAGE_BUFFER;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    private final int windowWidth;
    private final int windowHeight;
    private final int textureWidth;
    private final int textureHeight;
    private final String title;
    private long glfwWindow;
    private Timer timer;
    private Renderer renderer;
    private PlaneObject plane;
    private final Parameters simulationSettings;
    private final static int TARGET_UPS = 60;

    /**
     * Constructs a Window object with the specified title, window dimensions, and simulation settings.
     *
     * @param title             The title of the window.
     * @param windowWidth       The width of the window in pixels.
     * @param windowHeight      The height of the window in pixels.
     * @param simulationSettings The simulation settings.
     */
    public Window(String title, Parameters simulationSettings) {
        this.title = title;
        this.windowWidth = simulationSettings.getWINDOW_WIDTH();
        this.windowHeight = simulationSettings.getWINDOW_HEIGHT();
        this.simulationSettings = simulationSettings;
        this.textureWidth = simulationSettings.getWIDTH();
        this.textureHeight = simulationSettings.getHEIGHT();
    }

    /**
     * Initializes the window, timer, renderer, and plane objects.
     */
    public void run() {
        try {
            init();
            loop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Initializes the game.
     *
     * @throws Exception if initialization fails.
     */
    private void init() throws Exception {
        // Error callback
        GLFWErrorCallback.createPrint(System.err).set();

        // Initializing GLFW
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Window settings
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

        // Create window
        glfwWindow = glfwCreateWindow(this.windowWidth, this.windowHeight, this.title, NULL, NULL);
        if (glfwWindow == NULL) {
            throw new IllegalStateException("Failed to create the GLFW window.");
        }

        // Set OpenGL context to current window
        glfwMakeContextCurrent(glfwWindow);

        // Set keyboard callback
        GLFWKeyCallback keyCallback = new KeyboardInput();
        glfwSetKeyCallback(glfwWindow, keyCallback);

        // Set window to be visible and focused
        glfwShowWindow(glfwWindow);
        glfwFocusWindow(glfwWindow);

        // Create OpenGL capabilities
        GL.createCapabilities();

        // Enable transparent textures
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        glEnable(GL_STENCIL_TEST);
        glEnable(GL_DEPTH_TEST);

        // Initializing timer
        timer = new Timer();

        // Initializing renderer
        renderer = new Renderer(simulationSettings);

        // Initializing plane
        plane = new PlaneObject();

        // Create texture
        int textureID = glGenTextures();
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, textureID);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA32F, textureWidth, textureHeight, 0, GL_RGBA, GL_FLOAT, NULL);
        glBindImageTexture(0, textureID, 0, false, 0, GL_READ_WRITE, GL_RGBA32F);

        // Set plane texture
        plane.setTexture(new Texture(textureID, textureWidth, textureHeight));

        // Create temporary texture
        int tempTextureID = glGenTextures();
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, tempTextureID);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA32F, textureWidth, textureHeight, 0, GL_RGBA, GL_FLOAT, NULL);

        int framebuffer = glGenBuffers();
        glBindBuffer(GL_FRAMEBUFFER, framebuffer);
        glFramebufferTexture(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, tempTextureID, 0);

        renderer.init(); // Initialize the renderer
    }

    /**
     * Main game loop.
     * Renders the game and updates the simulation.
     */
    private void loop() {
        float accumulator = 0f;
        boolean simulation = false;

        render(); // Initial render before entering the loop

        while (!glfwWindowShouldClose(glfwWindow)) {

            float elapsedTime = timer.getElapsedTime();
            accumulator += elapsedTime;

            float interval = 1f / TARGET_UPS;
            while (accumulator >= interval) {
                accumulator -= interval;
            }

            // Check if the space key is pressed to start/stop the simulation
            if(KeyboardInput.isKeyDown(GLFW_KEY_SPACE)) {
                simulation = !simulation; // Toggle the simulation state
            }

            if (simulation) {
                render(); // Render the game during simulation
            } else {
                glfwPollEvents(); // Process input events when simulation is paused
            }
        }
    }

    /**
     * Renders the game and updates the simulation.
     */
    private void render() {
        int ssbo = glGenBuffers(); // Generate a new shader storage buffer object (SSBO)
        glBindBuffer(GL_SHADER_STORAGE_BUFFER, ssbo); // Bind the SSBO
        float[] agents = {0}; // Placeholder for agent data
        glBindBufferBase(GL_SHADER_STORAGE_BUFFER, 3, ssbo); // Bind the SSBO to shader binding point 3
        glBufferData(GL_SHADER_STORAGE_BUFFER, agents, GL_DYNAMIC_COPY); // Set the data of the SSBO
        glBindBuffer(GL_SHADER_STORAGE_BUFFER, 0); // Unbind the SSBO

        renderer.render(this, plane); // Perform game rendering

        glfwSwapBuffers(glfwWindow); // Swap the front and back buffers
        glfwPollEvents(); // Process input events
    }

    /**
     * Retrieves the width of the game window.
     *
     * @return The width of the window.
     */
    public int getWindowWidth() {
        return windowWidth;
    }

    /**
     * Retrieves the height of the game window.
     *
     * @return The height of the window.
     */
    public int getWindowHeight() {
        return windowHeight;
    }
}
