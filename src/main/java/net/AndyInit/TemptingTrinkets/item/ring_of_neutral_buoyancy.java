package net.AndyInit.TemptingTrinkets.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class ring_of_neutral_buoyancy extends Item implements ICurioItem {
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

    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return true;
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if(slotContext.entity() instanceof Player){
            Player player = (Player)slotContext.entity();
            player.getAbilities().mayfly = true;
            player.onUpdateAbilities();
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if(slotContext.entity() instanceof Player){
            Player player = (Player)slotContext.entity();
            player.getAbilities().mayfly = false;
            player.onUpdateAbilities();
        }
    }
}
