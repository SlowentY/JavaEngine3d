package Engine3D.engine.graphics;

import Engine3D.engine.scene.Scene;

import java.util.*;

import static org.lwjgl.opengl.GL40.*;

public class SceneRender {

    private ShaderProgram shaderProgram;

    public SceneRender() {
        List<ShaderProgram.ShaderModuleData> shaderModuleDataList = new ArrayList<>();
        shaderModuleDataList.add(new ShaderProgram.ShaderModuleData("resources/scene.vert", GL_VERTEX_SHADER));
        shaderModuleDataList.add(new ShaderProgram.ShaderModuleData("resources/scene.frag", GL_FRAGMENT_SHADER));
        shaderProgram = new ShaderProgram(shaderModuleDataList);
    }

    public void cleanup() {
        shaderProgram.cleanup();
    }

    public void render(Scene scene) {
        shaderProgram.bind();

        scene.getMeshMap().values().forEach(mesh -> {
                    glBindVertexArray(mesh.getVaoId());
                    glDrawArrays(GL_TRIANGLES, 0, mesh.getNumVertices());
                }
        );

        glBindVertexArray(0);

        shaderProgram.unbind();
    }
}