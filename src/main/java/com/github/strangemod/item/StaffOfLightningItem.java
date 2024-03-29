package com.github.strangemod.item;

import com.github.strangemod.client.renderer.item.StaffOfLightningRenderer;
import com.github.strangemod.entity.LightningFireballEntity;
import com.github.strangemod.item.extra.ItemEmptyClick;
import com.github.strangemod.util.Tooltips;
import com.github.strangemod.util.Vectors;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.function.Consumer;

public class StaffOfLightningItem extends Item implements ItemEmptyClick, GeoItem {
    private static final double RAY_TRACE_DISTANCE = 50.0;
    private static final RawAnimation IDLE_ANIM = RawAnimation.begin().thenLoop("idle");

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public StaffOfLightningItem() {
        this(new Item.Properties().stacksTo(1).durability(1000));
    }

    public StaffOfLightningItem(Properties properties) {
        super(properties);
    }

    public static void spawnLightningBolt(@NotNull Level level, @NotNull Vec3 pos) {
        EntityType<? extends LightningBolt> type = EntityType.LIGHTNING_BOLT;
        LightningBolt lightningBolt = new LightningBolt(type, level);
        lightningBolt.setPos(pos);

        level.addFreshEntity(lightningBolt);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level,
                                                           @NotNull Player player,
                                                           @NotNull InteractionHand hand) {

        ItemStack itemInHand = player.getItemInHand(hand);
        HitResult result = player.pick(RAY_TRACE_DISTANCE, 3.0f, true);

        if (result instanceof BlockHitResult blockHitResult) {
            BlockPos pos = blockHitResult.getBlockPos();

            if (!level.getBlockState(pos).is(Blocks.AIR)) {
                itemInHand.hurtAndBreak(4, player, entity -> entity.broadcastBreakEvent(hand));
                spawnLightningBolt(level, Vectors.toVec3(pos));

                return InteractionResultHolder.success(itemInHand);
            }
        }

        return InteractionResultHolder.pass(itemInHand);
    }

    @Override
    public void onEmptyClick(@NotNull Level level, @NotNull ServerPlayer player) {
        Vec2 rotation = player.getRotationVector();

        LightningFireballEntity fireballEntity = new LightningFireballEntity(level);
        fireballEntity.setPos(player.getX(), player.getEyeY(), player.getZ());
        fireballEntity.shootFromRotation(player, rotation.x, rotation.y,
                0.0f, 3.0f, 0.0f);
        level.addFreshEntity(fireballEntity);

        player.getMainHandItem().hurtAndBreak(25, player,
                entity -> entity.broadcastBreakEvent(player.getUsedItemHand()));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack,
                                @Nullable Level world,
                                @NotNull List<Component> components,
                                @NotNull TooltipFlag flag) {

        Tooltips.Appender appender = Tooltips.appender(components);
        appender.translate("tooltip.strange_mod.staff_of_lightning.0");
        appender.translate("tooltip.strange_mod.staff_of_lightning.1");

        super.appendHoverText(stack, world, components, flag);
    }

    /* ****************************************************************************************** *
     *                                          GECKOLIB                                          *
     * ****************************************************************************************** */

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0,
                state -> state.setAndContinue(IDLE_ANIM)));
    }

    @Override
    public void initializeClient(@NotNull Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private StaffOfLightningRenderer renderer = null;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (this.renderer == null) {
                    this.renderer = new StaffOfLightningRenderer();
                }

                return this.renderer;
            }
        });
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}