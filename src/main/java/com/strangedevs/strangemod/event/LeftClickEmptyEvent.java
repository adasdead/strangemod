package com.strangedevs.strangemod.event;

import com.strangedevs.strangemod.StrangeMod;
import com.strangedevs.strangemod.item.extra.ItemEmptyClick;
import com.strangedevs.strangemod.network.LeftEmptyClickPacket;
import com.strangedevs.strangemod.network.ModPacketHandler;
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

        if (clickItem instanceof ItemEmptyClick) {
            ModPacketHandler.INSTANCE.sendToServer(new LeftEmptyClickPacket());
        }
    }
}
