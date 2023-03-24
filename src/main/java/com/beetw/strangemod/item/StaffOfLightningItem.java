package com.beetw.strangemod.item;

import com.beetw.strangemod.StrangeMod;
import com.beetw.strangemod.entity.LightningFireballEntity;
import com.beetw.strangemod.init.ModGroups;
import com.beetw.strangemod.item.extra.ItemEmptyClick;
import net.minecraft.block.Blocks;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.item.ToolItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;

@Mod.EventBusSubscriber(modid = StrangeMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class StaffOfLightningItem extends ToolItem implements ItemEmptyClick {
    private static final Item.Properties PROPERTIES = new Item.Properties()
            .stacksTo(1)
            .durability(1000)
            .tab(ModGroups.EXAMPLE_MOD);

    private static final double RAY_TRACE_DISTANCE = 50.0;
    public boolean isShoot = false;

    public StaffOfLightningItem() {
        super(2, 3.0f, ItemTier.IRON, new HashSet<>(), PROPERTIES);
    }

    public static void spawnLightningBolt(@NotNull World world, @NotNull Vector3d pos) {
        EntityType<? extends LightningBoltEntity> type = EntityType.LIGHTNING_BOLT;
        LightningBoltEntity boltEntity = new LightningBoltEntity(type, world);
        boltEntity.setPos(pos.x, pos.y, pos.z);
        world.addFreshEntity(boltEntity);
    }

    public static float itemProperties(@NotNull ItemStack itemStack,
                                       @Nullable ClientWorld world,
                                       @Nullable LivingEntity entity) {

        if (((StaffOfLightningItem) itemStack.getItem()).isShoot) {
            ((StaffOfLightningItem) itemStack.getItem()).isShoot = false;
            return 1.0f;
        }

        return 0.0f;
    }

    @Override
    public @NotNull ActionResult<ItemStack> use(@NotNull World world,
                                                @NotNull PlayerEntity playerEntity,
                                                @NotNull Hand hand) {

        RayTraceResult result = playerEntity.pick(RAY_TRACE_DISTANCE, 3.0f, true);

        if (result instanceof BlockRayTraceResult) {
            BlockRayTraceResult blockResult = ((BlockRayTraceResult) result);
            BlockPos pos = blockResult.getBlockPos();

            if (!world.getBlockState(pos).is(Blocks.AIR)) {
                ItemStack itemInHand = playerEntity.getItemInHand(hand);
                Vector3d vec = new Vector3d(pos.getX(), pos.getY(), pos.getZ());
                itemInHand.hurtAndBreak(5, playerEntity,
                        entity -> entity.broadcastBreakEvent(hand));
                spawnLightningBolt(world, vec);
                isShoot = true;

                return ActionResult.success(itemInHand);
            }
        }

        return ActionResult.pass(playerEntity.getItemInHand(hand));
    }

    @Override
    public void onEmptyClick(@NotNull World world, @NotNull PlayerEntity playerEntity) {
        playerEntity.getMainHandItem().hurtAndBreak(25, playerEntity,
                entity -> entity.broadcastBreakEvent(playerEntity.getUsedItemHand()));
        LightningFireballEntity fireballEntity = new LightningFireballEntity(world);
        fireballEntity.setPos(playerEntity.getX(), playerEntity.getEyeY(), playerEntity.getZ());
        fireballEntity.shootFromRotation(playerEntity, playerEntity.xRot, playerEntity.yRot,
                0.0f, 3.0f, 0.0f);
        world.addFreshEntity(fireballEntity);
        isShoot = true;
    }
}
