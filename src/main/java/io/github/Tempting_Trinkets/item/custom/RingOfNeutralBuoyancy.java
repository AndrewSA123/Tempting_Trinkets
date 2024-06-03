package io.github.Tempting_Trinkets.item.custom;

import io.github.Tempting_Trinkets.item.client.RingOfNeutralBuoyancyRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.util.GeckoLibUtil;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.Objects;
import java.util.function.Consumer;

import static net.neoforged.neoforge.common.NeoForgeMod.CREATIVE_FLIGHT;

public class RingOfNeutralBuoyancy extends ArmorItem implements ICurioItem, GeoItem {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public RingOfNeutralBuoyancy(Properties pProperties) {
        super(ArmorMaterials.DIAMOND, Type.HELMET , pProperties);
    }

    public static String GetName() {
        return "ring_of_neutral_buoyancy";
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        var creative_flight = pPlayer.getAttribute(CREATIVE_FLIGHT);
        ItemStack stack = pPlayer.getItemInHand(pHand);

        if (creative_flight.getValue() > 0f) {
            creative_flight.setBaseValue(0);
            stack.set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, false);
            pLevel.playLocalSound(pPlayer.blockPosition(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 1f, 1f, false);
        } else {
            creative_flight.setBaseValue(1);
            stack.set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true);
            pLevel.playLocalSound(pPlayer.blockPosition(), SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 1f, 1f, false);
        }
        pPlayer.onUpdateAbilities();
        return InteractionResultHolder.pass(pPlayer.getItemInHand(pHand));
    }

    //Curios Stuff

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if(slotContext.entity() instanceof Player player){
            Objects.requireNonNull(player.getAttribute(CREATIVE_FLIGHT)).setBaseValue(1);
            player.onUpdateAbilities();
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if(slotContext.entity() instanceof Player player){
            Objects.requireNonNull(player.getAttribute(CREATIVE_FLIGHT)).setBaseValue(0);
            player.onUpdateAbilities();
        }
    }

    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return false;
    }

    //GeckoLib Stuff

    private PlayState predicate(AnimationState animationState){
        animationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, 20, state -> {
            state.getController().setAnimation(DefaultAnimations.IDLE);

            return PlayState.CONTINUE;
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private RingOfNeutralBuoyancyRenderer renderer;

            @Override
            public <T extends LivingEntity> HumanoidModel<?> getGeoArmorRenderer(@Nullable T livingEntity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot, @Nullable HumanoidModel<T> original) {
                if(renderer == null){
                    this.renderer = new RingOfNeutralBuoyancyRenderer();
                }
                return this.renderer;
            }
        });
    }
}
