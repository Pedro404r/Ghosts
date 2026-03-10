package dev.whoami.block;

import dev.whoami.Ghosts;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class GhostBlocks {

    public static final Block GUN_BENCH = register(
            new Block(AbstractBlock.Settings.create()
                    .sounds(BlockSoundGroup.ANVIL)
                    .nonOpaque()),
            "gun_bench",
            true
    );


    public static void initialize() {

        ItemGroupEvents.modifyEntriesEvent(net.minecraft.item.ItemGroups.FUNCTIONAL).register(entries -> {
            entries.add(GUN_BENCH);
        });
    }

    private static Block register(Block block, String name, boolean shouldRegisterItem) {
        Identifier id = Identifier.of(Ghosts.MOD_ID, name);

        if (shouldRegisterItem) {
            BlockItem blockItem = new BlockItem(block, new Item.Settings());
            Registry.register(Registries.ITEM, id, blockItem);
        }

        return Registry.register(Registries.BLOCK, id, block);
    }
}