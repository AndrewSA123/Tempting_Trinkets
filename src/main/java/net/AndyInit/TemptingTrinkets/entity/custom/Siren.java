package net.AndyInit.TemptingTrinkets.entity.custom;

import net.AndyInit.TemptingTrinkets.Tempting_Trinkets;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

public class Siren extends Monster implements GeoEntity {
    private final AnimatableInstanceCache cache =  new SingletonAnimatableInstanceCache(this);
    boolean searchingForLand;
    protected final WaterBoundPathNavigation waterNavigation;
    protected final GroundPathNavigation groundNavigation;
    protected BlockPos homeLocation;
    protected int searchRange = 16;

    public Siren(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.waterNavigation = new WaterBoundPathNavigation(this, level);
        this.groundNavigation = new GroundPathNavigation(this, level);
    }

    public void SetHome(int x, int y, int z){
        this.homeLocation = new BlockPos(x,y,z);
        System.out.println(homeLocation);
    }

    public static AttributeSupplier setAttributes(){
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 10)
                .add(Attributes.ATTACK_DAMAGE, 1f)
                .add(Attributes.ATTACK_SPEED, 2f)
                .add(Attributes.MOVEMENT_SPEED, 0.2f)
                .build();
    }

    boolean wantsToSwim() {
        if (this.searchingForLand) {
            return true;
        } else {
            LivingEntity livingentity = this.getTarget();
            return livingentity != null && livingentity.isInWater();
        }
    }
    @Override
    public void travel(Vec3 pTravelVector) {
        if (this.isControlledByLocalInstance() && this.isInWater() && this.wantsToSwim()) {
            this.moveRelative(0.01F, pTravelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
        } else {
            super.travel(pTravelVector);
        }

    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2D, false));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 0.5f));
        this.goalSelector.addGoal(2, new Siren.GoToHomeGoal(this, 1f));

        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Wolf.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Cat.class, true));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
        if(this.isSwimming()){
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.siren.swim", Animation.LoopType.LOOP));
        }

        if(tAnimationState.isMoving()){
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.siren.walk", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }

        tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.siren.idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public void updateSwimming() {
        if (!this.level().isClientSide) {
            if (this.isEffectiveAi() && this.isInWater() && this.wantsToSwim()) {
                this.navigation = this.waterNavigation;
                this.setSwimming(true);
            } else {
                this.navigation = this.groundNavigation;
                this.setSwimming(false);
            }
        }

    }

    static class GoToHomeGoal extends MoveToBlockGoal{
        private final Siren _siren;
        private static final int searchRadius = 16;

        public GoToHomeGoal(Siren siren, double pSpeedModifier) {
            super(siren, pSpeedModifier, searchRadius, searchRadius);
            this._siren = siren;
        }

        @Override
        public boolean canUse() {
            return super.canUse() && !this._siren.level().isDay() && this._siren.isInWater();
        }

        @Override
        public boolean canContinueToUse() {
            return super.canContinueToUse();
        }

        @Override
        public void start() {
            this._siren.navigation = this._siren.groundNavigation;
            Tempting_Trinkets.LOGGER.info("Siren move to home goal started");
            super.start();
        }

        @Override
        public void stop(){
            super.stop();
            Tempting_Trinkets.LOGGER.info("Siren move to home goal stopped");
        }

        @Override
        protected BlockPos getMoveToTarget() {
            return _siren.homeLocation;
        }

        @Override
        protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
            return pLevel.isEmptyBlock(pPos.above());
        }
    }
    static class AttackBoatGoal extends NearestAttackableTargetGoal {

        public AttackBoatGoal(Mob pMob, Class pTargetType, boolean pMustSee) {
            super(pMob, pTargetType, pMustSee);
        }
    }
}
