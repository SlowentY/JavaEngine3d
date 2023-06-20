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

        scene.getObjectMap().values().forEach(object -> {
            Matrix4f model = new Matrix4f();
            model.setTranslation(object.getMesh().getPosition());
            model.mul(new Matrix4f().scaling(object.getMesh().getScale()));
            uniforms.setUniform("model", model);

            object.getMesh().getTexture().bind();
            glBindVertexArray(object.getMesh().getVaoId());
            glDrawArrays(GL_TRIANGLES, 0, object.getMesh().getNumVertices());

        }
        );

        glBindVertexArray(0);

        shader.unbind();
    }
}