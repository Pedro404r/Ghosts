package dev.whoami;

import dev.whoami.block.GhostBlocks;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Ghosts implements ModInitializer { public static void registerGhostBlocks() {
    }
    public static final String MOD_ID = "ghosts";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    @Override
    public void onInitialize() {
        GhostBlocks.initialize();
    }

}
