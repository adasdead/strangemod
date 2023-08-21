package com.strangedevs.strangemod.registry;

import com.strangedevs.strangemod.StrangeMod;
import com.strangedevs.strangemod.entity.NokiaBoxEntity;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES
            = DeferredRegister.create(ForgeRegistries.ENTITIES, StrangeMod.MOD_ID);

    public static RegistryObject<EntityType<NokiaBoxEntity>> NOKIA_BOX
            = ENTITY_TYPES.register("nokia_box", NokiaBoxEntity::newEntityTypeFabric);
}
