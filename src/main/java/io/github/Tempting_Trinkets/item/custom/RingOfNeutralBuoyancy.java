package io.github.Tempting_Trinkets.item.custom;

import io.github.Tempting_Trinkets.TemptingTrinkets;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import java.util.Objects;

import static net.neoforged.neoforge.common.NeoForgeMod.CREATIVE_FLIGHT;

public class RingOfNeutralBuoyancy extends Item implements ICurioItem {
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
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }

    @Override
    public InteractionResult use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        var creative_flight = pPlayer.getAttribute(CREATIVE_FLIGHT);
        ItemStack stack = pPlayer.getItemInHand(pHand);

        if (creative_flight.getValue() > 0f) {
            creative_flight.setBaseValue(0);
            stack.set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, false);
            pLevel.playLocalSound(pPlayer.blockPosition(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 1f, 1f, false);
        } else {
            creative_flight.setBaseValue(1);
            stack.set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true);
            pLevel.playLocalSound(pPlayer.blockPosition(), SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 1f, 1f, false);
        }
        pPlayer.onUpdateAbilities();
        return InteractionResult.PASS;
    }

    //Curios Stuff

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if(slotContext.entity() instanceof Player player){
            Objects.requireNonNull(player.getAttribute(CREATIVE_FLIGHT)).setBaseValue(1);
            player.onUpdateAbilities();
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if(slotContext.entity() instanceof Player player){
            Objects.requireNonNull(player.getAttribute(CREATIVE_FLIGHT)).setBaseValue(0);
            player.onUpdateAbilities();
        }
    }

    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return false;
    }
}
