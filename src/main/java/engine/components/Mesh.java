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

public class Mesh {

    private final int vaoID;
    private final List<Integer> vboIDList;
    private final int vertexCount;

    public Mesh(float[] vertexPositions, float[] textureCoordinates, int[] indices) {
        vertexCount = indices.length;
        vboIDList = new ArrayList<>();

        vaoID = glGenVertexArrays();
        glBindVertexArray(vaoID);

        createVBO(0, 3, vertexPositions);
        createVBO(1, 2, textureCoordinates);

        int vboID = glGenBuffers();
        vboIDList.add(vboID);

        IntBuffer indicesBuffer = MemoryUtil.memAllocInt(indices.length);
        indicesBuffer.put(indices).flip();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboID);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);

        MemoryUtil.memFree(indicesBuffer);
    }

    private void createVBO(int index, int size, float[] content) {
        int vboID = glGenBuffers();
        vboIDList.add(vboID);

        FloatBuffer buffer = MemoryUtil.memAllocFloat(content.length);
        buffer.put(content).flip();

        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
        glEnableVertexAttribArray(index);
        glVertexAttribPointer(index, size, GL_FLOAT, false, 0, 0);

        MemoryUtil.memFree(buffer);
    }

    public int getVaoID() {
        return vaoID;
    }

    public int getVertexCount() {
        return vertexCount;
    }
}

