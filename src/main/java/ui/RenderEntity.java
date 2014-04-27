package ui;

/**
 * Created by till on 4/24/14.
 */
public interface RenderEntity extends Renderable {

    /**
     * Registers an entity as child in the scene graph
     *
     * @param child
     */
    public void registerChild(RenderEntity child);
}
