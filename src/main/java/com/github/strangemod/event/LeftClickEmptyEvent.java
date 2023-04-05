package com.github.strangemod.event;

import com.github.strangemod.StrangeMod;
import com.github.strangemod.item.extra.ItemEmptyClick;
import com.github.strangemod.network.LeftEmptyClickPacket;
import com.github.strangemod.network.ModPacketHandler;
import net.minecraft.world.item.Item;
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
