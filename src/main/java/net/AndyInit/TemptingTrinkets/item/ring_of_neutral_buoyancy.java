package net.AndyInit.TemptingTrinkets.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ring_of_neutral_buoyancy extends Item {
    private static String Name = "ring_of_neutral_buoyancy";

    public ring_of_neutral_buoyancy(Properties properties) {
        super(properties);
    }

    public static String GetItemName(){
        return Name;
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }
}
