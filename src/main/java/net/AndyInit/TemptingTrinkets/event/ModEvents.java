package net.AndyInit.TemptingTrinkets.event;

import net.AndyInit.TemptingTrinkets.Tempting_Trinkets;
import net.AndyInit.TemptingTrinkets.entity.ModEntities;
import net.AndyInit.TemptingTrinkets.entity.custom.Siren;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Tempting_Trinkets.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {
    @SubscribeEvent
    public static void eventAttributeEvent(EntityAttributeCreationEvent event){
        event.put(ModEntities.SIREN.get(), Siren.setAttributes());
    }
}
