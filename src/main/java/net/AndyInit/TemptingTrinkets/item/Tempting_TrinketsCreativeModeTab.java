package net.AndyInit.TemptingTrinkets.item;

import net.AndyInit.TemptingTrinkets.Tempting_Trinkets;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Tempting_Trinkets.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Tempting_TrinketsCreativeModeTab {
    public static CreativeModeTab Tempting_Trinkets_Tab;

    @SubscribeEvent
    public static void RregisterCreativeModeTabs(CreativeModeTabEvent.Register event){
        Tempting_Trinkets_Tab = event.registerCreativeModeTab(new ResourceLocation(Tempting_Trinkets.MOD_ID, "tempting_trinkets_tab"),
                builder -> builder.icon(() -> new ItemStack(ItemBase.Ring_Of_Neutral_Buoyancy.get()))
                        .title(Component.translatable("creativemodetab.tempting_trinkets_tab")));
    }
}
