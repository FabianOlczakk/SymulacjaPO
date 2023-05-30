package engine.utils;

import org.lwjgl.glfw.GLFWKeyCallback;

import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

/**
 * The KeyboardInput class is responsible for handling keyboard input using GLFW.
 */
public class KeyboardInput extends GLFWKeyCallback {

    /**
     * An array representing the state of each key on the keyboard.
     * The index corresponds to the key code, and the value is true if the key is pressed, false otherwise.
     */
    public static boolean[] keys = new boolean[65536];

    /**
     * Invoked when a key is pressed, released, or repeated.
     *
     * @param window   The window the event was generated for.
     * @param key      The keyboard key that was pressed or released.
     * @param scancode The system-specific scancode of the key.
     * @param action   The key action (GLFW_PRESS, GLFW_RELEASE, or GLFW_REPEAT).
     * @param mods     Bitfield describing which modifier keys were held down.
     */
    public void invoke(long window, int key, int scancode, int action, int mods) {
        keys[key] = action != GLFW_RELEASE;
    }

    /**
     * Checks if a specific key is currently pressed.
     *
     * @param keycode The key code to check.
     * @return true if the specified key is pressed, false otherwise.
     */
    public static boolean isKeyDown(int keycode) {
        return keys[keycode];
    }
}