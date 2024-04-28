package net.AndyInit.TemptingTrinkets.event;

import net.AndyInit.TemptingTrinkets.Tempting_Trinkets;
import net.AndyInit.TemptingTrinkets.entity.custom.Siren;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

@Mod.EventBusSubscriber(modid = Tempting_Trinkets.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class GenericEvents {

    @SubscribeEvent
    public static void entityJoinLevelEvent(EntityJoinLevelEvent event){
        if(event.getEntity() instanceof Siren){
            ((Siren) event.getEntity()).SetHome((int)event.getEntity().position().x, (int)event.getEntity().position().y, (int)event.getEntity().position().z);
        }
    }
}
