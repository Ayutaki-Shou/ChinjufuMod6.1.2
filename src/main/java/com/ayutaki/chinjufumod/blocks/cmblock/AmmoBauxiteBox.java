package com.ayutaki.chinjufumod.blocks.cmblock;

import com.ayutaki.chinjufumod.blocks.base.BaseFacingWater;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class AmmoBauxiteBox extends BaseFacingWater {

	/* Collision */
	protected static final VoxelShape AABB_BOX = VoxelShapes.or(Block.makeCuboidShape(0.01D, 0.0D, 0.01D, 15.99D, 15.0D, 15.99D),
			Block.makeCuboidShape(0.01D, 0.0D, 0.01D, 15.99D, 16.0D, 1.0D),
			Block.makeCuboidShape(0.01D, 0.0D, 15.0D, 15.99D, 16.0D, 15.99D),
			Block.makeCuboidShape(0.01D, 0.0D, 0.01D, 1.0D, 16.0D, 15.99D),
			Block.makeCuboidShape(15.0D, 0.0D, 0.01D, 15.99D, 16.0D, 15.99D));

	public AmmoBauxiteBox(Block.Properties properties) {
		super(properties);
	}

	/* プロパティに合わせて当たり判定 */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return AABB_BOX;
	}

	/* モブ湧き */
	@Override
	public boolean canEntitySpawn(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
		return false;
	}

}
