package io.github.Tempting_Trinkets.datagen;

import io.github.Tempting_Trinkets.TemptingTrinkets;
import io.github.Tempting_Trinkets.block.GoldenCarrotBlock;
import io.github.Tempting_Trinkets.block.ModBlocks;
import io.github.Tempting_Trinkets.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> provider) {
        super(pOutput, provider);
    }

    @Override
    protected void buildRecipes(RecipeOutput pWriter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.Ring_Of_Fire.get())
                .pattern(" # ")
                .pattern("# #")
                .pattern(" # ")
                .define('#', Items.BLAZE_ROD)
                .unlockedBy(getHasName(Items.BLAZE_ROD), has(Items.BLAZE_ROD))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.Ring_Of_Neutral_Buoyancy.get())
                .pattern("pup")
                .pattern("trt")
                .pattern("bwb")
                .define('p', Items.PHANTOM_MEMBRANE)
                .define('u', Items.PUMPKIN_SEEDS)
                .define('t', Items.BONE)
                .define('r', ModItems.Ring_Of_Fire.get())
                .define('b', Items.GLOW_BERRIES)
                .define('w', Items.MELON_SEEDS)
                .unlockedBy(getHasName(ModItems.Ring_Of_Fire.get()), has(ModItems.Ring_Of_Fire.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GOLDEN_CARROT_BLOCK.get())
                .requires(Items.GOLDEN_CARROT, 9)
                .unlockedBy(getHasName(Items.GOLDEN_CARROT), has(Items.GOLDEN_CARROT))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, Items.GOLDEN_CARROT, 9)
                .requires(ModBlocks.GOLDEN_CARROT_BLOCK.get(), 1)
                .unlockedBy(getHasName(Items.GOLDEN_CARROT), has(Items.GOLDEN_CARROT))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, Items.GOLDEN_CARROT)
                .pattern("###")
                .pattern("#C#")
                .pattern("###")
                .define('#', Items.GOLD_NUGGET)
                .define('C', Items.CARROT)
                .unlockedBy(getHasName(Items.CARROT), has(Items.CARROT))
                .save(pWriter, new ResourceLocation(TemptingTrinkets.MODID, "golden_carrot"));
    }
}
