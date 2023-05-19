package Engine3D.engine.scene;

import Engine3D.engine.graphics.Mesh;

import java.util.*;

public class Scene {

    private Map<String, Mesh> meshMap;

    public Scene() {
        meshMap = new HashMap<>();
    }

    public void addMesh(String meshId, Mesh mesh) {
        meshMap.put(meshId, mesh);
    }

    public void cleanup() {
        meshMap.values().stream().forEach(Mesh::cleanup);
    }

    public Map<String, Mesh> getMeshMap() {
        return meshMap;
    }
}
