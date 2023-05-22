package Engine3D.engine.graphics;

import Engine3D.engine.UniformMap;
import Engine3D.engine.scene.Scene;

import java.util.*;

import static org.lwjgl.opengl.GL40.*;

public class SceneRender {

    private Shader shader;
    private UniformMap uniforms;

    public SceneRender() {
        List<Shader.ShaderModuleData> shaderModuleDataList = new ArrayList<>();
        shaderModuleDataList.add(new Shader.ShaderModuleData("resources/scene.vert", GL_VERTEX_SHADER));
        shaderModuleDataList.add(new Shader.ShaderModuleData("resources/scene.frag", GL_FRAGMENT_SHADER));
        shader = new Shader(shaderModuleDataList);
        uniforms = new UniformMap(shader.getProgramId());
        uniforms.createUniform("projection");
    }

    public void cleanup() {
        shader.cleanup();
    }

    public void render(Scene scene) {
        shader.bind();
        uniforms.setUniform("projection", scene.getProjection());

        scene.getMeshMap().values().forEach(mesh -> {
                    mesh.getTexture().bind();
                    glBindVertexArray(mesh.getVaoId());
                    glDrawArrays(GL_TRIANGLES, 0, mesh.getNumVertices());
                }
        );

        glBindVertexArray(0);

        shader.unbind();
    }
}