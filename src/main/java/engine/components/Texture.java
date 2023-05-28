package engine.components;

import static org.lwjgl.opengl.GL11.glGenTextures;

public class Texture {

    private final int textureID, width, height;

    public Texture(int textureID, int width, int height) {
        this.textureID = textureID;
        this.width = width;
        this.height = height;
    }

    public int getTextureID() {
        return textureID;
    }
}

