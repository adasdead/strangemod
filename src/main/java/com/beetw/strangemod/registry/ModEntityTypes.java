package com.beetw.strangemod.registry;

import com.beetw.strangemod.StrangeMod;
import com.beetw.strangemod.entity.NokiaBoxEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES
            = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, StrangeMod.MOD_ID);

    public static RegistryObject<EntityType<NokiaBoxEntity>> NOKIA_BOX
            = ENTITY_TYPES.register("nokia_box", NokiaBoxEntity::newEntityTypeFabric);
}
