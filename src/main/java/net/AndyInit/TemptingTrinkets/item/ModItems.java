package net.AndyInit.TemptingTrinkets.item;

import net.AndyInit.TemptingTrinkets.Tempting_Trinkets;
import net.AndyInit.TemptingTrinkets.entity.ModEntities;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.createItems(Tempting_Trinkets.MOD_ID);

    public static final Supplier<Item> Ring_Of_Neutral_Buoyancy = ITEMS.register(RingOfNeutralBuoyancy.GetItemName(),
            () -> new RingOfNeutralBuoyancy(new Item.Properties()));

    public static final Supplier<Item> Ring_Of_Fire = ITEMS.register(RingOfFire.GetItemName(),
            () -> new RingOfFire(new Item.Properties()));

    public static final Supplier<Item> SIREN_SPAWN_EGG = ITEMS.register("siren_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.SIREN, 0x1e1d1f, 0xf7f2fc, new Item.Properties()));

    public static void register(IEventBus bus){
        ITEMS.register(bus);
    }
}
