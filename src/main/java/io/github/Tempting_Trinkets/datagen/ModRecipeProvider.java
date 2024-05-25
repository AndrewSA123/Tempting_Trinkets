package io.github.Tempting_Trinkets.datagen;

import io.github.Tempting_Trinkets.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
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
    }
}
