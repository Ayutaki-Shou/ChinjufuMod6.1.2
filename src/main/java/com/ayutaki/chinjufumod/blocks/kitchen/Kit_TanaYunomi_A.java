package com.ayutaki.chinjufumod.blocks.kitchen;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.blocks.base.BaseStage3_Face;
import com.ayutaki.chinjufumod.blocks.base.CollisionHelper;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Kitchen_Blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
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

public class Kit_TanaYunomi_A extends BaseStage3_Face {

	public static final String ID = "block_kit_yunomia";

	private static final AxisAlignedBB AABB_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.0, 0.0, 0.0, 0.625, 1.0, 1.0);
	private static final AxisAlignedBB AABB_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.0, 0.0, 0.0, 0.625, 1.0, 1.0);
	private static final AxisAlignedBB AABB_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.0, 0.0, 0.0, 0.625, 1.0, 1.0);
	private static final AxisAlignedBB AABB_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.0, 0.0, 0.0, 0.625, 1.0, 1.0);
	private static final AxisAlignedBB[] AABB = { AABB_SOUTH, AABB_WEST, AABB_NORTH, AABB_EAST };

	public Kit_TanaYunomi_A() {
		super(Material.WOOD);
		setRegistryName(new ResourceLocation(ChinjufuMod.MOD_ID, ID));
		setUnlocalizedName(ID);

		setSoundType(SoundType.WOOD);
		setHardness(1.0F);
		setResistance(10.0F);
		/** ハーフ・椅子・机=2, 障子=1, ガラス戸・窓=0, web=1, ice=3 **/
		setLightOpacity(2);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {

		int i = ((Integer)state.getValue(STAGE_1_3)).intValue();

		ItemStack itemstack = playerIn.getHeldItem(hand);
		Item item = itemstack.getItem();
		int k;
		k = itemstack.getMetadata();

		if ((item != Items_Teatime.Item_DISH || k !=1) && item != Items_Teatime.KYUSU_kara) {
			if (itemstack.isEmpty()) {
				CMEvents.soundItemPick(worldIn, pos);
	
				if (i == 1) {
					playerIn.inventory.addItemStackToInventory(new ItemStack(Items_Teatime.Item_DISH, 1, 1));
					worldIn.setBlockState(pos, Kitchen_Blocks.KIT_YUNOMI1.getDefaultState()
							.withProperty(H_FACING, state.getValue(H_FACING))
							.withProperty(BaseKit_Tana4Stage.STAGE_1_4, Integer.valueOf(4))); }
	
				if (i == 2) {
					playerIn.inventory.addItemStackToInventory(new ItemStack(Items_Teatime.Item_DISH, 1, 1));
					worldIn.setBlockState(pos, state.withProperty(STAGE_1_3, Integer.valueOf(i - 1)), 3); }
	
				if (i == 3) {
					playerIn.inventory.addItemStackToInventory(new ItemStack(Items_Teatime.KYUSU_kara));
					worldIn.setBlockState(pos, state.withProperty(STAGE_1_3, Integer.valueOf(i - 1)), 3); } }
			
			if (!itemstack.isEmpty()) { CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		
		if (item == Items_Teatime.Item_DISH && k ==1) {
			if (i == 1) {
				CMEvents.Consume_1Item(playerIn, hand);
				CMEvents.soundDishPlace(worldIn, pos);
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_3, Integer.valueOf(i + 1)), 3); }
			
			if (i != 1) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		
		if (item == Items_Teatime.KYUSU_kara) {
			if (i == 2) {
				CMEvents.Consume_1Item(playerIn, hand);
				CMEvents.soundDishPlace(worldIn, pos);
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_3, Integer.valueOf(i + 1)), 3); }
			
			if (i != 2) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}

		/** 'true' to not put anything on top. **/
		return true;
	}


	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing facing = state.getValue(H_FACING);
		return AABB[facing.getHorizontalIndex()];
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean t_f) {

		EnumFacing facing = state.getValue(H_FACING);
		super.addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB[facing.getHorizontalIndex()]);
	}

	/* 上面に植木鉢やレッドストーンを置けるようにする */
	@Override
	public boolean isTopSolid(IBlockState state) {
		return true;
	}

	/* 側面に松明などを置けるようにする */
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	/* Drop Item and Clone Item. */
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();

		int i = ((Integer)state.getValue(STAGE_1_3)).intValue();

		if (i == 1) {
			stack.add(new ItemStack(Items_Teatime.KIT_TANA, 1, 0));
			stack.add(new ItemStack(Items_Teatime.Item_DISH, 5, 1)); }

		if (i == 2) {
			stack.add(new ItemStack(Items_Teatime.KIT_TANA, 1, 0));
			stack.add(new ItemStack(Items_Teatime.Item_DISH, 6, 1)); }

		if (i == 3) {
			stack.add(new ItemStack(Items_Teatime.KIT_TANA, 1, 0));
			stack.add(new ItemStack(Items_Teatime.Item_DISH, 6, 1));
			stack.add(new ItemStack(Items_Teatime.KYUSU_kara, 1, 0)); }
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Teatime.KIT_TANA);
	}

}