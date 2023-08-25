package com.strangedevs.strangemod.item;

import com.strangedevs.strangemod.StrangeMod;
import com.strangedevs.strangemod.entity.LightningFireballEntity;
import com.strangedevs.strangemod.item.extra.ItemEmptyClick;
import com.strangedevs.strangemod.registry.ModGroups;
import com.strangedevs.strangemod.util.Tooltips;
import com.strangedevs.strangemod.util.Vectors;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
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
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;

@Mod.EventBusSubscriber(modid = StrangeMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class StaffOfLightningItem extends ToolItem implements ItemEmptyClick {
    private static final Item.Properties PROPERTIES = new Item.Properties()
            .stacksTo(1)
            .durability(1000)
            .tab(ModGroups.EXAMPLE_MOD);

    private static final double RAY_TRACE_DISTANCE = 50.0;

    public StaffOfLightningItem() {
        super(2, 3.0f, ItemTier.IRON, new HashSet<>(), PROPERTIES);
    }

    public static void spawnLightningBolt(@NotNull World world, @NotNull Vector3d pos) {
        EntityType<? extends LightningBoltEntity> type = EntityType.LIGHTNING_BOLT;
        LightningBoltEntity boltEntity = new LightningBoltEntity(type, world);
        boltEntity.setPos(pos.x, pos.y, pos.z);

        world.addFreshEntity(boltEntity);
    }

    @Override
    public @NotNull ActionResult<ItemStack> use(@NotNull World world,
                                                @NotNull PlayerEntity playerEntity,
                                                @NotNull Hand hand) {

        ItemStack itemInHand = playerEntity.getItemInHand(hand);
        RayTraceResult result = playerEntity.pick(RAY_TRACE_DISTANCE, 3.0f, true);

        if (result instanceof BlockRayTraceResult) {
            BlockRayTraceResult blockResult = ((BlockRayTraceResult) result);
            BlockPos pos = blockResult.getBlockPos();

            if (!world.getBlockState(pos).is(Blocks.AIR)) {
                itemInHand.hurtAndBreak(4, playerEntity, entity -> entity.broadcastBreakEvent(hand));
                spawnLightningBolt(world, Vectors.toVec3(pos));

                return ActionResult.success(itemInHand);
            }
        }

        return ActionResult.pass(itemInHand);
    }

    @Override
    public void onEmptyClick(@NotNull World world, @NotNull PlayerEntity playerEntity) {
        LightningFireballEntity fireballEntity = new LightningFireballEntity(world);
        fireballEntity.setPos(playerEntity.getX(), playerEntity.getEyeY(), playerEntity.getZ());
        fireballEntity.shootFromRotation(playerEntity, playerEntity.xRot, playerEntity.yRot,
                0.0f, 3.0f, 0.0f);
        world.addFreshEntity(fireballEntity);

        playerEntity.getMainHandItem().hurtAndBreak(25, playerEntity,
                entity -> entity.broadcastBreakEvent(playerEntity.getUsedItemHand()));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack,
                                @Nullable World world,
                                @NotNull List<ITextComponent> components,
                                @NotNull ITooltipFlag flag) {

        Tooltips.Appender appender = Tooltips.appender(components);
        appender.translate("tooltip.strange_mod.staff_of_lightning.0");
        appender.translate("tooltip.strange_mod.staff_of_lightning.1");

        super.appendHoverText(stack, world, components, flag);
    }
}
