package com.github.strangemod.effect;

import com.github.strangemod.registry.ModSoundEvents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Timer;
import java.util.TimerTask;

public class FlyyyyyEffect extends InstantenousMobEffect {
    private static final double MAX_HEIGHT = 250;

    public FlyyyyyEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyInstantenousEffect(@Nullable Entity source,
                                        @Nullable Entity indirectSource,
                                        @NotNull LivingEntity target,
                                        int amplifier,
                                        double effectiveness) {

        new Timer().schedule(new TossIntoTheAirTask(target), 0, 1);
    }

    private static class TossIntoTheAirTask extends TimerTask {
        private final LivingEntity entity;
        private final Vec3 startPos;
        private double currentY;

        public TossIntoTheAirTask(@NotNull LivingEntity entity) {
            this.entity = entity;
            this.startPos = entity.position();
            this.currentY = this.startPos.y;

            entity.playSound(ModSoundEvents.FLY_Y_Y_Y_Y_Y.get());
        }

        @Override
        public void run() {
            if (!entity.isAlive()) cancel();

            double normalized = normalize(currentY, startPos.y);
            double normalizedFinalY = easeIn(normalized);
            double finalY = denormalize(normalizedFinalY, startPos.y);

            entity.setPos(startPos.x, finalY, startPos.z);

            entity.level.addParticle(ParticleTypes.CLOUD,
                    startPos.x, finalY, startPos.z, 0.0D, 0.0D, 0.0D);

            if ((currentY - 1) >= MAX_HEIGHT) {
                entity.kill();
                cancel();
            }

            currentY += 0.1;
        }

        private double normalize(double value, double min) {
            return (value - min) / (FlyyyyyEffect.MAX_HEIGHT - min);
        }

        private double denormalize(double value, double min) {
            return min + (value * (FlyyyyyEffect.MAX_HEIGHT - min));
        }

        private double easeIn(double value) {
            if (value <= 0.5) return 2.0 * value * value;
            value -= 0.5;
            return 2.0 * value * (1.0 - value) + 0.5f;
        }
    }
}
