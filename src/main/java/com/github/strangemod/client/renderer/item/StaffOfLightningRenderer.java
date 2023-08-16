package com.github.strangemod.client.renderer.item;

import com.github.strangemod.client.model.StaffOfLightningModel;
import com.github.strangemod.item.StaffOfLightningItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class StaffOfLightningRenderer extends GeoItemRenderer<StaffOfLightningItem> {
    public StaffOfLightningRenderer() {
        super(new StaffOfLightningModel());
    }
}


