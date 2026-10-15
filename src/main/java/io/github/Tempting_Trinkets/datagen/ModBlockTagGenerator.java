// ModBlockTagGenerator.java
package io.github.Tempting_Trinkets.datagen;

import io.github.Tempting_Trinkets.TemptingTrinkets;
import io.github.Tempting_Trinkets.block.ModBlocks;
import io.github.Tempting_Trinkets.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {

    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, TemptingTrinkets.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ModTags.Blocks.CARROT_BLOCKS).add(ModBlocks.GOLDEN_CARROT_BLOCK.key()).addTag(Tags.Blocks.STORAGE_BLOCKS);
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.GOLDEN_CARROT_BLOCK.key());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.GOLDEN_CARROT_BLOCK.key());
    }
}