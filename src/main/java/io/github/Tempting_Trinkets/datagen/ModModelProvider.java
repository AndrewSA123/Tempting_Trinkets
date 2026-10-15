package io.github.Tempting_Trinkets.datagen;

import io.github.Tempting_Trinkets.TemptingTrinkets;
import io.github.Tempting_Trinkets.block.ModBlocks;
import io.github.Tempting_Trinkets.item.ModItems;
import net.minecraft.client.color.item.Constant;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

public class ModModelProvider extends ModelProvider {
    public ModModelProvider(PackOutput output) {
        super(output, TemptingTrinkets.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        blockModels.createTrivialCube(ModBlocks.GOLDEN_CARROT_BLOCK.get());

        itemModels.generateFlatItem(ModItems.Ring_Of_Neutral_Buoyancy.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.Ring_Of_Fire.get(), ModelTemplates.FLAT_ITEM);

        spawnEgg(itemModels, ModItems.SIREN_SPAWN_EGG.get(), 0x1e1d1f, 0xf7f2fc);
        spawnEgg(itemModels, ModItems.CARROT_WRAITH_SPAWN_EGG.get(), 0xc48b00, 0x9d6f00);
    }

    private static void spawnEgg(ItemModelGenerators itemModels, Item egg, int baseColor, int spotColor) {
        itemModels.itemModelOutput.accept(egg, ItemModelUtils.tintedModel(
                Identifier.withDefaultNamespace("item/template_spawn_egg"),
                new Constant(baseColor), new Constant(spotColor)));
    }
}