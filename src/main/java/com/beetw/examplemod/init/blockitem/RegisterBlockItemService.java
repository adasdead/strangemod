package com.beetw.examplemod.init.blockitem;

import com.beetw.examplemod.ExampleMod;
import com.beetw.examplemod.init.ModBlocks;
import com.beetw.examplemod.init.ModGroups;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = ExampleMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegisterBlockItemService {
    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> event) {
        ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)
                .filter(block -> block.getClass().isAnnotationPresent(RegisterBlockItem.class))
                .forEach(block -> registerBlockItem(block, event));
    }

    private static void registerBlockItem(Block block, RegistryEvent.Register<Item> event) {
        Item.Properties properties = new Item.Properties();

        if (block instanceof RegisterBlockItem.Properties) {
            RegisterBlockItem.Properties props = (RegisterBlockItem.Properties) block;
            properties = props.getBlockItemProperties();
        }

        BlockItem blockItem = new BlockItem(block, properties.tab(ModGroups.EXAMPLE_MOD));
        blockItem.setRegistryName(Objects.requireNonNull(block.getRegistryName()));
        event.getRegistry().register(blockItem);
    }
}
