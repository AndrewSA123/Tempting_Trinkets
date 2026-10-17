package io.github.Tempting_Trinkets.compat.curios;

import io.github.Tempting_Trinkets.item.ModItems;
import io.github.Tempting_Trinkets.item.Models.RingOfNeutralBuoyancyModel;
import io.github.Tempting_Trinkets.item.custom.RingOfNeutralBuoyancy;
import net.minecraft.client.Minecraft;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class TTItemRenderers {

    public static void register(){
        renderCurios();
    }

    private static void renderCurios(){
        ICurioRenderer.register(ModItems.Ring_Of_Neutral_Buoyancy.get(), () ->
                new GenericItemRenderer<>(
                        new RingOfNeutralBuoyancyModel(Minecraft.getInstance().getEntityModels().bakeLayer(RingOfNeutralBuoyancyModel.LAYER_LOCATION)),
                        RingOfNeutralBuoyancy.GetTextureLocation()));
    }
}
