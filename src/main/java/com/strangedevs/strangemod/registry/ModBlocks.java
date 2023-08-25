package com.strangedevs.strangemod.registry;

import com.strangedevs.strangemod.StrangeMod;
import com.strangedevs.strangemod.block.*;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, StrangeMod.MOD_ID);
    public static final RegistryObject<Block> GUNPOWDER_BARREL = register("gunpowder_barrel", GunpowderBarrelBlock::new);
    public static final RegistryObject<Block> WOOD_CASING = register("wood_casing", WoodCasingBlock::new);
    public static final RegistryObject<Block> STEEL_BLOCK = register("steel_block", AbstractMetalBlock.Impl::new);
    public static final RegistryObject<Block> AWAKENED_IRON_BLOCK = register("awakened_iron_block", AbstractMetalBlock.Impl::new);
    public static final RegistryObject<Block> ITZKYKYSHKA_STATUE = register("itzkykyshka_statue", ItzKyKySHkaStatueBlock::new);
    public static final RegistryObject<Block> FLYING_SWORD = register("flying_sword", FlyingSwordBlock::new);
    public static final RegistryObject<Block> CHIPS_STAND = register("chips_stand", ChipsStandBlock::new);

    public static @NotNull RegistryObject<Block> register(String id, Supplier<Block> supplier) {
        RegistryObject<Block> registryObject = BLOCKS.register(id, supplier);
        ModItems.register(id, () -> new BlockItem(registryObject.get(), new Item.Properties()
                .tab(ModGroups.EXAMPLE_MOD)));
        return registryObject;
    }
}
