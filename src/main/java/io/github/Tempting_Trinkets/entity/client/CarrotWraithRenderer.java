package io.github.Tempting_Trinkets.entity.client;

import io.github.Tempting_Trinkets.entity.custom.CarrotWraith;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import com.geckolib.renderer.GeoEntityRenderer;
import com.geckolib.renderer.base.GeoRenderState;

public class CarrotWraithRenderer<R extends LivingEntityRenderState & GeoRenderState> extends GeoEntityRenderer<CarrotWraith, R> {

    public CarrotWraithRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new CarrotWraithModel());
    }
}