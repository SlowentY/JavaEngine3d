package Engine3D.game;

import Engine3D.engine.*;
import Engine3D.engine.graphics.*;
import Engine3D.engine.scene.Object;
import Engine3D.engine.scene.Scene;
import org.joml.*;

import java.lang.Math;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class Main implements IMainInterface {

    private static final float MOUSE_SENSITIVITY = 0.1f;
    private static final float MOVEMENT_SPEED = 0.005f;
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

        float[] terrainPos = new float[] {
                -lenght, 0.0f, -weight,
                lenght, 0.0f, -weight,
                lenght,  0.0f, weight,
                lenght, 0.0f, weight,
                -lenght, 0.0f, weight,
                -lenght,  0.0f, -weight
        };

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

        float[] terrainTexCoords = new float[] {
                0.0f, 0.0f,
                1.0f, 0.0f,
                1.0f, 1.0f,
                1.0f, 1.0f,
                0.0f, 1.0f,
                -0.0f, 0.0f
        };

        Texture texture = new Texture("resources/gip.jpg");
        Texture grassTex = new Texture("resources/grass.jpg");

        Vector3f v1 = new Vector3f(2.0f, 5.0f, -5.0f);
        Vector3f v2 = new Vector3f(4.0f, -5.0f, -12.0f);
        Vector3f v3 = new Vector3f(-3.0f, 2.0f, -7.0f);

        Mesh mesh1 = new Mesh(positions, textCoords, texture, v1, 36);
        Mesh mesh2 = new Mesh(positions, textCoords, texture, v2, 36);
        Mesh mesh3 = new Mesh(positions, textCoords, texture, v3, 36);

        Mesh terrainMesh = new Mesh(terrainPos, terrainTexCoords, grassTex, new Vector3f(0.0f, 0.0f, 0.0f), 6);
        terrainMesh.setScale(500.0f);

        Object obj1 = new Object(mesh1, 10f, v1, new Vector3f(1.0f, 0.0f, 0.0f));
        Object obj2 = new Object(mesh2, 10f, v2, new Vector3f(0.0f, 0.0f, 0.0f));
        Object obj3 = new Object(mesh3, 10f, v3, new Vector3f(0.0f, 30.0f, -12.5f));

        Object terrain = new Object(terrainMesh, 100f, new Vector3f(0.0f, -10.0f, 0.0f), new Vector3f(0.0f, 0.0f, 0.0f));

        obj1.setAcceleration(new Vector3f(0.0f, -9.8f, 0.0f));
        obj2.setAcceleration(new Vector3f(0.0f, -9.8f, 0.0f));
        obj3.setAcceleration(new Vector3f(0.0f, -9.8f, 0.0f));

        scene.addObject("planks1", obj1);
        scene.addObject("planks2", obj2);
        scene.addObject("planks3", obj3);
        scene.addObject("terrain", terrain);
    }

    @Override
    public void input(Window window, Scene scene, long diffTimeMillis) {
        float move = diffTimeMillis * MOVEMENT_SPEED;
        Camera camera = scene.getCamera();
        if (window.isKeyPressed(GLFW_KEY_W)) {
            camera.moveForward(move);
        } else if (window.isKeyPressed(GLFW_KEY_S)) {
            camera.moveBackwards(move);
        }
        if (window.isKeyPressed(GLFW_KEY_A)) {
            camera.moveLeft(move);
        } else if (window.isKeyPressed(GLFW_KEY_D)) {
            camera.moveRight(move);
        }
        if (window.isKeyPressed(GLFW_KEY_UP)) {
            camera.moveUp(move);
        } else if (window.isKeyPressed(GLFW_KEY_DOWN)) {
            camera.moveDown(move);
        }

        MouseInput mouseInput = window.getMouseInput();
        if (mouseInput.isRightButtonPressed()) {
            Vector2f displVec = mouseInput.getDisplVec();
            camera.addRotation((float) Math.toRadians(-displVec.x * MOUSE_SENSITIVITY),
                    (float) Math.toRadians(-displVec.y * MOUSE_SENSITIVITY));
        }
    }

    @Override
    public void update(Window window, Scene scene, long diffTimeMillis) {
        // Nothing to be done yet
        scene.getObjectMap().values().forEach(object -> {

            object.simulate(diffTimeMillis, new Vector3f(0.0f, 0.0f, 0.0f));

        });
    }
}
