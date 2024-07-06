package io.github.Tempting_Trinkets.item.custom;

import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class RingOfFire extends Item implements ICurioItem {

    public RingOfFire() {
        super(new Item.Properties());
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }

    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return true;
    }

    public static String GetName() {
        return "ring_of_fire";
    }

    //TODO: Below is the code for spawning a blaze fireball, use this with a hotkey while the ring of fire is
    //TODO: equipped to fire at the target, potentially look into some form of cooldown system for it.

//    Vec3 vec3 = new Vec3(this.blaze.getRandom().triangle(d1, 2.297 * d4), d2, this.blaze.getRandom().triangle(d3, 2.297 * d4));
//    SmallFireball smallfireball = new SmallFireball(this.blaze.level(), this.blaze, vec3.normalize());
//    smallfireball.setPos(smallfireball.getX(), this.blaze.getY(0.5) + 0.5, smallfireball.getZ());
//    this.blaze.level().addFreshEntity(smallfireball);
}