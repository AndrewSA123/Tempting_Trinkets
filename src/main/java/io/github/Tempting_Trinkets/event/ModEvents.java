package io.github.Tempting_Trinkets.event;

import io.github.Tempting_Trinkets.TemptingTrinkets;
import io.github.Tempting_Trinkets.entity.ModEntities;
import io.github.Tempting_Trinkets.entity.custom.CarrotWraith;
import io.github.Tempting_Trinkets.entity.custom.Siren;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = TemptingTrinkets.MODID)
public class ModEvents {
    @SubscribeEvent
    public static void eventAttributeEvent(EntityAttributeCreationEvent event){
        event.put(ModEntities.SIREN.get(), Siren.setAttributes());
        event.put(ModEntities.CARROT_WRAITH.get(), CarrotWraith.setAttributes());
    }
}
