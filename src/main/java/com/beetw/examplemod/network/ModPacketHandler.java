package com.beetw.examplemod.network;

import com.beetw.examplemod.ExampleMod;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class ModPacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(ExampleMod.MOD_ID, "main"),
            () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals
    );
    private static int i = 0;

    public static void register() {
        INSTANCE.registerMessage(i++, StaffEmptyClickPacket.class, StaffEmptyClickPacket::encode,
                StaffEmptyClickPacket::decode, StaffEmptyClickPacket.Handler::handle);
    }
}
