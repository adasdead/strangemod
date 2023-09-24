package com.github.strangemod.network;

import com.github.strangemod.StrangeMod;
import com.github.strangemod.registry.ModTransmutations;
import net.minecraftforge.fml.InterModComms;

import java.util.ArrayList;
import java.util.List;

public class IMCHandler {
    public static void handleMessages() {
        List<ModTransmutationEntry> entries = new ArrayList<>();
        InterModComms.getMessages(StrangeMod.MOD_ID, CoolMethods.REGISTER_MOD_TRANSMUTATION::equals)
                .filter(msg -> msg.messageSupplier().get() instanceof ModTransmutationEntry)
                .forEach(msg -> {
                    ModTransmutationEntry transmutationEntry = (ModTransmutationEntry) msg.messageSupplier().get();
                    entries.add(transmutationEntry);


                });
       ModTransmutations.setModTransmutation(entries);
}}
