package net.AndyInit.TemptingTrinkets.item.client;

import net.AndyInit.TemptingTrinkets.Tempting_Trinkets;
import net.AndyInit.TemptingTrinkets.item.RingOfNeutralBuoyancy;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class RingOfNeutralBuoyancyModel extends GeoModel<RingOfNeutralBuoyancy> {
    @Override
    public ResourceLocation getModelResource(RingOfNeutralBuoyancy animatable) {
        return new ResourceLocation(Tempting_Trinkets.MOD_ID, "geo/ring_of_neutral_buoyancy.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(RingOfNeutralBuoyancy animatable) {
        return new ResourceLocation(Tempting_Trinkets.MOD_ID, "textures/curios/ring_of_neutral_buoyancy_old.png");
    }

    @Override
    public ResourceLocation getAnimationResource(RingOfNeutralBuoyancy animatable) {
        return new ResourceLocation(Tempting_Trinkets.MOD_ID, "animations/ring_of_neutral_buoyancy.animation.json");
    }
}
