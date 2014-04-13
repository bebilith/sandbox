/**
 * Created by till on 3/23/14.
 */
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
        import org.lwjgl.opengl.DisplayMode;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {

    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Main.class);
    public void start() {
        try {
            Display.setDisplayMode(new DisplayMode(800,600));
            Display.setResizable(true);
            Display.setTitle("Test");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        // init OpenGL here

        while (!Display.isCloseRequested()) {

            // render OpenGL here
            pollInput();

            Display.update();
        }

        Display.destroy();
    }

    private void pollInput() {
        pollMouse();
        pollKeyboard();
    }

    private void pollKeyboard() {
        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
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

    private void pollMouse(){
        if (Mouse.isButtonDown(0)){
            int x = Mouse.getX();
            int y = Mouse.getY();
            String entry = String.format("Mouse (%s/%s)",x,y);
            logger.debug(entry);
        }        
    }


    public static void main(String[] argv) {
        Main displayExample = new Main();
        displayExample.start();
    }
}

