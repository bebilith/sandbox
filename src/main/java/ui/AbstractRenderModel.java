package ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by till on 4/24/14.
 */
public abstract class AbstractRenderModel implements RenderEntity {

    List<RenderEntity> children = new ArrayList<RenderEntity>();

    @Override
    public void render() {

        renderThis();
        for (RenderEntity child : children) {
            child.render();
        }
    }

    protected abstract void renderThis();

    @Override
    public void registerChild(RenderEntity child) {
        children.add(child);
    }
}
