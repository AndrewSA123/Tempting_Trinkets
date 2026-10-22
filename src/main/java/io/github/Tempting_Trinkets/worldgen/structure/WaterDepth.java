package io.github.Tempting_Trinkets.worldgen.structure;

final class WaterDepth {
    private WaterDepth() {}

    static boolean inRange(int seaLevel, int[] firstFree, int min, int max) {
        for (int y : firstFree) {
            int depth = seaLevel - y;
            if (depth < min || depth > max) return false;
        }
        return true;
    }
}