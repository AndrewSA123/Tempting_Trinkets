package net.AndyInit.TemptingTrinkets.entity;

import net.AndyInit.TemptingTrinkets.Tempting_Trinkets;
import net.AndyInit.TemptingTrinkets.entity.custom.Siren;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, Tempting_Trinkets.MOD_ID);

    public static final Supplier<EntityType<Siren>> SIREN = ENTITY_TYPES.register("siren",
            () -> EntityType.Builder.of(Siren::new, MobCategory.WATER_CREATURE)
                    .sized(1f, 1f)
            .build(new ResourceLocation(Tempting_Trinkets.MOD_ID, "siren").toString()));

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
