package io.github.Tempting_Trinkets;

import io.github.Tempting_Trinkets.block.ModBlocks;
import io.github.Tempting_Trinkets.item.Models.RingOfNeutralBuoyancyModel;
import io.github.Tempting_Trinkets.item.client.item_renderers.RingOfNeutralBuoyancyRenderer;
import io.github.Tempting_Trinkets.item.custom.RingOfNeutralBuoyancy;
import net.minecraft.client.renderer.entity.EntityRenderers;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import io.github.Tempting_Trinkets.entity.ModEntities;
import io.github.Tempting_Trinkets.entity.client.SirenRenderer;
import io.github.Tempting_Trinkets.item.ModItems;
import io.github.Tempting_Trinkets.item.Tempting_TrinketsCreativeModeTab;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(TemptingTrinkets.MODID)
public class TemptingTrinkets
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "tempting_trinkets";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public TemptingTrinkets(IEventBus modEventBus, ModContainer modContainer)
    {
        Tempting_TrinketsCreativeModeTab.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEntities.register(modEventBus);
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if(event.getTab() == Tempting_TrinketsCreativeModeTab.Tempting_Trinkets_Tab.get()){
            event.accept(ModItems.Ring_Of_Neutral_Buoyancy.get());
            event.accept(ModItems.Ring_Of_Fire.get());
            event.accept(ModItems.SIREN_SPAWN_EGG.get());
            event.accept(ModBlocks.GOLDEN_CARROT_BLOCK.get());
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        LOGGER.info("Tempting Trinkets Server Initialising");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());

            CuriosRendererRegistry.register(ModItems.Ring_Of_Neutral_Buoyancy.get(), () -> new RingOfNeutralBuoyancyRenderer(Minecraft.getInstance().getEntityModels().bakeLayer(RingOfNeutralBuoyancyModel.LAYER_LOCATION), RingOfNeutralBuoyancy.GetTextureLocation()));

            EntityRenderers.register(ModEntities.SIREN.get(), SirenRenderer::new);
        }
    }
}
