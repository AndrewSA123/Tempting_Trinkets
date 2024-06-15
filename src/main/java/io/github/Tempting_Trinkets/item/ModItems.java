package io.github.Tempting_Trinkets.item;

import io.github.Tempting_Trinkets.TemptingTrinkets;
import io.github.Tempting_Trinkets.entity.ModEntities;
import io.github.Tempting_Trinkets.item.custom.RingOfFire;
import io.github.Tempting_Trinkets.item.custom.RingOfNeutralBuoyancy;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.createItems(TemptingTrinkets.MODID);

    public static final Supplier<Item> Ring_Of_Neutral_Buoyancy = ITEMS.register(RingOfNeutralBuoyancy.GetName(),
            RingOfNeutralBuoyancy::new);

    public static final Supplier<Item> Ring_Of_Fire = ITEMS.register(RingOfFire.GetName(),
            RingOfFire::new);

    public static final Supplier<Item> SIREN_SPAWN_EGG = ITEMS.register("siren_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.SIREN, 0x1e1d1f, 0xf7f2fc, new Item.Properties()));

    public static void register(IEventBus bus){
        ITEMS.register(bus);
    }
}
