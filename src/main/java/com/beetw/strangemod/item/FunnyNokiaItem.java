package com.beetw.strangemod.item;

import com.beetw.strangemod.entity.NokiaBoxEntity;
import com.beetw.strangemod.item.extra.ItemEmptyClick;
import com.beetw.strangemod.item.extra.ItemTooltipAppender;
import com.beetw.strangemod.registry.ModEntityTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FunnyNokiaItem extends Item implements ItemEmptyClick {
    private static final Item.Properties PROPERTIES = new Item.Properties()
            .stacksTo(1);

    private static final double TO_DEGREES = Math.PI / 180.0;

    public FunnyNokiaItem() {
        super(PROPERTIES);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level,
                                                           @NotNull Player player,
                                                           @NotNull InteractionHand hand) {

        EntityType<NokiaBoxEntity> entityType = ModEntityTypes.NOKIA_BOX.get();
        AABB aabb = player.getBoundingBox().inflate(50, 50, 50);

        player.level.getEntities(entityType, aabb, entity -> true)
                .forEach(entity -> entity.explode(entity.level));

        return super.use(level, player, hand);
    }

    @Override
    public void onEmptyClick(@NotNull Level world, @NotNull ServerPlayer player) {
        if (player.getCooldowns().isOnCooldown(this)) return;

        Vec3 direction = getPushDirectionByPlayer(player);
        NokiaBoxEntity entity = new NokiaBoxEntity(ModEntityTypes.NOKIA_BOX.get(), world);
        entity.setPos(player.getX(), player.getEyeY(), player.getZ());
        entity.push(direction.x, direction.y, direction.z);
        world.addFreshEntity(entity);

        player.getCooldowns().addCooldown(this, 15);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack,
                                @Nullable Level world,
                                @NotNull List<Component> components,
                                @NotNull TooltipFlag flag) {

        ItemTooltipAppender appender = new ItemTooltipAppender(components, true);
        appender.translate("funny_nokia.0").translate("funny_nokia.1");
        super.appendHoverText(stack, world, components, flag);
    }

    @NotNull
    private Vec3 getPushDirectionByPlayer(@NotNull Player player) {
        double x = -Math.sin(player.yHeadRot * TO_DEGREES) * Math.cos(player.xRotO * TO_DEGREES);
        double z = Math.cos(player.yHeadRot * TO_DEGREES) * Math.cos(player.xRotO * TO_DEGREES);
        return new Vec3(x, 0, z);
    }
}
