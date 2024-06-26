package io.github.Tempting_Trinkets.block;

import io.github.Tempting_Trinkets.TemptingTrinkets;
import io.github.Tempting_Trinkets.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(TemptingTrinkets.MODID);

    public static final Supplier<Block> GOLDEN_CARROT_BLOCK = registerBlock(GoldenCarrotBlock.getBlockName(),
            GoldenCarrotBlock::new,
            BlockBehaviour.Properties.of()
                    .destroyTime(5f)
                    .lightLevel(value -> 3));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, ? extends T> func, BlockBehaviour.Properties props){
        DeferredBlock<T> toReturn = BLOCKS.register(name, () -> func.apply(props));
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block>Supplier<Item> registerBlockItem(String name, DeferredBlock<T> block){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus event){
        BLOCKS.register(event);
    }
}