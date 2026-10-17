package io.github.Tempting_Trinkets.util;

import com.mojang.blaze3d.platform.InputConstants;
import io.github.Tempting_Trinkets.TemptingTrinkets;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;

public class ModBindings {
    public static final KeyMapping.Category CATEGORY =
            new KeyMapping.Category(Identifier.fromNamespaceAndPath(TemptingTrinkets.MODID, "bindings"));

    public static final KeyMapping RING_OF_FIRE_MAPPING = new KeyMapping(
            "key." + TemptingTrinkets.MODID + ".ring_of_fire",
            InputConstants.KEY_V,
            CATEGORY
    );
}
