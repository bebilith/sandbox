package ui;

import org.lwjgl.opengl.GL11;

/**
 * Created by till on 4/24/14.
 */
public class RenderModel extends AbstractRenderModel {

    private int camX =0;
    private float rotation;
    private int camY =0;
    private int camZ =-6;

    @Override
    protected void renderThis() {
        // set the color of the quad (R,G,B,A)
        GL11.glColor3f(0.5f, 0.5f, 1.0f);
        GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
        GL11.glLoadIdentity(); // Reset The View


        GL11.glPushMatrix();
        moveCube();
        drawCube();

        GL11.glPopMatrix();
    }

    private void moveCube(){
        GL11.glTranslatef(camX, camY, camZ);
        //GL11.glTranslatef(0,0,-6);
        GL11.glRotatef(rotation, 0f, 0f, 1f);
    }

    private void drawCube(){
        for (int i=0; i<4;i++){
            drawCubeSlice(i);
        }
    }

    private void drawCubeSlice(int z) {
        drawSquare3D(0,0,z);
        drawSquare3D(1,0,z);
        drawSquare3D(1,1,z);
        drawSquare3D(0,1,z);
    }


    private void drawSquare3D(float x, float y, float z){
        GL11.glBegin(GL11.GL_QUADS);                      // Draw A Quad
        GL11.glVertex3f(x -1.0f, y + 1.0f, z);              // Top Left
        GL11.glVertex3f(x + 1.0f, y + 1.0f, z);              // Top Right
        GL11.glVertex3f(x +1.0f, y -1.0f, z);              // Bottom Right
        GL11.glVertex3f(x -1.0f, y -1.0f, z);              // Bottom Left
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

    /**
     * Moves entire model by delta units along the X axis
     * @param delta
     */
    public void moveX (int delta){
        this.camX+=delta;
    }

    public void moveZ (int delta){
        this.camZ +=delta;
    }

    public void update(final float rotation) {
        this.rotation = rotation;
    }

    public void moveY(int delta) {
        this.camY += delta;
    }
}
