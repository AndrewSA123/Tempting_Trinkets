package io.github.Tempting_Trinkets.item.armour;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmourMaterials  {
    public static final Holder<ArmorMaterial> NOARMOURVAL = register("no_armour_val",
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BODY, 0);
                map.put(ArmorItem.Type.HELMET, 0);
                map.put(ArmorItem.Type.CHESTPLATE, 0);
                map.put(ArmorItem.Type.LEGGINGS, 0);
                map.put(ArmorItem.Type.BOOTS, 0);
            }),
            0,
            SoundEvents.ARMOR_EQUIP_ELYTRA,
            0,
            0,
            () -> Ingredient.of(Tags.Items.ENDER_PEARLS),
            List.of()
    );

    private static Holder<ArmorMaterial> register(
            String pName,
            EnumMap<ArmorItem.Type, Integer> pDefense,
            int pEnchantmentValue,
            Holder<SoundEvent> pEquipSound,
            float pToughness,
            float pKnockbackResistance,
            Supplier<Ingredient> pRepairIngridient,
            List<ArmorMaterial.Layer> pLayers
    ) {
        EnumMap<ArmorItem.Type, Integer> enummap = new EnumMap<>(ArmorItem.Type.class);

        for (ArmorItem.Type armoritem$type : ArmorItem.Type.values()) {
            enummap.put(armoritem$type, pDefense.get(armoritem$type));
        }

        return Registry.registerForHolder(
                BuiltInRegistries.ARMOR_MATERIAL,
                new ResourceLocation(pName),
                new ArmorMaterial(enummap, pEnchantmentValue, pEquipSound, pRepairIngridient, pLayers, pToughness, pKnockbackResistance)
        );
    }
}
