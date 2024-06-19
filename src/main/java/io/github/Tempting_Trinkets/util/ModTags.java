package io.github.Tempting_Trinkets.util;

import io.github.Tempting_Trinkets.TemptingTrinkets;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks{
        public static final TagKey<Block> CARROT_BLOCKS = tag("carrot_blocks");

        private static TagKey<Block> tag(String name){
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(TemptingTrinkets.MODID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> CARROT_ITEM = tag("carrot_items");

        private static TagKey<Item> tag(String name){
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(TemptingTrinkets.MODID, name));
        }
    }
}
