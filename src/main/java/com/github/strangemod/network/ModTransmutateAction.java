package com.github.strangemod.network;

import com.github.strangemod.registry.ModTransmutations;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

@SuppressWarnings("unused")
public abstract class ModTransmutateAction {

    protected final BlockState input;
    protected final BlockState output;
    @Nullable
    protected final BlockState sneakOutput;

    protected ModTransmutateAction(BlockState input, BlockState output, @Nullable BlockState sneakOutput) {
        this.input = input;
        this.output = output;
        this.sneakOutput = sneakOutput;
    }


    protected void apply(boolean add) {
        if (add) {
           ModTransmutations.register(this.input, this.output, this.sneakOutput);
        } else {
            ModTransmutations.getModTransmutations().removeIf(entry -> entry.origin() == this.input &&
                    entry.result() == this.output && entry.altResult() == this.sneakOutput);
        }
    }

    public abstract void apply();

    public abstract String describe();

    public abstract void undo();

    public abstract String describeUndo();

    public static class Add extends ModTransmutateAction {

        public Add(BlockState input, BlockState output, @Nullable BlockState sneakOutput) {
            super(input, output, sneakOutput);
        }

        @Override
        public void apply() {
            apply(true);
        }

        @Override
        public String describe() {
            return null;
        }

        @Override
        public void undo() {

        }

        @Override
        public String describeUndo() {
            return null;
        }


    }}






