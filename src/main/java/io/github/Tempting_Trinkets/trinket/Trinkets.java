package io.github.Tempting_Trinkets.trinket;

import io.github.Tempting_Trinkets.compat.curios.CuriosCompat;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.ModList;

import java.util.function.Predicate;

public final class Trinkets {
    private Trinkets() {}

    public static ItemStack find(Player player, Class<? extends Trinket> type) {
        Predicate<ItemStack> isType = stack -> type.isInstance(stack.getItem());
        return search(player, isType, isType);
    }

    public static ItemStack findActive(Player player, Class<? extends Trinket> type) {
        return search(player,
                stack -> type.isInstance(stack.getItem()) && ((Trinket) stack.getItem()).isActive(stack),
                stack -> type.isInstance(stack.getItem()));
    }

    public static boolean has(Player player, Class<? extends Trinket> type) {
        return !find(player, type).isEmpty();
    }

    public static boolean hasActive(Player player, Class<? extends Trinket> type) {
        return !findActive(player, type).isEmpty();
    }

    private static ItemStack search(Player player, Predicate<ItemStack> inInventory, Predicate<ItemStack> worn) {
        Inventory inventory = player.getInventory();
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (inInventory.test(stack)) {
                return stack;
            }
        }
        if (ModList.get().isLoaded("curios")) {
            return CuriosCompat.findWorn(player, worn);
        }
        return ItemStack.EMPTY;
    }
}