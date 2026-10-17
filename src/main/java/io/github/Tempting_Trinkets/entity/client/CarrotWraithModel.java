package io.github.Tempting_Trinkets.entity.client;

import io.github.Tempting_Trinkets.TemptingTrinkets;
import io.github.Tempting_Trinkets.entity.custom.CarrotWraith;
import net.minecraft.resources.Identifier;
import com.geckolib.model.GeoModel;
import com.geckolib.renderer.base.GeoRenderState;

public class CarrotWraithModel extends GeoModel<CarrotWraith> {
    @Override
    public Identifier getModelResource(GeoRenderState renderState) {
        return Identifier.fromNamespaceAndPath(TemptingTrinkets.MODID, "carrot_wraith");
    }

    @Override
    public Identifier getTextureResource(GeoRenderState renderState) {
        return Identifier.fromNamespaceAndPath(TemptingTrinkets.MODID, "textures/entity/carrot_wraith.png");
    }

    @Override
    public Identifier getAnimationResource(CarrotWraith animatable) {
        return Identifier.fromNamespaceAndPath(TemptingTrinkets.MODID, "carrot_wraith");
    }
}