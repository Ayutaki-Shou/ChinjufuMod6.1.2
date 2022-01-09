package com.ayutaki.chinjufumod.blocks.bamboo;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.entity.EntitySittableBlock;
import com.ayutaki.chinjufumod.registry.Items_Wadeco;
import com.ayutaki.chinjufumod.registry.JPDeco_Blocks;
import com.ayutaki.chinjufumod.state.BlockStateType;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BambooStairs extends Block {

	/** 上下と方角、変化のあるブロックであるトラップドアを応用 **/
	public static final PropertyDirection H_FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyEnum<BlockStateType> TYPE = PropertyEnum.<BlockStateType>create("type", BlockStateType.class);

	private static final AxisAlignedBB AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);

	protected static final AxisAlignedBB AABB_SLAB_TOP = new AxisAlignedBB(0.0D, 0.5D, 0.0D, 1.0D, 1.0D, 1.0D);
	protected static final AxisAlignedBB AABB_TOP_WEST = new AxisAlignedBB(0.5D, 0.5D, 0.0D, 1.0D, 1.0D, 1.0D);
	protected static final AxisAlignedBB AABB_TOP_EAST = new AxisAlignedBB(0.0D, 0.5D, 0.0D, 0.5D, 1.0D, 1.0D);
	protected static final AxisAlignedBB AABB_TOP_NORTH = new AxisAlignedBB(0.0D, 0.5D, 0.5D, 1.0D, 1.0D, 1.0D);
	protected static final AxisAlignedBB AABB_TOP_SOUTH = new AxisAlignedBB(0.0D, 0.5D, 0.0D, 1.0D, 1.0D, 0.5D);

	protected static final AxisAlignedBB AABB_SLAB_BOTTOM = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
	protected static final AxisAlignedBB AABB_BOT_WEST = new AxisAlignedBB(0.5D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
	protected static final AxisAlignedBB AABB_BOT_EAST = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.5D, 0.5D, 1.0D);
	protected static final AxisAlignedBB AABB_BOT_NORTH = new AxisAlignedBB(0.0D, 0.0D, 0.5D, 1.0D, 0.5D, 1.0D);
	protected static final AxisAlignedBB AABB_BOT_SOUTH = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 0.5D);


	public BambooStairs(String unlocalizedName) {
		super(Material.WOOD);
		setUnlocalizedName(unlocalizedName);
		setRegistryName(new ResourceLocation(ChinjufuMod.MOD_ID, unlocalizedName));
		setCreativeTab(ChinjufuModTabs.WADECO);

		setHardness(1.0F);
		setResistance(5.0F);
		setSoundType(SoundType.WOOD);
		/** ハーフ・椅子・机=2, 障子=1, ガラス戸・窓=0, web=1, ice=3 **/
		setLightOpacity(2);

		setDefaultState(this.blockState.getBaseState()
				.withProperty(H_FACING, EnumFacing.NORTH)
				.withProperty(TYPE, BlockStateType.BOTTOM));
	}

	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing,
			float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {

		IBlockState iblockstate = this.getDefaultState();

		if (facing.getAxis().isHorizontal()) {
			iblockstate = iblockstate.withProperty(H_FACING, facing);
			iblockstate = iblockstate.withProperty(TYPE, (hitY > 0.5F)? BlockStateType.TOP : BlockStateType.BOTTOM);
		}

		else {
			iblockstate = iblockstate.withProperty(H_FACING, placer.getHorizontalFacing().getOpposite());
			iblockstate = iblockstate.withProperty(TYPE, (facing == EnumFacing.UP)? BlockStateType.BOTTOM : BlockStateType.TOP);
		}

		return iblockstate;
	}

	@Override
	public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
		return true;
	}

	protected static EnumFacing getFacing(int meta) {
		switch (meta & 3) {
		case 0:
			return EnumFacing.NORTH;
		case 1:
			return EnumFacing.SOUTH;
		case 2:
			return EnumFacing.WEST;
		case 3:
		default:
			return EnumFacing.EAST;
		}
	}

	protected static int getMetaForFacing(EnumFacing facing) {
		switch (facing) {
		case NORTH:
			return 0;
		case SOUTH:
			return 1;
		case WEST:
			return 2;
		case EAST:
		default:
			return 3;
		}
	}

	/* Data valueをBlockstate に変換…トラップドアの OPEN(t/f) を削除 */
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(H_FACING, getFacing(meta))
				.withProperty(TYPE, (meta & 4) == 0 ? BlockStateType.BOTTOM : BlockStateType.TOP);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		i = i | getMetaForFacing((EnumFacing)state.getValue(H_FACING));
		if (state.getValue(TYPE) == BlockStateType.TOP) { i |= 4; }
		return i;
	}

	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(H_FACING, rot.rotate((EnumFacing)state.getValue(H_FACING)));
	}

	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(H_FACING)));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { H_FACING, TYPE });
	}

	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB;
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean t_f) {

		if (!(entityIn instanceof EntitySittableBlock)) {

			EnumFacing facing = state.getValue(H_FACING);
			BlockStateType half = state.getValue(TYPE);
			switch(facing) {

			case SOUTH:
				if (half != BlockStateType.BOTTOM) { super.addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_BOT_SOUTH); }
				if (half == BlockStateType.BOTTOM) { super.addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_TOP_SOUTH); }
				break;
			case EAST:
				if (half != BlockStateType.BOTTOM) { super.addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_BOT_EAST); }
				if (half == BlockStateType.BOTTOM) { super.addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_TOP_EAST); }
				break;
			case WEST:
				if (half != BlockStateType.BOTTOM) { super.addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_BOT_WEST); }
				if (half == BlockStateType.BOTTOM) { super.addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_TOP_WEST); }
				break;
			case NORTH:
			default:
				if (half != BlockStateType.BOTTOM) { super.addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_BOT_NORTH); }
				if (half == BlockStateType.BOTTOM) { super.addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_TOP_NORTH); }
				break;
			}
			if (half != BlockStateType.BOTTOM) { super.addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_SLAB_TOP); }
			if (half == BlockStateType.BOTTOM) { super.addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_SLAB_BOTTOM); }
		}
	}

	/* 上面に植木鉢やレッドストーンを置けるようにする */
	@Override
	public boolean isTopSolid(IBlockState state) {
		return state.getValue(TYPE) == BlockStateType.TOP;
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

	/*Drop Item and Clone Item.*/
	public boolean canSilkHarvest(World worldIn, EntityPlayer playerIn, int x, int y, int z, int metadata) {
		return false;
	}

	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();

		if (this == JPDeco_Blocks.TAKE_ST) { stack.add(new ItemStack(Items_Wadeco.TAKE_ST, 1, 0)); }
		if (this == JPDeco_Blocks.TAKE_STY) { stack.add(new ItemStack(Items_Wadeco.TAKE_STY, 1, 0)); }
		if (this == JPDeco_Blocks.TAKE_STK) { stack.add(new ItemStack(Items_Wadeco.TAKE_STK, 1, 0)); }
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		if (this == JPDeco_Blocks.TAKE_STY) { return new ItemStack(Items_Wadeco.TAKE_STY); }
		if (this == JPDeco_Blocks.TAKE_STK) { return new ItemStack(Items_Wadeco.TAKE_STK); }
		return new ItemStack(Items_Wadeco.TAKE_ST);
	}

}
