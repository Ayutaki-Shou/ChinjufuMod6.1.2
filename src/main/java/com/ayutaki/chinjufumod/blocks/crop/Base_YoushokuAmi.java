package com.ayutaki.chinjufumod.blocks.crop;

import net.minecraft.block.AirBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class Base_YoushokuAmi extends Block implements IWaterLoggable {

	/* Property */
	public static final IntegerProperty STAGE_1_9 = IntegerProperty.create("stage", 1, 9);
	public static final IntegerProperty AGE_1_12 = IntegerProperty.create("age", 1, 12);
	public static final BooleanProperty WATERLOGGED = BooleanProperty.create("waterlogged");
	public static final BooleanProperty DOWN = BooleanProperty.create("down");
	public static final BooleanProperty UP = BooleanProperty.create("up");
	public static final BooleanProperty WAKE = BooleanProperty.create("wake");

	/* Collision */
	protected static final VoxelShape AABB_BOX = Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);
	
	protected static final VoxelShape AABB_1 = VoxelShapes.or(Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 16.0D));
	protected static final VoxelShape AABB_2 = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D);
	protected static final VoxelShape AABB_3 = VoxelShapes.or(Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D),
			Block.makeCuboidShape(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D));
	protected static final VoxelShape AABB_4 = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 16.0D);
	protected static final VoxelShape AABB_6 = Block.makeCuboidShape(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape AABB_7 = VoxelShapes.or(Block.makeCuboidShape(0.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 16.0D));
	protected static final VoxelShape AABB_8 = Block.makeCuboidShape(0.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape AABB_9 = VoxelShapes.or(Block.makeCuboidShape(0.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D),
			Block.makeCuboidShape(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D));	
	
	protected static final VoxelShape BOT_BASE = Block.makeCuboidShape(0.0D, -1.0D, 0.0D, 16.0D, 2.0D, 16.0D);
	protected static final VoxelShape BOT_1 = VoxelShapes.or(AABB_1, BOT_BASE);
	protected static final VoxelShape BOT_2 = VoxelShapes.or(AABB_2, BOT_BASE);
	protected static final VoxelShape BOT_3 = VoxelShapes.or(AABB_3, BOT_BASE);
	protected static final VoxelShape BOT_4 = VoxelShapes.or(AABB_4, BOT_BASE);
	protected static final VoxelShape BOT_6 = VoxelShapes.or(AABB_6, BOT_BASE);
	protected static final VoxelShape BOT_7 = VoxelShapes.or(AABB_7, BOT_BASE);
	protected static final VoxelShape BOT_8 = VoxelShapes.or(AABB_8, BOT_BASE);
	protected static final VoxelShape BOT_9 = VoxelShapes.or(AABB_9, BOT_BASE);
	
	protected static final VoxelShape TAKE_1 = VoxelShapes.or(Block.makeCuboidShape(0.0D, 13.0D, 0.0D, 16.0D, 16.0D, 3.0D),
			Block.makeCuboidShape(0.0D, 13.0D, 3.0D, 3.0D, 16.0D, 16.0D));
	protected static final VoxelShape TAKE_2 = Block.makeCuboidShape(0.0D, 13.0D, 0.0D, 16.0D, 16.0D, 3.0D);
	protected static final VoxelShape TAKE_3 = VoxelShapes.or(Block.makeCuboidShape(0.0D, 13.0D, 0.0D, 16.0D, 16.0D, 3.0D),
			Block.makeCuboidShape(13.0D, 13.0D, 3.0D, 16.0D, 16.0D, 16.0D));
	protected static final VoxelShape TAKE_4 = Block.makeCuboidShape(0.0D, 13.0D, 0.0D, 3.0D, 16.0D, 16.0D);
	protected static final VoxelShape TAKE_6 = Block.makeCuboidShape(13.0D, 13.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape TAKE_7 = VoxelShapes.or(Block.makeCuboidShape(0.0D, 13.0D, 13.0D, 16.0D, 16.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 13.0D, 0.0D, 3.0D, 16.0D, 13.0D));
	protected static final VoxelShape TAKE_8 = Block.makeCuboidShape(0.0D, 13.0D, 13.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape TAKE_9 = VoxelShapes.or(Block.makeCuboidShape(0.0D, 13.0D, 13.0D, 16.0D, 16.0D, 16.0D),
			Block.makeCuboidShape(13.0D, 13.0D, 0.0D, 16.0D, 16.0D, 13.0D));
	
	protected static final VoxelShape TOP_1 = VoxelShapes.or(AABB_1, TAKE_1);
	protected static final VoxelShape TOP_2 = VoxelShapes.or(AABB_2, TAKE_2);
	protected static final VoxelShape TOP_3 = VoxelShapes.or(AABB_3, TAKE_3);
	protected static final VoxelShape TOP_4 = VoxelShapes.or(AABB_4, TAKE_4);
	protected static final VoxelShape TOP_6 = VoxelShapes.or(AABB_6, TAKE_6);
	protected static final VoxelShape TOP_7 = VoxelShapes.or(AABB_7, TAKE_7);
	protected static final VoxelShape TOP_8 = VoxelShapes.or(AABB_8, TAKE_8);
	protected static final VoxelShape TOP_9 = VoxelShapes.or(AABB_9, TAKE_9);
	
	protected static final VoxelShape TOPBOT_1 = VoxelShapes.or(AABB_1, TAKE_1, BOT_BASE);
	protected static final VoxelShape TOPBOT_2 = VoxelShapes.or(AABB_2, TAKE_2, BOT_BASE);
	protected static final VoxelShape TOPBOT_3 = VoxelShapes.or(AABB_3, TAKE_3, BOT_BASE);
	protected static final VoxelShape TOPBOT_4 = VoxelShapes.or(AABB_4, TAKE_4, BOT_BASE);
	protected static final VoxelShape TOPBOT_6 = VoxelShapes.or(AABB_6, TAKE_6, BOT_BASE);
	protected static final VoxelShape TOPBOT_7 = VoxelShapes.or(AABB_7, TAKE_7, BOT_BASE);
	protected static final VoxelShape TOPBOT_8 = VoxelShapes.or(AABB_8, TAKE_8, BOT_BASE);
	protected static final VoxelShape TOPBOT_9 = VoxelShapes.or(AABB_9, TAKE_9, BOT_BASE);
	
	public Base_YoushokuAmi(Block.Properties properties) {
		super(properties);
		
		/** Default blockstate **/
		setDefaultState(this.stateContainer.getBaseState().with(STAGE_1_9, Integer.valueOf(5))
				.with(AGE_1_12, Integer.valueOf(1))
				.with(DOWN, Boolean.valueOf(false))
				.with(UP, Boolean.valueOf(false))
				.with(WAKE, Boolean.valueOf(false))
				.with(WATERLOGGED, Boolean.valueOf(false)));
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		int stage = state.get(STAGE_1_9);
		
		switch (stage) {
		case 5 :
		default : return AABB_BOX;
		case 1 :
		case 2 :
		case 3 :
		case 4 :
		case 6 :
		case 7 :
		case 8 :
		case 9 : return VoxelShapes.empty();
		}
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		int stage = state.get(STAGE_1_9);
		
		boolean down = state.get(DOWN);
		boolean up = state.get(UP);
		
		if (down == false) {
			if (up == false) {
				switch (stage) {
				case 1 :
				default : return BOT_1;
				case 2 : return BOT_2;
				case 3 : return BOT_3;
				case 4 : return BOT_4;
				case 5 : return BOT_BASE;
				case 6 : return BOT_6;
				case 7 : return BOT_7;
				case 8 : return BOT_8;
				case 9 : return BOT_9;
				}
			}
			
			else {
				switch (stage) {
				case 1 :
				default : return TOPBOT_1;
				case 2 : return TOPBOT_2;
				case 3 : return TOPBOT_3;
				case 4 : return TOPBOT_4;
				case 5 : return BOT_BASE;
				case 6 : return TOPBOT_6;
				case 7 : return TOPBOT_7;
				case 8 : return TOPBOT_8;
				case 9 : return TOPBOT_9;
				}
			}
		} // Down block is Not this.
		
		else {
			if (up == false) {
				switch (stage) {
				case 1 :
				default : return AABB_1;
				case 2 : return AABB_2;
				case 3 : return AABB_3;
				case 4 : return AABB_4;
				case 5 : return VoxelShapes.empty();
				case 6 : return AABB_6;
				case 7 : return AABB_7;
				case 8 : return AABB_8;
				case 9 : return AABB_9;
				}
			}
			
			else {
				switch (stage) {
				case 1 :
				default : return TOP_1;
				case 2 : return TOP_2;
				case 3 : return TOP_3;
				case 4 : return TOP_4;
				case 5 : return VoxelShapes.empty();
				case 6 : return TOP_6;
				case 7 : return TOP_7;
				case 8 : return TOP_8;
				case 9 : return TOP_9;
				}
			}
		} // Down block is this.
	}
	
	/* Connect the blocks. */
	protected boolean connectThis(IBlockReader worldIn, BlockPos pos, Direction face) {
		BlockPos newPos = pos.offset(face);
		BlockState state = worldIn.getBlockState(newPos);
		Block block = state.getBlock();

		return block instanceof Base_YoushokuAmi;
	}
	
	protected boolean connectAir(IBlockReader worldIn, BlockPos pos, Direction face) {
		BlockPos newPos = pos.offset(face);
		BlockState state = worldIn.getBlockState(newPos);
		Block block = state.getBlock();

		return block instanceof AirBlock;
	}
	
	/* Waterlogged */
	@SuppressWarnings("deprecation")
	public IFluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}

	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld worldIn, BlockPos pos, BlockPos facingPos) {
		if ((Boolean)state.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn)); }
		
		if (!state.isValidPosition(worldIn, pos)) { return Blocks.AIR.getDefaultState(); }
		
		boolean down = connectThis(worldIn, pos, Direction.DOWN);
		boolean up = connectAir(worldIn, pos, Direction.UP);
		return state.with(DOWN, down).with(UP, up);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		int stage = state.get(STAGE_1_9);
		
		double x = (double) pos.getX();
		double y = (double) pos.getY();
		double z = (double) pos.getZ();
		
		if (stage == 1) { return worldIn.getBlockState(new BlockPos(x + 1, y, z)).getBlock() instanceof Base_YoushokuAmi; }
		if (stage == 2) { return worldIn.getBlockState(new BlockPos(x, y, z + 1)).getBlock() instanceof Base_YoushokuAmi; }
		if (stage == 3) { return worldIn.getBlockState(new BlockPos(x - 1, y, z)).getBlock() instanceof Base_YoushokuAmi; }
		if (stage == 4) { return worldIn.getBlockState(new BlockPos(x + 1, y, z)).getBlock() instanceof Base_YoushokuAmi; }
		if (stage == 6) { return worldIn.getBlockState(new BlockPos(x - 1, y, z)).getBlock() instanceof Base_YoushokuAmi; }
		if (stage == 7) { return worldIn.getBlockState(new BlockPos(x + 1, y, z)).getBlock() instanceof Base_YoushokuAmi; }
		if (stage == 8) { return worldIn.getBlockState(new BlockPos(x, y, z - 1)).getBlock() instanceof Base_YoushokuAmi; }
		if (stage == 9) { return worldIn.getBlockState(new BlockPos(x - 1, y, z)).getBlock() instanceof Base_YoushokuAmi; }
		return super.isValidPosition(state, worldIn, pos);
	}
	
	/* Create Blockstate */
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(AGE_1_12, DOWN, STAGE_1_9, UP, WAKE, WATERLOGGED);
	}

	/* Destroy at the same time. & Drop item. */
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity playerIn) {

		double x = (double) pos.getX();
		double y = (double) pos.getY();
		double z = (double) pos.getZ();
		
		BlockState state1 = worldIn.getBlockState(new BlockPos(x - 1, y, z - 1));
		BlockState state2 = worldIn.getBlockState(new BlockPos(x, y, z - 1));
		BlockState state3 = worldIn.getBlockState(new BlockPos(x + 1, y, z - 1));
		BlockState state4 = worldIn.getBlockState(new BlockPos(x - 1, y, z));
		BlockState state6 = worldIn.getBlockState(new BlockPos(x + 1, y, z));
		BlockState state7 = worldIn.getBlockState(new BlockPos(x - 1, y, z + 1));
		BlockState state8 = worldIn.getBlockState(new BlockPos(x, y, z + 1));
		BlockState state9 = worldIn.getBlockState(new BlockPos(x + 1, y, z + 1));

		/** if 内のブロックを同時に破壊。false だと同時破壊側のドロップは無し **/
		if (state1.getBlock() instanceof Base_YoushokuAmi) { worldIn.destroyBlock(new BlockPos(x - 1, y, z - 1), false); }
		if (state2.getBlock() instanceof Base_YoushokuAmi) { worldIn.destroyBlock(new BlockPos(x, y, z - 1), false); }
		if (state3.getBlock() instanceof Base_YoushokuAmi) { worldIn.destroyBlock(new BlockPos(x + 1, y, z - 1), false); }
		if (state4.getBlock() instanceof Base_YoushokuAmi) { worldIn.destroyBlock(new BlockPos(x - 1, y, z), false); }
		if (state6.getBlock() instanceof Base_YoushokuAmi) { worldIn.destroyBlock(new BlockPos(x + 1, y, z), false); }
		if (state7.getBlock() instanceof Base_YoushokuAmi) { worldIn.destroyBlock(new BlockPos(x - 1, y, z + 1), false); }
		if (state8.getBlock() instanceof Base_YoushokuAmi) { worldIn.destroyBlock(new BlockPos(x, y, z + 1), false); }
		if (state9.getBlock() instanceof Base_YoushokuAmi) { worldIn.destroyBlock(new BlockPos(x + 1, y, z + 1), false); }
		super.onBlockHarvested(worldIn, pos, state, playerIn);
	}

	/* 窒息 */
	@Override
	public boolean causesSuffocation(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return false;
	}

	/* 立方体 */
	@Override
	public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return false;
	}

	/* モブ湧き */
	@Override
	public boolean canEntitySpawn(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
		return false;
	}
	
}
