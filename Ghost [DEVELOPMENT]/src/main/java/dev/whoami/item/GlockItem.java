package dev.whoami.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class GlockItem extends Item {
    public GlockItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        // Som de disparo (Pistol-like)
        world.playSound(null, user.getX(), user.getY(), user.getZ(),
                SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 0.8F, 2.0F);

        if (!world.isClient) {
            // Cooldown de 0.25 segundos (5 ticks) para ser rápida
            user.getItemCooldownManager().set(this, 5);

            // Aqui você pode adicionar lógica de dano ou projétil depois
        }

        return TypedActionResult.success(itemStack);
    }
}