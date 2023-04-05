package com.github.strangemod.entity;

import com.github.strangemod.StrangeMod;
import com.github.strangemod.registry.ModSoundEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class NokiaBoxEntity extends Mob {
    private final NokiaBoxSoundInstance soundInstance;

    public NokiaBoxEntity(EntityType<? extends NokiaBoxEntity> type, Level level) {
        super(type, level);

        this.soundInstance = new NokiaBoxSoundInstance(this);
    }

    public static @NotNull EntityType<NokiaBoxEntity> newEntityTypeFabric() {
        return EntityType.Builder
                .of(NokiaBoxEntity::new, MobCategory.MISC)
                .build(StrangeMod.location("nokia_box").toString());
    }

    public void explode(@NotNull Level level) {
        if (!level.isClientSide()) {
            level.explode(null, getX(), getY(), getZ(), 5.0f,
                    false, Level.ExplosionInteraction.TNT);
            remove(RemovalReason.KILLED);
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
    public void onAddedToWorld() {
        SoundManager manager = Minecraft.getInstance().getSoundManager();

        if (!manager.isActive(soundInstance)) {
            manager.queueTickingSound(soundInstance);
        }

        super.onAddedToWorld();
    }

    static public class NokiaBoxSoundInstance extends AbstractTickableSoundInstance {
        private final NokiaBoxEntity nokiaBoxEntity;

        public NokiaBoxSoundInstance(NokiaBoxEntity nokiaBoxEntity) {
            super(ModSoundEvents.NOKIA_BOX_RINGTONE.get(), SoundSource.NEUTRAL,
                    SoundInstance.createUnseededRandom());

            this.nokiaBoxEntity = nokiaBoxEntity;
            this.looping = true;
        }

        @Override
        public void tick() {
            if (!nokiaBoxEntity.isRemoved() && nokiaBoxEntity.isAlive()) {
                this.x = nokiaBoxEntity.getX();
                this.y = nokiaBoxEntity.getY();
                this.z = nokiaBoxEntity.getZ();
            } else {
                this.stop();
            }
        }
    }
}
