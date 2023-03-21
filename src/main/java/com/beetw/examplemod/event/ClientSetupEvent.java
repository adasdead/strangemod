package com.beetw.examplemod.event;

import com.beetw.examplemod.ExampleMod;
import com.beetw.examplemod.init.ModItems;
import com.beetw.examplemod.item.StaffOfLightningItem;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.jetbrains.annotations.NotNull;

public class ClientSetupEvent {
    @SubscribeEvent
    public static void onClientSetup(@NotNull FMLClientSetupEvent event) {
        event.enqueueWork(() -> ItemModelsProperties.register(ModItems.STAFF_OF_LIGHTNING.get(),
                new ResourceLocation(ExampleMod.MOD_ID, "shoot"),
                (stack, worldIn, entityIn) -> StaffOfLightningItem.checkShoot(stack)));
    }
}
