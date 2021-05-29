/*
 * Copyright (C) 2021 tiagoluz
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package luz.tiago.fcgt2;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

/**
 *
 * @author tiagoluz
 */
public class Main {

    public final static int WIDTH = 800;
    public final static int HEIGHT = 600;

    // ELEMENTOS
    Canhao canhao;
    List<Predio> predios;
    List<Aviao> avioes;
    List<Tiro> tirosAvioes;
    List<Tiro> tirosCanhao;

    public static Sound sound;

    public static void main(String[] args) {
        System.out.println("PUCRS - Fundamentos de Computação Gráfica - Tiago Luz - 2021/01");

        sound = new Sound();
        sound.rock();
        Main main = new Main();
        main.run();

    }

    public Main() {
        this.canhao = new Canhao();
        this.predios = new ArrayList<>();
        this.avioes = new ArrayList<>();
        this.tirosAvioes = new ArrayList<>();
        this.tirosCanhao = new ArrayList<>();

        createPredios();

        Thread t = new Thread(() -> {
            do {
                createAviao();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (true);
        });
        t.start();

    }

    int lastAviaoCreated = 0;

    void createAviao() {
        
        long count = this.avioes.parallelStream().filter((d)->d.remover == false).count();
        
        if(count >= 10) {
            return;
        }
        
        lastAviaoCreated++;
        Aviao a = new Aviao();
        a.tipo = lastAviaoCreated % 3;
        this.avioes.add(a);
    }

    // The window handle
    private long window;

    void createPredios() {
        int qtd = 10;
        for (int i = 0; i < qtd; i++) {
            Predio p = new Predio();
            p.y = 0;
            p.x = WIDTH / qtd * i;
            p.tipo = i % 3;
            predios.add(p);
        }
    }

    public void run() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");

        init();
        loop();

        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private void init() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

        // Create the window
        window = glfwCreateWindow(WIDTH, HEIGHT, "Tiago Luz - CG T2", NULL, NULL);
        if (window == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
                if (this.sound != null) {
                    this.sound.rockStop();
                }
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
                System.exit(0);
            } else if (key == GLFW_KEY_LEFT) {
                leftTyped();
            } else if (key == GLFW_KEY_RIGHT) {
                rightTyped();
            } else if (key == GLFW_KEY_UP && action == GLFW_RELEASE) {
                upTyped();
            } else if (key == GLFW_KEY_DOWN && action == GLFW_RELEASE) {
                downTyped();
            } else if (key == GLFW_KEY_Q) {
                qTyped();
            } else if (key == GLFW_KEY_E) {
                eTyped();
            } else if (key == GLFW_KEY_D && action == GLFW_RELEASE) {
                debug();
            } else if (key == GLFW_KEY_SPACE && action == GLFW_RELEASE) {
                spaceTyped();
            }
        });

        // Get the thread stack and push a new frame
        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        } // the stack frame is popped automatically

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);

        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);

    }

    void debug() {
        for (Aviao a : avioes) {
            System.out.println(a);
        }
    }

    private void display() {

        glClear(GL_COLOR_BUFFER_BIT);

        glMatrixMode(GL_MODELVIEW);

        glLoadIdentity();

        // Desenhar
        canhao.draw();

        for (Predio p : predios) {
            p.draw();
        }

        for (Aviao a : avioes) {
            a.draw();
        }

        for (Tiro t : tirosAvioes) {
            t.draw();
        }

        for (Tiro t : tirosCanhao) {
            t.draw();
        }

        // fim desenhar
        glFlush();
    }

    private void loop() {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

        // Set the clear color
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        glMatrixMode(GL11.GL_PROJECTION);
        glOrtho(0, WIDTH, 0, HEIGHT, 0, 1);
        glMatrixMode(GL11.GL_MODELVIEW);

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            display();

            glfwSwapBuffers(window); // swap the color buffers

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();

        }
    }

    private void spaceTyped() {
        Tiro t = new Tiro(this.canhao);
        this.tirosCanhao.add(t);
    }

    private void downTyped() {
        if (this.canhao.forca > 1) {
            this.canhao.forca -= 1;
        }
    }

    private void upTyped() {
        if (this.canhao.forca < 3) {
            this.canhao.forca += 1;
        }
    }

    private void rightTyped() {
        if (this.canhao.x < (Main.WIDTH - 100)) {
            this.canhao.x += 20;
        }

    }

    private void leftTyped() {
        if (this.canhao.x > 0) {
            this.canhao.x -= 20;
        }

    }

    private void qTyped() {
        if (this.canhao.angulo < 80) {
            this.canhao.angulo += 10;
        }
    }

    private void eTyped() {
        if (this.canhao.angulo > -80) {
            this.canhao.angulo -= 10;
        }
    }
}
