package com.ayutaki.chinjufumod.blocks.base;

import java.util.List;

import com.ayutaki.chinjufumod.ChinjufuMod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BaseSlabWType2 extends Block {

	/** ハーフブロックを応用(瓦ハーフ、なまこ壁ハーフ、しっくいハーフ) **/
	public static final PropertyBool CRA = PropertyBool.create("cra");
	public static final PropertyBool DOUBLE = PropertyBool.create("double");
	public static final PropertyEnum<BaseSlabWType2.SlabHalf> HALF = PropertyEnum.<BaseSlabWType2.SlabHalf>create("half", BaseSlabWType2.SlabHalf.class);

	protected static final AxisAlignedBB DOUBLE_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	protected static final AxisAlignedBB BOTTOM_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
	protected static final AxisAlignedBB TOP_AABB = new AxisAlignedBB(0.0D, 0.5D, 0.0D, 1.0D, 1.0D, 1.0D);

	public BaseSlabWType2(String unlocalizedName) {
		super(Material.WOOD);
		setUnlocalizedName(unlocalizedName);
		setRegistryName(new ResourceLocation(ChinjufuMod.MOD_ID, unlocalizedName));

		/** ハーフ・椅子・机=2, 障子=1, ガラス戸・窓=0, web=1, ice=3 **/
		setLightOpacity(2);

		setDefaultState(this.blockState.getBaseState()
				.withProperty(CRA, Boolean.valueOf(false))
				.withProperty(DOUBLE, Boolean.valueOf(false))
				.withProperty(HALF, BaseSlabWType2.SlabHalf.BOTTOM));
	}

	/* RightClick Action */


	/** Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the IBlockstate */
	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing,
			float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {

		IBlockState iblockstate = this.getDefaultState();

		if (facing.getAxis().isHorizontal()) {
			iblockstate = iblockstate.withProperty(HALF, (hitY > 0.6F)? BaseSlabWType2.SlabHalf.TOP : BaseSlabWType2.SlabHalf.BOTTOM);
		}

		else {
			iblockstate = iblockstate.withProperty(HALF, (facing == EnumFacing.UP)? BaseSlabWType2.SlabHalf.BOTTOM : BaseSlabWType2.SlabHalf.TOP);
		}

		return iblockstate;
	}

	/* 隣接ブロックへのチェック */
	@Override
	public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
		return true;
	}

	/* Data valueをBlockstate に変換…BaseFacingSlabW から FACING を CRA(t/f)に変更 */
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(CRA, Boolean.valueOf((meta & 1) != 0))
				.withProperty(DOUBLE, Boolean.valueOf((meta & 2) != 0))
				.withProperty(HALF, (meta & 4) == 0 ? BaseSlabWType2.SlabHalf.BOTTOM : BaseSlabWType2.SlabHalf.TOP);
	}

	/* Data valueの変換 */
	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		/** 「 i |= 4 」は「 i = i | 4 」で「 i = i OR i = 4 」**/
		if (((Boolean)state.getValue(CRA)).booleanValue()) { i |= 1; }
		if (state.getValue(DOUBLE) == true) { i |= 2; }
		if (state.getValue(HALF) != BaseSlabWType2.SlabHalf.TOP) { i |= 4; }

		return i;
	}

	@Override
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { CRA, DOUBLE, HALF });
	}

	public static enum SlabHalf implements IStringSerializable {
		TOP("top"),
		BOTTOM("bottom");

		private final String name;
		private SlabHalf(String name) { this.name = name; }
		public String toString() { return this.name; }
		public String getName() { return this.name; }
	}

	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {

		if (state.getValue(DOUBLE) == true) { return DOUBLE_AABB; }

		else { return (state.getValue(HALF) == BaseSlabWType2.SlabHalf.TOP)? TOP_AABB : BOTTOM_AABB; }
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean t_f) {

		boolean flag = (state.getValue(DOUBLE) == true);
		BaseSlabWType2.SlabHalf blockhalf = state.getValue(HALF);

		switch(blockhalf) {
		case TOP :
			super.addCollisionBoxToList(pos, entityBox, collidingBoxes, flag? DOUBLE_AABB : TOP_AABB);
			break;
			
		case BOTTOM :
		default:
			super.addCollisionBoxToList(pos, entityBox, collidingBoxes, flag? DOUBLE_AABB : BOTTOM_AABB);
			break;
		}
	}

	/* 上面に植木鉢やレッドストーンを置けるようにする */
	@Override
	public boolean isTopSolid(IBlockState state) {
		return (state.getValue(DOUBLE) == true || state.getValue(HALF) == BaseSlabWType2.SlabHalf.TOP);
	}

	/* 側面に松明などを置けるようにする */
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {

		if (state.getValue(DOUBLE) == true || (face == EnumFacing.UP && state.getValue(HALF) == BaseSlabWType2.SlabHalf.TOP)) {
			return BlockFaceShape.SOLID;
		}

		else {
			return (face == EnumFacing.DOWN && state.getValue(HALF) == BaseSlabWType2.SlabHalf.BOTTOM)? BlockFaceShape.SOLID : BlockFaceShape.UNDEFINED;
		}
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

}
