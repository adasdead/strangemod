package com.strangedevs.strangemod.block.entity;

import com.strangedevs.strangemod.client.screens.ChipsStandScreen;
import com.strangedevs.strangemod.registry.ModBlockEntities;
import com.strangedevs.strangemod.registry.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class ChipsStandBlockEntity extends TileEntity implements INamedContainerProvider {
    private static final Ingredient CHIPS = Ingredient.of(ModItems.CHIPS.get());

    private final ItemStackHandler itemStackHandler = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    public ChipsStandBlockEntity() {
        super(ModBlockEntities.CHIPS_BLOCK_ENTITY.get());
    }

    @Override
    public @NotNull ITextComponent getDisplayName() {
        return new TranslationTextComponent("block.strange_mod.chips_stand");
    }

    @Nullable
    @Override
    public Container createMenu(int id,
                                @NotNull PlayerInventory inventory,
                                @NotNull PlayerEntity playerEntity) {

        return new ChipsStandScreen.Menu(id, inventory, this);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap,
                                             @Nullable Direction side) {

        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemStackHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    public @NotNull CompoundNBT save(@NotNull CompoundNBT compoundTag) {
        compoundTag.put("inventory", itemStackHandler.serializeNBT());
        return super.save(compoundTag);
    }

    @Override
    public void load(@NotNull BlockState blockState, @NotNull CompoundNBT compoundTag) {
        super.load(blockState, compoundTag);
        itemStackHandler.deserializeNBT(compoundTag.getCompound("inventory"));
    }

    public boolean containsChips() {
        return CHIPS.test(itemStackHandler.getStackInSlot(0));
    }

    public void drop() {
        ItemEntity entity = new ItemEntity(Objects.requireNonNull(level), worldPosition.getX(),
                worldPosition.getY(), worldPosition.getZ(), itemStackHandler.getStackInSlot(0));
        level.addFreshEntity(entity);
    }
}
