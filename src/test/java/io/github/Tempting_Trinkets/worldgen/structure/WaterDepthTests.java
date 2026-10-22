package io.github.Tempting_Trinkets.worldgen.structure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WaterDepthTest {
    private static boolean ok(int... firstFree) { return WaterDepth.inRange(63, firstFree, 2, 7); }

    @Test void landIsRejected() { assertFalse(ok(64)); }
    @Test void atSeaLevelIsRejected() { assertFalse(ok(63)); }
    @Test void tooShallowIsRejected() { assertFalse(ok(62)); }
    @Test void boundariesAreInclusive() { assertTrue(ok(61)); assertTrue(ok(56)); }
    @Test void midRangeIsAccepted() { assertTrue(ok(58)); }
    @Test void tooDeepIsRejected() { assertFalse(ok(55)); }
    @Test void oneBadPointRejectsTheLot() { assertFalse(ok(58, 64, 58)); }
}