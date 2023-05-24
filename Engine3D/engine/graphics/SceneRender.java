package Engine3D.engine.graphics;

import Engine3D.engine.UniformMap;
import Engine3D.engine.scene.Scene;
import org.joml.Matrix4f;

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

        uniforms.createUniform("model");
        uniforms.createUniform("view");
    }

    public void cleanup() {
        shader.cleanup();
    }

    public void render(Scene scene) {
        shader.bind();
        uniforms.setUniform("projection", scene.getProjection());
        uniforms.setUniform("view", scene.getCamera().getViewMatrix());

        Matrix4f model = new Matrix4f();
        model.setTranslation(0.0f, 0.0f, -4.0f);
        model.setRotationXYZ((float) Math.toRadians(45.0f), 0.0f, (float) Math.toRadians(45.0f));

        scene.getMeshMap().values().forEach(mesh -> {

            model.setTranslation(mesh.getPosition());
            uniforms.setUniform("model", model);

            mesh.getTexture().bind();
            glBindVertexArray(mesh.getVaoId());
            glDrawArrays(GL_TRIANGLES, 0, mesh.getNumVertices());

        }
        );

        glBindVertexArray(0);

        shader.unbind();
    }
}