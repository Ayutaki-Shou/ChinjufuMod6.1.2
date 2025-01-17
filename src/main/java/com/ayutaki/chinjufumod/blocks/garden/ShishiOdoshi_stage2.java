package com.ayutaki.chinjufumod.blocks.garden;

import java.util.Random;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Garden_Blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ShishiOdoshi_stage2 extends BaseShishiOdoshi {

	public ShishiOdoshi_stage2(AbstractBlock.Properties properties) {
		super(properties);
	}

	/* RightClick Action */
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {

		ItemStack itemstack = playerIn.getItemInHand(hand);

		if (itemstack.isEmpty() && state.getValue(HALF) == DoubleBlockHalf.LOWER) {
			
			CMEvents.soundStoneButton_Off(worldIn, pos);
			worldIn.setBlock(pos, Garden_Blocks.SHISHIODOSHI.defaultBlockState()
							.setValue(BaseShishiOdoshi.H_FACING, state.getValue(H_FACING))
							.setValue(BaseShishiOdoshi.HALF, DoubleBlockHalf.LOWER)
							.setValue(BaseShishiOdoshi.WHICH, state.getValue(WHICH))
							.setValue(BaseShishiOdoshi.STAGE_1_4, Integer.valueOf(1)), 3);

			worldIn.setBlock(pos.above(), Garden_Blocks.SHISHIODOSHI.defaultBlockState()
							.setValue(BaseShishiOdoshi.H_FACING, state.getValue(H_FACING))
							.setValue(BaseShishiOdoshi.HALF, DoubleBlockHalf.UPPER)
							.setValue(BaseShishiOdoshi.WHICH, state.getValue(WHICH))
							.setValue(BaseShishiOdoshi.STAGE_1_4, Integer.valueOf(1)), 3); }
		
		if (!itemstack.isEmpty()) { CMEvents.textFullItem(worldIn, pos, playerIn); }

		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	/* TickRandom */
	@Override
	public void onPlace(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		worldIn.getBlockTicks().scheduleTick(pos, this, 30);
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {

		int i = state.getValue(STAGE_1_4);
		Direction direction = state.getValue(H_FACING);
		DoubleBlockHalf half = state.getValue(HALF);
		
		BlockState northstate = worldIn.getBlockState(pos.north());
		BlockState southstate = worldIn.getBlockState(pos.south());
		BlockState eaststate = worldIn.getBlockState(pos.east());
		BlockState weststate = worldIn.getBlockState(pos.west());
		Block northblock = northstate.getBlock();
		Block southblock = southstate.getBlock();
		Block eastblock = eaststate.getBlock();
		Block westblock = weststate.getBlock();

		if (!worldIn.isAreaLoaded(pos, 1)) { return; }
		
		switch (half) {
		case LOWER :
		default :

			switch (i) {
			case 1 :
			default :
				worldIn.setBlock(pos, state.setValue(BaseShishiOdoshi.STAGE_1_4, Integer.valueOf(i + 1)), 3);
				worldIn.setBlock(pos.above(), this.defaultBlockState().setValue(BaseShishiOdoshi.H_FACING, state.getValue(H_FACING))
						.setValue(BaseShishiOdoshi.HALF, DoubleBlockHalf.UPPER)
						.setValue(BaseShishiOdoshi.WHICH, state.getValue(WHICH))
						.setValue(BaseShishiOdoshi.STAGE_1_4, Integer.valueOf(i + 1)), 3);

				worldIn.getBlockTicks().scheduleTick(pos, this, 30);
				break;

			case 2 :
				worldIn.setBlock(pos, state.setValue(BaseShishiOdoshi.STAGE_1_4, Integer.valueOf(i + 1)), 3);
				worldIn.setBlock(pos.above(), this.defaultBlockState().setValue(BaseShishiOdoshi.H_FACING, state.getValue(H_FACING))
						.setValue(BaseShishiOdoshi.HALF, DoubleBlockHalf.UPPER)
						.setValue(BaseShishiOdoshi.WHICH, state.getValue(WHICH))
						.setValue(BaseShishiOdoshi.STAGE_1_4, Integer.valueOf(i + 1)), 3);
				
				/* state.getValue(WHICH) == false 空=0,1,2,3=満,4=溢 */
				if (direction == Direction.NORTH && state.getValue(WHICH) == false) {
					if (eastblock instanceof Chouzubachi && eaststate.getValue(Chouzubachi.STAGE_0_3) < 3) {
						worldIn.setBlock(pos.east(), eaststate.setValue(Chouzubachi.STAGE_0_3, Integer.valueOf(eaststate.getValue(Chouzubachi.STAGE_0_3) + 1)), 3); }
					else { } }

				if (direction == Direction.SOUTH && state.getValue(WHICH) == false) {
					if (westblock instanceof Chouzubachi && weststate.getValue(Chouzubachi.STAGE_0_3) < 3) {
						worldIn.setBlock(pos.west(), weststate.setValue(Chouzubachi.STAGE_0_3, Integer.valueOf(weststate.getValue(Chouzubachi.STAGE_0_3) + 1)), 3); }
					else { } }

				if (direction == Direction.EAST && state.getValue(WHICH) == false) {
					if (southblock instanceof Chouzubachi && southstate.getValue(Chouzubachi.STAGE_0_3) < 3) {
						worldIn.setBlock(pos.south(), southstate.setValue(Chouzubachi.STAGE_0_3, Integer.valueOf(southstate.getValue(Chouzubachi.STAGE_0_3) + 1)), 3); }
					else { } }

				if (direction == Direction.WEST && state.getValue(WHICH) == false) {
					if (northblock instanceof Chouzubachi && northstate.getValue(Chouzubachi.STAGE_0_3) < 3) {
						worldIn.setBlock(pos.north(), northstate.setValue(Chouzubachi.STAGE_0_3, Integer.valueOf(northstate.getValue(Chouzubachi.STAGE_0_3) + 1)), 3); }
					else { } }

				/* state.getValue(WHICH) == true */
				if (direction == Direction.NORTH && state.getValue(WHICH) == true) {
					if (westblock instanceof Chouzubachi && weststate.getValue(Chouzubachi.STAGE_0_3) < 3) {
						worldIn.setBlock(pos.west(), weststate.setValue(Chouzubachi.STAGE_0_3, Integer.valueOf(weststate.getValue(Chouzubachi.STAGE_0_3) + 1)), 3); }
					else { } }

				if (direction == Direction.SOUTH && state.getValue(WHICH) == true) {
					if (eastblock instanceof Chouzubachi && eaststate.getValue(Chouzubachi.STAGE_0_3) < 3) {
						worldIn.setBlock(pos.east(), eaststate.setValue(Chouzubachi.STAGE_0_3, Integer.valueOf(eaststate.getValue(Chouzubachi.STAGE_0_3) + 1)), 3); }
					else { } }

				if (direction == Direction.EAST && state.getValue(WHICH) == true) {
					if (northblock instanceof Chouzubachi && northstate.getValue(Chouzubachi.STAGE_0_3) < 3) {
						worldIn.setBlock(pos.north(), northstate.setValue(Chouzubachi.STAGE_0_3, Integer.valueOf(northstate.getValue(Chouzubachi.STAGE_0_3) + 1)), 3); }
					else { } }

				if (direction == Direction.WEST && state.getValue(WHICH) == true) {
					if (southblock instanceof Chouzubachi && southstate.getValue(Chouzubachi.STAGE_0_3) < 3) {
						worldIn.setBlock(pos.south(), southstate.setValue(Chouzubachi.STAGE_0_3, Integer.valueOf(southstate.getValue(Chouzubachi.STAGE_0_3) + 1)), 3); }
					else { } }
				
				worldIn.getBlockTicks().scheduleTick(pos, this, 30);
				break;

			case 3 :
				worldIn.setBlock(pos, state.setValue(BaseShishiOdoshi.STAGE_1_4, Integer.valueOf(i + 1)), 3);
				worldIn.setBlock(pos.above(), this.defaultBlockState().setValue(BaseShishiOdoshi.H_FACING, state.getValue(H_FACING))
						.setValue(BaseShishiOdoshi.HALF, DoubleBlockHalf.UPPER)
						.setValue(BaseShishiOdoshi.WHICH, state.getValue(WHICH))
						.setValue(BaseShishiOdoshi.STAGE_1_4, Integer.valueOf(i + 1)), 3);
				
				/* state.getValue(WHICH) == false */
				if (direction == Direction.NORTH && state.getValue(WHICH) == false) {
					if (eastblock instanceof Chouzubachi) {
						worldIn.setBlock(pos.east(), eaststate.setValue(Chouzubachi.STAGE_0_3, Integer.valueOf(3)), 3); }
					else { } }

				if (direction == Direction.SOUTH && state.getValue(WHICH) == false) {
					if (westblock instanceof Chouzubachi) {
						worldIn.setBlock(pos.west(), weststate.setValue(Chouzubachi.STAGE_0_3, Integer.valueOf(3)), 3); }
					else { } }

				if (direction == Direction.EAST && state.getValue(WHICH) == false) {
					if (southblock instanceof Chouzubachi) {
						worldIn.setBlock(pos.south(), southstate.setValue(Chouzubachi.STAGE_0_3, Integer.valueOf(3)), 3); }
					else { } }

				if (direction == Direction.WEST && state.getValue(WHICH) == false) {
					if (northblock instanceof Chouzubachi) {
						worldIn.setBlock(pos.north(), northstate.setValue(Chouzubachi.STAGE_0_3, Integer.valueOf(3)), 3); }
					else { } }

				/* state.getValue(WHICH) == true */
				if (direction == Direction.NORTH && state.getValue(WHICH) == true) {
					if (westblock instanceof Chouzubachi) {
						worldIn.setBlock(pos.west(), weststate.setValue(Chouzubachi.STAGE_0_3, Integer.valueOf(3)), 3); }
					else { } }

				if (direction == Direction.SOUTH && state.getValue(WHICH) == true) {
					if (eastblock instanceof Chouzubachi) {
						worldIn.setBlock(pos.east(), eaststate.setValue(Chouzubachi.STAGE_0_3, Integer.valueOf(3)), 3); }
					else { } }

				if (direction == Direction.EAST && state.getValue(WHICH) == true) {
					if (northblock instanceof Chouzubachi) {
						worldIn.setBlock(pos.north(), northstate.setValue(Chouzubachi.STAGE_0_3, Integer.valueOf(3)), 3); }
					else { } }

				if (direction == Direction.WEST && state.getValue(WHICH) == true) {
					if (southblock instanceof Chouzubachi) {
						worldIn.setBlock(pos.south(), southstate.setValue(Chouzubachi.STAGE_0_3, Integer.valueOf(3)), 3); }
					else { } }
				
				worldIn.getBlockTicks().scheduleTick(pos, this, 30);
				break;
				
			case 4 :
				worldIn.playSound(null, pos, SoundEvents_CM.SHISHIODOSHI, SoundCategory.BLOCKS, 0.5F, 1.0F);
				
				worldIn.setBlock(pos, Garden_Blocks.SHISHIODOSHI.defaultBlockState()
							.setValue(BaseShishiOdoshi.H_FACING, state.getValue(H_FACING))
							.setValue(BaseShishiOdoshi.WHICH, state.getValue(WHICH))
							.setValue(BaseShishiOdoshi.HALF, DoubleBlockHalf.LOWER)
							.setValue(BaseShishiOdoshi.STAGE_1_4, Integer.valueOf(2)), 3);
				worldIn.setBlock(pos.above(), Garden_Blocks.SHISHIODOSHI.defaultBlockState()
						.setValue(BaseShishiOdoshi.H_FACING, state.getValue(H_FACING))
						.setValue(BaseShishiOdoshi.WHICH, state.getValue(WHICH))
						.setValue(BaseShishiOdoshi.HALF, DoubleBlockHalf.UPPER)
						.setValue(BaseShishiOdoshi.STAGE_1_4, Integer.valueOf(2)), 3);
				
				worldIn.getBlockTicks().scheduleTick(pos, this, 30);
				break;
			} // STAGE_1_4
			break;

		case UPPER :
			break;
		} // HALF

	}

	/* Play Sound・Particle */
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World worldIn, BlockPos pos, Random rand) {

		double d0 = (double)pos.getX() + 0.5D;
		double d1 = (double)pos.getY() + 0.5D;
		double d2 = (double)pos.getZ() + 0.5D;

		worldIn.playLocalSound(d0, d1, d2, SoundEvents.WATER_AMBIENT, SoundCategory.BLOCKS, rand.nextFloat() * 0.25F, rand.nextFloat() + 0.75F, false);
	}

}
