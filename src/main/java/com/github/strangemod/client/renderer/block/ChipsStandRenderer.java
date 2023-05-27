package com.github.strangemod.client.renderer.block;

import com.github.strangemod.block.entity.ChipsStandBlockEntity;
import com.github.strangemod.registry.ModItems;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ChipsStandRenderer implements BlockEntityRenderer<ChipsStandBlockEntity> {
    private final BlockEntityRendererProvider.Context context;

    public ChipsStandRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }

    @Override
    public void render(@NotNull ChipsStandBlockEntity entity,
                       float partialTicks,
                       @NotNull PoseStack stack,
                       @NotNull MultiBufferSource bufferSource,
                       int overlay,
                       int light) {

        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack itemStack = ModItems.CHIPS.get().getDefaultInstance();
        BakedModel bakedModel = itemRenderer.getModel(itemStack, entity.getLevel(), null, 0);

        if (entity.containsChips()) {
            stack.pushPose();
            stack.translate(0.5f, 0.8f, 0.5f);
            itemRenderer.render(itemStack, ItemDisplayContext.GROUND, false, stack,
                    bufferSource, overlay, light, bakedModel);

            stack.popPose();
        }
    }
}
