package com.strangedevs.strangemod.client.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.strangedevs.strangemod.StrangeMod;
import com.strangedevs.strangemod.block.entity.ChipsStandBlockEntity;
import com.strangedevs.strangemod.registry.ModBlocks;
import com.strangedevs.strangemod.registry.ModItems;
import com.strangedevs.strangemod.registry.ModMenuTypes;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class ChipsStandScreen extends ContainerScreen<ChipsStandScreen.Menu> {
    private static final ResourceLocation TEXTURE = StrangeMod.location("textures/gui/chips_stand_menu.png");

    public ChipsStandScreen(ChipsStandScreen.Menu menu, PlayerInventory inventory, ITextComponent title) {
        super(menu, inventory, title);
    }

    @Override
    public void render(@NotNull MatrixStack stack, int mouseX, int mouseY, float tick) {
        this.renderBackground(stack);
        super.render(stack, mouseX, mouseY, tick);
        this.renderTooltip(stack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(@NotNull MatrixStack stack, float tick, int mouseX, int mouseY) {
        RenderSystem.blendColor(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(TEXTURE);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(stack, x, y, 0, 0, imageWidth, imageHeight);
    }

    public static class Menu extends Container {
        // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
        // must assign a slot number to each of the slots used by the GUI.
        // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
        // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
        //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
        //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
        //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
        private static final int HOTBAR_SLOT_COUNT = 9;
        private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
        private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
        private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
        private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
        private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
        private static final int VANILLA_FIRST_SLOT_INDEX = 0;
        // THIS YOU HAVE TO DEFINE!
        private static final int TE_INVENTORY_SLOT_COUNT = 4;  // must be the number of slots you have!
        private final ChipsStandBlockEntity blockEntity;
        private final World world;
        public Menu(int containerId, PlayerInventory inventory, @NotNull PacketBuffer byteBuf) {
            this(containerId, inventory, inventory.player.level.getBlockEntity(byteBuf.readBlockPos()));
        }

        public Menu(int containerId, PlayerInventory inventory, TileEntity entity) {
            super(ModMenuTypes.CHIPS_STAND_MENU.get(), containerId);

            checkContainerSize(inventory, 1);
            this.blockEntity = (ChipsStandBlockEntity) entity;
            this.world = inventory.player.level;
            addPlayerInventory(inventory);
            addPlayerHotbar(inventory);

            this.blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
                this.addSlot(new ChipsSlot(handler, 0, 80, 34));
            });
        }

        @Override
        public @NotNull ItemStack quickMoveStack(@NotNull PlayerEntity playerIn, int index) {
            Slot sourceSlot = slots.get(index);
            if (!sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
            ItemStack sourceStack = sourceSlot.getItem();
            ItemStack copyOfSourceStack = sourceStack.copy();

            // Check if the slot clicked is one of the vanilla container slots
            if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
                // This is a vanilla container slot so merge the stack into the tile inventory
                if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                        + TE_INVENTORY_SLOT_COUNT, false)) {
                    return ItemStack.EMPTY;  // EMPTY_ITEM
                }
            } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
                // This is a TE slot so merge the stack into the players inventory
                if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                    return ItemStack.EMPTY;
                }
            } else {
                System.out.println("Invalid slotIndex:" + index);
                return ItemStack.EMPTY;
            }
            // If stack size == 0 (the entire stack was moved) set slot contents to null
            if (sourceStack.getCount() == 0) {
                sourceSlot.set(ItemStack.EMPTY);
            } else {
                sourceSlot.setChanged();
            }
            sourceSlot.onTake(playerIn, sourceStack);
            return copyOfSourceStack;
        }

        @Override
        public boolean stillValid(@NotNull PlayerEntity player) {
            return stillValid(IWorldPosCallable.create(world, blockEntity.getBlockPos()), player,
                    ModBlocks.CHIPS_STAND.get());
        }

        private void addPlayerInventory(PlayerInventory playerInventory) {
            for (int i = 0; i < 3; ++i) {
                for (int l = 0; l < 9; ++l) {
                    this.addSlot(new Slot(playerInventory, l + i * 9 + 9,
                            8 + l * 18, 84 + i * 18));
                }
            }
        }

        private void addPlayerHotbar(PlayerInventory playerInventory) {
            for (int i = 0; i < 9; ++i) {
                this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
            }
        }
    }

    private static class ChipsSlot extends SlotItemHandler {
        private static final Ingredient CHIPS = Ingredient.of(ModItems.CHIPS.get());

        public ChipsSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
            super(itemHandler, index, xPosition, yPosition);
        }

        @Override
        public boolean mayPlace(@NotNull ItemStack stack) {
            return CHIPS.test(stack) && super.mayPlace(stack);
        }
    }
}
