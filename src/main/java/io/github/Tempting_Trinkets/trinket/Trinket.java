package io.github.Tempting_Trinkets.trinket;

import net.minecraft.world.item.ItemStack;

public interface Trinket {

    default boolean isActive(ItemStack stack) {
        return true;
    }
}