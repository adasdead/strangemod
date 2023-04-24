package com.github.strangemod.registry.plugin;

import com.blakebr0.mysticalagriculture.api.IMysticalAgriculturePlugin;
import com.blakebr0.mysticalagriculture.api.MysticalAgriculturePlugin;
import com.blakebr0.mysticalagriculture.api.lib.PluginConfig;
import com.blakebr0.mysticalagriculture.api.registry.ICropRegistry;
import com.github.strangemod.StrangeMod;
import com.github.strangemod.registry.ModCrops;
import org.jetbrains.annotations.NotNull;

@MysticalAgriculturePlugin
public class AgriculturePlugin implements IMysticalAgriculturePlugin {
    @Override
    public void configure(@NotNull PluginConfig config) {
        config.setModId(StrangeMod.MOD_ID);
    }

    @Override
    public void onRegisterCrops(ICropRegistry registry) {
        ModCrops.onRegisterCrops(registry);
    }

    @Override
    public void onPostRegisterCrops(ICropRegistry registry) {
        ModCrops.onPostRegisterCrops(registry);
    }
}
