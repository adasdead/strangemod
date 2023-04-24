package com.github.strangemod.client.renderer.block;

import com.github.strangemod.client.renderer.item.FlyingSwordBlockItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class FlyingSwordBlockItemRenderer extends GeoItemRenderer<FlyingSwordBlockItem> {
    public FlyingSwordBlockItemRenderer() {
        super(new FlyingSwordBlockItemModel());
    }
}