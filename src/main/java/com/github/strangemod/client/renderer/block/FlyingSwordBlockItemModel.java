package com.github.strangemod.client.renderer.block;

import com.github.strangemod.StrangeMod;
import com.github.strangemod.client.renderer.item.FlyingSwordBlockItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class FlyingSwordBlockItemModel extends GeoModel<FlyingSwordBlockItem>{

    @Override
    public ResourceLocation getModelResource(FlyingSwordBlockItem animatable) {
        return new ResourceLocation(StrangeMod.MOD_ID, "geo/flying_sword.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FlyingSwordBlockItem animatable) {
        return new ResourceLocation(StrangeMod.MOD_ID, "textures/block/flying_sword.png");
    }

    @Override
    public ResourceLocation getAnimationResource(FlyingSwordBlockItem animatable) {
        return new ResourceLocation(StrangeMod.MOD_ID, "animations/flying_sword.animation.json");
    }
}