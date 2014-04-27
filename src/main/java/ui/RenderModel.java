package ui;

import org.lwjgl.opengl.GL11;

/**
 * Created by till on 4/24/14.
 */
public class RenderModel extends AbstractRenderModel {

    private int x;
    private float rotation;
    private int y;

    @Override
    protected void renderThis() {
        // set the color of the quad (R,G,B,A)
        GL11.glColor3f(0.5f, 0.5f, 1.0f);
        GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
        GL11.glLoadIdentity(); // Reset The View
        GL11.glTranslatef(0, 0.0f, -6.0f); // Move Right 3 Units
        // draw quad
        GL11.glPushMatrix();
        //GL11.glTranslatef(x, y, 0);
        GL11.glRotatef(rotation, 0f, 0f, 1f);
        //GL11.glTranslatef(-x, -y, 0);
        drawCube();
        //drawSquare2d();

        GL11.glPopMatrix();
    }

    private void drawCube(){
        drawSquare3D(0);
        drawSquare3D(1);
    }

    private void drawSquare3D(float start){
        GL11.glBegin(GL11.GL_QUADS);                      // Draw A Quad
        GL11.glVertex3f(start -1.0f, start + 1.0f, start);              // Top Left
        GL11.glVertex3f(start + 1.0f, start + 1.0f, start);              // Top Right
        GL11.glVertex3f(start +1.0f, start -1.0f, start);              // Bottom Right
        GL11.glVertex3f(start -1.0f, start -1.0f, start);              // Bottom Left
        GL11.glEnd();                            // Done Drawing The Quad
    }

    private void drawSquare2d() {
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(100, 100);
        GL11.glVertex2f(100 + 200, 100);
        GL11.glVertex2f(100 + 200, 100 + 200);
        GL11.glVertex2f(100, 100 + 200);
        GL11.glEnd();
    }

    public void update(final int x, final int y, final float rotation) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }
}
