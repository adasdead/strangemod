package com.beetw.examplemod.event;

import com.beetw.examplemod.ExampleMod;
import com.beetw.examplemod.init.ModItems;
import com.beetw.examplemod.network.ModPacketHandler;
import com.beetw.examplemod.network.StaffEmptyClickPacket;
import net.minecraft.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@SuppressWarnings("InstantiationOfUtilityClass")
@Mod.EventBusSubscriber(modid = ExampleMod.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class LeftClickEmptyEvent {
    @SubscribeEvent
    public static void onLeftClickEmpty(PlayerInteractEvent.LeftClickEmpty event) {
        Item clickItem = event.getItemStack().getItem();

        if (clickItem == ModItems.STAFF_OF_LIGHTNING.get()) {
            ModPacketHandler.INSTANCE.sendToServer(new StaffEmptyClickPacket());
        }
    }
}
