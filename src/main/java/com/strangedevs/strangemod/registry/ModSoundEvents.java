package com.strangedevs.strangemod.registry;

import com.strangedevs.strangemod.StrangeMod;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, StrangeMod.MOD_ID);

    public static final RegistryObject<SoundEvent> NOKIA_BOX_RINGTONE = register("nokia_box_ringtone");
    public static final RegistryObject<SoundEvent> FLY_Y_Y_Y_Y_Y = register("fly_y_y_y_y_y");

    private static RegistryObject<SoundEvent> register(String id) {
        return SOUND_EVENTS.register(id, () -> new SoundEvent(StrangeMod.location(id)));
    }
}
