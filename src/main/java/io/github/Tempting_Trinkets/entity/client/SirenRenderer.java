package io.github.Tempting_Trinkets.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.Tempting_Trinkets.TemptingTrinkets;
import io.github.Tempting_Trinkets.entity.custom.Siren;
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
        return new ResourceLocation(TemptingTrinkets.MODID, "textures/entity/siren.png");
    }

    @Override
    public void render(Siren entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
