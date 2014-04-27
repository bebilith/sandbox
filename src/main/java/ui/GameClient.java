package ui;

import helper.SystemUtilHelper;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

/**
 * Created by till on 4/24/14.
 */
public class GameClient {

    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(GameClient.class);

    private long fps, lastfps;

    private int x = 400, y = 300;

    private float rotation = 0;

    private RenderModel renderModel;

    private void init() {
        try {
            Display.setDisplayMode(new DisplayMode(800, 600));
            Display.setResizable(true);
            Display.setTitle("Test");
            Display.create();
            // init OpenGL here
            GL11.glMatrixMode(GL11.GL_PROJECTION);
            GL11.glLoadIdentity();
            GL11.glOrtho(0, 800, 0, 600, 1, -1);
            GL11.glMatrixMode(GL11.GL_MODELVIEW);

            renderModel = new RenderModel();
        } catch (LWJGLException e) {
            logger.error("Failed to init OpenGL! Aborting...", e);
            System.exit(0);
        }
    }

    public void start() {

        init();

        long lastframe = SystemUtilHelper.getTime();
        lastfps = SystemUtilHelper.getTime();

        while (!Display.isCloseRequested()) {
            lastframe = SystemUtilHelper.getTime();
            renderWorld();


            long delta = getDelta(lastframe);
            update(delta);
        }

        Display.destroy();
    }

    private void renderWorld() {
        // Clear the screen and depth buffer
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        renderModel.render();
        Display.update();
    }

    private void update(long delta) {
        renderModel.update(x, y, rotation);
        pollInput(delta);
        updateFps();
    }

    private void pollInput(long delta) {
        pollMouse();
        pollKeyboard(delta);
    }

    private void pollMouse() {
        if (Mouse.isButtonDown(0)) {
            int x = Mouse.getX();
            int y = Mouse.getY();
            String entry = String.format("Mouse (%s/%s)", x, y);
            logger.debug(entry);
        }
    }

    private void pollKeyboard(long delta) {
        rotation += 0.15f * delta;

        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            logger.debug("Space pressed");
        } else if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            y -= 0.35f * delta;
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

    public int getDelta(long lastFrame) {
        long time = SystemUtilHelper.getTime();
        int delta = (int) (time - lastFrame);

        return delta;
    }

    private void updateFps() {
        if (SystemUtilHelper.getTime() - lastfps > 1000) {
            Display.setTitle("FPS: " + fps);
            fps = 0;
            lastfps += 1000;
        }
        fps++;
    }


}
