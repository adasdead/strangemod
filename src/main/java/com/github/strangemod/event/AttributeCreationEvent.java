package com.github.strangemod.event;

import com.github.strangemod.StrangeMod;
import com.github.strangemod.registry.ModEntityTypes;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber(modid = StrangeMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AttributeCreationEvent {
    @SubscribeEvent
    public static void addEntityAttributes(@NotNull EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.NOKIA_BOX.get(), Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0)
                .build());
    }
}
