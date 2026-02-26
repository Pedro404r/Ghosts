package dev.whoami.item;

import dev.whoami.Ghosts;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class GhostItems {

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
                .register((itemGroup) -> {
                    itemGroup.add(GhostItems.DEMON_TOTEM);
                    itemGroup.add(GhostItems.GLOCK_17);
                });
    }
    public static Item register(Item item, String id){
        Identifier itemID = Identifier.of(Ghosts.MOD_ID, id);

        Item registeredItem = Registry.register(Registries.ITEM, itemID, item);

        return registeredItem;
    }

    public static final Item DEMON_TOTEM = register(
            new Item(new Item.Settings()),
            "demon_totem"
    );

    public static final Item GLOCK_17 = register(
            new GlockItem(new Item.Settings().maxCount(1)),
            "glock_17"
    );
}
