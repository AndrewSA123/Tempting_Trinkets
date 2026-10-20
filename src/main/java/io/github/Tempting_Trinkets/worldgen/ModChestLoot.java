package io.github.Tempting_Trinkets.worldgen;

import io.github.Tempting_Trinkets.TemptingTrinkets;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;

public final class ModChestLoot {
    private ModChestLoot() {}

    public static final ResourceKey<LootTable> SIREN_ROCK = ResourceKey.create(Registries.LOOT_TABLE,
            Identifier.fromNamespaceAndPath(TemptingTrinkets.MODID, "chests/siren_rock"));

    public static void generate(LootTableSubProvider.Context context) {
        context.accept(SIREN_ROCK, LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(Items.GOLDEN_CARROT))
                        .add(LootItem.lootTableItem(Items.GOLD_NUGGET))
                        .add(LootItem.lootTableItem(Items.PRISMARINE_SHARD))));
    }
}