package io.github.Tempting_Trinkets.item.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class GenericItemRenderer<R extends EntityModel> implements ICurioRenderer {
    private final R model;
    private final ResourceLocation resourceLocation;

    public GenericItemRenderer(R model, ResourceLocation textureLocation){
        this.model = model;
        this.resourceLocation = textureLocation;
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack matrixStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource renderTypeBuffer, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        //TODO: Fix the pPackedOverlay variable causing weird colour issues.
        matrixStack.pushPose();
        VertexConsumer vertexBuilder = renderTypeBuffer.getBuffer(RenderType.entityCutout(resourceLocation));
        model.renderToBuffer(matrixStack, vertexBuilder, light, 0);
        matrixStack.popPose();
    }
}
