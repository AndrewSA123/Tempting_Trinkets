package io.github.Tempting_Trinkets.worldgen.structure;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StructureDefTest {
    private static StructureDef def(String name, int spacing, int separation) {
        return new StructureDef(name, List.of(new StructureDef.Piece("siren_rock", 1)), 1,
                StructureDef.Step.SURFACE_STRUCTURES, StructureDef.Terrain.NONE,
                StructureDef.Surface.OCEAN_FLOOR_WG, 0, List.of("c:is_beach"), spacing, separation);
    }

    @Test
    void validDefPasses() { assertDoesNotThrow(def("siren_rock", 20, 6)::validate); }
    @Test void spacingMustExceedSeparation() { assertThrows(IllegalArgumentException.class, def("a", 6, 6)::validate); }
    @Test void saltIsStableAndDiffersByName() {
        assertEquals(def("siren_rock", 20, 6).salt(), def("siren_rock", 30, 8).salt());
        assertNotEquals(def("siren_rock", 20, 6).salt(), def("outpost", 20, 6).salt());
    }
    @Test void duplicateNamesRejected() {
        assertThrows(IllegalArgumentException.class, () -> StructureDef.validateAll(List.of(def("a", 20, 6), def("a", 20, 6))));
    }
    @Test void registeredStructuresAreValidAndTheirTemplatesExist() {
        StructureDef.validateAll(ModStructures.ALL);
        for (StructureDef def : ModStructures.ALL) {
            for (StructureDef.Piece piece : def.pieces()) {
                assertTrue(Files.exists(Path.of("src/main/resources/data/tempting_trinkets/structure/" + piece.template() + ".nbt")),
                        "missing template: " + piece.template());
            }
        }
    }
}
// imports: org.junit.jupiter.api.Test, static org.junit.jupiter.api.Assertions.*, java.nio.file.*, java.util.List