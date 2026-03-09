package net.seu_nome.ghosts.block; // Ajuste o package para o seu projeto

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.state.StateManager;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.block.BlockState;
import net.minecraft.state.property.Properties;

public class GhostBlocks {

    // 1. Definindo a Gun Bench com rotação
    public static final Block GUN_BENCH = registerBlock("gun_bench", 
        new HorizontalFacingBlock(AbstractBlock.Settings.create()
            .strength(3.5f) // Resistência (igual ao ferro)
            .requiresTool() // Precisa de ferramenta para quebrar
            .nonOpaque()    // IMPORTANTE: evita sombras pretas no modelo do Blockbench
        )

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of("ghosts", name), block);
    }


    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of("ghosts", name), 
            new BlockItem(block, new Item.Settings()));
    }


}
