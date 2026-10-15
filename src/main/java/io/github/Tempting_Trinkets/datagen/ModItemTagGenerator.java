// ModItemTagGenerator.java
package io.github.Tempting_Trinkets.datagen;

import io.github.Tempting_Trinkets.TemptingTrinkets;
import io.github.Tempting_Trinkets.item.ModItems;
import io.github.Tempting_Trinkets.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.data.ItemTagsProvider;   // NeoForge's, not net.minecraft.data.tags

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, TemptingTrinkets.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ModTags.Items.CARROT_ITEM)
                .add(BuiltInRegistries.ITEM.getResourceKey(Items.GOLDEN_CARROT).orElseThrow())
                .add(ModItems.SIREN_SPAWN_EGG.key())
                .add(ModItems.CARROT_WRAITH_SPAWN_EGG.key())
                .add(ModItems.Ring_Of_Fire.key())
                .add(ModItems.Ring_Of_Neutral_Buoyancy.key());
    }
}