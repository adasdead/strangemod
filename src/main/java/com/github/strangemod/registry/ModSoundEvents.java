package com.github.strangemod.registry;

import com.github.strangemod.StrangeMod;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, StrangeMod.MOD_ID);

    public static final RegistryObject<SoundEvent> NOKIA_BOX_RINGTONE = SOUND_EVENTS
            .register("nokia_box_ringtone", () -> SoundEvent.createVariableRangeEvent(
                    StrangeMod.location("nokia_box_ringtone")));

    public static final RegistryObject<SoundEvent> FLY_Y_Y_Y_Y_Y = SOUND_EVENTS
            .register("fly_y_y_y_y_y", () -> SoundEvent.createVariableRangeEvent(
                    StrangeMod.location("fly_y_y_y_y_y")));

    public static final RegistryObject<SoundEvent> FALLING_STAR = SOUND_EVENTS
            .register("falling_star", () -> SoundEvent.createVariableRangeEvent(
                    StrangeMod.location("falling_star")));
}
