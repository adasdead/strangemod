package com.beetw.examplemod.block.item;

import com.beetw.examplemod.ExampleMod;
import com.beetw.examplemod.ModGroup;
import com.beetw.examplemod.block.ModBlocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = ExampleMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlockItemRegister {
    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
        ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)
                .filter(block -> block.getClass().isAnnotationPresent(ModBlockItem.class))
                .forEach(block -> {
                    Item.Properties properties = new Item.Properties();

                    if (block instanceof ModBlockItem.Properties) {
                        ModBlockItem.Properties props = (ModBlockItem.Properties) block;
                        properties = props.getBlockItemProperties();
                    }

                    BlockItem blockItem = new BlockItem(block, properties.tab(ModGroup.getInstance()));
                    blockItem.setRegistryName(Objects.requireNonNull(block.getRegistryName()));
                    event.getRegistry().register(blockItem);
                });
    }
}
