package com.strangedevs.strangemod.client.renderer.block;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.strangedevs.strangemod.block.entity.ChipsStandBlockEntity;
import com.strangedevs.strangemod.registry.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ChipsStandRenderer extends TileEntityRenderer<ChipsStandBlockEntity> {
    public ChipsStandRenderer(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(@NotNull ChipsStandBlockEntity entity,
                       float partialTicks,
                       @NotNull MatrixStack stack,
                       @NotNull IRenderTypeBuffer bufferSource,
                       int overlay,
                       int light) {

        ItemRenderer renderer = Minecraft.getInstance().getItemRenderer();
        ItemStack itemStack = ModItems.CHIPS.get().getDefaultInstance();
        IBakedModel model = renderer.getModel(itemStack, entity.getLevel(), null);

        if (entity.containsChips()) {
            stack.pushPose();
            stack.translate(0.5f, 0.8f, 0.5f);
            renderer.render(itemStack, ItemCameraTransforms.TransformType.GROUND, false, stack,
                    bufferSource, overlay, light, model);

            stack.popPose();
        }
    }
}
