package twilightforest.entity.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import twilightforest.entity.boss.EntityTFKnightPhantom;
import twilightforest.entity.boss.EntityTFThrownWep;
import twilightforest.item.TFItems;

public class EntityAIPhantomThrowWeapon extends EntityAIBase {

	private final EntityTFKnightPhantom boss;

	public EntityAIPhantomThrowWeapon(EntityTFKnightPhantom entity) {
		boss = entity;
		setMutexBits(2);
	}

	@Override
	public boolean shouldExecute() {
		return boss.getAttackTarget() != null && boss.getCurrentFormation() == EntityTFKnightPhantom.Formation.ATTACK_PLAYER_ATTACK;
	}

	@Override
	public void updateTask() {
		if (boss.getAttackTarget() != null && boss.getTicksProgress() % 4 == 0) {
			if (boss.isAxeKnight())
				launchAxeAt(boss.getAttackTarget());
			else if (boss.isPickKnight())
				launchPicks();
		}
	}

	private void launchAxeAt(Entity targetedEntity) {
		float bodyFacingAngle = ((boss.renderYawOffset * 3.141593F) / 180F);
		double sx = boss.posX + (MathHelper.cos(bodyFacingAngle) * 1);
		double sy = boss.posY + (boss.height * 0.82);
		double sz = boss.posZ + (MathHelper.sin(bodyFacingAngle) * 1);

		double tx = targetedEntity.posX - sx;
		double ty = (targetedEntity.getEntityBoundingBox().minY + (double) (targetedEntity.height / 2.0F)) - (boss.posY + boss.height / 2.0F);
		double tz = targetedEntity.posZ - sz;

		boss.playSound(SoundEvents.ENTITY_SNOWBALL_THROW, 1.0F, (boss.getRNG().nextFloat() - boss.getRNG().nextFloat()) * 0.2F + 0.4F);
		EntityTFThrownWep projectile = new EntityTFThrownWep(boss.world, boss).setItem(new ItemStack(TFItems.knightmetal_axe));

		float speed = 0.75F;

		projectile.shoot(tx, ty, tz, speed, 1.0F);

		projectile.setLocationAndAngles(sx, sy, sz, boss.rotationYaw, boss.rotationPitch);

		boss.world.spawnEntity(projectile);
	}

	private void launchPicks() {
		boss.playSound(SoundEvents.ENTITY_SNOWBALL_THROW, 1.0F, (boss.getRNG().nextFloat() - boss.getRNG().nextFloat()) * 0.4F + 0.8F);

		for (int i = 0; i < 8; i++) {
			float throwAngle = i * 3.14159165F / 4F;

			double sx = boss.posX + (MathHelper.cos(throwAngle) * 1);
			double sy = boss.posY + (boss.height * 0.82);
			double sz = boss.posZ + (MathHelper.sin(throwAngle) * 1);

			double vx = MathHelper.cos(throwAngle);
			double vy = 0;
			double vz = MathHelper.sin(throwAngle);


			EntityTFThrownWep projectile = new EntityTFThrownWep(boss.world, boss).setDamage(3).setVelocity(0.015F).setItem(new ItemStack(TFItems.knightmetal_pickaxe));


			projectile.setLocationAndAngles(sx, sy, sz, i * 45F, boss.rotationPitch);

			float speed = 0.5F;

			projectile.shoot(vx, vy, vz, speed, 1.0F);

			boss.world.spawnEntity(projectile);
		}
	}
}