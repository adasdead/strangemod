package com.github.strangemod.client.renderer.model;

import com.github.strangemod.StrangeMod;
import com.github.strangemod.entity.NokiaBoxEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(value = Dist.CLIENT)
public class NokiaBoxModel extends EntityModel<NokiaBoxEntity> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            StrangeMod.location("nokia_box"), "main");

    private final ModelPart mainModelPart;

    public NokiaBoxModel(@NotNull ModelPart root) {
        this.mainModelPart = root.getChild("main");
    }

    public static @NotNull LayerDefinition createLayer() {
        MeshDefinition meshDefinition = QuadrupedModel.createBodyMesh(12, CubeDeformation.NONE);
        PartDefinition partDefinition = meshDefinition.getRoot();

        CubeListBuilder cubeListBuilder = CubeListBuilder.create()
                .texOffs(0, 0)
                .addBox(-6.0F, -10.0F, -5.0F, 12.0F, 10.0F, 10.0F, new CubeDeformation(0.0F));
        partDefinition.addOrReplaceChild("main", cubeListBuilder, PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshDefinition, 64, 64);
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
    public void renderToBuffer(@NotNull PoseStack poseStack,
                               @NotNull VertexConsumer consumer,
                               int packedLight,
                               int packedOverlay,
                               float red,
                               float green,
                               float blue,
                               float alpha) {

        mainModelPart.render(poseStack, consumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
