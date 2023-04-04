package com.beetw.strangemod.item;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Timer;
import java.util.TimerTask;

public class AwakingStaffItem extends Item {
    private static final Item.Properties PROPERTIES = new Item.Properties()
            .stacksTo(1).durability(1000);

    private static final double MAX_HEIGHT = 250;

    private final Timer scheduleTimer = new Timer();

    public AwakingStaffItem() {
        super(PROPERTIES);
    }

    @NotNull
    @Override
    public InteractionResult interactLivingEntity(@NotNull ItemStack itemStack,
                                                  @NotNull Player player,
                                                  @NotNull LivingEntity livingEntity,
                                                  @NotNull InteractionHand hand) {

        ItemStack itemInHand = player.getItemInHand(hand);
        itemInHand.hurtAndBreak(50, player,
                entity -> entity.broadcastBreakEvent(hand));
        scheduleTimer.schedule(new TossIntoTheAirTask(livingEntity), 0, 1);
        return InteractionResult.PASS;
    }

    private static class TossIntoTheAirTask extends TimerTask {
        private final LivingEntity entity;
        private final Vec3 startPos;
        private double currentY;

        public TossIntoTheAirTask(@NotNull LivingEntity entity) {
            this.entity = entity;
            this.startPos = entity.position();
            this.currentY = this.startPos.y;
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
            return (value - min) / (AwakingStaffItem.MAX_HEIGHT - min);
        }

        private double denormalize(double value, double min) {
            return min + (value * (AwakingStaffItem.MAX_HEIGHT - min));
        }

        private double easeIn(double value) {
            if (value <= 0.5) return 2.0 * value * value;
            value -= 0.5;
            return 2.0 * value * (1.0 - value) + 0.5f;
        }
    }
}