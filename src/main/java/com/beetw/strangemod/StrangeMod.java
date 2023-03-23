package com.beetw.strangemod;

import com.beetw.strangemod.event.ClientSetupEvent;
import com.beetw.strangemod.event.CommonSetupEvent;
import com.beetw.strangemod.init.ModBlocks;
import com.beetw.strangemod.init.ModItems;
import com.beetw.strangemod.init.ModPaintings;
import com.beetw.strangemod.init.ModRecipes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;


@Mod(StrangeMod.MOD_ID)
public class StrangeMod {
    public static final String MOD_ID = "strange_mod";

    public StrangeMod() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        eventBus.addListener(CommonSetupEvent::onCommonSetup);
        eventBus.addListener(ClientSetupEvent::onClientSetup);

        ModBlocks.BLOCKS.register(eventBus);
        ModItems.ITEMS.register(eventBus);
        ModRecipes.RECIPES.register(eventBus);
        ModPaintings.PAINTINGS_TYPES.register(eventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void enqueueIMC(final InterModEnqueueEvent event) {
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,
                () -> SlotTypePreset.HANDS.getMessageBuilder().build());
    }


    }


