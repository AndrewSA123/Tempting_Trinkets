package io.github.Tempting_Trinkets.item.custom;

import io.github.Tempting_Trinkets.TemptingTrinkets;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
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
    public InteractionResult use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack stack = pPlayer.getItemInHand(pHand);
        boolean activating = !Boolean.TRUE.equals(stack.get(DataComponents.ENCHANTMENT_GLINT_OVERRIDE));
        stack.set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, activating);
        pLevel.playLocalSound(pPlayer.blockPosition(),
                activating ? SoundEvents.PLAYER_LEVELUP : SoundEvents.EXPERIENCE_ORB_PICKUP,
                SoundSource.PLAYERS, 1f, 1f, false);
        return InteractionResult.PASS;
    }

    public static boolean hasActiveRing(Player player) {
        Inventory inventory = player.getInventory();
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (stack.getItem() instanceof RingOfNeutralBuoyancy
                    && Boolean.TRUE.equals(stack.get(DataComponents.ENCHANTMENT_GLINT_OVERRIDE))) {
                return true;
            }
        }
        return false;
    }

    private static final Identifier RING_FLIGHT_ID =
            Identifier.fromNamespaceAndPath(TemptingTrinkets.MODID, "ring_of_neutral_buoyancy_flight");

    @SubscribeEvent
    public static void playerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        if (player.level().isClientSide()) return;   // the server decides; the attribute syncs to the client

        AttributeInstance flight = player.getAttribute(NeoForgeMod.CREATIVE_FLIGHT);
        if (flight == null) return;

        boolean shouldFly = RingOfNeutralBuoyancy.hasActiveRing(player);
        boolean hasModifier = flight.hasModifier(RING_FLIGHT_ID);

        if (shouldFly && !hasModifier) {
            flight.addTransientModifier(new AttributeModifier(RING_FLIGHT_ID, 1.0, AttributeModifier.Operation.ADD_VALUE));
            player.onUpdateAbilities();
        } else if (!shouldFly && hasModifier) {
            flight.removeModifier(RING_FLIGHT_ID);
            player.onUpdateAbilities();
        }
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
