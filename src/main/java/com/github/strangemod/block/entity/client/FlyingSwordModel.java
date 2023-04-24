package com.github.strangemod.block.entity.client;

import com.github.strangemod.StrangeMod;
import com.github.strangemod.block.entity.FlyingSwordBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class FlyingSwordModel extends GeoModel<FlyingSwordBlockEntity> {
    @Override
    public ResourceLocation getModelResource(FlyingSwordBlockEntity animatable) {
        return new ResourceLocation(StrangeMod.MOD_ID, "geo/flying_sword.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FlyingSwordBlockEntity animatable) {
        return new ResourceLocation(StrangeMod.MOD_ID, "textures/block/flying_sword.png");
    }

    @Override
    public ResourceLocation getAnimationResource(FlyingSwordBlockEntity animatable) {
        return new ResourceLocation(StrangeMod.MOD_ID, "animations/flying_sword.animation.json");
    }
}
