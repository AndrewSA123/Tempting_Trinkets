package io.github.Tempting_Trinkets.item.Models;

import io.github.Tempting_Trinkets.TemptingTrinkets;
import io.github.Tempting_Trinkets.item.ModItems;
import io.github.Tempting_Trinkets.item.custom.RingOfFire;
import io.github.Tempting_Trinkets.item.custom.RingOfNeutralBuoyancy;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public final class TrinketModels {
    public record Entry(Supplier<? extends Item> item, ModelLayerLocation layer,
                        Supplier<LayerDefinition> definition,
                        Function<ModelPart, EntityModel<LivingEntityRenderState>> factory,
                        Identifier texture) {}

    public static final List<Entry> ALL = List.of(
            new Entry(ModItems.Ring_Of_Neutral_Buoyancy, RingOfNeutralBuoyancyModel.LAYER_LOCATION,
                    RingOfNeutralBuoyancyModel::createBodyLayer, RingOfNeutralBuoyancyModel::new,
                    texture(RingOfNeutralBuoyancy.GetName())),
            new Entry(ModItems.Ring_Of_Fire, RingOfFireModel.LAYER_LOCATION,
                    RingOfFireModel::createBodyLayer, RingOfFireModel::new,
                    texture(RingOfFire.GetName())));

    private static Identifier texture(String name) {
        return Identifier.fromNamespaceAndPath(TemptingTrinkets.MODID, "textures/curios/" + name + ".png");
    }
}