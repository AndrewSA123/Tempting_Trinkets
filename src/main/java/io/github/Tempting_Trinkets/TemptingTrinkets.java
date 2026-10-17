package io.github.Tempting_Trinkets;

import io.github.Tempting_Trinkets.block.ModBlocks;
import io.github.Tempting_Trinkets.compat.curios.CuriosCompat;
import io.github.Tempting_Trinkets.entity.client.CarrotWraithRenderer;
import io.github.Tempting_Trinkets.compat.curios.TTItemRenderers;
import io.github.Tempting_Trinkets.item.Models.RingOfNeutralBuoyancyModel;
import io.github.Tempting_Trinkets.network.FireballPayload;
import io.github.Tempting_Trinkets.util.ModBindings;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
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

@Mod(TemptingTrinkets.MODID)
public class TemptingTrinkets
{
    public static final String MODID = "tempting_trinkets";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public TemptingTrinkets(IEventBus modEventBus, ModContainer modContainer)
    {
        Tempting_TrinketsCreativeModeTab.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEntities.register(modEventBus);
        modEventBus.addListener(this::commonSetup);

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
        modContainer.registerConfig(ModConfig.Type.LOCAL, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        if (ModList.get().isLoaded("curios")) {
            CuriosCompat.register();
        }
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if(event.getTab() == Tempting_TrinketsCreativeModeTab.Tempting_Trinkets_Tab.get()){
            event.accept(ModItems.Ring_Of_Neutral_Buoyancy.get());
            event.accept(ModItems.Ring_Of_Fire.get());
            event.accept(ModItems.SIREN_SPAWN_EGG.get());
            event.accept(ModItems.CARROT_WRAITH_SPAWN_EGG.get());
            event.accept(ModBlocks.GOLDEN_CARROT_BLOCK.get());
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        LOGGER.info("Tempting Trinkets Server Initialising");
    }

    @EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());

            if (ModList.get().isLoaded("curios")) {
                TTItemRenderers.register();
            }
        }

        @SubscribeEvent
        public static void clientTick(ClientTickEvent.Post event) {
            while (ModBindings.RING_OF_FIRE_MAPPING.consumeClick()) {
                if (Minecraft.getInstance().player != null) {
                    ClientPacketDistributor.sendToServer(FireballPayload.INSTANCE);
                }
            }
        }

        @SubscribeEvent
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event){
            event.registerEntityRenderer(ModEntities.SIREN.get(), SirenRenderer::new);
            event.registerEntityRenderer(ModEntities.CARROT_WRAITH.get(), CarrotWraithRenderer::new);
        }

        @SubscribeEvent
        public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event){
            event.registerLayerDefinition(RingOfNeutralBuoyancyModel.LAYER_LOCATION, RingOfNeutralBuoyancyModel::createBodyLayer);
        }

        @SubscribeEvent
        public static void registerBindings(RegisterKeyMappingsEvent event) {
            event.registerCategory(ModBindings.CATEGORY);
            event.register(ModBindings.RING_OF_FIRE_MAPPING);
        }
    }
}
