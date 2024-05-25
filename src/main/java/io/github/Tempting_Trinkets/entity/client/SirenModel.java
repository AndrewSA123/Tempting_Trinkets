package io.github.Tempting_Trinkets.entity.client;

import io.github.Tempting_Trinkets.TemptingTrinkets;
import io.github.Tempting_Trinkets.entity.custom.Siren;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class SirenModel extends GeoModel<Siren> {
    @Override
    public ResourceLocation getModelResource(Siren animatable) {
        return new ResourceLocation(TemptingTrinkets.MODID, "geo/siren.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Siren animatable) {
        return new ResourceLocation(TemptingTrinkets.MODID, "textures/entity/siren.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Siren animatable) {
        return new ResourceLocation(TemptingTrinkets.MODID, "animations/siren.animation.json");
    }

    @Override
    public void setCustomAnimations(Siren animatable, long instanceId, AnimationState<Siren> animationState) {
        GeoBone head = getAnimationProcessor().getBone("Head");

        if(head != null){
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}
