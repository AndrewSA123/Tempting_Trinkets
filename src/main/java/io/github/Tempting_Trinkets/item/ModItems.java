package io.github.Tempting_Trinkets.item;

import io.github.Tempting_Trinkets.TemptingTrinkets;
import io.github.Tempting_Trinkets.entity.ModEntities;
import io.github.Tempting_Trinkets.item.custom.RingOfFire;
import io.github.Tempting_Trinkets.item.custom.RingOfNeutralBuoyancy;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TemptingTrinkets.MODID);

    public static final DeferredItem<Item> Ring_Of_Neutral_Buoyancy = ITEMS.registerItem(
            RingOfNeutralBuoyancy.GetName(), RingOfNeutralBuoyancy::new);

    public static final DeferredItem<Item> Ring_Of_Fire = ITEMS.registerItem(
            RingOfFire.GetName(), RingOfFire::new);

    public static final DeferredItem<Item> SIREN_SPAWN_EGG = ITEMS.registerItem("siren_spawn_egg",
            props -> new SpawnEggItem(props.spawnEgg(ModEntities.SIREN.get())));

    public static final DeferredItem<Item> CARROT_WRAITH_SPAWN_EGG = ITEMS.registerItem("carrot_wraith_spawn_egg",
            props -> new SpawnEggItem(props.spawnEgg(ModEntities.CARROT_WRAITH.get())));

    public static void register(IEventBus bus){
        ITEMS.register(bus);
    }
}
