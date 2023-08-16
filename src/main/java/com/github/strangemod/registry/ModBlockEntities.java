package com.github.strangemod.registry;

import com.github.strangemod.StrangeMod;
import com.github.strangemod.block.entity.ChipsStandBlockEntity;
import com.github.strangemod.block.entity.FlyingSwordBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, StrangeMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<FlyingSwordBlockEntity>> FLYING_SWORD =
            register("flying_sword_block_entity", () -> BlockEntityType.Builder.of(
                    FlyingSwordBlockEntity::new, ModBlocks.FLYING_SWORD.get()));

    public static final RegistryObject<BlockEntityType<ChipsStandBlockEntity>> CHIPS_BLOCK_ENTITY =
            register("chips_stand_block_entity", () -> BlockEntityType.Builder.of(
                    ChipsStandBlockEntity::new, ModBlocks.CHIPS_STAND.get()));

    @SuppressWarnings("DataFlowIssue")
    public static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(
            String id, Supplier<BlockEntityType.Builder<T>> builderSupplier
    ) {
        return BLOCK_ENTITIES.register(id, () -> builderSupplier.get().build(null));
    }
}
