package net.themryeetman.thewrithing.entity.costom.goals;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.phys.Vec3;

import static org.apache.logging.log4j.Level.getLevel;

public class RunAwayAndShootGoal extends Goal {

    private final Mob mob;
    private final double speed;
    private final float safeDistance;
    private final float healthThreshold; // Health percentage threshold
    private LivingEntity target;

    public RunAwayAndShootGoal(Mob mob, double speed, float safeDistance, float healthThreshold) {
        this.mob = mob;
        this.speed = speed;
        this.safeDistance = safeDistance;
        this.healthThreshold = healthThreshold;
    }

    @Override
    public boolean canUse() {
        this.target = this.mob.getTarget();
        if (this.target == null) return false;

        float currentHealth = this.mob.getHealth();
        float maxHealth = this.mob.getMaxHealth();
        boolean isLowHealth = (currentHealth / maxHealth) <= this.healthThreshold;

        double distanceToTarget = this.mob.distanceToSqr(this.target);
        boolean isTooClose = distanceToTarget < this.safeDistance * this.safeDistance;

        return isLowHealth && isTooClose;
    }

    @Override
    public boolean canContinueToUse() {
        float currentHealth = this.mob.getHealth();
        float maxHealth = this.mob.getMaxHealth();
        boolean isLowHealth = (currentHealth / maxHealth) <= this.healthThreshold;

        double distanceToTarget = this.mob.distanceToSqr(this.target);
        boolean isTooClose = distanceToTarget < this.safeDistance * this.safeDistance;

        return isLowHealth && isTooClose;
    }

    @Override
    public void start() {
        // Setup when the goal starts
    }

    @Override
    public void stop() {
        // Cleanup when the goal stops
        this.target = null; // Reset target
    }

    @Override
    public void tick() {
        // Run away logic
        if (this.target != null) {
            // Calculate direction to move away from the target
            double dx = this.mob.getX() - this.target.getX();
            double dz = this.mob.getZ() - this.target.getZ();
            this.mob.getNavigation().moveTo(
                    this.mob.getX() + dx,  // Move in the opposite direction of the target
                    this.mob.getY(),
                    this.mob.getZ() + dz,
                    this.speed
            );

            // Ensure the mob faces the target while running
            //mob.lookAt(target, 30.0F, 30.0F);  // Adjust the facing speed if needed

            // Shoot arrows periodically
            if (this.mob.tickCount % 40 == 0) { // Example: shoot every 2 seconds
                shootArrowAtTarget(this.target);
                mob.lookAt(target, 30.0F, 30.0F);
            }
        }
    }

    private void shootArrowAtTarget(LivingEntity target) {
        // Create a normal arrow
        Arrow arrow = new Arrow(mob.getCommandSenderWorld(), mob);  // Get the world using getCommandSenderWorld()

        double dx = target.getX() - mob.getX();
        double dy = target.getEyeY() - mob.getEyeY();
        double dz = target.getZ() - mob.getZ();

        // Normalize and scale the direction vector
        Vec3 direction = new Vec3(dx, dy, dz).normalize().scale(1.6D);

        // Set the arrow's velocity
        arrow.setDeltaMovement(direction);

        // Add the arrow to the world
        mob.getCommandSenderWorld().addFreshEntity(arrow);
    }
}
