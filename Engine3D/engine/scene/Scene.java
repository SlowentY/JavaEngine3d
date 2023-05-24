package Engine3D.engine.scene;

import Engine3D.engine.Camera;
import Engine3D.engine.graphics.Mesh;
import org.joml.Matrix4f;

import java.util.*;

public class Scene {
    private Map<String, Mesh> meshMap;
    private Matrix4f projection;

    private Camera camera;
    private float FOV, Z_NEAR, Z_FAR;

    public Scene(int width, int height) {
        FOV = (float) Math.toRadians(75.0f);
        Z_NEAR = 0.01f;
        Z_FAR = 1000.0f;

        projection = new Matrix4f();
        projection.setPerspective(FOV, (float) width / height, Z_NEAR, Z_FAR);
        meshMap = new HashMap<>();
        camera = new Camera();
    }

    public void addMesh(String meshId, Mesh mesh) {
        meshMap.put(meshId, mesh);
    }

    public void updateProjection(int width, int height) {
        projection.setPerspective(FOV, (float) width / height, Z_NEAR, Z_FAR);
    }

    public Matrix4f getProjection() {
        return projection;
    }

    public void cleanup() {
        meshMap.values().stream().forEach(Mesh::cleanup);
    }

    public Map<String, Mesh> getMeshMap() {
        return meshMap;
    }

    public Camera getCamera() {
        return camera;
    }
}