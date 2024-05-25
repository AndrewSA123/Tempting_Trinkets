package io.github.Tempting_Trinkets.event;

import io.github.Tempting_Trinkets.TemptingTrinkets;
import io.github.Tempting_Trinkets.entity.custom.Siren;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

@EventBusSubscriber(modid = TemptingTrinkets.MODID, bus = EventBusSubscriber.Bus.GAME)
public class GenericEvents {

    @SubscribeEvent
    public static void entityJoinLevelEvent(EntityJoinLevelEvent event){
        if(event.getEntity() instanceof Siren){
            ((Siren) event.getEntity()).SetHome((int)event.getEntity().position().x, (int)event.getEntity().position().y, (int)event.getEntity().position().z);
        }
    }
}
