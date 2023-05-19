package Engine3D.engine;

import Engine3D.engine.graphics.Render;
import Engine3D.engine.scene.Scene;

public interface IMainInterface {

    void cleanup();

    void init(Window window, Scene scene, Render render);

    void input(Window window, Scene scene, long diffTimeMillis);

    void update(Window window, Scene scene, long diffTimeMillis);
}