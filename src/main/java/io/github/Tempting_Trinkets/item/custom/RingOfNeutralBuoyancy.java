package io.github.Tempting_Trinkets.item.custom;

import io.github.Tempting_Trinkets.trinket.Trinket;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public class RingOfNeutralBuoyancy extends Item implements Trinket {
    public RingOfNeutralBuoyancy(Item.Properties properties) {
        super(properties.stacksTo(1));
    }

    public static String GetName() {
        return "ring_of_neutral_buoyancy";
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

    @Override
    public boolean isActive(ItemStack stack) {
        return Boolean.TRUE.equals(stack.get(DataComponents.ENCHANTMENT_GLINT_OVERRIDE));
    }
}
