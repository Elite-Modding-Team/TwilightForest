package twilightforest.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import twilightforest.TwilightForestMod;
import twilightforest.entity.ai.EntityAITFChargeAttack;

public class EntityTFPinchBeetle extends EntityMob implements IHostileMount {

    public static final ResourceLocation LOOT_TABLE = TwilightForestMod.prefix("entities/pinch_beetle");

    private static final DataParameter<Boolean> DATA_CONFUSED = EntityDataManager.createKey(EntityTFPinchBeetle.class, DataSerializers.BOOLEAN);

    public EntityTFPinchBeetle(World world) {
        super(world);
        setSize(1.2F, 1.1F);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        dataManager.register(DATA_CONFUSED, false);
    }

    public boolean isConfused() {
        return dataManager.get(DATA_CONFUSED);
    }

    protected void setConfused(boolean confused) {
        dataManager.set(DATA_CONFUSED, confused);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAITFChargeAttack(this, 2.0F, false) {
            @Override
            public boolean shouldExecute() {
                return !isConfused() && super.shouldExecute();
            }
        });
        this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.0D, false) {
            @Override
            public boolean shouldExecute() {
                return !isConfused() && super.shouldExecute();
            }
        });
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_SPIDER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SPIDER_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, Block block) {
        playSound(SoundEvents.ENTITY_SPIDER_STEP, 0.15F, 1.0F);
    }

    @Override
    public void onLivingUpdate() {
        if (!this.getPassengers().isEmpty()) {
            this.setSize(1.9F, 2.0F);

            if (this.getPassengers().get(0).isSneaking()) {
                this.getPassengers().get(0).setSneaking(false);
            }
        } else {
            this.setSize(1.2F, 1.1F);

        }

        if (this.isConfused()) {
            Vec3d pos = new Vec3d(this.posX, this.posY + 1.0D, this.posZ).add(new Vec3d(0.9D, 0, 0).rotateYaw((float) Math.toRadians(this.getRNG().nextInt(360))));
            this.world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, pos.x, pos.y, pos.z, 0, 0, 0);

        }

        super.onLivingUpdate();

        if (!this.getPassengers().isEmpty()) {
            this.getLookHelper().setLookPositionWithEntity(getPassengers().get(0), 100F, 100F);

            // push out of user in wall
            Vec3d riderPos = this.getRiderPosition();
            this.pushOutOfBlocks(riderPos.x, riderPos.y, riderPos.z);
        }
    }

    @Override
    protected void updateAITasks() {
        if (this.isConfused() && this.rand.nextInt(120) == 0) {
            this.setConfused(false);
        }


        super.updateAITasks();
    }

    @Override
    public boolean attackEntityAsMob(Entity entity) {
        if (!this.getPassengers().isEmpty() && this.getPassengers().get(0) == entity && entity instanceof EntityLivingBase && ((EntityLivingBase) entity).isActiveItemStackBlocking()) {
            // Cannot guard when pinched
            if (entity instanceof EntityPlayer) {
                ((EntityPlayer) entity).disableShield(true);
            }
        } else if (entity instanceof EntityLivingBase && ((EntityLivingBase) entity).isActiveItemStackBlocking()) {
            // Beetle is confused when successfully guarded
            if (entity instanceof EntityPlayer) {
                ((EntityPlayer) entity).disableShield(true);
                this.setConfused(true);
            }
        } else if (this.getPassengers().isEmpty() && !entity.isRiding()) {
            entity.startRiding(this);
        }

        return super.attackEntityAsMob(entity);
    }

    @Override
    public float getEyeHeight() {
        return 0.25F;
    }

    @Override
    public void updatePassenger(Entity passenger) {
        if (!this.getPassengers().isEmpty()) {
            Vec3d riderPos = this.getRiderPosition();

            this.getPassengers().get(0).setPosition(riderPos.x, riderPos.y, riderPos.z);
        }
    }

    @Override
    public double getMountedYOffset() {
        return 0.75D;
    }

    private Vec3d getRiderPosition() {
        if (!this.getPassengers().isEmpty()) {
            float distance = 0.9F;

            double dx = Math.cos((this.rotationYaw + 90) * Math.PI / 180.0D) * distance;
            double dz = Math.sin((this.rotationYaw + 90) * Math.PI / 180.0D) * distance;

            return new Vec3d(this.posX + dx, this.posY + this.getMountedYOffset() + this.getPassengers().get(0).getYOffset(), this.posZ + dz);
        } else {
            return new Vec3d(this.posX, this.posY, this.posZ);
        }
    }

    @Override
    public boolean canRiderInteract() {
        return true;
    }

    @Override
    protected ResourceLocation getLootTable() {
        return LOOT_TABLE;
    }
}
