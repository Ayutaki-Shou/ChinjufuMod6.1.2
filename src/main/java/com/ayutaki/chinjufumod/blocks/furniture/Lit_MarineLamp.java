package com.ayutaki.chinjufumod.blocks.furniture;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.blocks.base.CollisionHelper;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;
import com.ayutaki.chinjufumod.registry.Lamp_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Lit_MarineLamp extends Block {

	public static final String ID = "lit_marinelamp";

	public static final PropertyDirection FACING = PropertyDirection.create("facing");

	private static final AxisAlignedBB AABB_UP = CollisionHelper.getBlockBounds(EnumFacing.UP, 0.375, 0.0, 0.375, 0.625, 0.4375, 0.625);
	private static final AxisAlignedBB AABB_DOWN = CollisionHelper.getBlockBounds(EnumFacing.DOWN, 0.375, 0.5625, 0.375, 0.625, 1.0, 0.625);
	private static final AxisAlignedBB AABB_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.0, 0.375, 0.375, 0.4375, 0.625, 0.625);
	private static final AxisAlignedBB AABB_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.0, 0.375, 0.375, 0.4375, 0.625, 0.625);
	private static final AxisAlignedBB AABB_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.0, 0.375, 0.375, 0.4375, 0.625, 0.625);
	private static final AxisAlignedBB AABB_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.0, 0.375, 0.375, 0.4375, 0.625, 0.625);

	public Lit_MarineLamp() {
		super(Material.WOOD);
		setRegistryName(new ResourceLocation(ChinjufuMod.MOD_ID, ID));
		setUnlocalizedName(ID);

		setSoundType(SoundType.METAL);
		setHardness(1.0F);
		setResistance(5.0F);
		/** ハーフ・机=2, 障子・椅子=1, ガラス戸・窓=0, web=1, ice=3 **/
		setLightOpacity(0);

		/* グロウストーン=1.0F 松明=0.9375F */
		setLightLevel(1.0F);

		setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.UP));
	}

	/* ブロック遷移 */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		CMEvents.soundStoneButton_Off(worldIn, pos);
		worldIn.setBlockState(pos, Lamp_Blocks.M_LAMP
				.getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);
		/** 'true' to not put anything on top. **/
		return true;
	}

	/* Collision */
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {

		switch ((EnumFacing)state.getValue(FACING)) {
		case SOUTH:
			return AABB_SOUTH;
			
		case EAST:
			return AABB_EAST;
			
		case WEST:
			return AABB_WEST;
			
		case NORTH:
			return AABB_NORTH;
			
		case UP:
		default:
			return AABB_UP;
			
		case DOWN:
			return AABB_DOWN;
		}
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		/** Have no collision. **/
		return NULL_AABB;
	}

	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
	}

	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
	}

	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return true;
	}

	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing,
			float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {

		IBlockState iblockstate = worldIn.getBlockState(pos.offset(facing.getOpposite()));

		if (iblockstate.getBlock() == Lamp_Blocks.LIT_M_LAMP) {
			EnumFacing direction = (EnumFacing)iblockstate.getValue(FACING);

			if (direction == facing) {
				return this.getDefaultState().withProperty(FACING, facing.getOpposite());
			}
		}
		return this.getDefaultState().withProperty(FACING, facing);
	}

	public IBlockState getStateFromMeta(int meta) {
		IBlockState iblockstate = this.getDefaultState();
		iblockstate = iblockstate.withProperty(FACING, EnumFacing.getFront(meta));
		return iblockstate;
	}

	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing)state.getValue(FACING)).getIndex();
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}

	public EnumPushReaction getMobilityFlag(IBlockState state) {
		return EnumPushReaction.NORMAL;
	}

	/* 上面に植木鉢やレッドストーンを置けるようにする */
	public boolean isTopSolid(IBlockState state) {
		return false;
	}

	/* 側面に松明などを置けるようにする */
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	/* Rendering */
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	/*Drop Item and Clone Item.*/
	public boolean canSilkHarvest(World worldIn, EntityPlayer playerIn, int x, int y, int z, int metadata) {
		return false;
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		stack.add(new ItemStack(Items_Chinjufu.M_LAMP, 1, 0));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Chinjufu.M_LAMP, 1, 0);
	}

}