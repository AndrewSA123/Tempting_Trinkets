package net.AndyInit.TemptingTrinkets.entity;

import net.AndyInit.TemptingTrinkets.Tempting_Trinkets;
import net.AndyInit.TemptingTrinkets.entity.custom.Siren;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Tempting_Trinkets.MOD_ID);

    public static final RegistryObject<EntityType<Siren>> SIREN = ENTITY_TYPES.register("siren",
            () -> EntityType.Builder.of(Siren::new, MobCategory.WATER_CREATURE)
                    .sized(1f, 1f)
            .build(new ResourceLocation(Tempting_Trinkets.MOD_ID, "siren").toString()));

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
