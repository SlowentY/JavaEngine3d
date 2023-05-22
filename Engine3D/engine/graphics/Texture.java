package Engine3D.engine.graphics;

import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT;
import static org.lwjgl.stb.STBImage.*;
import static org.lwjgl.opengl.GL40.*;

public class Texture {

    private int textureID;
    private ByteBuffer dataBuffer;
    private IntBuffer width, height;
    private String path;
    public Texture(String path)
    {
        try(MemoryStack stack = MemoryStack.stackPush())
        {
            this.path = path;
            width = stack.mallocInt(1);
            height = stack.mallocInt(1);
            IntBuffer channels = stack.mallocInt(1);

            dataBuffer = stbi_load(path, width, height, channels, 4);
            if(dataBuffer == null)
            {
                throw new RuntimeException("Image " + path + " do not loaded!");
            }

            textureID = glGenTextures();
            glBindTexture(GL_TEXTURE_2D, textureID);
            glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAX_ANISOTROPY_EXT, 8);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width.get(), height.get(), 0,
                    GL_RGBA, GL_UNSIGNED_BYTE, dataBuffer);
            glGenerateMipmap(GL_TEXTURE_2D);
        }
    }
    public void bind()
    {
        glBindTexture(GL_TEXTURE_2D, textureID);
    }
    public void cleanup()
    {
        glDeleteTextures(textureID);
    }
    public String getPath()
    {
        return path;
    }
}
