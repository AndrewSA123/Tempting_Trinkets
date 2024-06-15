package io.github.Tempting_Trinkets.item.client.item_renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.Tempting_Trinkets.item.Models.RingOfNeutralBuoyancyModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class RingOfNeutralBuoyancyRenderer implements ICurioRenderer {
    private final RingOfNeutralBuoyancyModel model;
    private final ResourceLocation resourceLocation;

    public RingOfNeutralBuoyancyRenderer(ModelPart model, ResourceLocation textureLocation){
        this.model = new RingOfNeutralBuoyancyModel(model);
        this.resourceLocation = textureLocation;
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack matrixStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource renderTypeBuffer, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        matrixStack.pushPose();

        VertexConsumer vertexBuilder = renderTypeBuffer.getBuffer(RenderType.entityCutout(resourceLocation));
        model.renderToBuffer(matrixStack, vertexBuilder, light, OverlayTexture.NO_OVERLAY, 1,1,1,1);
        matrixStack.popPose();
    }
}
