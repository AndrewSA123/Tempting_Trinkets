package io.github.Tempting_Trinkets;

import io.github.Tempting_Trinkets.block.ModBlocks;
import io.github.Tempting_Trinkets.entity.client.CarrotWraithRenderer;
import io.github.Tempting_Trinkets.item.client.TTItemRenderers;
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
import top.theillusivec4.curios.api.CuriosApi;

@Mod(TemptingTrinkets.MODID)
public class TemptingTrinkets
{
    public static final String MODID = "tempting_trinkets";
    public static final String KEYCATEGORY = "key.categories.tempting_trinkets.bindings";
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
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

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

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());

            TTItemRenderers.register();
            EntityRenderers.register(ModEntities.SIREN.get(), SirenRenderer::new);
            EntityRenderers.register(ModEntities.CARROT_WRAITH.get(), CarrotWraithRenderer::new);
        }
    }
}
