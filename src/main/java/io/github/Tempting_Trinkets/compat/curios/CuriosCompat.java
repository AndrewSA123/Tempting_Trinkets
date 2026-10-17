package io.github.Tempting_Trinkets.compat.curios;

import io.github.Tempting_Trinkets.item.ModItems;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.function.Predicate;

public final class CuriosCompat {
    private CuriosCompat() {}

    // Right-click must use the ring, not equip it into a slot
    public static void register() {
        ICurioItem noEquipFromUse = new ICurioItem() {
            @Override
            public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
                return false;
            }
        };
        CuriosApi.registerCurio(ModItems.Ring_Of_Fire.get(), noEquipFromUse);
        CuriosApi.registerCurio(ModItems.Ring_Of_Neutral_Buoyancy.get(), noEquipFromUse);
    }

    public static ItemStack findWorn(Player player, Predicate<ItemStack> filter) {
        return CuriosApi.getCuriosInventory(player)
                .flatMap(handler -> handler.findFirstCurio(filter))
                .map(SlotResult::stack)
                .orElse(ItemStack.EMPTY);
    }
}