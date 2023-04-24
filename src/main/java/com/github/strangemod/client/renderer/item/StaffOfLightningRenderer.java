package com.github.strangemod.client.renderer.item;

import com.github.strangemod.client.renderer.model.StaffOfLightningModel;
import com.github.strangemod.item.StaffOfLightningItem;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class StaffOfLightningRenderer extends GeoItemRenderer<StaffOfLightningItem> {
    public StaffOfLightningRenderer() {
        super(new StaffOfLightningModel());
    }

    public static class ExConsumer implements IClientItemExtensions {
        private StaffOfLightningRenderer renderer;

        @Override
        public BlockEntityWithoutLevelRenderer getCustomRenderer() {
            if (this.renderer == null) {
                renderer = new StaffOfLightningRenderer();
            }

            return this.renderer;
        }
    }
}


