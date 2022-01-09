package com.ayutaki.chinjufumod.blocks.dish;

import java.util.Random;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class SushiGeta_Salmon extends BaseFood_Stage4Water {

	/* Collision */
	protected static final VoxelShape AABB_SOUTH = Block.makeCuboidShape(1.0D, 0.0D, 7.0D, 14.0D, 2.5D, 12.5D);
	protected static final VoxelShape AABB_WEST = Block.makeCuboidShape(3.5D, 0.0D, 1.0D, 9.0D, 2.5D, 14.0D);
	protected static final VoxelShape AABB_NORTH = Block.makeCuboidShape(2.0D, 0.0D, 3.5D, 15.0D, 2.5D, 9.0D);
	protected static final VoxelShape AABB_EAST = Block.makeCuboidShape(7.0D, 0.0D, 2.0D, 12.5D, 2.5D, 15.0D);

	protected static final VoxelShape DOWN_SOUTH = Block.makeCuboidShape(1.0D, -8.0D, 7.0D, 14.0D, 0.1D, 12.5D);
	protected static final VoxelShape DOWN_WEST = Block.makeCuboidShape(3.5D, -8.0D, 1.0D, 9.0D, 0.1D, 14.0D);
	protected static final VoxelShape DOWN_NORTH = Block.makeCuboidShape(2.0D, -8.0D, 3.5D, 15.0D, 0.1D, 9.0D);
	protected static final VoxelShape DOWN_EAST = Block.makeCuboidShape(7.0D, -8.0D, 2.0D, 12.5D, 0.1D, 15.0D);

	public SushiGeta_Salmon(Block.Properties properties) {
		super(properties);
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {

		ItemStack itemstack = playerIn.getHeldItem(hand);
		int i = state.get(STAGE_1_4);

		if (itemstack.isEmpty()) {
			
			CMEvents.soundTake_Pick(worldIn, pos);	
			playerIn.inventory.addItemStackToInventory(new ItemStack(Items_Teatime.SHOUYUSUSHI_S));

			if (i == 1) {
				worldIn.setBlockState(pos, Dish_Blocks.SUSHIGETA_kara.getDefaultState()
						.with(H_FACING, state.get(H_FACING))
						.with(DOWN, state.get(DOWN))
						.with(SushiGeta_kara1_5.STAGE_1_5, Integer.valueOf(3))); }

			if (i == 2) {
				worldIn.setBlockState(pos, Dish_Blocks.SUSHIGETA_kara.getDefaultState()
						.with(H_FACING, state.get(H_FACING))
						.with(DOWN, state.get(DOWN))
						.with(SushiGeta_kara1_5.STAGE_1_5, Integer.valueOf(4))); }

			if (i == 3) {
				worldIn.setBlockState(pos, Dish_Blocks.SUSHIGETA_kara.getDefaultState()
						.with(H_FACING, state.get(H_FACING))
						.with(DOWN, state.get(DOWN))
						.with(SushiGeta_kara1_5.STAGE_1_5, Integer.valueOf(5))); }

			if (i == 4) {
				worldIn.setBlockState(pos, Dish_Blocks.SUSHIGETA_kara.getDefaultState()
						.with(H_FACING, state.get(H_FACING))
						.with(DOWN, state.get(DOWN))
						.with(SushiGeta_kara1_5.STAGE_1_5, Integer.valueOf(1))); }
		}
		
		if (!itemstack.isEmpty()) { CMEvents.textFullItem(worldIn, pos, playerIn); }
		
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

		Direction direction = state.get(H_FACING);
		boolean flag= !((Boolean)state.get(DOWN)).booleanValue();

		switch(direction) {
		case SOUTH:
			return flag? AABB_SOUTH : DOWN_SOUTH;
		case WEST:
			return flag? AABB_WEST : DOWN_WEST;
		case NORTH:
		default:
			return flag? AABB_NORTH : DOWN_NORTH;
		case EAST:
			return flag? AABB_EAST : DOWN_EAST;
		}
	}

	/* Clone Item in Creative. */
	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items_Teatime.SUSHIGETA_kara);
	}

	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {

		if (inWater(state, worldIn, pos)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(worldIn));
			CMEvents.soundSnowBreak(worldIn, pos);
			worldIn.setBlockState(pos, Dish_Blocks.SUSHIGETA_kara.getDefaultState()
						.with(H_FACING, state.get(H_FACING))
						.with(DOWN, state.get(DOWN))
						.with(SushiGeta_kara1_5.STAGE_1_5, Integer.valueOf(5))
						.with(SushiGeta_kara1_5.WATERLOGGED, state.get(WATERLOGGED)), 3);
			this.dropRottenfood(worldIn, pos); }
		
		else { }
	}

}
