package dev.whoami.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class GhostBlocks {
    public static final Block GUN_BENCH = registerBlock("gun_bench",
            new Block(AbstractBlock.Settings.create()
                    .strength(3.0f)
                    .requiresTool()
                    .nonOpaque()
            ));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of("ecto_arsenal", name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of("ghosts", name),
                new BlockItem(block, new Item.Settings()));
    }


}