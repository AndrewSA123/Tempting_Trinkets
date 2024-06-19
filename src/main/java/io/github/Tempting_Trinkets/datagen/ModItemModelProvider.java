package io.github.Tempting_Trinkets.datagen;

import io.github.Tempting_Trinkets.TemptingTrinkets;
import io.github.Tempting_Trinkets.item.custom.RingOfFire;
import io.github.Tempting_Trinkets.item.custom.RingOfNeutralBuoyancy;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TemptingTrinkets.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(RingOfNeutralBuoyancy.GetName());
        simpleItem(RingOfFire.GetName());
        withExistingParent("siren_spawn_egg", mcLoc("item/template_spawn_egg"));
        withExistingParent("carrot_wraith_spawn_egg", mcLoc("item/template_spawn_egg"));
    }

    private ItemModelBuilder simpleItem(String itemName){
        return withExistingParent(itemName,
                ResourceLocation.fromNamespaceAndPath(TemptingTrinkets.MODID, "item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(TemptingTrinkets.MODID, "item/" + itemName));
    }

    private ItemModelBuilder handheldItem(String itemName){
        return withExistingParent(itemName, modLoc("item/handheld"))
                .texture("layer0", modLoc("item/" + itemName));
    }
}
