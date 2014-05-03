package ui;

import org.lwjgl.opengl.GL11;

/**
 * Created by till on 4/24/14.
 */
public class RenderModel extends AbstractRenderModel {

    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(RenderModel.class);
    public static final int chunkSize = 3;
    private int camX = 0;
    private float rotation;
    private int camY = 0;
    private int camZ = -6;

    private int[][][] world = new int[chunkSize][chunkSize][chunkSize];

    private float length = 1f, height = 1f, width = 1f;

    @Override
    protected void renderThis() {
        // set the color of the quad (R,G,B,A)
        GL11.glColor3f(0.5f, 0.5f, 1.0f);
        GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE); //Wireframe
        GL11.glLoadIdentity(); // Reset The View


        GL11.glPushMatrix();

        moveCube();
        for (int x = 0; x < chunkSize; x++) {
            for (int y = 0; y < chunkSize; y++) {
                for (int z = 0; z < chunkSize; z++) {
//                    GL11.glTranslatef(x, y, z);
//                    logger.debug(String.format("Cube at (%s/%s/%s)", x, y, z));
                    drawCube(x,y,z);
                }
            }
        }

        GL11.glPopMatrix();
    }

    private void moveCube() {
        GL11.glTranslatef(camX, camY, camZ);
//        GL11.glRotatef(rotation, 0f, 0f, 1f);
    }

    private void drawCube(float x,float y,float z) {

        GL11.glBegin(GL11.GL_QUADS);

        GL11.glVertex3f(length +x, -height +y, -width +z);
        GL11.glVertex3f(-length +x, -height +y, -width +z);
        GL11.glVertex3f(-length +x, height +y, -width +z);
        GL11.glVertex3f(length +x, height +y, -width +z);

        GL11.glVertex3f(-length +x, -height +y, width +z);
        GL11.glVertex3f(length +x, -height +y, width +z);
        GL11.glVertex3f(length +x, height +y, width +z);
        GL11.glVertex3f(-length +x, height +y, width +z);

        GL11.glVertex3f(length +x, -height +y, width +z);
        GL11.glVertex3f(length +x, -height +y, -width +z);
        GL11.glVertex3f(length +x, height +y, -width +z);
        GL11.glVertex3f(length +x, height +y, width +z);

        GL11.glVertex3f(-length +x, -height +y, -width +z);
        GL11.glVertex3f(-length +x, -height +y, width +z);
        GL11.glVertex3f(-length +x, height +y, width +z);
        GL11.glVertex3f(-length +x, height +y, -width +z);

        GL11.glVertex3f(-length +x, -height, -width +z);
        GL11.glVertex3f(length +x, -height, -width +z);
        GL11.glVertex3f(length +x, -height, width +z);
        GL11.glVertex3f(-length +x, -height, width +z);

        GL11.glVertex3f(length +x, height +y, -width +z);
        GL11.glVertex3f(-length +x, height +y, -width +z);
        GL11.glVertex3f(-length +x, height +y, width +z);
        GL11.glVertex3f(length +x, height +y, width +z);

        GL11.glEnd();
    }


    /**
     * Moves entire model by delta units along the X axis
     *
     * @param delta the amount by which to move
     */
    public void moveX(int delta) {
        this.camX += delta;
    }

    public void moveZ(int delta) {
        this.camZ += delta;
    }

    public void update(final float rotation) {
        this.rotation = rotation;
    }

    public void moveY(int delta) {
        this.camY += delta;
    }
}
