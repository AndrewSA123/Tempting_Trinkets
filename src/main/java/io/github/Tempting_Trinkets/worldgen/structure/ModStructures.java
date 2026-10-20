package io.github.Tempting_Trinkets.worldgen.structure;

import java.util.List;

public final class ModStructures {
    private ModStructures() {}

    public static final List<StructureDef> ALL = List.of(
            new StructureDef("siren_rock",
                    List.of(new StructureDef.Piece("siren_rock", 1)),
                    1, StructureDef.Step.SURFACE_STRUCTURES, StructureDef.Terrain.NONE,
                    StructureDef.Surface.OCEAN_FLOOR_WG, -4,
                    List.of("#c:is_beach"),
                    20, 6)
    );
}