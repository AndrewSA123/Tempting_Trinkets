package io.github.Tempting_Trinkets.item.custom;

import io.github.Tempting_Trinkets.TemptingTrinkets;
import io.github.Tempting_Trinkets.compat.curios.CuriosCompat;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.neoforged.fml.ModList;

public class RingOfNeutralBuoyancy extends Item {
    public RingOfNeutralBuoyancy(Item.Properties properties) {
        super(properties.stacksTo(1));
    }

    public static String GetName() {
        return "ring_of_neutral_buoyancy";
    }

    public static Identifier GetTextureLocation(){
        return Identifier.fromNamespaceAndPath(TemptingTrinkets.MODID, "textures/curios/" + GetName() + ".png");
    }

    @Override
    public InteractionResult use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack stack = pPlayer.getItemInHand(pHand);
        boolean activating = !Boolean.TRUE.equals(stack.get(DataComponents.ENCHANTMENT_GLINT_OVERRIDE));
        stack.set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, activating);
        pLevel.playLocalSound(pPlayer.blockPosition(),
                activating ? SoundEvents.PLAYER_LEVELUP : SoundEvents.EXPERIENCE_ORB_PICKUP,
                SoundSource.PLAYERS, 1f, 1f, false);
        return InteractionResult.PASS;
    }

    private static boolean isActive(ItemStack stack) {
        return stack.getItem() instanceof RingOfNeutralBuoyancy
                && Boolean.TRUE.equals(stack.get(DataComponents.ENCHANTMENT_GLINT_OVERRIDE));
    }

    public static boolean hasActiveRing(Player player) {
        Inventory inventory = player.getInventory();
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            if (isActive(inventory.getItem(i))) {
                return true;
            }
        }
        return ModList.get().isLoaded("curios")
                && !CuriosCompat.findWorn(player, stack -> stack.getItem() instanceof RingOfNeutralBuoyancy).isEmpty();
    }
}
