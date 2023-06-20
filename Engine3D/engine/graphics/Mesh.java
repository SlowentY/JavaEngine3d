package Engine3D.engine.graphics;

import org.joml.Vector3f;
import org.lwjgl.opengl.GL40;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.util.*;

import static org.lwjgl.opengl.GL40.*;

public class Mesh {
    private Texture texture;
    private int numVertices;
    private Vector3f position;
    private float scale;
    private int vaoId;
    private List<Integer> vboIdList;

    public Mesh(float[] positions, float[] textCoords, Texture texture, Vector3f pos, int numVertices) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            this.position = pos;
            this.texture = texture;
            this.numVertices = numVertices;
            vboIdList = new ArrayList<>();

            this.scale = 1.0f;

            vaoId = glGenVertexArrays();
            glBindVertexArray(vaoId);

            // Positions VBO
            int vboId = glGenBuffers();
            vboIdList.add(vboId);
            FloatBuffer positionsBuffer = stack.callocFloat(positions.length);
            positionsBuffer.put(0, positions);
            glBindBuffer(GL_ARRAY_BUFFER, vboId);
            glBufferData(GL_ARRAY_BUFFER, positionsBuffer, GL_STATIC_DRAW);

            glEnableVertexAttribArray(0);
            glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

            vboId = glGenBuffers();
            vboIdList.add(vboId);
            FloatBuffer textCoordsBuffer = MemoryUtil.memAllocFloat(textCoords.length);
            textCoordsBuffer.put(0, textCoords);
            glBindBuffer(GL_ARRAY_BUFFER, vboId);
            glBufferData(GL_ARRAY_BUFFER, textCoordsBuffer, GL_STATIC_DRAW);

            glEnableVertexAttribArray(1);
            glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);

            glBindBuffer(GL_ARRAY_BUFFER, 0);
            glBindVertexArray(0);
        }
    }

    public void cleanup() {
        vboIdList.stream().forEach(GL40::glDeleteBuffers);
        glDeleteVertexArrays(vaoId);
    }
    public void setPosition(Vector3f pos)
    {
        this.position = pos;
    }
    public int getNumVertices() {
        return numVertices;
    }

    public final int getVaoId() {
        return vaoId;
    }

    public Texture getTexture()
    {
        return texture;
    }

    public Vector3f getPosition() { return position; }
    public void setScale(float scale)
    {
        this.scale = scale;
    }
    public float getScale() {
        return scale;
    }
}
