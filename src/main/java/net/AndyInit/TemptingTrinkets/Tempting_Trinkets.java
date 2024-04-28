package net.AndyInit.TemptingTrinkets;

import com.mojang.logging.LogUtils;
import net.AndyInit.TemptingTrinkets.entity.ModEntities;
import net.AndyInit.TemptingTrinkets.entity.client.SirenRenderer;
import net.AndyInit.TemptingTrinkets.event.GenericEvents;
import net.AndyInit.TemptingTrinkets.item.ModItems;
import net.AndyInit.TemptingTrinkets.item.Tempting_TrinketsCreativeModeTab;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import org.slf4j.Logger;

@Mod(Tempting_Trinkets.MOD_ID)
public class Tempting_Trinkets
{
    public static final String MOD_ID = "tempting_trinkets";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Tempting_Trinkets()
    {
        IEventBus modEventBus = NeoForge.EVENT_BUS;

        Tempting_TrinketsCreativeModeTab.register(modEventBus);

        ModItems.register(modEventBus);
        ModEntities.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this);
        NeoForge.EVENT_BUS.register(new GenericEvents());

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event){
        if(event.getTab() == Tempting_TrinketsCreativeModeTab.Tempting_Trinkets_Tab.get()){
            event.accept(ModItems.Ring_Of_Neutral_Buoyancy.get());
            event.accept(ModItems.Ring_Of_Fire.get());
            event.accept(ModItems.SIREN_SPAWN_EGG.get());
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        LOGGER.info("Tempting Trinkets Server Initialising");
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("Tempting Trinkets Initialising");

            EntityRenderers.register(ModEntities.SIREN.get(), SirenRenderer::new);
        }
    }

}
