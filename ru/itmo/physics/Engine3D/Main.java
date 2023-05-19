package ru.itmo.physics.Engine3D;

import org.lwjgl.Version;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Main {

    // Window Instance
    private long WND;

    public static void main(String[] args)
    {
        new Main().run();
    }

    public void run()
    {
        // Some logs...
        System.out.println("RUN() method works..."); // It just works...
        System.out.println("LWJGL Version: " + Version.getVersion()); // What's a version?

        // Engine is working...
        this.Initialize();
        this.EngineLoop();

        // Now we are doing a bit of things with window...
        glfwFreeCallbacks(WND); // We don't need callbacks after the end of loop!
        glfwDestroyWindow(WND); // bye-bye...

        // Goodbye forever (or no).
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private void Initialize()
    {
        GLFWErrorCallback.createPrint(System.err).set(); // This cool.

        if (!glfwInit()) {
            throw new IllegalStateException("Cannot init GLFW!");
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // Mister Elastic

        WND = glfwCreateWindow(800, 600, "Java Engine", NULL, NULL);
        if (WND == NULL) {
            throw new IllegalStateException("Cannot create window!");
        }

        glfwSetKeyCallback(WND, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
        });

        // Cause Java is a dumb language we need these tricks to simulate pointers in C-methods
        try (MemoryStack stack = stackPush())
        {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            glfwGetWindowSize(WND, pWidth, pHeight); // Size
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            glfwSetWindowPos(
                    WND,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        }

        // Make openGL context on this window
        glfwMakeContextCurrent(WND);
        // Enable v-sync
        glfwSwapInterval(1);
        // Make the window visible
        glfwShowWindow(WND);
    }

    private void EngineLoop()
    {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

        // Set the clear color
        glClearColor(0.5f, 0.6f, 0.99f, 1.0f);

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while (!glfwWindowShouldClose(WND)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            glfwSwapBuffers(WND); // swap the color buffers

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }
}
