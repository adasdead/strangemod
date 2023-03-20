package com.beetw.examplemod.item;

import com.beetw.examplemod.init.ModGroups;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.item.ToolItem;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.common.ForgeHooks;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;

public class AwakingStaffItem extends ToolItem {
    private static final Item.Properties PROPERTIES = new Item.Properties()
            .stacksTo(1)
            .durability(100)
            .tab(ModGroups.EXAMPLE_MOD);

    private static final double MAX_HEIGHT = 250;

    private final Timer scheduleTimer = new Timer();

    public AwakingStaffItem() {
        super(2, 3.0f, ItemTier.IRON, new HashSet<>(), PROPERTIES);
    }

    @Override
    public @NotNull ActionResultType interactLivingEntity(@NotNull ItemStack itemStack,
                                                          @NotNull PlayerEntity playerEntity,
                                                          @NotNull LivingEntity livingEntity,
                                                          @NotNull Hand hand) {

        ItemStack itemInHand = playerEntity.getItemInHand(hand);
        itemInHand.hurtAndBreak(20, playerEntity, entity -> entity.broadcastBreakEvent(hand));
        scheduleTimer.schedule(new TossIntoTheAirTask(livingEntity), 0, 1);
        return ActionResultType.PASS;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        return ForgeHooks.getContainerItem(itemStack);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    private static class TossIntoTheAirTask extends TimerTask {
        private final LivingEntity livingEntity;
        private final Vector3d startPos;
        private double currentY;

        public TossIntoTheAirTask(@NotNull LivingEntity livingEntity) {
            this.livingEntity = livingEntity;
            this.startPos = livingEntity.position();
            this.currentY = this.startPos.y;
        }

        @Override
        public void run() {
            if (!livingEntity.isAlive()) cancel();

            double normalized = normalize(currentY, startPos.y);
            double normalizedFinalY = easeIn(normalized);
            double finalY = denormalize(normalizedFinalY, startPos.y);

            livingEntity.setPos(startPos.x, finalY, startPos.z);

            if ((currentY - 1) >= MAX_HEIGHT) {
                livingEntity.kill();
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