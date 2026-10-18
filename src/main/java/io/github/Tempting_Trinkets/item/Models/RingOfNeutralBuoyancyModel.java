package io.github.Tempting_Trinkets.item.Models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.Tempting_Trinkets.TemptingTrinkets;
import io.github.Tempting_Trinkets.item.custom.RingOfNeutralBuoyancy;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;

public class RingOfNeutralBuoyancyModel extends EntityModel<LivingEntityRenderState> {

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			Identifier.fromNamespaceAndPath(TemptingTrinkets.MODID, "textures/curios/" + RingOfNeutralBuoyancy.GetName() + ".png"), "main");

	public RingOfNeutralBuoyancyModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Ring = partdefinition.addOrReplaceChild("Ring", CubeListBuilder.create().texOffs(0, 10).addBox(-6.0F, -1.0F, -4.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.0F, -1.0F, -4.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(12, 4).addBox(-4.0F, -1.0F, -4.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(12, 0).addBox(-4.0F, -1.0F, 2.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 9.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}
}