package com.ayutaki.chinjufumod.blocks.gate;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.material.PushReaction;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.DoorHingeSide;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BaseGate extends Block implements IWaterLoggable {

	/* Property */
	public static final DirectionProperty H_FACING = DirectionProperty.create("facing", Direction.Plane.HORIZONTAL);
	public static final BooleanProperty OPEN = BooleanProperty.create("open");
	public static final EnumProperty<DoorHingeSide> HINGE = EnumProperty.create("hinge", DoorHingeSide.class);
	public static final BooleanProperty POWERED = BooleanProperty.create("powered");
	public static final EnumProperty<DoubleBlockHalf> HALF = EnumProperty.create("half", DoubleBlockHalf.class);
	public static final BooleanProperty WATERLOGGED = BooleanProperty.create("waterlogged");
	/** 水中処理無しの場合と比較の結果、有りを採用 **/

	public BaseGate(AbstractBlock.Properties properties) {
		super(properties);

		/** Default blockstate **/
		registerDefaultState(this.defaultBlockState().setValue(H_FACING, Direction.NORTH)
				.setValue(OPEN, Boolean.valueOf(false))
				.setValue(HINGE, DoorHingeSide.LEFT)
				.setValue(POWERED, Boolean.valueOf(false))
				.setValue(HALF, DoubleBlockHalf.LOWER)
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* Destroy a DoubleBlock from DoublePlantBlock.class */
	@Override
	public void playerWillDestroy(World worldIn, BlockPos pos, BlockState state, PlayerEntity playerIn) {

		if (!worldIn.isClientSide) {
			if (playerIn.isCreative()) { breakLowerPart(worldIn, pos, state, playerIn); }
			else { dropResources(state, worldIn, pos, (TileEntity)null, playerIn, playerIn.getMainHandItem()); }
		}
		super.playerWillDestroy(worldIn, pos, state, playerIn);
	}

	@Override
	public void playerDestroy(World worldIn, PlayerEntity playerIn, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
		super.playerDestroy(worldIn, playerIn, pos, Blocks.AIR.defaultBlockState(), te, stack);
	}

	protected static void breakLowerPart(World worldIn, BlockPos pos, BlockState state, PlayerEntity playerIn) {
		DoubleBlockHalf half = state.getValue(HALF);
		if (half == DoubleBlockHalf.UPPER) {
			BlockPos downpos = pos.below();
			BlockState downstate = worldIn.getBlockState(downpos);

			if (downstate.getBlock() == state.getBlock() && downstate.getValue(HALF) == DoubleBlockHalf.LOWER) {
				worldIn.setBlock(downpos, Blocks.AIR.defaultBlockState(), 35);
				worldIn.levelEvent(playerIn, 2001, downpos, Block.getId(downstate));
			}
		}
	}

	@Override
	public BlockRenderType getRenderShape(BlockState state) {
		return BlockRenderType.MODEL;
	}

	@Override
	public boolean isPathfindable(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
		switch (type) {
		case LAND:
			return state.getValue(OPEN);
		case WATER:
			return false;
		case AIR:
			return state.getValue(OPEN);
		default:
			return false;
		}
	}

	/* Gives a value when placed. */
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
		PlayerEntity playerIn = context.getPlayer();
		BlockPos blockpos = context.getClickedPos();

		/** pos.up() = Replaceable block. **/
		if (blockpos.getY() < 255 && context.getLevel().getBlockState(blockpos.above()).canBeReplaced(context)) {
			World worldIn = context.getLevel();
			boolean flag = worldIn.hasNeighborSignal(blockpos) || worldIn.hasNeighborSignal(blockpos.above());

			if (playerIn.isCrouching()) {
				return this.defaultBlockState().setValue(H_FACING, context.getHorizontalDirection()).setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER))
						.setValue(HINGE, DoorHingeSide.RIGHT).setValue(POWERED, Boolean.valueOf(flag)).setValue(OPEN, Boolean.valueOf(flag)).setValue(HALF, DoubleBlockHalf.LOWER); }

			return this.defaultBlockState().setValue(H_FACING, context.getHorizontalDirection()).setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER))
					.setValue(HINGE, DoorHingeSide.LEFT).setValue(POWERED, Boolean.valueOf(flag)).setValue(OPEN, Boolean.valueOf(flag)).setValue(HALF, DoubleBlockHalf.LOWER);
		}

		else { return null; }
	}

	/* Add DoubleBlockHalf.UPPER on the Block. */
	@Override
	public void setPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		FluidState fluidUp = worldIn.getFluidState(pos.above());

		worldIn.setBlock(pos.above(), this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER).setValue(H_FACING, state.getValue(H_FACING))
				.setValue(WATERLOGGED, Boolean.valueOf(fluidUp.getType() == Fluids.WATER)), 3);
	}

	/* Limit the place. */
	@Override
	public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
		BlockPos downpos = pos.below();
		BlockState downstate = worldIn.getBlockState(downpos);

		/** Lower part is true. **/
		if (state.getValue(HALF) == DoubleBlockHalf.LOWER) { return true; }

		/** Upper part is this block. **/
		else { return downstate.getBlock() == this; }
	}

	/* Waterlogged */
	@SuppressWarnings("deprecation")
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	/* Update BlockState. */
	@SuppressWarnings("deprecation")
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, IWorld worldIn, BlockPos pos, BlockPos facingPos) {

		BlockState blockstate = super.updateShape(state, facing, facingState, worldIn, pos, facingPos);
		if (!blockstate.isAir(worldIn, pos)) {
			worldIn.getLiquidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
		}

		DoubleBlockHalf half = state.getValue(HALF);
		if (facing.getAxis() == Direction.Axis.Y && half == DoubleBlockHalf.LOWER == (facing == Direction.UP)) {
			return facingState.getBlock() == this && facingState.getValue(HALF) != half ? state
					.setValue(H_FACING, facingState.getValue(H_FACING)).setValue(OPEN, facingState.getValue(OPEN))
					.setValue(HINGE, facingState.getValue(HINGE)).setValue(POWERED, facingState.getValue(POWERED)) : Blocks.AIR.defaultBlockState();
		}
		else {
			return half == DoubleBlockHalf.LOWER && facing == Direction.DOWN && !state.canSurvive(worldIn, pos) ? Blocks.AIR
					.defaultBlockState() : super.updateShape(state, facing, facingState, worldIn, pos, facingPos);
		}
	}

	/* @deprecated call via {@link IBlockState#getMobilityFlag()} whenever possible. Implementing/overriding is fine. */
	@Override
	public PushReaction getPistonPushReaction(BlockState state) {
		return PushReaction.DESTROY;
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rotation) {
		return state.setValue(H_FACING, rotation.rotate(state.getValue(H_FACING)));
	}

	@SuppressWarnings("deprecation")
	public BlockState mirror(BlockState state, Mirror mirror) {
		return mirror == Mirror.NONE ? state : state.rotate(mirror.getRotation(state.getValue(H_FACING)));
	}

	/* Return a random long to be passed to {@link IBakedModel#getQuads}, used for random model rotations */
	@OnlyIn(Dist.CLIENT)
	public long getSeed(BlockState state, BlockPos pos) {
		return MathHelper.getSeed(pos.getX(), pos.below(state.getValue(HALF) == DoubleBlockHalf.LOWER ? 0 : 1).getY(), pos.getZ());
	}

	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(HALF, H_FACING, OPEN, HINGE, POWERED, WATERLOGGED);
	}

	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag tipFlag) {
		super.appendHoverText(stack, worldIn, tooltip, tipFlag);
		tooltip.add((new TranslationTextComponent("tips.block_gate")).withStyle(TextFormatting.GRAY));
	}

}