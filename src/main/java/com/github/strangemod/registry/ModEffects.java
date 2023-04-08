package com.github.strangemod.registry;

import com.github.strangemod.StrangeMod;
import com.github.strangemod.effect.FlyyyyyEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public class ModEffects {
    public static DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, StrangeMod.MOD_ID);

    public static RegistryObject<MobEffect> FLYYYYY = MOB_EFFECTS.register("fly_y_y_y_y_y",
            () -> new FlyyyyyEffect(MobEffectCategory.HARMFUL, 3124687));
}
