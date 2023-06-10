package net.AndyInit.TemptingTrinkets.datagen;

import net.AndyInit.TemptingTrinkets.Tempting_Trinkets;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Tempting_Trinkets.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(true, new ModRecipeProvider(output));
        generator.addProvider(true, ModLootTableProvider.create(output));
        generator.addProvider(true, new ModBlockStateProvider(output, existingFileHelper));
        generator.addProvider(true, new ModItemModelProvider(output, existingFileHelper));
    }
}
