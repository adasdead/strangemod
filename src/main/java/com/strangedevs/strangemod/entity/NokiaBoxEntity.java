package com.strangedevs.strangemod.entity;

import com.strangedevs.strangemod.StrangeMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.EntityTickableSound;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class NokiaBoxEntity extends MobEntity {
    private static final ResourceLocation SOUNDS_LOCATION = StrangeMod
            .location("nokia_box_ringtone");

    private final NokiaBoxTickableSound tickableSound;

    public NokiaBoxEntity(EntityType<? extends NokiaBoxEntity> type, World world) {
        super(type, world);

        tickableSound = new NokiaBoxTickableSound(this, new SoundEvent(SOUNDS_LOCATION));
    }

    public static @NotNull EntityType<NokiaBoxEntity> newEntityTypeFabric() {
        return EntityType.Builder
                .of(NokiaBoxEntity::new, EntityClassification.MISC)
                .build(StrangeMod.location("nokia_box").toString());
    }

    public void explode(@NotNull DamageSource damageSource) {
        if (Objects.nonNull(damageSource.getEntity())) {
            World world = damageSource.getEntity().level;

            if (!world.isClientSide()) {
                world.explode(null, getX(), getY(), getZ(),
                        5.0f, false, Explosion.Mode.DESTROY);
                remove();
            }
        }
    }

    @Override
    public boolean hurt(@NotNull DamageSource damageSource, float damage) {
        if (damage >= Float.MAX_VALUE) {
            return super.hurt(damageSource, damage);
        }

        return false;
    }

    @Override
    public void baseTick() {
        tickableSound.startPlaying();
        super.baseTick();
    }

    static public class NokiaBoxTickableSound extends EntityTickableSound {
        private boolean played = false;

        public NokiaBoxTickableSound(Entity entity, SoundEvent event) {
            super(event, SoundCategory.BLOCKS, entity);
            this.looping = true;
        }

        public void startPlaying() {
            if (!played) {
                SoundHandler handler = Minecraft.getInstance().getSoundManager();
                handler.queueTickingSound(this);
                played = true;
            }
        }
    }
}
