package net.AndyInit.TemptingTrinkets;

import com.mojang.logging.LogUtils;
import net.AndyInit.TemptingTrinkets.entity.ModEntities;
import net.AndyInit.TemptingTrinkets.entity.client.SirenRenderer;
import net.AndyInit.TemptingTrinkets.item.ModItems;
import net.AndyInit.TemptingTrinkets.item.Tempting_TrinketsCreativeModeTab;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
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
    private static final Logger LOGGER = LogUtils.getLogger();

    public Tempting_Trinkets()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModEntities.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("Ring").build());
    }

    private void addCreative(CreativeModeTabEvent.BuildContents event){
        if(event.getTab() == Tempting_TrinketsCreativeModeTab.Tempting_Trinkets_Tab){
            event.accept(ModItems.Ring_Of_Neutral_Buoyancy);
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
