package io.github.Tempting_Trinkets.worldgen.structure;

import java.util.List;

public final class ModStructures {
    private ModStructures() {}

    public static final List<StructureDef> ALL = List.of(
            new StructureDef("siren_rock",
                    List.of(new StructureDef.Piece("siren_rock", 1)),
                    1, StructureDef.Step.SURFACE_STRUCTURES, StructureDef.Terrain.NONE,
                    StructureDef.Surface.OCEAN_FLOOR_WG, -4,
                    List.of("minecraft:ocean", "minecraft:lukewarm_ocean", "minecraft:warm_ocean",
                            "minecraft:cold_ocean", "minecraft:frozen_ocean"),
                    20, 6, 6, 8)
    );
}