package dev.whoami;

import dev.whoami.client.model.GhostEntityModel;
import dev.whoami.client.renderer.GhostEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class GhostsClient implements ClientModInitializer {
    public static final EntityModelLayer MODEL_CUBE_LAYER = new EntityModelLayer(Identifier.of("ghosts", "ghost"), "main");
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(Ghosts.GHOST, (context) -> {
            return new GhostEntityRenderer(context);
        });

        EntityModelLayerRegistry.registerModelLayer(MODEL_CUBE_LAYER, GhostEntityModel::getTexturedModelData);
    }
}
