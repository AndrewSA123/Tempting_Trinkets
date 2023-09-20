package net.AndyInit.TemptingTrinkets.item;

import net.AndyInit.TemptingTrinkets.Tempting_Trinkets;
import net.AndyInit.TemptingTrinkets.entity.ModEntities;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Tempting_Trinkets.MOD_ID);

    public static final RegistryObject<Item> Ring_Of_Neutral_Buoyancy = ITEMS.register(RingOfNeutralBuoyancy.GetItemName(),
            () -> new RingOfNeutralBuoyancy(new Item.Properties()));

    public static final RegistryObject<Item> Ring_Of_Fire = ITEMS.register(RingOfFire.GetItemName(),
            () -> new RingOfFire(new Item.Properties()));

    public static final RegistryObject<Item> SIREN_SPAWN_EGG = ITEMS.register("siren_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.SIREN, 0x1e1d1f, 0xf7f2fc, new Item.Properties()));

    public static void register(IEventBus bus){
        ITEMS.register(bus);
    }
}
