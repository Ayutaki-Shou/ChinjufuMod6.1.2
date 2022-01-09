package com.ayutaki.chinjufumod.blocks.garden;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Base_Niwaishi extends Block {

	/* Property */
	public static final PropertyInteger STAGE_0_15 = PropertyInteger.create("stage", 0, 15);

	public Base_Niwaishi() {
		super(Material.WOOD);

		setHardness(2.0F);
		setResistance(10.0F);
		setSoundType(SoundType.STONE);
		/** ハーフ・椅子・机=2, 障子=1, ガラス戸・窓=0, web=1, ice=3 **/
		setLightOpacity(2);

		setDefaultState(this.blockState.getBaseState().withProperty(STAGE_0_15, Integer.valueOf(0)));
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		boolean mode = playerIn.capabilities.isCreativeMode;
		
		ItemStack itemstack = playerIn.getHeldItem(hand);
		Item item = itemstack.getItem();
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();

		if (itemstack.isEmpty()) {
			if (playerIn.isSneaking()) {
				if (i == 0 || i == 2 || i == 4 || i == 6 || i == 8 || i == 10 || i == 12|| i == 14) {
					CMEvents.soundStonePlace(worldIn, pos);
					worldIn.setBlockState(pos, this.getDefaultState().withProperty(STAGE_0_15, Integer.valueOf(i + 1)), 3); }
				
				if (i == 1 || i == 3 || i == 5 || i == 7 || i == 9 || i == 11 || i == 13|| i == 15) {
					CMEvents.soundStonePlace(worldIn, pos);
					worldIn.setBlockState(pos, this.getDefaultState().withProperty(STAGE_0_15, Integer.valueOf(i - 1)), 3); } }
			
			if (!playerIn.isSneaking()) { CMEvents.textNotSneak(worldIn, pos, playerIn); }
		}
		
		if (!itemstack.isEmpty()) {
			if (item == Items_Wadeco.NOMI) {
				if (i <= 13) {
					itemstack.damageItem(1, playerIn);
					
					CMEvents.soundStoneBreak(worldIn, pos);
					worldIn.setBlockState(pos, this.getDefaultState().withProperty(STAGE_0_15, Integer.valueOf(i + 2)), 3); }
				
				if (i == 14 || i == 15) {
					itemstack.damageItem(1, playerIn);
					
					CMEvents.soundStoneBreak(worldIn, pos);
					if (!mode) { worldIn.destroyBlock(pos, true); }
					if (mode) { worldIn.destroyBlock(pos, false); } }
			}
			
			if (item != Items_Wadeco.NOMI) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		/** 'true' to not put anything on top. **/
		return true;
	}

	/* Data value */
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(STAGE_0_15, Integer.valueOf(meta));
	}

	public int getMetaFromState(IBlockState state) {
		return ((Integer)state.getValue(STAGE_0_15)).intValue();
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { STAGE_0_15 });
	}

	@Override
	public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
		return (15 - ((Integer)blockState.getValue(STAGE_0_15)).intValue()) * 2;
	}

	/* 上面に植木鉢やレッドストーンを置けるようにする */
	@Override
	public boolean isTopSolid(IBlockState state) {
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();
		return (i == 0 || i == 1)? true : false;
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

}
