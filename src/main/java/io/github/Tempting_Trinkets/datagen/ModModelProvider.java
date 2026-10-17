package io.github.Tempting_Trinkets.datagen;

import io.github.Tempting_Trinkets.TemptingTrinkets;
import io.github.Tempting_Trinkets.block.ModBlocks;
import io.github.Tempting_Trinkets.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;

public class ModModelProvider extends ModelProvider {
    public ModModelProvider(PackOutput output) {
        super(output, TemptingTrinkets.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        blockModels.createTrivialCube(ModBlocks.GOLDEN_CARROT_BLOCK.get());

        itemModels.generateFlatItem(ModItems.Ring_Of_Neutral_Buoyancy.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.Ring_Of_Fire.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.SIREN_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.CARROT_WRAITH_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
    }
}