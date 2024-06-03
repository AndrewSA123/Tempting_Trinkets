package io.github.Tempting_Trinkets.datagen;

import io.github.Tempting_Trinkets.TemptingTrinkets;
import io.github.Tempting_Trinkets.block.ModBlocks;
import io.github.Tempting_Trinkets.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {

    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TemptingTrinkets.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ModTags.Blocks.CARROT_BLOCKS).add(ModBlocks.GOLDEN_CARROT_BLOCK.get()).addTag(Tags.Blocks.ORES);
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.GOLDEN_CARROT_BLOCK.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.GOLDEN_CARROT_BLOCK.get());
    }
}
