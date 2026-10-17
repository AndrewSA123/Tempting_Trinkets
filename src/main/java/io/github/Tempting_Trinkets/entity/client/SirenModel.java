package io.github.Tempting_Trinkets.entity.client;

import io.github.Tempting_Trinkets.TemptingTrinkets;
import io.github.Tempting_Trinkets.entity.custom.Siren;
import net.minecraft.resources.Identifier;
import com.geckolib.model.GeoModel;
import com.geckolib.renderer.base.GeoRenderState;

public class SirenModel extends GeoModel<Siren> {
    @Override
    public Identifier getModelResource(GeoRenderState renderState) {
        return Identifier.fromNamespaceAndPath(TemptingTrinkets.MODID, "siren");
    }

    @Override
    public Identifier getTextureResource(GeoRenderState renderState) {
        return Identifier.fromNamespaceAndPath(TemptingTrinkets.MODID, "textures/entity/siren.png");
    }

    @Override
    public Identifier getAnimationResource(Siren animatable) {
        return Identifier.fromNamespaceAndPath(TemptingTrinkets.MODID, "siren");
    }
}