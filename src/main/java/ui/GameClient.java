package ui;

import helper.SystemUtilHelper;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

/**
 * Created by till on 4/24/14.
 */
public class GameClient {

    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(GameClient.class);

    private long fps, lastfps;

    private int width =800,height = 600;

    private int moveX =0, moveY =0, moveZ=0;

    private float rotation = 0;

    private RenderModel renderModel;

    private void init() {
        try {
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.setResizable(true);
            Display.setTitle("Test");
            Display.create();
            // init OpenGL here
            GL11.glMatrixMode(GL11.GL_PROJECTION);
            GL11.glLoadIdentity();
            GLU.gluPerspective(45f, width / height, 0.1f, 100.f);
            //GL11.glOrtho(0, 800, 0, 600, 1, -1);
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glLoadIdentity();
            GL11.glShadeModel(GL11.GL_SMOOTH); // Enables Smooth Shading
            GL11.glClearDepth(1.0f); // Depth Buffer Setup
            GL11.glEnable(GL11.GL_DEPTH_TEST); // Enables Depth Testing
            GL11.glDepthFunc(GL11.GL_LEQUAL); // The Type Of Depth Test To Do
            GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST); // Really Nice Perspective Calculations

            renderModel = new RenderModel();
        } catch (LWJGLException e) {
            logger.error("Failed to init OpenGL! Aborting...", e);
            System.exit(0);
        }
    }

    public void start() {

        init();

        long lastframe;
        lastfps = SystemUtilHelper.getTime();

        while (!Display.isCloseRequested()) {
            lastframe = SystemUtilHelper.getTime();
            
            if (Display.wasResized()){
                resize();
            }
            renderWorld();


            long delta = getDelta(lastframe);
            update(delta);
        }

        Display.destroy();
    }

    private void resize() {
        this.width = Display.getWidth();
        this.height = Display.getHeight();
        GL11.glViewport(0,0,width,height);
    }

    private void renderWorld() {
        // Clear the screen and depth buffer
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        renderModel.render();
        Display.update();
        Display.sync(60);
    }

    private void testRender(){

        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); // Clear The Screen And The Depth Buffer
        GL11.glLoadIdentity(); // Reset The View

        GL11.glTranslatef(-1.5f, 0.0f, -6.0f); // Move Left 1.5 Units And Into The Screen 6.0

        GL11.glBegin(GL11.GL_TRIANGLES); // Drawing Using Triangles
        GL11.glVertex3f(0.0f, 1.0f, 0.0f); // Top
        GL11.glVertex3f(-1.0f, -1.0f, 0.0f); // Bottom Left
        GL11.glVertex3f(1.0f, -1.0f, 0.0f); // Bottom Right
        GL11.glEnd(); // Finished Drawing The Triangle

        GL11.glTranslatef(3.0f, 0.0f, 0.0f); // Move Right 3 Units

        GL11.glBegin(GL11.GL_QUADS); // Draw A Quad
        GL11.glVertex3f(-1.0f, 1.0f, 0.0f); // Top Left
        GL11.glVertex3f(1.0f, 1.0f, 0.0f); // Top Right
        GL11.glVertex3f(1.0f, -1.0f, 0.0f); // Bottom Right
        GL11.glVertex3f(-1.0f, -1.0f, 0.0f); // Bottom Left
        GL11.glEnd(); // Done Drawing The Quad
    }

    private void update(long delta) {
        renderModel.update(rotation);
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
            renderModel.moveZ(1);
        }if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
            renderModel.moveZ(-1);
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
