package com.beetw.strangemod.item;

import com.beetw.strangemod.entity.NokiaBoxEntity;
import com.beetw.strangemod.init.ModEntityTypes;
import com.beetw.strangemod.init.ModGroups;
import com.beetw.strangemod.item.extra.ItemEmptyClick;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

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
        Vector3d direction = getPushDirectionByPlayer(playerEntity);
        NokiaBoxEntity entity = new NokiaBoxEntity(ModEntityTypes.NOKIA_BOX.get(), world);
        entity.setPos(playerEntity.getX(), playerEntity.getEyeY(), playerEntity.getZ());
        entity.push(direction.x, direction.y, direction.z);
        world.addFreshEntity(entity);
    }

    @NotNull
    private Vector3d getPushDirectionByPlayer(@NotNull PlayerEntity playerEntity) {
        double x = -Math.sin(playerEntity.yRot * TO_DEGREES) * Math.cos(playerEntity.xRot * TO_DEGREES);
        double z = Math.cos(playerEntity.yRot * TO_DEGREES) * Math.cos(playerEntity.xRot * TO_DEGREES);
        return new Vector3d(x, 0, z);
    }
}