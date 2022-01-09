package com.ayutaki.chinjufumod.blocks.garden;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Garden_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Items_Wadeco;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Ishitourou extends Block {

	/* Property */
	public static final PropertyInteger STAGE_1_4 = PropertyInteger.create("stage", 1, 4);

	public static final String ID = "block_ishitourou_stone";

	public Ishitourou() {
		super(Material.WOOD);
		setRegistryName(new ResourceLocation(ChinjufuMod.MOD_ID, ID));
		setUnlocalizedName(ID);

		setSoundType(SoundType.STONE);
		setHardness(1.0F);
		setResistance(5.0F);
		/** ハーフ・椅子・机=2, 障子=1, ガラス戸・窓=0, web=1, ice=3 **/
		setLightOpacity(1);

		setDefaultState(this.blockState.getBaseState()
				.withProperty(STAGE_1_4, Integer.valueOf(1)));
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		ItemStack itemstack = playerIn.getHeldItem(hand);
		Item item = itemstack.getItem();
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (item == Items.FLINT_AND_STEEL) {
			CMEvents.soundFlint(worldIn, pos);

			if (i == 1) { worldIn.setBlockState(pos, Garden_Blocks.LIT_ISHITOUROU.getDefaultState()
						.withProperty(Lit_Ishitourou.STAGE_1_4, Integer.valueOf(1))); }

			if (i == 2) { worldIn.setBlockState(pos, Garden_Blocks.LIT_ISHITOUROU.getDefaultState()
						.withProperty(Lit_Ishitourou.STAGE_1_4, Integer.valueOf(2))); }

			if (i == 3) { worldIn.setBlockState(pos, Garden_Blocks.LIT_ISHITOUROU.getDefaultState()
						.withProperty(Lit_Ishitourou.STAGE_1_4, Integer.valueOf(3))); }

			if (i == 4) { worldIn.setBlockState(pos, Garden_Blocks.LIT_ISHITOUROU.getDefaultState()
						.withProperty(Lit_Ishitourou.STAGE_1_4, Integer.valueOf(4))); }

			itemstack.damageItem(1, playerIn); }

		if (item == Items_Teatime.Item_MATCH) {
			CMEvents.Consume_1Item(playerIn, hand);
			CMEvents.soundFlint(worldIn, pos);

			if (i == 1) { worldIn.setBlockState(pos, Garden_Blocks.LIT_ISHITOUROU.getDefaultState()
						.withProperty(Lit_Ishitourou.STAGE_1_4, Integer.valueOf(1))); }

			if (i == 2) { worldIn.setBlockState(pos, Garden_Blocks.LIT_ISHITOUROU.getDefaultState()
						.withProperty(Lit_Ishitourou.STAGE_1_4, Integer.valueOf(2))); }

			if (i == 3) { worldIn.setBlockState(pos, Garden_Blocks.LIT_ISHITOUROU.getDefaultState()
						.withProperty(Lit_Ishitourou.STAGE_1_4, Integer.valueOf(3))); }

			if (i == 4) { worldIn.setBlockState(pos, Garden_Blocks.LIT_ISHITOUROU.getDefaultState()
						.withProperty(Lit_Ishitourou.STAGE_1_4, Integer.valueOf(4))); } }
		
		if (item != Items.FLINT_AND_STEEL && item != Items_Teatime.Item_MATCH) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		
		/** 'true' to not put anything on top. **/
		return true;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.9375D, 0.9375D);
	}

	/* Data value */
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(STAGE_1_4, Integer.valueOf(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((Integer)state.getValue(STAGE_1_4)).intValue();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { STAGE_1_4 });
	}

	@Override
	public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
		return (4 - ((Integer)blockState.getValue(STAGE_1_4)).intValue()) * 2;
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

		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (i == 1) { stack.add(new ItemStack(Items_Wadeco.ISHITOUROU, 1, 1)); }
		if (i == 2) { stack.add(new ItemStack(Items_Wadeco.ISHITOUROU, 1, 2)); }
		if (i == 3) { stack.add(new ItemStack(Items_Wadeco.ISHITOUROU, 1, 3)); }
		if (i == 4) { stack.add(new ItemStack(Items_Wadeco.ISHITOUROU, 1, 4)); }
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {

		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (i == 2) { return new ItemStack(Items_Wadeco.ISHITOUROU, 1, 2); }
		if (i == 3) { return new ItemStack(Items_Wadeco.ISHITOUROU, 1, 3); }
		if (i == 4) { return new ItemStack(Items_Wadeco.ISHITOUROU, 1, 4); }
		return new ItemStack(Items_Wadeco.ISHITOUROU, 1, 1);
	}

}
