package com.beetw.strangemod.registry;

import com.beetw.strangemod.StrangeMod;
import com.beetw.strangemod.block.GunpowderBarrelBlock;
import com.beetw.strangemod.block.WoodCasingBlock;
import com.beetw.strangemod.block.extra.RegisterBlockItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

import static com.beetw.strangemod.block.AbstractMetalBlock.AwakenedIronBlock;
import static com.beetw.strangemod.block.AbstractMetalBlock.SteelBlock;

@SuppressWarnings("unused")
public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(
            ForgeRegistries.BLOCKS, StrangeMod.MOD_ID);

    public static final RegistryObject<Block> GUNPOWDER_BARREL = register(GunpowderBarrelBlock.class);
    public static final RegistryObject<Block> WOOD_CASING = register(WoodCasingBlock.class);
    public static final RegistryObject<Block> STEEL_BLOCK = register(SteelBlock.class);
    public static final RegistryObject<Block> AWAKENED_IRON_BLOCK = register(AwakenedIronBlock.class);

    private static <T extends Block> RegistryObject<Block> register(@NotNull Class<T> clazz) {

        RegisterBlockItem registerInfo = clazz.getAnnotation(RegisterBlockItem.class);

        Supplier<Block> blockSupplier = () -> {
            try {
                return clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        };

        if (registerInfo != null) {
            RegistryObject<Block> registryObject = BLOCKS.register(registerInfo.registryName(), blockSupplier);
            ModItems.ITEMS.register(registerInfo.registryName(), () -> new BlockItem(registryObject.get(),
                    new Item.Properties()));
            return registryObject;
        }

        throw new RuntimeException("No RegisterBlockItem annotation");
    }
}
