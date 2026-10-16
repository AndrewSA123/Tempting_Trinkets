package io.github.Tempting_Trinkets.event;

import io.github.Tempting_Trinkets.TemptingTrinkets;
import io.github.Tempting_Trinkets.entity.custom.Siren;
import io.github.Tempting_Trinkets.item.custom.RingOfNeutralBuoyancy;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = TemptingTrinkets.MODID)
public class GenericEvents {

    @SubscribeEvent
    public static void entityJoinLevelEvent(EntityJoinLevelEvent event){
        if(event.getEntity() instanceof Siren siren){
            siren.SetHome((int)siren.position().x, (int)siren.position().y, (int)siren.position().z);
        }
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
}
