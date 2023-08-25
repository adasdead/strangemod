package com.strangedevs.strangemod.event;

import com.strangedevs.strangemod.block.entity.ChipsStandBlockEntity;
import com.strangedevs.strangemod.registry.ModBlocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TileEntityRegisterEvent {
    @SubscribeEvent
    public static void onTileEntityRegistry(RegistryEvent.@NotNull Register<TileEntityType<?>> event) {
        event.getRegistry().register(TileEntityType.Builder.of(ChipsStandBlockEntity::new, ModBlocks.CHIPS_STAND.get()).
                build(null).setRegistryName("chips_stand"));
    }
}
