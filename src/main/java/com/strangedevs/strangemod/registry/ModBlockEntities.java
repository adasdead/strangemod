package com.strangedevs.strangemod.registry;

import com.strangedevs.strangemod.StrangeMod;
import com.strangedevs.strangemod.block.entity.ChipsStandBlockEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<TileEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, StrangeMod.MOD_ID);

//    public static final RegistryObject<BlockEntityType<FlyingSwordBlockEntity>> FLYING_SWORD =
//            register("flying_sword_block_entity", () -> BlockEntityType.Builder.of(
//                    FlyingSwordBlockEntity::new, ModBlocks.FLYING_SWORD.get()));

    public static final RegistryObject<TileEntityType<ChipsStandBlockEntity>> CHIPS_BLOCK_ENTITY =
            register("chips_stand_block_entity", () -> TileEntityType.Builder.of(
                    ChipsStandBlockEntity::new, ModBlocks.CHIPS_STAND.get()));

    @SuppressWarnings("DataFlowIssue")
    public static <T extends TileEntity> RegistryObject<TileEntityType<T>> register(
            String id, Supplier<TileEntityType.Builder<T>> builderSupplier
    ) {
        return BLOCK_ENTITIES.register(id, () -> builderSupplier.get().build(null));
    }
}
