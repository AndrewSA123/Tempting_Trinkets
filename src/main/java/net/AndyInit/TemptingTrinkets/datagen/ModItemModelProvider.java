package net.AndyInit.TemptingTrinkets.datagen;

import net.AndyInit.TemptingTrinkets.Tempting_Trinkets;
import net.AndyInit.TemptingTrinkets.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Tempting_Trinkets.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.Ring_Of_Neutral_Buoyancy);
        simpleItem(ModItems.Ring_Of_Fire);
        withExistingParent(ModItems.SIREN_SPAWN_EGG.get().toString(), mcLoc("item/template_spawn_egg"));
    }

    private ItemModelBuilder simpleItem(Supplier<Item> item){
        return withExistingParent(item.get().toString(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Tempting_Trinkets.MOD_ID, "item/" + item.get().toString()));
    }

    private ItemModelBuilder handheldItem(Supplier<Item> item){
        return withExistingParent(item.get().toString(), modLoc("item/handheld"))
                .texture("layer0", modLoc("item/" + item.get().toString()));
                //new ResourceLocation("item/handheld")).texture("layer0",
                //new ResourceLocation(Tempting_Trinkets.MOD_ID, "item/" + item.getId().getPath()));
    }
}
