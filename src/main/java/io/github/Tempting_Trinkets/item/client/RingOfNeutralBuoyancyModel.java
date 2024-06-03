package io.github.Tempting_Trinkets.item.client;

import io.github.Tempting_Trinkets.TemptingTrinkets;
import io.github.Tempting_Trinkets.item.custom.RingOfNeutralBuoyancy;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class RingOfNeutralBuoyancyModel extends GeoModel<RingOfNeutralBuoyancy> {
    @Override
    public ResourceLocation getModelResource(RingOfNeutralBuoyancy animatable) {
        return new ResourceLocation(TemptingTrinkets.MODID, "geo/ring_of_neutral_buoyancy.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(RingOfNeutralBuoyancy animatable) {
        return new ResourceLocation(TemptingTrinkets.MODID, "textures/curios/ring_of_neutral_buoyancy.png");
    }

    @Override
    public ResourceLocation getAnimationResource(RingOfNeutralBuoyancy animatable) {
        return new ResourceLocation(TemptingTrinkets.MODID, "animations/ring_of_neutral_buoyancy.animation.json");
    }
}
