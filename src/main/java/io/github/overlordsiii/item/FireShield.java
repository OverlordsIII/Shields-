package io.github.overlordsiii.item;

import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricShieldItem;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CampfireBlock;
import net.minecraft.block.CandleBlock;
import net.minecraft.block.CandleCakeBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;

public class FireShield extends FabricShieldItem {
	public FireShield() {
		super(new FabricItemSettings().maxDamage(2500), 10, 13, Items.IRON_INGOT);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		if (world.isClient) {
			return TypedActionResult.success(user.getStackInHand(hand));
		}
		super.use(world, user, hand);
		Vec3d vec3d = user.getEyePos();
		Vec3d vec3d2 = user.getRotationVec(1.0f);
		Vec3d vec3d3 = vec3d.add(vec3d2.x * 100.0, vec3d2.y * 100.0, vec3d2.z * 100.0);
		EntityHitResult entityHitResult = ProjectileUtil.getEntityCollision(user.world, user, vec3d, vec3d3, new Box(vec3d, vec3d3).expand(1.0), entity -> !entity.isSpectator(), 0.0f);

		if (entityHitResult != null) {
			Entity entity = entityHitResult.getEntity();
			world.addParticle(ParticleTypes.LARGE_SMOKE,entity.getX(), entity.getY(), entity.getZ() , 0.0, 0.0, 0.0);
			world.addParticle(ParticleTypes.LARGE_SMOKE,user.getX(), user.getY(), user.getZ() , 0.0, 0.0, 0.0);
			entity.setOnFireFor(3);
			entity.damage(DamageSource.thorns(user), 4);
			return TypedActionResult.success(user.getStackInHand(hand));
		}


		return TypedActionResult.fail(user.getStackInHand(hand));
	}
}
