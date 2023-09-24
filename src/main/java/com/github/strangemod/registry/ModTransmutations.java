package com.github.strangemod.registry;

import com.github.strangemod.StrangeMod;
import com.github.strangemod.network.CoolMethods;
import com.github.strangemod.network.ModTransmutationEntry;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.fml.InterModComms;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class ModTransmutations {
    private static List<ModTransmutationEntry> DEFAULT_ENTRIES = Collections.emptyList();
    private static List<ModTransmutationEntry> ENTRIES = Collections.emptyList();

    public static void init() {
        registerDefault(Blocks.STONE, Blocks.COBBLESTONE, Blocks.GRASS_BLOCK);
}
    @Nullable
    public static BlockState getModTransmutation(BlockState current, boolean isSneaking) {
        for (ModTransmutationEntry e : ENTRIES) {
            if (e.origin() == current) {
                return isSneaking ? e.altResult() : e.result();
            }
        }
        return null;
    }

    public static List<ModTransmutationEntry> getModTransmutations() {
        return ENTRIES;
    }

    public static void setModTransmutation(List<ModTransmutationEntry> entries) {
        DEFAULT_ENTRIES = ImmutableList.copyOf(entries);
        resetWorldTransmutations();
    }

    public static void resetWorldTransmutations() {
        //Make it so that ENTRIES are mutable we can modify it with CraftTweaker
        ENTRIES = new ArrayList<>(DEFAULT_ENTRIES);
    }

    public static void register(BlockState from, BlockState result, @Nullable BlockState altResult) {
        ENTRIES.add(new ModTransmutationEntry(from, result, altResult));
    }

    private static void registerIMC(BlockState from, BlockState result, @Nullable BlockState altResult) {
        InterModComms.sendTo(StrangeMod.MOD_ID, CoolMethods.REGISTER_MOD_TRANSMUTATION, () -> new ModTransmutationEntry(from, result, altResult));
    }

    private static void registerDefault(Block from, Block result, @Nullable Block altResult) {
        registerIMC(from.defaultBlockState(), result.defaultBlockState(), altResult == null ? null : altResult.defaultBlockState());
    }

    private static void registerAllStates(Block from, Block result, @Nullable Block altResult) {
        StateDefinition<Block, BlockState> stateContainer = from.getStateDefinition();
        ImmutableList<BlockState> validStates = stateContainer.getPossibleStates();
        for (BlockState validState : validStates) {
            try {
                BlockState resultState = copyProperties(validState, result.defaultBlockState());
                BlockState altResultState = altResult == null ? null : copyProperties(validState, altResult.defaultBlockState());
                registerIMC(validState, resultState, altResultState);
            } catch (IllegalArgumentException e) {

            }
        }
    }

    private static BlockState copyProperties(BlockState source, BlockState target) {
        ImmutableMap<Property<?>, Comparable<?>> values = source.getValues();
        for (Map.Entry<Property<?>, Comparable<?>> entry : values.entrySet()) {
            target = applyProperty(target, entry.getKey(), entry.getValue());
        }
        return target;
    }

    private static <T extends Comparable<T>, V extends T> BlockState applyProperty(BlockState target, Property<T> property, Comparable<?> value) {
        return target.setValue(property, (V) value);
    }

    private static void registerBackAndForth(Block first, Block second) {
        registerDefault(first, second, null);
        registerDefault(second, first, null);
    }

    private static void registerBackAndForthAllStates(Block first, Block second) {
        registerAllStates(first, second, null);
        registerAllStates(second, first, null);
    }

    private static void registerConsecutivePairs(RegisterBlock registerMethod, Block... blocks) {
        for (int i = 0; i < blocks.length; i++) {
            Block prev = i == 0 ? blocks[blocks.length - 1] : blocks[i - 1];
            Block cur = blocks[i];
            Block next = i == blocks.length - 1 ? blocks[0] : blocks[i + 1];
            registerMethod.register(cur, next, prev);
        }
    }

    private static void registerConsecutivePairs(Block... blocks) {
        registerConsecutivePairs(ModTransmutations::registerDefault, blocks);
    }

    private static void registerConsecutivePairsAllStates(Block... blocks) {
        registerConsecutivePairs(ModTransmutations::registerAllStates, blocks);
    }

    @FunctionalInterface
    private interface RegisterBlock {

        void register(Block from, Block result, @Nullable Block altResult);
    }
}
