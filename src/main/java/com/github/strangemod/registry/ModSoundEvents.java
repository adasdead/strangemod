package com.github.strangemod.registry;

import com.github.strangemod.StrangeMod;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, StrangeMod.MOD_ID);

    public static final RegistryObject<SoundEvent> NOKIA_BOX_RINGTONE = register("nokia_box_ringtone");
    public static final RegistryObject<SoundEvent> FLY_Y_Y_Y_Y_Y = register("fly_y_y_y_y_y");
    public static final RegistryObject<SoundEvent> STAR_FELL = register("star_fell");

    private static RegistryObject<SoundEvent> register(String id) {
        return SOUND_EVENTS.register(id, () -> SoundEvent.createVariableRangeEvent(StrangeMod.location(id)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
