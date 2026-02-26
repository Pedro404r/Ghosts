package dev.whoami.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.EnumSet;
import java.util.List;

public class GhostEntity extends PathAwareEntity {

    public GhostEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new FlightMoveControl(this, 20, true);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EatMembraneGoal(this));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.2D, false));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));

        this.targetSelector.add(1, new RevengeGoal(this));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    protected EntityNavigation createNavigation(World world) {
        BirdNavigation birdNavigation = new BirdNavigation(this, world);
        birdNavigation.setCanPathThroughDoors(true);
        birdNavigation.setCanSwim(true);
        birdNavigation.setCanEnterOpenDoors(true);
        return birdNavigation;
    }

    @Override
    public void tick() {
        this.noClip = true;
        super.tick();
        this.noClip = false;
        this.setNoGravity(true);

        if (!this.getWorld().isClient) {
            BlockPos pos = this.getBlockPos();
            if (this.getWorld().getBlockState(pos).isFullCube(this.getWorld(), pos)) {
                this.addVelocity(0, 0.08, 0);
            } else if (!this.getWorld().getBlockState(pos.down()).isAir()) {
                if (this.getVelocity().y < 0) {
                    this.setVelocity(this.getVelocity().multiply(1, 0, 1));
                }
            }
        }
    }

    @Override
    public boolean isInvulnerableTo(DamageSource damageSource) {
        return damageSource.isOf(DamageTypes.IN_WALL) || super.isInvulnerableTo(damageSource);
    }

    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }

    public static DefaultAttributeContainer.Builder createGhostAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25)
                .add(EntityAttributes.GENERIC_FLYING_SPEED, 0.6)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64.0);
    }

    static class EatMembraneGoal extends Goal {
        private final GhostEntity ghost;
        private ItemEntity targetItem;

        public EatMembraneGoal(GhostEntity ghost) {
            this.ghost = ghost;
            this.setControls(EnumSet.of(Goal.Control.MOVE));
        }

        @Override
        public boolean canStart() {
            if (this.ghost.getHealth() >= this.ghost.getMaxHealth()) return false;
            List<ItemEntity> items = this.ghost.getWorld().getEntitiesByClass(ItemEntity.class,
                    this.ghost.getBoundingBox().expand(16.0D),
                    item -> item.getStack().isOf(Items.PHANTOM_MEMBRANE));
            if (!items.isEmpty()) {
                this.targetItem = items.get(0);
                return true;
            }
            return false;
        }

        @Override
        public void tick() {
            if (this.targetItem != null && this.targetItem.isAlive()) {
                this.ghost.getNavigation().startMovingTo(this.targetItem, 1.2D);
                if (this.ghost.getBoundingBox().expand(1.0D).intersects(this.targetItem.getBoundingBox())) {
                    this.ghost.heal(5.0F);
                    this.targetItem.getStack().decrement(1);
                    this.stop();
                }
            }
        }

        @Override
        public boolean shouldContinue() {
            return this.targetItem != null && this.targetItem.isAlive() && this.ghost.getHealth() < this.ghost.getMaxHealth();
        }
    }
}