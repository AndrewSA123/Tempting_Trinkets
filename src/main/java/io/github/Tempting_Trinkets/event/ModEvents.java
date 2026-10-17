package io.github.Tempting_Trinkets.event;

import io.github.Tempting_Trinkets.TemptingTrinkets;
import io.github.Tempting_Trinkets.entity.ModEntities;
import io.github.Tempting_Trinkets.entity.custom.CarrotWraith;
import io.github.Tempting_Trinkets.entity.custom.Siren;
import io.github.Tempting_Trinkets.network.FireballPayload;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

@EventBusSubscriber(modid = TemptingTrinkets.MODID)
public class ModEvents {

    @SubscribeEvent
    public static void eventAttributeEvent(EntityAttributeCreationEvent event){
        event.put(ModEntities.SIREN.get(), Siren.setAttributes());
        event.put(ModEntities.CARROT_WRAITH.get(), CarrotWraith.setAttributes());
    }

    @SubscribeEvent
    public static void registerPayloads(RegisterPayloadHandlersEvent event) {
        event.registrar("1").playToServer(FireballPayload.TYPE, FireballPayload.STREAM_CODEC, FireballPayload::handle);
    }
}
