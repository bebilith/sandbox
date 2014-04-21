/**
 * Created by till on 3/23/14.
 */

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;


public class Main {

    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Main.class);

    private long fps, lastfps;

    public void start() {
        try {
            Display.setDisplayMode(new DisplayMode(800, 600));
            Display.setResizable(true);
            Display.setTitle("Test");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        // init OpenGL here
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, 800, 0, 600, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);


        long lastframe = getTime();

        while (!Display.isCloseRequested()) {
            // Clear the screen and depth buffer
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            // set the color of the quad (R,G,B,A)
            GL11.glColor3f(0.5f, 0.5f, 1.0f);

            // draw quad
            GL11.glBegin(GL11.GL_QUADS);
            GL11.glVertex2f(100, 100);
            GL11.glVertex2f(100 + 200, 100);
            GL11.glVertex2f(100 + 200, 100 + 200);
            GL11.glVertex2f(100, 100 + 200);
            GL11.glEnd();

            // render OpenGL here
            pollInput();

            Display.update();
            long delta = getDelta(lastframe);
            updateFps();
        }

        Display.destroy();
    }

    private void pollInput() {
        pollMouse();
        pollKeyboard();
    }

    private void pollKeyboard() {
        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            logger.debug("Space pressed");
        }
        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                if (Keyboard.getEventKey() == Keyboard.KEY_A) {
                    logger.debug("A Key Pressed");
                }
                if (Keyboard.getEventKey() == Keyboard.KEY_S) {
                    logger.debug("S Key Pressed");
                }
                if (Keyboard.getEventKey() == Keyboard.KEY_D) {
                    logger.debug("D Key Pressed");
                }
            } else {
                if (Keyboard.getEventKey() == Keyboard.KEY_A) {
                    logger.debug("A Key Released");
                }
                if (Keyboard.getEventKey() == Keyboard.KEY_S) {
                    logger.debug("S Key Released");
                }
                if (Keyboard.getEventKey() == Keyboard.KEY_D) {
                    logger.debug("D Key Released");
                }
            }
        }
    }

    private void pollMouse() {
        if (Mouse.isButtonDown(0)) {
            int x = Mouse.getX();
            int y = Mouse.getY();
            String entry = String.format("Mouse (%s/%s)", x, y);
            logger.debug(entry);
        }
    }


    public static void main(String[] argv) {
        Main displayExample = new Main();
        displayExample.start();
    }

    /**
     * Get the time in milliseconds
     *
     * @return The system time in milliseconds
     */
    public long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

    public int getDelta(long lastFrame) {
        long time = getTime();
        int delta = (int) (time - lastFrame);

        return delta;
    }

    private void updateFps() {
        if (getTime() - lastfps > 1000) {
            Display.setTitle("FPS: " + fps);
            fps = 0;
            lastfps += 1000;
        }
    }
}

