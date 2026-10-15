package io.github.Tempting_Trinkets.item.client;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

// GenericItemRenderer.java
public class GenericItemRenderer<M extends EntityModel<LivingEntityRenderState>>
        implements ICurioRenderer.ModelRender<LivingEntityRenderState, M> {
    private final M model;
    private final Identifier textureLocation;

    public GenericItemRenderer(M model, Identifier textureLocation){
        this.model = model;
        this.textureLocation = textureLocation;
    }

    @Override
    public M getModel(ItemStack stack, SlotContext slotContext) { return model; }

    @Override
    public Identifier getModelTexture(ItemStack stack, SlotContext slotContext) { return textureLocation; }
}
