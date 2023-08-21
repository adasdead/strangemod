package com.strangedevs.strangemod.renderer.model;

import com.strangedevs.strangemod.entity.NokiaBoxEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(value = Dist.CLIENT)
public class NokiaBoxModel extends EntityModel<NokiaBoxEntity> {
    private final ModelRenderer renderer;

    public NokiaBoxModel() {
        this.texWidth = 64;
        this.texHeight = 64;

        renderer = new ModelRenderer(this);
        renderer.setPos(0.0F, 24.0F, 0.0F);
        renderer.texOffs(0, 0)
                .addBox(-6.0F, -10.0F, -5.0F, 12.0F, 10.0F, 10.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(@NotNull NokiaBoxEntity entity,
                          float limbSwing,
                          float limbSwingAmount,
                          float ageInTicks,
                          float netHeadYaw,
                          float headPitch) {
    }

    @Override
    public void renderToBuffer(@NotNull MatrixStack matrixStack,
                               @NotNull IVertexBuilder buffer,
                               int packedLight,
                               int packedOverlay,
                               float red,
                               float green,
                               float blue,
                               float alpha) {

        renderer.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
