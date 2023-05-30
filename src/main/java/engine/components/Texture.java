package engine.components;

/**
 * The Texture class represents a texture in OpenGL.
 * It stores the texture ID, width, and height of the texture.
 */
public class Texture {

    private final int textureID, width, height;

    /**
     * Creates a Texture with the specified texture ID, width, and height.
     *
     * @param textureID The OpenGL-generated texture ID.
     * @param width     The width of the texture.
     * @param height    The height of the texture.
     */
    public Texture(int textureID, int width, int height) {
        this.textureID = textureID; // Store the texture ID
        this.width = width;         // Store the texture width
        this.height = height;       // Store the texture height
    }

    /**
     * Returns the texture ID of this Texture.
     *
     * @return The texture ID.
     */
    public int getTextureID() {
        return textureID;
    }
}