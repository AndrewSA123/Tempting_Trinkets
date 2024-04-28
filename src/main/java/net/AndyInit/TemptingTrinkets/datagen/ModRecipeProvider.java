package net.AndyInit.TemptingTrinkets.datagen;

import net.AndyInit.TemptingTrinkets.Tempting_Trinkets;
import net.AndyInit.TemptingTrinkets.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
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
