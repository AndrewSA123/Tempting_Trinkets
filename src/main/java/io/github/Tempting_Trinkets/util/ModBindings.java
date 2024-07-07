package io.github.Tempting_Trinkets.util;

import com.mojang.blaze3d.platform.InputConstants;
import io.github.Tempting_Trinkets.TemptingTrinkets;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class ModBindings {
    public static final KeyMapping RING_OF_FIRE_MAPPING = new KeyMapping(
                    "key." + TemptingTrinkets.MODID + ".ring_of_fire",
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_G,
                    TemptingTrinkets.KEYCATEGORY
    );
}
