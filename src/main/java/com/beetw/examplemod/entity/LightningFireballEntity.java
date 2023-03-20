package com.beetw.examplemod.entity;

import com.beetw.examplemod.item.StaffOfLightningItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.AbstractFireballEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class LightningFireballEntity extends AbstractFireballEntity {
    private static final int LIGHTNING_COUNT = 25;

    public LightningFireballEntity(World world) {
        super(EntityType.FIREBALL, world);
    }

    @Override
    protected void onHit(@NotNull RayTraceResult result) {
        if (!this.level.isClientSide) {
            Vector3d location = result.getLocation();

            for (int i = 0; i < LIGHTNING_COUNT; i++) {
                int xOffset = 5 - random.nextInt(10);
                int zOffset = 5 - random.nextInt(10);

                StaffOfLightningItem.spawnLightningBolt(this.level,
                        location.add(xOffset, 0, zOffset));
            }

            this.remove();
        }
    }
}
