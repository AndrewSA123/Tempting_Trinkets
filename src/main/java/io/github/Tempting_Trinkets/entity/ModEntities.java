package io.github.Tempting_Trinkets.entity;

import io.github.Tempting_Trinkets.TemptingTrinkets;
import io.github.Tempting_Trinkets.entity.custom.CarrotWraith;
import io.github.Tempting_Trinkets.entity.custom.Siren;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, TemptingTrinkets.MODID);

    public static final Supplier<EntityType<Siren>> SIREN = ENTITY_TYPES.register(Siren.getEntityName(),
            () -> EntityType.Builder.of(Siren::new, MobCategory.WATER_CREATURE)
                    .sized(1f, 1f)
                    .build(ResourceLocation.fromNamespaceAndPath(TemptingTrinkets.MODID, Siren.getEntityName()).toString()));

    public static final Supplier<EntityType<CarrotWraith>> CARROT_WRAITH = ENTITY_TYPES.register(CarrotWraith.getEntityName(),
            () -> EntityType.Builder.of(CarrotWraith::new, MobCategory.MONSTER)
                    .sized(2f, 2.5f)
                    .build(ResourceLocation.fromNamespaceAndPath(TemptingTrinkets.MODID, CarrotWraith.getEntityName()).toString()));

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}