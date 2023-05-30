package engine.components;

import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

/**
 * The Mesh class represents a 3D model geometry using Vertex Buffer Objects (VBOs).
 * It handles the creation and management of VBOs and Vertex Array Objects (VAOs).
 */
public class Mesh {

    private final int vaoID;
    private final List<Integer> vboIDList;
    private final int vertexCount;

    /**
     * Creates a Mesh with vertex positions, texture coordinates, and indices.
     *
     * @param vertexPositions    An array of vertex positions.
     * @param textureCoordinates An array of texture coordinates.
     * @param indices            An array of vertex indices.
     */
    public Mesh(float[] vertexPositions, float[] textureCoordinates, int[] indices) {
        vertexCount = indices.length;
        vboIDList = new ArrayList<>();

        // Generate and bind VAO
        vaoID = glGenVertexArrays();
        glBindVertexArray(vaoID);

        // Create VBOs for vertex positions and texture coordinates
        createVBO(0, 3, vertexPositions);
        createVBO(1, 2, textureCoordinates);

        // Create VBO for indices and store in the VAO
        int vboID = glGenBuffers();
        vboIDList.add(vboID);

        IntBuffer indicesBuffer = MemoryUtil.memAllocInt(indices.length);
        indicesBuffer.put(indices).flip();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboID);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);

        // Unbind buffers
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);

        // Free the memory allocated for the indices buffer
        MemoryUtil.memFree(indicesBuffer);
    }

    /**
     * Creates a Vertex Buffer Object (VBO) and stores it in the list.
     *
     * @param index   The index of the VBO in the VAO.
     * @param size    The size of each vertex attribute.
     * @param content The content of the VBO (vertex positions or texture coordinates).
     */
    private void createVBO(int index, int size, float[] content) {
        int vboID = glGenBuffers();
        vboIDList.add(vboID);

        // Allocate memory for the buffer and fill it with the content
        FloatBuffer buffer = MemoryUtil.memAllocFloat(content.length);
        buffer.put(content).flip();

        // Bind, upload data to the GPU, and setup vertex attribute pointers
        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
        glEnableVertexAttribArray(index);
        glVertexAttribPointer(index, size, GL_FLOAT, false, 0, 0);

        // Free the memory allocated for the buffer
        MemoryUtil.memFree(buffer);
    }

    /**
     * Returns the Vertex Array Object (VAO) ID of this Mesh.
     *
     * @return The VAO ID.
     */
    public int getVaoID() {
        return vaoID;
    }

    /**
     * Returns the number of vertices in this Mesh.
     *
     * @return The vertex count.
     */
    public int getVertexCount() {
        return vertexCount;
    }
}