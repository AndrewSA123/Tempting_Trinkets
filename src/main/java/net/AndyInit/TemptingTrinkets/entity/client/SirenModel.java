package net.AndyInit.TemptingTrinkets.entity.client;

import net.AndyInit.TemptingTrinkets.Tempting_Trinkets;
import net.AndyInit.TemptingTrinkets.entity.custom.Siren;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class SirenModel extends GeoModel<Siren> {
    @Override
    public ResourceLocation getModelResource(Siren animatable) {
        return new ResourceLocation(Tempting_Trinkets.MOD_ID, "geo/siren.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Siren animatable) {
        return new ResourceLocation(Tempting_Trinkets.MOD_ID, "texture/entity/siren.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Siren animatable) {
        return new ResourceLocation(Tempting_Trinkets.MOD_ID, "animations/siren.animation.json");
    }

    @Override
    public void setCustomAnimations(Siren animatable, long instanceId, AnimationState<Siren> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if(head != null){
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}
