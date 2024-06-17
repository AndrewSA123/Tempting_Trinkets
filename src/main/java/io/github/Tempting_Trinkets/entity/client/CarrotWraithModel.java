package io.github.Tempting_Trinkets.entity.client;

import io.github.Tempting_Trinkets.TemptingTrinkets;
import io.github.Tempting_Trinkets.entity.custom.CarrotWraith;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CarrotWraithModel extends GeoModel<CarrotWraith> {
    @Override
    public ResourceLocation getModelResource(CarrotWraith animatable) {
        return new ResourceLocation(TemptingTrinkets.MODID, "geo/carrot_wraith.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CarrotWraith animatable) {
        return new ResourceLocation(TemptingTrinkets.MODID, "textures/entity/carrot_wraith.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CarrotWraith animatable) {
        return new ResourceLocation(TemptingTrinkets.MODID, "animations/carrot_wraith.animation.json");
    }
}
