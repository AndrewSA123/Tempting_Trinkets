package io.github.Tempting_Trinkets.item.custom;

import io.github.Tempting_Trinkets.util.ModBindings;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class RingOfFire extends Item implements ICurioItem {

    public RingOfFire() {
        super(new Item.Properties()
                .stacksTo(1)
                .fireResistant());
    }

    public static String GetName() {
        return "ring_of_fire";
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        createFireBall(pPlayer);
        return InteractionResultHolder.pass(pPlayer.getItemInHand(pUsedHand));
    }

    //Curio Stuff
    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return false;
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if(slotContext.entity() instanceof Player player){
            if(ModBindings.RING_OF_FIRE_MAPPING.consumeClick()) {
                createFireBall(player);
            }

            if(player.isOnFire()){
                player.setRemainingFireTicks(0);
            }
        }
    }

    public void createFireBall(Player entity){
        if(!entity.getCooldowns().isOnCooldown(this)){
            SmallFireball smallfireball = new SmallFireball(entity.level(), entity, entity.getLookAngle().normalize());
            smallfireball.setPos(smallfireball.getX(), entity.getY(0.5) + 0.5, smallfireball.getZ());
            entity.level().addFreshEntity(smallfireball);
            entity.getCooldowns().addCooldown(this, 40);
        }
    }
}