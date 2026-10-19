package io.github.Tempting_Trinkets.compat.curios;

import io.github.Tempting_Trinkets.item.Models.TrinketModels;
import net.minecraft.client.Minecraft;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class TTItemRenderers {

    public static void register() {
        for (TrinketModels.Entry entry : TrinketModels.ALL) {
            ICurioRenderer.register(entry.item().get(), () -> new GenericItemRenderer<>(
                    entry.factory().apply(Minecraft.getInstance().getEntityModels().bakeLayer(entry.layer())),
                    entry.texture()));
        }
    }
}
