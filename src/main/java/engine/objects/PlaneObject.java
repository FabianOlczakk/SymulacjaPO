package engine.objects;

import engine.components.Mesh;
import engine.components.Texture;
import org.joml.Vector3f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

/**
 * The PlaneObject class represents a 2D plane object in the simulation.
 * It extends the Object class and initializes its own mesh and texture.
 */
public class PlaneObject extends Object implements GameEngineObject{

    /**
     * Constructor for the PlaneObject class.
     * Initializes the plane object by calling the init() method.
     */
    public PlaneObject() {
        init();
    }

    /**
     * Initializes the plane object by creating a mesh and setting its position, rotation, and scale.
     */
    @Override
    public void init() {
        createMesh();
        this.setPosition(new Vector3f(0.5f, 0.5f, 0.0f));
        this.setRotation(new Vector3f(0.0f, 0.0f, 0.0f));
        this.setScale(new Vector3f(0.5f, 0.5f, 0.5f));
    }

    /**
     * Creates the mesh for the plane object with vertex positions, texture coordinates, and indices.
     */
    @Override
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

    /**
     * Renders the plane object by binding the texture and mesh, then drawing the elements using OpenGL.
     */
    @Override
    public void render() {
        if (this.getTexture() != null) {
            // Activate first texture bank
            glActiveTexture(GL_TEXTURE0);
            // Bind texture
            glBindTexture(GL_TEXTURE_2D, this.getTexture().getTextureID());
        }

        glBindVertexArray(this.getMesh().getVaoID());
        glDrawElements(GL_TRIANGLES, this.getMesh().getVertexCount(), GL_UNSIGNED_INT, 0);

        glBindVertexArray(0);
        glBindTexture(GL_TEXTURE_2D, 0);
    }
}