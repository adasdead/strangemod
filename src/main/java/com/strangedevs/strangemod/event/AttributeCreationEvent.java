package com.strangedevs.strangemod.event;

import com.strangedevs.strangemod.StrangeMod;
import com.strangedevs.strangemod.registry.ModEntityTypes;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber(modid = StrangeMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AttributeCreationEvent {
    @SubscribeEvent
    public static void addEntityAttributes(@NotNull EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.NOKIA_BOX.get(), MobEntity.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0)
                .build());
    }
}
