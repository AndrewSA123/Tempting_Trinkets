package io.github.Tempting_Trinkets.datagen;

import io.github.Tempting_Trinkets.TemptingTrinkets;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.data.event.GatherDataRegistryEntriesEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = TemptingTrinkets.MODID)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherRegistries(GatherDataRegistryEntriesEvent event) {
        event.gatherFor(TemptingTrinkets.MODID)
                .recipe(ModRecipeProvider::new)
                .lootTable(new LootTableProvider.SubProviderEntry(
                        ModBlockLootTablesProvider::new, LootContextParamSets.BLOCK));
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        event.createProvider(ModModelProvider::new);
        event.createProvider(ModBlockTagGenerator::new);
        event.createProvider(ModItemTagGenerator::new);
    }
}
