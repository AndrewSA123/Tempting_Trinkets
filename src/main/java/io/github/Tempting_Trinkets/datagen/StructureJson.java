package io.github.Tempting_Trinkets.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.github.Tempting_Trinkets.TemptingTrinkets;
import io.github.Tempting_Trinkets.worldgen.structure.StructureDef;

public final class StructureJson {
    private StructureJson() {}

    public static JsonObject pool(StructureDef def) {
        JsonObject pool = new JsonObject();
        pool.addProperty("fallback", "minecraft:empty");
        JsonArray elements = new JsonArray();
        for (StructureDef.Piece piece : def.pieces()) {
            JsonObject element = new JsonObject();
            element.addProperty("element_type", "minecraft:single_pool_element");
            element.addProperty("location", TemptingTrinkets.MODID + ":" + piece.template());
            element.addProperty("processors", "minecraft:empty");
            element.addProperty("projection", "rigid");
            JsonObject entry = new JsonObject();
            entry.addProperty("weight", piece.weight());
            entry.add("element", element);
            elements.add(entry);
        }
        pool.add("elements", elements);
        return pool;
    }

    public static JsonObject structure(StructureDef def) {
        JsonObject json = new JsonObject();
        json.addProperty("type", "minecraft:jigsaw");
        json.addProperty("biomes", "#" + TemptingTrinkets.MODID + ":has_structure/" + def.name());
        json.addProperty("step", def.step().name().toLowerCase());
        json.add("spawn_overrides", new JsonObject());
        json.addProperty("terrain_adaptation", def.terrain().name().toLowerCase());
        json.addProperty("start_pool", TemptingTrinkets.MODID + ":" + def.name());
        json.addProperty("size", def.jigsawDepth());
        JsonObject startHeight = new JsonObject();
        startHeight.addProperty("absolute", def.heightOffset());     // an offset from the heightmap when projecting
        json.add("start_height", startHeight);
        json.addProperty("project_start_to_heightmap", def.surface().name());
        json.addProperty("max_distance_from_center", 80);
        json.addProperty("use_expansion_hack", false);
        return json;
    }

    public static JsonObject structureSet(StructureDef def) {
        JsonObject placement = new JsonObject();
        placement.addProperty("type", "minecraft:random_spread");
        placement.addProperty("spacing", def.spacing());
        placement.addProperty("separation", def.separation());
        placement.addProperty("salt", def.salt());
        JsonObject entry = new JsonObject();
        entry.addProperty("structure", TemptingTrinkets.MODID + ":" + def.name());
        entry.addProperty("weight", 1);
        JsonArray structures = new JsonArray();
        structures.add(entry);
        JsonObject set = new JsonObject();
        set.add("placement", placement);
        set.add("structures", structures);
        return set;
    }

    public static JsonObject biomeTag(StructureDef def) {
        JsonArray values = new JsonArray();
        for (String seed : def.biomeSeeds()) {
            values.add(seed);        // "#c:is_beach" stays a tag reference
        }
        JsonObject tag = new JsonObject();
        tag.addProperty("replace", false);
        tag.add("values", values);
        return tag;
    }
}