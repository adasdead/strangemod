package com.github.strangemod.item.client;

import com.github.strangemod.item.StaffOfLightningItem;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class StaffOfLightningRenderer extends GeoItemRenderer<StaffOfLightningItem> {
    public StaffOfLightningRenderer() {
        super(new StaffOfLightningModel());
    }
}


