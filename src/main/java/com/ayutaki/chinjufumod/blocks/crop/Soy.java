package com.ayutaki.chinjufumod.blocks.crop;

import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

/* バニラの CropsBlock を継承 */
public class Soy extends CropsBlock {

	/* Collision */
	protected static final VoxelShape[] SHAPES = new VoxelShape[]{ Block.makeCuboidShape(1.0D, -1.0D, 1.0D, 15.0D, 1.0D, 15.0D),
			Block.makeCuboidShape(1.0D, -1.0D, 1.0D, 15.0D, 1.0D, 15.0D),
			Block.makeCuboidShape(1.0D, -1.0D, 1.0D, 15.0D, 3.0D, 15.0D),
			Block.makeCuboidShape(1.0D, -1.0D, 1.0D, 15.0D, 6.0D, 15.0D),
			Block.makeCuboidShape(1.0D, -1.0D, 1.0D, 15.0D, 8.0D, 15.0D),
			Block.makeCuboidShape(1.0D, -1.0D, 1.0D, 15.0D, 10.0D, 15.0D),
			Block.makeCuboidShape(1.0D, -1.0D, 1.0D, 15.0D, 12.0D, 15.0D),
			Block.makeCuboidShape(1.0D, -1.0D, 1.0D, 15.0D, 12.0D, 15.0D) };

	public Soy(Block.Properties properties) {
		super(properties);
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPES[state.get(this.getAgeProperty())];
	}

	/* Clone Item in Creative. */
	@Override
	protected IItemProvider getSeedsItem() {
		return Items_Teatime.SEEDS_SOY;
	}

}
