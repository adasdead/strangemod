package com.github.strangemod.event;

import com.github.strangemod.StrangeMod;
import com.github.strangemod.client.model.NokiaBoxModel;
import com.github.strangemod.client.renderer.block.ChipsStandRenderer;
import com.github.strangemod.client.renderer.block.FlyingSwordRenderer;
import com.github.strangemod.client.renderer.entity.NokiaBoxRenderer;
import com.github.strangemod.registry.ModBlockEntities;
import com.github.strangemod.registry.ModEntityTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = StrangeMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RegisterRenderersEvent {
    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityTypes.NOKIA_BOX.get(), NokiaBoxRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.FLYING_SWORD.get(),
                context -> new FlyingSwordRenderer());
        event.registerBlockEntityRenderer(ModBlockEntities.CHIPS_BLOCK_ENTITY.get(),
                ChipsStandRenderer::new);
    }

    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(NokiaBoxModel.LAYER_LOCATION, NokiaBoxModel::createLayer);
    }
}
