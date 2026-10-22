package io.github.Tempting_Trinkets.datagen;

import io.github.Tempting_Trinkets.TemptingTrinkets;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.UniformContainerBase;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

public final class ModChestLoot {
    private ModChestLoot() {}

    public static final ResourceKey<LootTable> SIREN_ROCK = ResourceKey.create(Registries.LOOT_TABLE,
            Identifier.fromNamespaceAndPath(TemptingTrinkets.MODID, "chests/siren_rock"));

    public static void generate(LootTableSubProvider.Context context) {
        context.accept(SIREN_ROCK, LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ContextIntProviders.between(3, 6))
                        .add(stack(Items.GOLDEN_CARROT, 1, 8))
                        .add(stack(Items.GOLD_NUGGET, 0, 18))
                        .add(stack(Items.PRISMARINE_SHARD, 1, 7)))
                .withPool(LootPool.lootPool()
                        .setRolls(ContextIntProviders.binomial(1, 0.5f))
                        .add(LootItem.lootTableItem(Items.HEART_OF_THE_SEA))));
    }

    private static UniformContainerBase.Builder<?> stack(ItemLike item, int min, int max) {
        return LootItem.lootTableItem(item)
                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(min, max)));
    }
}