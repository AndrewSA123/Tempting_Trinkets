package io.github.Tempting_Trinkets.datagen;

import io.github.Tempting_Trinkets.TemptingTrinkets;
import io.github.Tempting_Trinkets.item.ModItems;
import io.github.Tempting_Trinkets.item.RingOfFire;
import io.github.Tempting_Trinkets.item.RingOfNeutralBuoyancy;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TemptingTrinkets.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(RingOfNeutralBuoyancy.GetItemName());
        simpleItem(RingOfFire.GetItemName());
        withExistingParent("siren_spawn_egg", mcLoc("item/template_spawn_egg"));
    }

    private ItemModelBuilder simpleItem(String itemName){
        return withExistingParent(itemName,
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(TemptingTrinkets.MODID, String.format("item/%s", itemName)));
    }

    private ItemModelBuilder handheldItem(Item item){
        return withExistingParent(item.toString(), modLoc("item/handheld"))
                .texture("layer0", modLoc("item/" + item.toString()));
                //new ResourceLocation("item/handheld")).texture("layer0",
                //new ResourceLocation(Tempting_Trinkets.MOD_ID, "item/" + item.getId().getPath()));
    }
}
