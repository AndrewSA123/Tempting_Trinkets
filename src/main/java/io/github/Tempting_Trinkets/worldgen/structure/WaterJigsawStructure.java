package io.github.Tempting_Trinkets.worldgen.structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;

import java.util.Optional;

public final class WaterJigsawStructure extends Structure {
    public static final MapCodec<WaterJigsawStructure> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
            JigsawStructure.CODEC.forGetter(s -> s.jigsaw),
            Codec.INT.fieldOf("min_water_depth").forGetter(s -> s.minDepth),
            Codec.INT.fieldOf("max_water_depth").forGetter(s -> s.maxDepth)
    ).apply(i, WaterJigsawStructure::new));

    private static final int FOOTPRINT_RADIUS = 3;   // siren_rock is 6x5; covers either rotation

    private final JigsawStructure jigsaw;
    private final int minDepth;
    private final int maxDepth;

    private WaterJigsawStructure(JigsawStructure jigsaw, int minDepth, int maxDepth) {
        super(jigsaw.modifiableStructureInfo().getOriginalStructureInfo().structureSettings());
        this.jigsaw = jigsaw;
        this.minDepth = minDepth;
        this.maxDepth = maxDepth;
    }

    @Override
    protected Optional<GenerationStub> findGenerationPoint(GenerationContext context) {
        return jigsaw.findGenerationPoint(context).filter(stub -> inWater(context, stub.position()));
    }

    private boolean inWater(GenerationContext context, BlockPos center) {
        int r = FOOTPRINT_RADIUS;
        int[][] offsets = {{0, 0}, {-r, -r}, {-r, r}, {r, -r}, {r, r}};
        int[] firstFree = new int[offsets.length];
        for (int k = 0; k < offsets.length; k++) {
            firstFree[k] = context.chunkGenerator().getFirstFreeHeight(
                    center.getX() + offsets[k][0], center.getZ() + offsets[k][1],
                    Heightmap.Types.OCEAN_FLOOR_WG, context.heightAccessor(), context.randomState());
        }
        return WaterDepth.inRange(context.chunkGenerator().getSeaLevel(), firstFree, minDepth, maxDepth);
    }

    @Override
    public StructureType<?> type() {
        return ModStructureTypes.WATER_JIGSAW.get();
    }
}