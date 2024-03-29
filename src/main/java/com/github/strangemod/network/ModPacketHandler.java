package com.github.strangemod.network;

import com.github.strangemod.StrangeMod;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModPacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            StrangeMod.location("main"), () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals
    );
    private static int i = 0;

    public static void register() {
        INSTANCE.registerMessage(i++, LeftEmptyClickPacket.class, LeftEmptyClickPacket::encode,
                LeftEmptyClickPacket::decode, LeftEmptyClickPacket.Handler::handle);
    }
}
