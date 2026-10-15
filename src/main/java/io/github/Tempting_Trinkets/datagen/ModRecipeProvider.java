package io.github.Tempting_Trinkets.datagen;

import io.github.Tempting_Trinkets.TemptingTrinkets;
import io.github.Tempting_Trinkets.block.ModBlocks;
import io.github.Tempting_Trinkets.item.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.*;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(BootstrapContext<Recipe<?>> recipeOutput, BootstrapContext<Advancement> advancementOutput) {
        super(recipeOutput, advancementOutput);
    }

    private static ResourceKey<Recipe<?>> recipeKey(String path) {
        return ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath(TemptingTrinkets.MODID, path));
    }

    @Override
    protected void buildRecipes() {
        this.shaped(RecipeCategory.MISC, ModItems.Ring_Of_Fire.get())
                .pattern(" # ")
                .pattern("# #")
                .pattern(" # ")
                .define('#', Items.BLAZE_ROD)
                .unlockedBy(getHasName(Items.BLAZE_ROD), has(Items.BLAZE_ROD))
                .save(output, recipeKey("ring_of_fire"));

        this.shaped(RecipeCategory.MISC, ModItems.Ring_Of_Neutral_Buoyancy.get())
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
                .save(output, recipeKey("ring_of_neutral_buoyancy"));

        this.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GOLDEN_CARROT_BLOCK.get())
                .requires(Items.GOLDEN_CARROT, 9)
                .unlockedBy(getHasName(Items.GOLDEN_CARROT), has(Items.GOLDEN_CARROT))
                .save(output, recipeKey("golden_carrot_block"));

        this.shapeless(RecipeCategory.FOOD, Items.GOLDEN_CARROT, 9)
                .requires(ModBlocks.GOLDEN_CARROT_BLOCK.get(), 1)
                .unlockedBy(getHasName(Items.GOLDEN_CARROT), has(Items.GOLDEN_CARROT))
                .save(output, recipeKey("golden_carrot_from_block"));

        this.shaped(RecipeCategory.FOOD, Items.GOLDEN_CARROT)
                .pattern("###")
                .pattern("#C#")
                .pattern("###")
                .define('#', Items.GOLD_NUGGET)
                .define('C', Items.CARROT)
                .unlockedBy(getHasName(Items.CARROT), has(Items.CARROT))
                .save(output, recipeKey("golden_carrot"));
    }
}
