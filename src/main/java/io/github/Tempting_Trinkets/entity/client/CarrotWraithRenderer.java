package io.github.Tempting_Trinkets.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.Tempting_Trinkets.TemptingTrinkets;
import io.github.Tempting_Trinkets.entity.custom.CarrotWraith;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CarrotWraithRenderer extends GeoEntityRenderer<CarrotWraith> {

    public CarrotWraithRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new CarrotWraithModel());
    }

    @Override
    public ResourceLocation getTextureLocation(CarrotWraith animatable) {
        return ResourceLocation.fromNamespaceAndPath(TemptingTrinkets.MODID, "textures/entity/carrot_wraith.png");
    }

    @Override
    public void render(CarrotWraith entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
