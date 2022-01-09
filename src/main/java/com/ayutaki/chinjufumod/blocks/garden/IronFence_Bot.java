package com.ayutaki.chinjufumod.blocks.garden;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.blocks.base.BaseFacingSapo;
import com.ayutaki.chinjufumod.registry.Garden_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Wadeco;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class IronFence_Bot extends BaseFacingSapo {

	public static final String ID = "block_ironfence_bot";

	public static final PropertyEnum<Type> TYPE = PropertyEnum.create("type", Type.class);

	private static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB( 0.0D, 0.0D, 0.46875D, 1.0D, 1.6875D, 0.53125D);
	private static final AxisAlignedBB EAST_AABB = new AxisAlignedBB( 0.46875D, 0.0D, 0.0D, 0.53125D, 1.6875D, 1.0D);
	private static final AxisAlignedBB WEST_AABB = new AxisAlignedBB( 0.46875D, 0.0D, 0.0D, 0.53125D, 1.6875D, 1.0D);
	private static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB( 0.0D, 0.0D, 0.46875D, 1.0D, 1.6875D, 0.53125D);

	public IronFence_Bot() {
		super(Material.WOOD);
		setRegistryName(new ResourceLocation(ChinjufuMod.MOD_ID, ID));
		setUnlocalizedName(ID);

		setSoundType(SoundType.METAL);
		setHardness(1.0F);
		setResistance(5.0F);
		/** ハーフ・椅子・机=2, 障子=1, ガラス戸・窓=0, web=1, ice=3 **/
		setLightOpacity(0);
	}

	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {

		state = state.getActualState(source, pos);
		EnumFacing direction = (EnumFacing)state.getValue(H_FACING);

		switch (direction) {
		case SOUTH:
			return SOUTH_AABB;
			
		case EAST:
			return EAST_AABB;
			
		case WEST:
			return WEST_AABB;
			
		case NORTH:
		default:
			return NORTH_AABB;
		}
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean t_f) {

		EnumFacing direction = state.getValue(H_FACING);

		switch (direction) {
		case SOUTH:
			super.addCollisionBoxToList(pos, entityBox, collidingBoxes, SOUTH_AABB);
			break;

		case EAST:
			super.addCollisionBoxToList(pos, entityBox, collidingBoxes, EAST_AABB);
			break;

		case WEST:
			super.addCollisionBoxToList(pos, entityBox, collidingBoxes, WEST_AABB);
			break;

		case NORTH :
		default:
			super.addCollisionBoxToList(pos, entityBox, collidingBoxes, NORTH_AABB);
			break;
		}
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {

		EnumFacing facing = (EnumFacing) state.getValue(H_FACING);
		IBlockState left_block = worldIn.getBlockState(pos.offset(facing.rotateYCCW()));
		IBlockState right_block = worldIn.getBlockState(pos.offset(facing.rotateY()));
		boolean left = left_block.getBlock() instanceof IronFence_Bot && left_block.getValue(H_FACING).equals(facing);
		boolean right = right_block.getBlock() instanceof IronFence_Bot && right_block.getValue(H_FACING).equals(facing);

		 if (right) {

			if (left) { return state.withProperty(TYPE, Type.BOTH); }

			else { return state.withProperty(TYPE, Type.RIGHT); }
		}

		else if (left) {

			if (right) { return state.withProperty(TYPE, Type.BOTH); }

			else { return state.withProperty(TYPE, Type.LEFT); }
		}
		return state.withProperty(TYPE, Type.DEFAULT);
	}


	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { H_FACING, TYPE });
	}

	public static enum Type implements IStringSerializable {

		DEFAULT, LEFT, RIGHT, BOTH ;

		@Override
		public String getName() { return this.toString().toLowerCase(); }
	}

	/* 同時破壊とドロップの指定 */
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn) {
			if (worldIn.getBlockState(pos.up()).getBlock() == Garden_Blocks.TETSUSAKU_TOP) {
				worldIn.destroyBlock(pos.up(), false); }
	}

	/* 設置制限 */
	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos).getMaterial().isReplaceable() &&
				worldIn.getBlockState(pos.up()).getMaterial().isReplaceable();
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
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	/* Harvest by Pickaxe. */
	@Nullable
	@Override
	public String getHarvestTool(IBlockState state) {
		return "pickaxe";
	}

	@Override
	public int getHarvestLevel(IBlockState state) {
		return 0;
	}

	/*Drop Item and Clone Item.*/
	public boolean canSilkHarvest(World worldIn, EntityPlayer playerIn, int x, int y, int z, int metadata) {
		return false;
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		stack.add(new ItemStack(Items_Wadeco.TETSUSAKU_BOT, 1, 0));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Wadeco.TETSUSAKU_BOT, 1, 0);
	}

}
