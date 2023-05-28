package engine.objects;

import engine.components.Mesh;
import engine.components.Texture;
import org.joml.Vector3f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class PlaneObject extends Object {

    public PlaneObject() {
        init();
    }

    public void init() {
        createMesh();
        this.setPosition(new Vector3f(0.5f, 0.5f, 0.0f));
        this.setRotation(new Vector3f(0.0f, 0.0f, 0.0f));
        this.setScale(new Vector3f(0.5f, 0.5f, 0.5f));
    }

    public void createMesh() {
        float[] vertexPositions = {
                -1f, -1f, 0,  // Bottom left
                1f, -1f, 0,   // Bottom right
                -1f, 1f, 0,   // Top left
                1f, 1f, 0     // Top right
        };

        float[] textureCoordinates = {
                0, 0,    // Bottom left
                1, 0,    // Bottom right
                0, 1,    // Top left
                1, 1     // Top right
        };

        int[] indices = {
                1, 2, 3,
                1, 0, 2
        };

        this.setMesh(new Mesh(vertexPositions, textureCoordinates, indices));
    }

    public void render() {
        if(this.getTexture() != null){
            //Activate first texture Bank
            glActiveTexture(GL_TEXTURE0);
            //Bind texture
            glBindTexture(GL_TEXTURE_2D, this.getTexture().getTextureID());
        }

        glBindVertexArray(this.getMesh().getVaoID());
        glDrawElements(GL_TRIANGLES, this.getMesh().getVertexCount(), GL_UNSIGNED_INT, 0);

        glBindVertexArray(0);
        glBindTexture(GL_TEXTURE_2D,0);
    }
}
