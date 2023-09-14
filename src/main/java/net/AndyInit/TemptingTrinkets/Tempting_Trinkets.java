package net.AndyInit.TemptingTrinkets;

import com.mojang.logging.LogUtils;
import net.AndyInit.TemptingTrinkets.entity.ModEntities;
import net.AndyInit.TemptingTrinkets.entity.client.SirenRenderer;
import net.AndyInit.TemptingTrinkets.event.GenericEvents;
import net.AndyInit.TemptingTrinkets.item.ModItems;
import net.AndyInit.TemptingTrinkets.item.Tempting_TrinketsCreativeModeTab;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import top.theillusivec4.curios.api.SlotTypeMessage;

@Mod(Tempting_Trinkets.MOD_ID)
public class Tempting_Trinkets
{
    public static final String MOD_ID = "tempting_trinkets";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Tempting_Trinkets()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        Tempting_TrinketsCreativeModeTab.register(modEventBus);

        ModItems.register(modEventBus);
        ModEntities.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new GenericEvents());

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("ring")
                .cosmetic()
                .icon(new ResourceLocation("tempting_trinkets:slot/ring_slot")).build());
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event){
        if(event.getTab() == Tempting_TrinketsCreativeModeTab.Tempting_Trinkets_Tab.get()){
            event.accept(ModItems.Ring_Of_Neutral_Buoyancy);
            event.accept(ModItems.SIREN_SPAWN_EGG);
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
