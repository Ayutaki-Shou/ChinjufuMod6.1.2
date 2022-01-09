package com.ayutaki.chinjufumod.blocks.cmblock;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.blocks.base.BaseStage4_Face;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Chinjufu_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
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

public class AlumiBlock_W extends BaseStage4_Face {

	/** 1=アルミ、2=鋼材、3=金、4=燃料缶 **/
	public static final String ID = "block_alumiw_block";

	public AlumiBlock_W() {
		super(Material.WOOD);
		setRegistryName(new ResourceLocation(ChinjufuMod.MOD_ID, ID));
		setUnlocalizedName(ID);

		setSoundType(SoundType.METAL);
		setHardness(1.0F);
		setResistance(10.0F);

		/** ハーフ・椅子・机=2, 障子=1, ガラス戸・窓=0, web=1, ice=3 **/
		setLightOpacity(1);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		ItemStack itemstack = playerIn.getHeldItem(hand);
		Item item = itemstack.getItem();
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		/** Hand is Empty. **/
		if (itemstack.isEmpty() && facing == EnumFacing.UP) {
			if (i == 1) {
				playerIn.inventory.addItemStackToInventory(new ItemStack(Items_Chinjufu.ALUMI, 1, 1));

				CMEvents.soundItemPick(worldIn, pos);
				worldIn.setBlockState(pos, Chinjufu_Blocks.ALUMI.getDefaultState()
						.withProperty(H_FACING, state.getValue(H_FACING))
						.withProperty(BaseStage4_Face.STAGE_1_4, Integer.valueOf(1))); }

			if (i == 2) {
				playerIn.inventory.addItemStackToInventory(new ItemStack(Items_Chinjufu.ALUMI, 1, 2));

				CMEvents.soundItemPick(worldIn, pos);
				worldIn.setBlockState(pos, Chinjufu_Blocks.ALUMI.getDefaultState()
						.withProperty(H_FACING, state.getValue(H_FACING))
						.withProperty(BaseStage4_Face.STAGE_1_4, Integer.valueOf(2))); }

			if (i == 3) {
				playerIn.inventory.addItemStackToInventory(new ItemStack(Items_Chinjufu.ALUMI, 1, 3));

				CMEvents.soundItemPick(worldIn, pos);
				worldIn.setBlockState(pos, Chinjufu_Blocks.ALUMI.getDefaultState()
						.withProperty(H_FACING, state.getValue(H_FACING))
						.withProperty(BaseStage4_Face.STAGE_1_4, Integer.valueOf(3))); }

			if (i == 4) {
				playerIn.inventory.addItemStackToInventory(new ItemStack(Items_Chinjufu.OIL_DRUM, 1, 0));

				CMEvents.soundItemPick(worldIn, pos);
				worldIn.setBlockState(pos, Blocks.AIR.getDefaultState()); }
			return true;
		}

		if (!itemstack.isEmpty() && item != Items_Chinjufu.ALUMI) { 
			CMEvents.textFullItem(worldIn, pos, playerIn);
			return true; }
		
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {

		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (i == 4) { return new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 1.0D, 0.9375D); }
		return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
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
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();

		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		if (i == 1) { stack.add(new ItemStack(Items_Chinjufu.ALUMINUM, 8, 0)); }
		if (i == 2) { stack.add(new ItemStack(Items.IRON_INGOT, 8, 0)); }
		if (i == 3) { stack.add(new ItemStack(Items.GOLD_INGOT, 8, 0)); }
		if (i == 4) { stack.add(new ItemStack(Items.COAL, 4, 0)); }
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {

		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		if (i == 2) { return new ItemStack(Items_Chinjufu.ALUMI, 1, 2); }
		if (i == 3) { return new ItemStack(Items_Chinjufu.ALUMI, 1, 3); }
		if (i == 4) { return new ItemStack(Items_Chinjufu.OIL_DRUM, 1, 0); }
		return new ItemStack(Items_Chinjufu.ALUMI, 1, 1);
	}

}
