package io.github.Tempting_Trinkets.entity.client;

import io.github.Tempting_Trinkets.entity.custom.Siren;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import com.geckolib.constant.DefaultAnimations;
import com.geckolib.renderer.GeoEntityRenderer;
import com.geckolib.renderer.base.BoneSnapshots;
import com.geckolib.renderer.base.GeoRenderState;
import com.geckolib.renderer.base.RenderPassInfo;

public class SirenRenderer<R extends LivingEntityRenderState & GeoRenderState> extends GeoEntityRenderer<Siren, R> {
    public SirenRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SirenModel());
    }

    @Override
    public void adjustModelBonesForRender(RenderPassInfo<R> renderPassInfo, BoneSnapshots snapshots) {
        DefaultAnimations.hardcodedHeadRotation(renderPassInfo, snapshots, "Head");
    }
}