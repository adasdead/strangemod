package com.beetw.strangemod.network;

import com.beetw.strangemod.item.extra.ItemEmptyClick;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Supplier;

@SuppressWarnings({"unused", "InstantiationOfUtilityClass"})
public class LeftEmptyClickPacket {
    @Contract(value = "_ -> new", pure = true)
    public static @NotNull LeftEmptyClickPacket decode(FriendlyByteBuf buf) {
        return new LeftEmptyClickPacket();
    }

    public static void encode(LeftEmptyClickPacket msg, FriendlyByteBuf buf) {
    }

    public static class Handler {
        public static void handle(LeftEmptyClickPacket msg,
                                  @NotNull Supplier<NetworkEvent.Context> ctx) {

            ctx.get().enqueueWork(() -> handleWork(ctx));
            ctx.get().setPacketHandled(true);
        }

        private static void handleWork(@NotNull Supplier<NetworkEvent.Context> ctx) {
            ServerPlayer player = Objects.requireNonNull(ctx.get().getSender());
            ItemStack itemInHand = player.getMainHandItem();

            if (itemInHand.getItem() instanceof ItemEmptyClick) {
                Level level = Objects.requireNonNull(ctx.get().getSender()).getLevel();
                ((ItemEmptyClick) itemInHand.getItem()).onEmptyClick(level, player);
            }
        }
    }

}
