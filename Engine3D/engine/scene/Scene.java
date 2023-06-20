package Engine3D.engine.scene;

import Engine3D.engine.Camera;
import Engine3D.engine.graphics.Mesh;
import org.joml.Matrix4f;

import java.util.*;

public class Scene {
    private Map<String, Object> objectMap;
    private Matrix4f projection;

    private Camera camera;
    private float FOV, Z_NEAR, Z_FAR;

    public Scene(int width, int height) {
        FOV = (float) Math.toRadians(75.0f);
        Z_NEAR = 0.01f;
        Z_FAR = 1000.0f;

        projection = new Matrix4f();
        projection.setPerspective(FOV, (float) width / height, Z_NEAR, Z_FAR);
        objectMap = new HashMap<>();
        camera = new Camera();
    }

    public void addObject(String objectId, Object object) {
        objectMap.put(objectId, object);
    }

    public void updateProjection(int width, int height) {
        projection.setPerspective(FOV, (float) width / height, Z_NEAR, Z_FAR);
    }

    public Matrix4f getProjection() {
        return projection;
    }

    public void cleanup() {
        objectMap.values().stream().forEach(Object::cleanup);
    }

    public Map<String, Object> getObjectMap() {
        return objectMap;
    }

    public Camera getCamera() {
        return camera;
    }
}