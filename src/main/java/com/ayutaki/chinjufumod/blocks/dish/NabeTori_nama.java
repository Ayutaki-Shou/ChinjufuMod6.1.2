package com.ayutaki.chinjufumod.blocks.dish;

import java.util.Random;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class NabeTori_nama extends BaseNabe_nama {

	public NabeTori_nama(Block.Properties properties) {
		super(properties);
	}

	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {

		if (isCooking(worldIn, pos)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(worldIn) + (20 * rand.nextInt(5)));
			worldIn.setBlockState(pos, Dish_Blocks.NABETORI.getDefaultState()
					.with(H_FACING, state.get(H_FACING)).with(COOK, state.get(COOK)).with(DOWN, state.get(DOWN))
					.with(BaseNabe.STAGE_1_4, Integer.valueOf(1)));

			/** Get EXP. **/
			worldIn.addEntity(new ExperienceOrbEntity(worldIn, pos.getX(), pos.getY() + 0.5D, pos.getZ(), 2)); }

		if (inWater(state, worldIn, pos)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60);
			CMEvents.soundSnowBreak(worldIn, pos);
			worldIn.setBlockState(pos, Dish_Blocks.NABE_kara.getDefaultState()
					.with(Nabe_kara.H_FACING, state.get(H_FACING))
					.with(Nabe_kara.STAGE_1_4, Integer.valueOf(4))
					.with(Nabe_kara.WATERLOGGED, state.get(WATERLOGGED)), 3);
			this.dropRottenfood(worldIn, pos); }
		
		else { }
	}

	/* 効果音・パーティクル */
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World worldIn, BlockPos pos, Random rand) {

		double d0 = (double)pos.getX() + 0.5D;
		double d1 = (double)pos.getY() + 0.8D;
		double d2 = (double)pos.getZ() + 0.5D;
		double d4 = rand.nextDouble() * 0.6D - 0.3D;
		double d6 = rand.nextDouble() * 6.0D / 16.0D;

		if (isCooking(worldIn, pos)) {

			if (rand.nextDouble() < 0.1D) {
				worldIn.playSound(d0, d1, d2, SoundEvents_CM.GUTSUGUTSU, SoundCategory.BLOCKS, 0.5F, 0.7F, false); }

			if (rand.nextDouble() < 0.25D) {
				/** 種類, 座標x, y, z, 速度x, y, z **/
				worldIn.addParticle(ParticleTypes.POOF, d0 + d4, d1 + d6, d2 + d4, 0.0D, 0.0D, 0.0D); }
		}
	}

}
