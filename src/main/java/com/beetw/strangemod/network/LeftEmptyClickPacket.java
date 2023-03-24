package com.beetw.strangemod.network;

import com.beetw.strangemod.item.extra.ItemEmptyClick;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Supplier;

@SuppressWarnings({"unused", "InstantiationOfUtilityClass"})
public class LeftEmptyClickPacket {
    @Contract(value = "_ -> new", pure = true)
    public static @NotNull LeftEmptyClickPacket decode(PacketBuffer buf) {
        return new LeftEmptyClickPacket();
    }

    public static void encode(LeftEmptyClickPacket msg, PacketBuffer buf) {
    }

    public static class Handler {
        public static void handle(LeftEmptyClickPacket msg,
                                  @NotNull Supplier<NetworkEvent.Context> ctx) {

            ctx.get().enqueueWork(() -> handleWork(ctx));
            ctx.get().setPacketHandled(true);
        }

        private static void handleWork(@NotNull Supplier<NetworkEvent.Context> ctx) {
            PlayerEntity player = Objects.requireNonNull(ctx.get().getSender());
            ItemStack itemInHand = player.getMainHandItem();

            if (itemInHand.getItem() instanceof ItemEmptyClick) {
                World world = Objects.requireNonNull(ctx.get().getSender()).getLevel();
                ((ItemEmptyClick) itemInHand.getItem()).onEmptyClick(world, player);
            }
        }
    }

}
