package io.github.Tempting_Trinkets.item.client;

import io.github.Tempting_Trinkets.item.ModItems;
import io.github.Tempting_Trinkets.item.Models.RingOfNeutralBuoyancyModel;
import io.github.Tempting_Trinkets.item.custom.RingOfNeutralBuoyancy;
import net.minecraft.client.Minecraft;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

public class TTItemRenderers {

    public static void register(){
        renderCurios();
    }

    private static void renderCurios(){
        CuriosRendererRegistry.register(ModItems.Ring_Of_Neutral_Buoyancy.get(), () ->
                new GenericItemRenderer(
                        new RingOfNeutralBuoyancyModel(Minecraft.getInstance().getEntityModels().bakeLayer(RingOfNeutralBuoyancyModel.LAYER_LOCATION)),
                        RingOfNeutralBuoyancy.GetTextureLocation()));
    }
}
