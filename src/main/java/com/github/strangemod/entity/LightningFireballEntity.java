package com.github.strangemod.entity;

import com.github.strangemod.item.StaffOfLightningItem;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class LightningFireballEntity extends Fireball {
    private static final int LIGHTNING_COUNT = 25;

    public LightningFireballEntity(Level level) {
        super(EntityType.FIREBALL, level);
    }

    @Override
    protected void onHit(@NotNull HitResult result) {
        Level fireballLevel = this.level();

        if (!fireballLevel.isClientSide) {
            Vec3 curPosition = result.getLocation();

            for (int i = 0; i < LIGHTNING_COUNT; i++) {
                Vec3 position = curPosition.add(random(), 0, random());
                StaffOfLightningItem.spawnLightningBolt(fireballLevel, position);
            }

            remove(RemovalReason.DISCARDED);
        }
    }

    private int random() {
        return 5 - random.nextInt(10);
    }
}
