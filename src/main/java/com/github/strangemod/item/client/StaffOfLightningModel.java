package com.github.strangemod.item.client;

import com.github.strangemod.StrangeMod;
import com.github.strangemod.item.StaffOfLightningItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class StaffOfLightningModel extends GeoModel<StaffOfLightningItem> {
    @Override
    public ResourceLocation getModelResource(StaffOfLightningItem animatable) {
        return new ResourceLocation(StrangeMod.MOD_ID, "geo/staff_of_lightning.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(StaffOfLightningItem animatable) {
        return new ResourceLocation(StrangeMod.MOD_ID, "textures/item/staff_of_lightning.png");
    }

    @Override
    public ResourceLocation getAnimationResource(StaffOfLightningItem animatable) {
        return new ResourceLocation(StrangeMod.MOD_ID, "animations/staff_of_lightning.animation.json");
    }
}

