package com.strangedevs.strangemod.item.pill;

import com.strangedevs.strangemod.registry.ModSoundEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.vector.Vector3d;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class FlyyyyyPill extends AbstractPillItem {
    private static final double MAX_HEIGHT = 250;

    @Override
    public void onEat(PlayerEntity player) {
        TossIntoTheAirTask.toss(player);
    }

    @Override
    public Color getColor() {
        return Color.WHITE;
    }

    private static class TossIntoTheAirTask extends TimerTask {
        private final LivingEntity entity;
        private final double startY;
        private double currentY;

        public TossIntoTheAirTask(@NotNull LivingEntity entity) {
            this.entity = entity;
            this.currentY = entity.position().y;
            this.startY = entity.position().y;

            entity.playSound(ModSoundEvents.FLY_Y_Y_Y_Y_Y.get(), 1.0f, 1.0f);
        }

        public static void toss(LivingEntity entity) {
            TossIntoTheAirTask task = new TossIntoTheAirTask(entity);
            new Timer().schedule(task, 0, 1);
        }

        @Override
        public void run() {
            if (!entity.isAlive()) cancel();

            Vector3d position = entity.position();
            double normalized = normalize(currentY, startY);
            double normalizedFinalY = easeIn(normalized);
            double finalY = denormalize(normalizedFinalY, startY);

            entity.setPos(position.x, finalY, position.z);

            entity.level.addParticle(ParticleTypes.CLOUD,
                    position.x, finalY, position.z,
                    0.0D, 0.0D, 0.0D);

            if ((currentY - 1) >= MAX_HEIGHT) {
                cancel();
            }

            currentY += 0.1;
        }

        private double normalize(double value, double min) {
            return (value - min) / (MAX_HEIGHT - min);
        }

        private double denormalize(double value, double min) {
            return min + (value * (MAX_HEIGHT - min));
        }

        private double easeIn(double value) {
            if (value <= 0.5) return 2.0 * value * value;
            value -= 0.5;
            return 2.0 * value * (1.0 - value) + 0.5f;
        }
    }
}
