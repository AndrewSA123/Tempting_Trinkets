package io.github.Tempting_Trinkets.worldgen.structure;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public record StructureDef(String name, List<Piece> pieces, int jigsawDepth, Step step, Terrain terrain,
                           Surface surface, int heightOffset, List<String> biomeSeeds,
                           int spacing, int separation) {

    public record Piece(String template, int weight) {}
    public enum Step { SURFACE_STRUCTURES, UNDERGROUND_STRUCTURES }
    public enum Terrain { NONE, BEARD_THIN }
    public enum Surface { WORLD_SURFACE_WG, OCEAN_FLOOR_WG }

    private static final String PATH = "[a-z0-9_]+(/[a-z0-9_]+)*";

    public int salt() {
        return name.hashCode() & 0x7fffffff;
    }

    public void validate() {
        if (!name.matches(PATH)) throw new IllegalArgumentException("bad name: " + name);
        if (pieces.isEmpty()) throw new IllegalArgumentException(name + ": needs at least one piece");
        for (Piece piece : pieces) {
            if (!piece.template().matches(PATH)) throw new IllegalArgumentException(name + ": bad template " + piece.template());
            if (piece.weight() < 1) throw new IllegalArgumentException(name + ": weight must be at least 1");
        }
        if (jigsawDepth < 1 || jigsawDepth > 20) throw new IllegalArgumentException(name + ": depth must be 1-20");
        if (separation < 0 || spacing <= separation) throw new IllegalArgumentException(name + ": spacing must exceed separation");
        if (biomeSeeds.isEmpty()) throw new IllegalArgumentException(name + ": needs at least one biome seed");
        for (String seed : biomeSeeds) {
            if (!seed.matches("#?[a-z0-9_.-]+:[a-z0-9_/.-]+")) throw new IllegalArgumentException(name + ": bad biome seed " + seed);
        }
        if (heightOffset < -64 || heightOffset > 64) throw new IllegalArgumentException(name + ": height offset out of range");
    }

    public static void validateAll(List<StructureDef> defs) {
        Set<String> names = new HashSet<>();
        Set<Integer> salts = new HashSet<>();
        for (StructureDef def : defs) {
            def.validate();
            if (!names.add(def.name())) throw new IllegalArgumentException("duplicate name: " + def.name());
            if (!salts.add(def.salt())) throw new IllegalArgumentException("salt collision: " + def.name());
        }
    }
}