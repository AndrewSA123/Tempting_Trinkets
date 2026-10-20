package io.github.Tempting_Trinkets.datagen;

import io.github.Tempting_Trinkets.TemptingTrinkets;
import io.github.Tempting_Trinkets.worldgen.structure.ModStructures;
import io.github.Tempting_Trinkets.worldgen.structure.StructureDef;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModStructureProvider implements DataProvider {
    private final PackOutput.PathProvider pools, structures, sets, biomeTags;

    public ModStructureProvider(PackOutput output) {
        pools = output.createPathProvider(PackOutput.Target.DATA_PACK, "worldgen/template_pool");
        structures = output.createPathProvider(PackOutput.Target.DATA_PACK, "worldgen/structure");
        sets = output.createPathProvider(PackOutput.Target.DATA_PACK, "worldgen/structure_set");
        biomeTags = output.createPathProvider(PackOutput.Target.DATA_PACK, "tags/worldgen/biome/has_structure");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        StructureDef.validateAll(ModStructures.ALL);
        List<CompletableFuture<?>> writes = new ArrayList<>();
        for (StructureDef def : ModStructures.ALL) {
            Identifier id = Identifier.fromNamespaceAndPath(TemptingTrinkets.MODID, def.name());
            writes.add(DataProvider.saveStable(cache, StructureJson.pool(def), pools.json(id)));
            writes.add(DataProvider.saveStable(cache, StructureJson.structure(def), structures.json(id)));
            writes.add(DataProvider.saveStable(cache, StructureJson.structureSet(def), sets.json(id)));
            writes.add(DataProvider.saveStable(cache, StructureJson.biomeTag(def), biomeTags.json(id)));
        }
        return CompletableFuture.allOf(writes.toArray(CompletableFuture[]::new));
    }

    @Override
    public String getName() {
        return "Structures";
    }
}