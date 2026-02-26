package dev.whoami;

import dev.whoami.entity.GhostEntity;
import dev.whoami.item.GhostItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ghosts implements ModInitializer {

    public static final String MOD_ID = "ghosts";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final EntityType<GhostEntity> GHOST = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(MOD_ID, "ghost"),
            EntityType.Builder.create(GhostEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.75f, 0.75f)
                    .build("ghost")
    );

    public static final Item GHOST_SPAWN_EGG = Registry.register(
            Registries.ITEM,
            Identifier.of(MOD_ID, "ghost_spawn_egg"),
            new SpawnEggItem(GHOST, 0x123456, 0x789abc, new Item.Settings())
    );

    @Override
    public void onInitialize() {
        GhostItems.initialize();
        LOGGER.info("Inicializando > [GhostItems]");
        FabricDefaultAttributeRegistry.register(GHOST, GhostEntity.createGhostAttributes());
        
        LOGGER.info("GhostEntity e Spawn Egg inicializados!");

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(content -> {
            content.add(GHOST_SPAWN_EGG);
        });
    }
}
