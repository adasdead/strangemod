package com.github.strangemod.block.entity;

import com.github.strangemod.StrangeMod;
import com.github.strangemod.registry.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, StrangeMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<FlyingSwordBlockEntity>> FLYING_SWORD =
            BLOCK_ENTITIES.register("flying_sword_block_entity", () ->
                    BlockEntityType.Builder.of(FlyingSwordBlockEntity::new,
                            ModBlocks.FLYING_SWORD.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
