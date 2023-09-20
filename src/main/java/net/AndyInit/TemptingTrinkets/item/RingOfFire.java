package net.AndyInit.TemptingTrinkets.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class RingOfFire extends Item implements ICurioItem {
    private static String Name = "ring_of_fire";

    public RingOfFire(Properties pProperties) {
        super(pProperties);
    }

    public static String GetItemName(){
        return Name;
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }

    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return true;
    }
}
