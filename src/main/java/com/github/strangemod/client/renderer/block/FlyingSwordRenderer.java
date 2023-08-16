package com.github.strangemod.client.renderer.block;

import com.github.strangemod.block.entity.FlyingSwordBlockEntity;
import com.github.strangemod.client.model.FlyingSwordModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class FlyingSwordRenderer extends GeoBlockRenderer<FlyingSwordBlockEntity> {
    public FlyingSwordRenderer() {
        super(new FlyingSwordModel());
    }
}
