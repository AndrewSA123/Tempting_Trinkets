package io.github.Tempting_Trinkets.datagen;

import io.github.Tempting_Trinkets.TemptingTrinkets;
import io.github.Tempting_Trinkets.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output) {
        super(output, TemptingTrinkets.MODID);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.GOLDEN_CARROT_BLOCK.get());
    }

    private void blockWithItem(Block block){
        simpleBlockWithItem(block, cubeAll(block));
    }
}
