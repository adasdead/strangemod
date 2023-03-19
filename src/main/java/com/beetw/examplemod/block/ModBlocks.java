package com.beetw.examplemod.block;

import com.beetw.examplemod.ExampleMod;
import com.beetw.examplemod.ModGroup;
import com.beetw.examplemod.block.impl.BlockGunpowderBarrel;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = ExampleMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(
            ForgeRegistries.BLOCKS, ExampleMod.MOD_ID);

    public static final RegistryObject<Block> GUNPOWDER_BARREL = new BlockGunpowderBarrel()
            .register(BLOCKS);

    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
        BLOCKS.getEntries().stream().map(RegistryObject::get)
                .filter(block -> block.getClass().isAnnotationPresent(ModBlockItem.class))
                .filter(block -> block instanceof ModBlock)
                .forEach(block -> {
                    final Item.Properties properties = ((ModBlock) block).getBlockItemProperties()
                            .tab(ModGroup.getInstance());
                    final BlockItem blockItem = new BlockItem(block, properties);
                    blockItem.setRegistryName(Objects.requireNonNull(block.getRegistryName()));
                    event.getRegistry().register(blockItem);
                });
    }
}
