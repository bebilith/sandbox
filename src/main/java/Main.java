/**
 * Created by till on 3/23/14.
 */
import org.lwjgl.LWJGLException;
        import org.lwjgl.opengl.Display;
        import org.lwjgl.opengl.DisplayMode;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Main {
    public void start() {
        try {
            Display.setDisplayMode(new DisplayMode(800,600));
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        // init OpenGL here

        while (!Display.isCloseRequested()) {

            // render OpenGL here

            Display.update();
        }

        Display.destroy();
    }

    public static void main(String[] argv) {
        Main displayExample = new Main();
        displayExample.start();
    }
}
