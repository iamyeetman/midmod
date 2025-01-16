package net.themryeetman.thewrithing.entity.costom.goals;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;

public class LeapingAttackGoal extends Goal {
    private final Mob mob;
    private LivingEntity target;
    private final double leapSpeed;
    private final double triggerDistance; // Distance to trigger the leap
    private final double damageRadius; // Radius for dealing damage after leap
    private final float damageAmount;
    private final int waitTicks; // Delay in ticks before starting Y-level checks
    private int delayTimer; // Timer to handle the one-time delay
    private boolean hasLanded;

    public LeapingAttackGoal(Mob mob, double leapSpeed, double triggerDistance, double damageRadius, float damageAmount, int waitTicks) {
        this.mob = mob;
        this.leapSpeed = leapSpeed;
        this.triggerDistance = triggerDistance;
        this.damageRadius = damageRadius;
        this.damageAmount = damageAmount;
        this.waitTicks = waitTicks;
        this.delayTimer = 0;
    }

    @Override
    public boolean canUse() {
        // Check if the mob has a target
        this.target = this.mob.getTarget();
        if (this.target == null) return false;

        // Check if the target is farther than the trigger distance
        double distanceToTarget = this.mob.distanceToSqr(this.target);
        return distanceToTarget > this.triggerDistance * this.triggerDistance;
    }

    @Override
    public boolean canContinueToUse() {
        // Continue the leap until it has landed
        return !this.hasLanded;
    }

    @Override
    public void start() {
        if (this.target == null) return;

        // Calculate direction to leap toward the target
        double dx = this.target.getX() - this.mob.getX();
        double dz = this.target.getZ() - this.mob.getZ();

        // Normalize the direction and add vertical motion for the leap
        double distance = Math.sqrt(dx * dx + dz * dz);
        if (distance == 0) return; // Avoid division by zero
        dx /= distance;
        dz /= distance;

        // Set the mob's motion to leap toward the target
        this.mob.setDeltaMovement(dx * this.leapSpeed, 0.5, dz * this.leapSpeed);

        // Play leap animation or sound if desired (optional)
        this.mob.level().playSound(null, mob.blockPosition(), SoundEvents.GENERIC_EXPLODE, SoundSource.HOSTILE, 1.0F, 1.0F);

        this.hasLanded = false; // Reset the landed flag
        this.delayTimer = this.waitTicks; // Initialize the delay timer
    }

    @Override
    public void tick() {
        if (this.delayTimer > 0) {
            // Decrement the delay timer
            this.delayTimer--;
            return; // Skip checking until the delay is over
        }

        // Check if the mob's Y-level is at or below the target's Y-level
        if (this.target != null && this.mob.getY() <= this.target.getY()) {
            this.hasLanded = true;

            // Deal damage if the mob is near the target
            double distanceToTarget = this.mob.distanceToSqr(this.target);
            if (distanceToTarget < this.damageRadius * this.damageRadius) {
                this.target.hurt(this.mob.damageSources().mobAttack(this.mob), this.damageAmount);
            }
        }
    }
}
