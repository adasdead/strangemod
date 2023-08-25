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
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import org.jetbrains.annotations.NotNull;

public class ChipsStandRenderer extends TileEntityRenderer<ChipsStandBlockEntity> {
    private final float OMEGA = 2.0f;
    private float time = 0.0f;

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
            time += Minecraft.getInstance().getFrameTime();

            Vector3f rotVec = new Vector3f(0.0f, 1.0f, 0.0f);
            Quaternion quaternion = new Quaternion(rotVec, OMEGA * time / 2.0f, true);

            stack.pushPose();
            stack.translate(0.5f, 0.8f, 0.5f);
            stack.mulPose(quaternion);

            renderer.render(itemStack, ItemCameraTransforms.TransformType.GROUND, false, stack,
                    bufferSource, overlay, light, model);

            stack.popPose();
        }
    }
}
