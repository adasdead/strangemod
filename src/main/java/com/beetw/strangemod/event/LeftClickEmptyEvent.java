package com.beetw.strangemod.event;

import com.beetw.strangemod.StrangeMod;
import com.beetw.strangemod.init.ModItems;
import com.beetw.strangemod.network.ModPacketHandler;
import com.beetw.strangemod.network.StaffEmptyClickPacket;
import net.minecraft.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@SuppressWarnings("InstantiationOfUtilityClass")
@Mod.EventBusSubscriber(modid = StrangeMod.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class LeftClickEmptyEvent {
    @SubscribeEvent
    public static void onLeftClickEmpty(PlayerInteractEvent.LeftClickEmpty event) {
        Item clickItem = event.getItemStack().getItem();

        if (clickItem == ModItems.STAFF_OF_LIGHTNING.get()) {
            ModPacketHandler.INSTANCE.sendToServer(new StaffEmptyClickPacket());
        }
    }
}
