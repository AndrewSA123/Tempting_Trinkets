package net.AndyInit.TemptingTrinkets.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.AndyInit.TemptingTrinkets.Tempting_Trinkets;
import net.AndyInit.TemptingTrinkets.entity.custom.Siren;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SirenRenderer extends GeoEntityRenderer<Siren> {
    public SirenRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SirenModel());
    }

    @Override
    public ResourceLocation getTextureLocation(Siren animatable) {
        return new ResourceLocation(Tempting_Trinkets.MOD_ID, "textures/entity/siren.png");
    }

    @Override
    public void render(Siren entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
