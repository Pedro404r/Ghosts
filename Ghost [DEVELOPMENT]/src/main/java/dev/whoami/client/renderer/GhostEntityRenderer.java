package dev.whoami.client.renderer;

import dev.whoami.GhostsClient;
import dev.whoami.client.model.GhostEntityModel;
import dev.whoami.entity.GhostEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class GhostEntityRenderer extends MobEntityRenderer<GhostEntity, GhostEntityModel> {

    public GhostEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new GhostEntityModel(context.getPart(GhostsClient.MODEL_CUBE_LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(GhostEntity entity) {
        return Identifier.of("ghosts", "textures/entity/ghost/ghost.png");
    }
}
