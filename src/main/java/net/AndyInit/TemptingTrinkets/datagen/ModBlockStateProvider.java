package net.AndyInit.TemptingTrinkets.datagen;

import net.AndyInit.TemptingTrinkets.Tempting_Trinkets;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Tempting_Trinkets.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
    }

    private void blockWithItem(Supplier<Block> block){
        simpleBlockWithItem(block.get(), cubeAll(block.get()));
    }
}
