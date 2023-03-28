package com.beetw.strangemod.item;

import com.beetw.strangemod.init.ModGroups;
import net.minecraft.item.*;

public class FurhandsItem extends PickaxeItem {

    private static final Item.Properties PROPERTIES = new Item.Properties()
            .stacksTo(1)
            .durability(5000)
            .tab(ModGroups.EXAMPLE_MOD);

  public FurhandsItem() {
    super(ItemTier.NETHERITE, 2, 3.0f, PROPERTIES);
}


}
