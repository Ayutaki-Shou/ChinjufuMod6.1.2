package com.ayutaki.chinjufumod.blocks.window;

import java.util.Random;

import com.ayutaki.chinjufumod.blocks.base.CollisionHelper;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.state.BlockStateHalf;
import com.ayutaki.chinjufumod.state.BlockStateHinge;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BaseWindowTall extends Block {

	/* Property */
	public static final PropertyDirection H_FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyBool OPEN = PropertyBool.create("open");
	public static final PropertyEnum<BlockStateHinge> HINGE = PropertyEnum.<BlockStateHinge>create("hinge", BlockStateHinge.class);
	public static final PropertyEnum<BlockStateHalf> HALF = PropertyEnum.<BlockStateHalf>create("half", BlockStateHalf.class);

	/* Collisionの数値 */
	protected static final AxisAlignedBB SOUTH_AABB_CLOSE = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.4375, 0.0, 0.0, 0.5625, 1.0, 1.0);
	protected static final AxisAlignedBB EAST_AABB_CLOSE = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.4375, 0.0, 0.0, 0.5625, 1.0, 1.0);
	protected static final AxisAlignedBB WEST_AABB_CLOSE = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.4375, 0.0, 0.0, 0.5625, 1.0, 1.0);
	protected static final AxisAlignedBB NORTH_AABB_CLOSE = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.4375, 0.0, 0.0, 0.5625, 1.0, 1.0);

	protected static final AxisAlignedBB SOUTH_AABB_OPEN_R = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.4375, 0.0125, 0.8625, 1.4375, 0.9875, 0.9875);
	protected static final AxisAlignedBB EAST_AABB_OPEN_R = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.4375, 0.0125, 0.8625, 1.4375, 0.9875, 0.9875);
	protected static final AxisAlignedBB WEST_AABB_OPEN_R = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.4375, 0.0125, 0.8625, 1.4375, 0.9875, 0.9875);
	protected static final AxisAlignedBB NORTH_AABB_OPEN_R = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.4375, 0.0125, 0.8625, 1.4375, 0.9875, 0.9875);

	protected static final AxisAlignedBB SOUTH_AABB_OPEN_L = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.4375, 0.0125, 0.0125, 1.4375, 0.9875, 0.1375);
	protected static final AxisAlignedBB EAST_AABB_OPEN_L = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.4375, 0.0125, 0.0125, 1.4375, 0.9875, 0.1375);
	protected static final AxisAlignedBB WEST_AABB_OPEN_L = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.4375, 0.0125, 0.0125, 1.4375, 0.9875, 0.1375);
	protected static final AxisAlignedBB NORTH_AABB_OPEN_L = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.4375, 0.0125, 0.0125, 1.4375, 0.9875, 0.1375);

	protected BaseWindowTall(Material materialIn) {
		super(materialIn);

		setHardness(1.0F);
		setResistance(1.0F);
		setSoundType(SoundType.WOOD);

		setDefaultState(this.blockState.getBaseState().withProperty(H_FACING, EnumFacing.NORTH)
				.withProperty(OPEN, Boolean.valueOf(false))
				.withProperty(HINGE, BlockStateHinge.RIGHT)
				.withProperty(HALF, BlockStateHalf.LOWER));

		/** ハーフ・椅子・机=2, 障子=1, ガラス戸・窓=0, web=1, ice=3 **/
		setLightOpacity(0);
	}

	/* Collision */
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {

		state = state.getActualState(source, pos);
		EnumFacing direction = (EnumFacing)state.getValue(H_FACING);
		boolean flag = !((Boolean)state.getValue(OPEN)).booleanValue();
		boolean flag1 = state.getValue(HINGE) == BlockStateHinge.LEFT;

		switch (direction) {
		case SOUTH:
			return flag ? SOUTH_AABB_CLOSE : (flag1 ? SOUTH_AABB_OPEN_L : SOUTH_AABB_OPEN_R);
			
		case EAST:
			return flag ? EAST_AABB_CLOSE : (flag1 ? EAST_AABB_OPEN_L : EAST_AABB_OPEN_R);
			
		case WEST:
			return flag ? WEST_AABB_CLOSE : (flag1 ? WEST_AABB_OPEN_L : WEST_AABB_OPEN_R);
			
		case NORTH:
		default:
			return flag ? NORTH_AABB_CLOSE : (flag1 ? NORTH_AABB_OPEN_L : NORTH_AABB_OPEN_R);
		}
	}

	public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
		return isOpen(combineMetadata(worldIn, pos));
	}

	public static boolean isOpen(IBlockAccess worldIn, BlockPos pos) {
		return isOpen(combineMetadata(worldIn, pos));
	}

	public static EnumFacing getFacing(IBlockAccess worldIn, BlockPos pos) {
		return getFacing(combineMetadata(worldIn, pos));
	}

	public static int combineMetadata(IBlockAccess worldIn, BlockPos pos) {

		IBlockState iblockstate = worldIn.getBlockState(pos);
		int i = iblockstate.getBlock().getMetaFromState(iblockstate);
		boolean flag = isTop(i);
		IBlockState iblockstate1 = worldIn.getBlockState(pos.down());
		int j = iblockstate1.getBlock().getMetaFromState(iblockstate1);
		int k = flag ? j : i;
		IBlockState iblockstate2 = worldIn.getBlockState(pos.up());
		int l = iblockstate2.getBlock().getMetaFromState(iblockstate2);
		int i1 = flag ? i : l;
		boolean flag1 = (i1 & 1) != 0;
		boolean flag2 = (i1 & 2) != 0;
		return removeHalfBit(k) | (flag ? 8 : 0) | (flag1 ? 16 : 0) | (flag2 ? 32 : 0);
	}

	protected static boolean isOpen(int combinedMeta) {
		return (combinedMeta & 4) != 0;
	}

	public static EnumFacing getFacing(int combinedMeta) {
		return EnumFacing.getHorizontal(combinedMeta & 3).rotateYCCW();
	}

	protected static boolean isTop(int meta) {
		return (meta & 8) != 0;
	}

	protected static int removeHalfBit(int meta) {
		return meta & 7;
	}

	/* Sound */
	private SoundEvent getCloseSound() {
		return SoundEvents_CM.WINDOW_CLOSE;
	}

	private SoundEvent getOpenSound() {
		return SoundEvents_CM.WINDOW_OPEN;
	}

	/* RightClick Action */
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		BlockPos blockpos = state.getValue(HALF) == BlockStateHalf.LOWER ? pos : pos.down();
		IBlockState iblockstate = pos.equals(blockpos) ? state : worldIn.getBlockState(blockpos);

		if (iblockstate.getBlock() != this) { return false; }

		else {
			state = iblockstate.cycleProperty(OPEN);
			worldIn.setBlockState(blockpos, state, 10);
			worldIn.markBlockRangeForRenderUpdate(blockpos, pos);
			worldIn.playSound(null, pos, state.getValue(OPEN) ? this.getOpenSound() : this.getCloseSound(), SoundCategory.BLOCKS, 1.0F, 1.0F);
			return true;
		}
	}


	/* 留め釘 村人AIが参照 他に参照がなく、無くてもドアは動く？
	public void toggleDoor(World worldIn, BlockPos pos, boolean open) {

		IBlockState iblockstate = worldIn.getBlockState(pos);

		if (iblockstate.getBlock() == this) {

			BlockPos blockpos = iblockstate.getValue(HALF) == BlockBlockStateHalf.LOWER ? pos : pos.down();
			IBlockState iblockstate1 = pos == blockpos ? iblockstate : worldIn.getBlockState(blockpos);

			if (iblockstate1.getBlock() == this && ((Boolean)iblockstate1.getValue(OPEN)).booleanValue() != open) {

				worldIn.setBlockState(blockpos, iblockstate1.withProperty(OPEN, Boolean.valueOf(open)), 10);
				worldIn.markBlockRangeForRenderUpdate(blockpos, pos);
				worldIn.playSound(null, pos, open ? this.getOpenSound() : this.getCloseSound(), SoundCategory.BLOCKS, 1.0F, 1.0F);
			}
		}
	} */

	/* 隣接ブロック変化への反応 */
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {

		if (state.getValue(HALF) == BlockStateHalf.UPPER) {

			BlockPos blockpos = pos.down();
			IBlockState iblockstate = worldIn.getBlockState(blockpos);

			if (iblockstate.getBlock() != this) {
				worldIn.setBlockToAir(pos);
			}
			else if (blockIn != this) {
				iblockstate.neighborChanged(worldIn, blockpos, blockIn, fromPos);
			}
		}

		else {

			boolean flag1 = false;
			BlockPos blockpos1 = pos.up();
			IBlockState iblockstate1 = worldIn.getBlockState(blockpos1);

			if (iblockstate1.getBlock() != this) {
				worldIn.setBlockToAir(pos);
				flag1 = true;
			}

			/* 下がガラス等だとアイテム化
			if (!worldIn.getBlockState(pos.down()).isSideSolid(worldIn, pos.down(), EnumFacing.UP)) {
				worldIn.setBlockToAir(pos);
				flag1 = true;

				if (iblockstate1.getBlock() == this) {
					worldIn.setBlockToAir(blockpos1);
				}
			} */

			if (flag1) {

				if (!worldIn.isRemote) {
					this.dropBlockAsItem(worldIn, pos, state, 0);
				}
			}

			else { }
		}
	}

	/* 設置制限 */
	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos).getMaterial().isReplaceable() &&
				worldIn.getBlockState(pos.up()).getMaterial().isReplaceable();
	}

	public EnumPushReaction getMobilityFlag(IBlockState state) {
		return EnumPushReaction.DESTROY;
	}

	/* Drop Item */
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn) {

		BlockPos blockpos = pos.down();
		BlockPos blockpos1 = pos.up();

		if (playerIn.capabilities.isCreativeMode && state.getValue(HALF) == BlockStateHalf.UPPER && worldIn.getBlockState(blockpos).getBlock() == this) {
			worldIn.setBlockToAir(blockpos);
		}

		if (state.getValue(HALF) == BlockStateHalf.LOWER && worldIn.getBlockState(blockpos1).getBlock() == this) {

			if (playerIn.capabilities.isCreativeMode) {
				worldIn.setBlockToAir(pos);
			}
			worldIn.setBlockToAir(blockpos1);
		}
	}

	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return state.getValue(HALF) == BlockStateHalf.UPPER ? Items.AIR : this.getItem();
	}

	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(this.getItem());
	}

	private Item getItem() {
		return null;
	}

	/* BlockState関連 */
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {

		if (state.getValue(HALF) == BlockStateHalf.LOWER) {

			IBlockState iblockstate = worldIn.getBlockState(pos.up());

			if (iblockstate.getBlock() == this) {
				state = state.withProperty(HINGE, iblockstate.getValue(HINGE));
			}
		}

		else {
			IBlockState iblockstate1 = worldIn.getBlockState(pos.down());

			if (iblockstate1.getBlock() == this) {
				state = state.withProperty(H_FACING, iblockstate1.getValue(H_FACING)).withProperty(OPEN, iblockstate1.getValue(OPEN));
			}
		}
		return state;
	}

	public IBlockState withRotation(IBlockState state, Rotation rot) {

		return state.getValue(HALF) != BlockStateHalf.LOWER ? state : state
				.withProperty(H_FACING, rot.rotate((EnumFacing)state.getValue(H_FACING)));
	}

	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return mirrorIn == Mirror.NONE ? state : state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(H_FACING))).cycleProperty(HINGE);
	}

	public IBlockState getStateFromMeta(int meta) {

		return (meta & 8) > 0 ? this.getDefaultState().withProperty(HALF, BlockStateHalf.UPPER)
				.withProperty(HINGE, (meta & 1) > 0 ? BlockStateHinge.LEFT : BlockStateHinge.RIGHT) : this.getDefaultState()
				.withProperty(HALF, BlockStateHalf.LOWER)
				.withProperty(H_FACING, EnumFacing.getHorizontal(meta & 3).rotateYCCW())
				.withProperty(OPEN, Boolean.valueOf((meta & 4) > 0));
	}

	public int getMetaFromState(IBlockState state) {

		int i = 0;

		if (state.getValue(HALF) == BlockStateHalf.UPPER) {
			i = i | 8;

			if (state.getValue(HINGE) == BlockStateHinge.LEFT) {
				i |= 1;
			}
		}

		else {
			i = i | ((EnumFacing)state.getValue(H_FACING)).rotateY().getHorizontalIndex();

			if (((Boolean)state.getValue(OPEN)).booleanValue()) {
				i |= 4;
			}
		}
		return i;
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { HALF, H_FACING, OPEN, HINGE });
	}

	/* 上面に植木鉢やレッドストーンを置けるようにする */
	@Override
	public boolean isTopSolid(IBlockState state) {
		return false;
	}

	/* 側面に松明などを置けるようにする */
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	/* Rendering */
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

}
