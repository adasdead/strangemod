package com.strangedevs.strangemod.item;

import com.strangedevs.strangemod.entity.NokiaBoxEntity;
import com.strangedevs.strangemod.item.extra.ItemEmptyClick;
import com.strangedevs.strangemod.item.extra.ItemTooltipAppender;
import com.strangedevs.strangemod.registry.ModEntityTypes;
import com.strangedevs.strangemod.registry.ModGroups;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FunnyNokiaItem extends Item implements ItemEmptyClick {
    private static final Item.Properties PROPERTIES = new Item.Properties()
            .stacksTo(1)
            .tab(ModGroups.EXAMPLE_MOD);

    private static final double TO_DEGREES = Math.PI / 180.0;

    public FunnyNokiaItem() {
        super(PROPERTIES);
    }

    @Override
    public @NotNull ActionResult<ItemStack> use(@NotNull World world,
                                                @NotNull PlayerEntity playerEntity,
                                                @NotNull Hand hand) {

        EntityType<NokiaBoxEntity> entityType = ModEntityTypes.NOKIA_BOX.get();
        AxisAlignedBB alignedBB = playerEntity.getBoundingBox()
                .inflate(50, 50, 50);

        playerEntity.level.getEntities(entityType, alignedBB, entity -> true)
                .forEach(entity -> entity.explode(DamageSource.mobAttack(playerEntity)));

        return super.use(world, playerEntity, hand);
    }

    @Override
    public void onEmptyClick(@NotNull World world, @NotNull PlayerEntity playerEntity) {
        if (playerEntity.getCooldowns().isOnCooldown(this)) return;

        Vector3d direction = getPushDirectionByPlayer(playerEntity);
        NokiaBoxEntity entity = new NokiaBoxEntity(ModEntityTypes.NOKIA_BOX.get(), world);
        entity.setPos(playerEntity.getX(), playerEntity.getEyeY(), playerEntity.getZ());
        entity.push(direction.x, direction.y, direction.z);
        world.addFreshEntity(entity);

        playerEntity.getCooldowns().addCooldown(this, 15);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack,
                                @Nullable World world,
                                @NotNull List<ITextComponent> components,
                                @NotNull ITooltipFlag flag) {

        ItemTooltipAppender appender = new ItemTooltipAppender(components, true);
        appender.translate("funny_nokia.0").translate("funny_nokia.1");
        super.appendHoverText(stack, world, components, flag);
    }

    @NotNull
    private Vector3d getPushDirectionByPlayer(@NotNull PlayerEntity playerEntity) {
        double x = -Math.sin(playerEntity.yRot * TO_DEGREES) * Math.cos(playerEntity.xRot * TO_DEGREES);
        double z = Math.cos(playerEntity.yRot * TO_DEGREES) * Math.cos(playerEntity.xRot * TO_DEGREES);
        return new Vector3d(x, 0, z);
    }
}
