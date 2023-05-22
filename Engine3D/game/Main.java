package Engine3D.game;

import Engine3D.engine.*;
import Engine3D.engine.graphics.*;
import Engine3D.engine.scene.Scene;

public class Main implements IMainInterface {

    public static void main(String[] args) {
        Main main = new Main();
        Engine gameEng = new Engine("Engine3D", new Window.WindowOptions(), main);
        gameEng.start();
    }

    @Override
    public void cleanup() {
        // Nothing to be done yet
    }

    @Override
    public void init(Window window, Scene scene, Render render) {

        float lenght, height, weight;
        lenght = 1.0f;
        height = 1.0f;
        weight = 1.0f;

        float[] positions = new float[] {
                -lenght, -height, -weight,
                lenght, -height, -weight,
                lenght,  height, -weight,
                lenght, height, -weight,
                -lenght, height, -weight,
                -lenght,  -height, -weight,

                -lenght, -height, weight,
                lenght, -height, weight,
                lenght,  height, weight,
                lenght, height, weight,
                -lenght, height, weight,
                -lenght,  -height, weight,

                -lenght, height, weight,
                -lenght, height, -weight,
                -lenght,  -height, -weight,
                -lenght, -height, -weight,
                -lenght, -height, weight,
                -lenght,  height, weight,

                lenght, height, weight,
                lenght, height, -weight,
                lenght,  -height, -weight,
                lenght, -height, -weight,
                lenght, -height, weight,
                lenght,  height, weight,

                -lenght, -height, -weight,
                lenght, -height, -weight,
                lenght,  -height, weight,
                lenght, -height, weight,
                -lenght, -height, weight,
                -lenght,  -height, -weight,

                -lenght, height, -weight,
                lenght, height, -weight,
                lenght,  height, weight,
                lenght, height, weight,
                -lenght, height, weight,
                -lenght,  height, -weight};

        float[] textCoords = new float[] {
                0.0f, 0.0f,
                1.0f, 0.0f,
                1.0f, 1.0f,
                1.0f, 1.0f,
                0.0f, 1.0f,
                -0.0f, 0.0f,

                0.0f, 0.0f,
                1.0f, 0.0f,
                1.0f, 1.0f,
                1.0f, 1.0f,
                0.0f, 1.0f,
                -0.0f, 0.0f,

                0.0f, 0.0f,
                1.0f, 0.0f,
                1.0f, 1.0f,
                1.0f, 1.0f,
                0.0f, 1.0f,
                -0.0f, 0.0f,

                0.0f, 0.0f,
                1.0f, 0.0f,
                1.0f, 1.0f,
                1.0f, 1.0f,
                0.0f, 1.0f,
                -0.0f, 0.0f,

                0.0f, 0.0f,
                1.0f, 0.0f,
                1.0f, 1.0f,
                1.0f, 1.0f,
                0.0f, 1.0f,
                -0.0f, 0.0f,

                0.0f, 0.0f,
                1.0f, 0.0f,
                1.0f, 1.0f,
                1.0f, 1.0f,
                0.0f, 1.0f,
                -0.0f, 0.0f
        };

        Texture texture = new Texture("resources/gip.jpg");
        Mesh mesh = new Mesh(positions, textCoords, texture, 36);
        scene.addMesh("planks", mesh);
    }

    @Override
    public void input(Window window, Scene scene, long diffTimeMillis) {
        // Nothing to be done yet
    }

    @Override
    public void update(Window window, Scene scene, long diffTimeMillis) {
        // Nothing to be done yet
    }
}
