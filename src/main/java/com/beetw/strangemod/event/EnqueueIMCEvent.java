package com.beetw.strangemod.event;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

public class EnqueueIMCEvent {
    @SubscribeEvent
    public static void onEnqueueIMC(@NotNull InterModEnqueueEvent event) {
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,
                () -> SlotTypePreset.HANDS.getMessageBuilder().build());
    }
}
