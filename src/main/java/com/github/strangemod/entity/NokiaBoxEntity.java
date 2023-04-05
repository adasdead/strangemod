package com.github.strangemod.entity;

import com.github.strangemod.StrangeMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

// TODO: need to fix entity model & music
public class NokiaBoxEntity extends Mob {
    private static final ResourceLocation SOUNDS_LOCATION = StrangeMod
            .location("nokia_box_ringtone");

    private final NokiaBoxTickableSound tickableSound;

    public NokiaBoxEntity(EntityType<? extends NokiaBoxEntity> type, Level level) {
        super(type, level);

        SoundEvent event = SoundEvent.createVariableRangeEvent(SOUNDS_LOCATION);
        tickableSound = new NokiaBoxTickableSound(event, this.getSoundSource());
    }

    public static @NotNull EntityType<NokiaBoxEntity> newEntityTypeFabric() {
        return EntityType.Builder
                .of(NokiaBoxEntity::new, MobCategory.MISC)
                .build(StrangeMod.location("nokia_box").toString());
    }

    public void explode(@NotNull Level level) {
        if (!level.isClientSide()) {
            level.explode(null, getX(), getY(), getZ(),
                    5.0f, false, Level.ExplosionInteraction.TNT);
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
    public void baseTick() {
        tickableSound.startPlaying();
        super.baseTick();
    }

    static public class NokiaBoxTickableSound extends AbstractTickableSoundInstance {
        private boolean played = false;

        public NokiaBoxTickableSound(SoundEvent event, SoundSource soundSource) {
            super(event, soundSource, SoundInstance.createUnseededRandom());
            this.looping = true;
        }

        public void startPlaying() {
            if (!played) {
                SoundManager manager = Minecraft.getInstance().getSoundManager();
                manager.queueTickingSound(this);
                played = true;
            }
        }

        @Override
        public void tick() {
            // None
        }
    }
}
