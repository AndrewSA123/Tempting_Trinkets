package io.github.Tempting_Trinkets.item.custom;

import io.github.Tempting_Trinkets.trinket.Trinket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.hurtingprojectile.SmallFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class RingOfFire extends Item implements Trinket {

    public RingOfFire(Item.Properties properties) {
        super(properties
                .stacksTo(1)
                .fireResistant());
    }

    public static String GetName() {
        return "ring_of_fire";
    }

    @Override
    public InteractionResult use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        createFireBall(pPlayer, pPlayer.getItemInHand(pUsedHand));
        return InteractionResult.PASS;
    }

    public void createFireBall(Player entity, ItemStack stack){
        if (entity.level().isClientSide()) return;
        if(!entity.getCooldowns().isOnCooldown(stack)){
            SmallFireball smallfireball = new SmallFireball(entity.level(), entity, entity.getLookAngle().normalize());
            smallfireball.setPos(smallfireball.getX(), entity.getY(0.5) + 0.5, smallfireball.getZ());
            entity.level().addFreshEntity(smallfireball);
            entity.getCooldowns().addCooldown(stack, 40);
        }
    }
}