package com.beetw.examplemod.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Данный абстрактный класс, во много раз упрощает создание и регистрацию блоков.
 *
 * <pre>
 * public class BlockName extends ModBlock {
 *     public BlockName() {
 *         super("name"); // name - идентификатор блока
 *     }
 * }
 * </pre>
 */
public abstract class ModBlock extends Block {
    private final String registryName;

    /**
     * Создает новый {@link ModBlock} объект.
     *
     * @param registryName идентификатор блока
     * @param properties   свойства блока
     */
    protected ModBlock(@NotNull String registryName, Properties properties) {
        super(Objects.requireNonNull(properties));
        this.registryName = registryName;
    }

    /**
     * Создает новый {@link ModBlock} объект с свойствами камня.
     *
     * @param registryName идентификатор блока
     */
    protected ModBlock(@NotNull String registryName) {
        this(registryName, AbstractBlock.Properties.of(Material.STONE));
    }

    /**
     * Регистрирует данный блок в игре
     *
     * @param difReg инициализированный менеджер для регистрации блока
     * @return {@link RegistryObject} данного блока
     */
    @NotNull
    public RegistryObject<Block> register(final @NotNull DeferredRegister<Block> difReg) {
        return difReg.register(registryName, () -> this);
    }

    /**
     * ВНИМАНИЕ: Не используй без реализации {@link ModBlockItem}
     *
     * @return {@link Item.Properties} предмета для данного блока
     */
    @NotNull
    public Item.Properties getBlockItemProperties() {
        return new Item.Properties();
    }
}
