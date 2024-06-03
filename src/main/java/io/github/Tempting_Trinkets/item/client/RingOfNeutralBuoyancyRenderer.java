package io.github.Tempting_Trinkets.item.client;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.Tempting_Trinkets.item.custom.RingOfNeutralBuoyancy;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.checkerframework.checker.nullness.qual.NonNull;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class RingOfNeutralBuoyancyRenderer extends GeoArmorRenderer<RingOfNeutralBuoyancy> implements ICurioRenderer {
    private static final RingOfNeutralBuoyancyModel ringModel = new RingOfNeutralBuoyancyModel();
    private final ModelPart base;
    private static final MeshDefinition mesh = new MeshDefinition();
    private static final PartDefinition root = mesh.getRoot();

    public RingOfNeutralBuoyancyRenderer() {
        super(ringModel);
        root.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 1).addBox(-1.0F, -25.0F, 0.0F, 1.0F, 25.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
        this.base = root.bake(1,1);
        //TODO: This may also not be needed as its all to do with the render method stuff
    }

    @Override
    public void setupAnim(@NonNull Entity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack matrixStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource renderTypeBuffer, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        base.render(matrixStack, renderTypeBuffer.getBuffer(RenderType.armorGlint()), light, light, 1f, 1f, 1f, 0f);
        //TODO: Figure out why this isn't rendering the GeoModel on the character while equipped :)
    }
}
