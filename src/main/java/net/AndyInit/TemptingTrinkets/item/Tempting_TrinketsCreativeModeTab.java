package net.AndyInit.TemptingTrinkets.item;

import net.AndyInit.TemptingTrinkets.Tempting_Trinkets;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class Tempting_TrinketsCreativeModeTab {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Tempting_Trinkets.MOD_ID);

    public static RegistryObject<CreativeModeTab> Tempting_Trinkets_Tab = CREATIVE_MODE_TAB.register("tempting_trinkets_tab", () ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.Ring_Of_Neutral_Buoyancy.get()))
                    .title(Component.translatable("creativemodetab.tempting_trinkets_tab")).build());

    public static void register(IEventBus event){
        CREATIVE_MODE_TAB.register(event);
    }
}
