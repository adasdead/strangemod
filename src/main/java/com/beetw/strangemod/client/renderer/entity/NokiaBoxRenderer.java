package com.beetw.strangemod.client.renderer.entity;

import com.beetw.strangemod.StrangeMod;
import com.beetw.strangemod.client.renderer.model.NokiaBoxModel;
import com.beetw.strangemod.entity.NokiaBoxEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(value = Dist.CLIENT)
public class NokiaBoxRenderer extends MobRenderer<NokiaBoxEntity, NokiaBoxModel> {
    public NokiaBoxRenderer(EntityRendererManager manager) {
        super(manager, new NokiaBoxModel(), 0.7f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull NokiaBoxEntity entity) {
        return new ResourceLocation(StrangeMod.MOD_ID, "textures/entity/nokia_box.png");
    }
}
