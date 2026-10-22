package io.github.Tempting_Trinkets.worldgen.structure;

import io.github.Tempting_Trinkets.TemptingTrinkets;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModStructureTypes {
    private ModStructureTypes() {}

    private static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES =
            DeferredRegister.create(Registries.STRUCTURE_TYPE, TemptingTrinkets.MODID);

    public static final DeferredHolder<StructureType<?>, StructureType<WaterJigsawStructure>> WATER_JIGSAW =
            STRUCTURE_TYPES.register("water_jigsaw", () -> () -> WaterJigsawStructure.CODEC);

    public static void register(IEventBus bus) {
        STRUCTURE_TYPES.register(bus);
    }
}