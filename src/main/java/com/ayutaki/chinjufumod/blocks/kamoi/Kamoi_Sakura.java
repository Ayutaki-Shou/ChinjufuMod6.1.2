package com.ayutaki.chinjufumod.blocks.kamoi;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Wablock;
import com.ayutaki.chinjufumod.registry.KamoiPlanks_Blocks;
import com.ayutaki.chinjufumod.registry.KamoiPlaster_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class Kamoi_Sakura extends Base_Kamoi {

	public Kamoi_Sakura(Block.Properties properties) {
		super(properties);
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {

		ItemStack itemstack = playerIn.getHeldItem(hand);
		Item item = itemstack.getItem();

		if (!playerIn.isSneaking()) {
			if (item == Items_Wablock.DIRTWALL_SH) {
				CMEvents.Consume_1Item(playerIn, hand);
				CMEvents.soundStonePlace(worldIn, pos);
				
				worldIn.setBlockState(pos, KamoiPlaster_Blocks.KAMOI_dirt_sakura.getDefaultState()
						.with(H_FACING, state.get(H_FACING))
						.with(STAGE_1_4, state.get(STAGE_1_4)), 3);
				return ActionResultType.SUCCESS; }

			if (item == Items_Wablock.SHIKKUI_SH_white) {
				CMEvents.Consume_1Item(playerIn, hand);
				CMEvents.soundStonePlace(worldIn, pos);
				
				worldIn.setBlockState(pos, KamoiPlaster_Blocks.KAMOI_white_sakura.getDefaultState()
						.with(H_FACING, state.get(H_FACING))
						.with(STAGE_1_4, state.get(STAGE_1_4)), 3);
				return ActionResultType.SUCCESS; } //0

			if (item == Items_Wablock.SHIKKUI_SH_orange) {
				CMEvents.Consume_1Item(playerIn, hand);
				CMEvents.soundStonePlace(worldIn, pos);
				
				worldIn.setBlockState(pos, KamoiPlaster_Blocks.KAMOI_orange_sakura.getDefaultState()
						.with(H_FACING, state.get(H_FACING))
						.with(STAGE_1_4, state.get(STAGE_1_4)), 3);
				return ActionResultType.SUCCESS; } //1

			if (item == Items_Wablock.SHIKKUI_SH_magenta) {
				CMEvents.Consume_1Item(playerIn, hand);
				CMEvents.soundStonePlace(worldIn, pos);
				
				worldIn.setBlockState(pos, KamoiPlaster_Blocks.KAMOI_magenta_sakura.getDefaultState()
						.with(H_FACING, state.get(H_FACING))
						.with(STAGE_1_4, state.get(STAGE_1_4)), 3);
				return ActionResultType.SUCCESS; } //2

			if (item == Items_Wablock.SHIKKUI_SH_lightb) {
				CMEvents.Consume_1Item(playerIn, hand);
				CMEvents.soundStonePlace(worldIn, pos);
				
				worldIn.setBlockState(pos, KamoiPlaster_Blocks.KAMOI_lightb_sakura.getDefaultState()
						.with(H_FACING, state.get(H_FACING))
						.with(STAGE_1_4, state.get(STAGE_1_4)), 3);
				return ActionResultType.SUCCESS; } //3

			if (item == Items_Wablock.SHIKKUI_SH_yellow) {
				CMEvents.Consume_1Item(playerIn, hand);
				CMEvents.soundStonePlace(worldIn, pos);
				
				worldIn.setBlockState(pos, KamoiPlaster_Blocks.KAMOI_yellow_sakura.getDefaultState()
						.with(H_FACING, state.get(H_FACING))
						.with(STAGE_1_4, state.get(STAGE_1_4)), 3);
				return ActionResultType.SUCCESS; } //4

			if (item == Items_Wablock.SHIKKUI_SH_lime) {
				CMEvents.Consume_1Item(playerIn, hand);
				CMEvents.soundStonePlace(worldIn, pos);
				
				worldIn.setBlockState(pos, KamoiPlaster_Blocks.KAMOI_lime_sakura.getDefaultState()
						.with(H_FACING, state.get(H_FACING))
						.with(STAGE_1_4, state.get(STAGE_1_4)), 3);
				return ActionResultType.SUCCESS; } //5

			if (item == Items_Wablock.SHIKKUI_SH_pink) {
				CMEvents.Consume_1Item(playerIn, hand);
				CMEvents.soundStonePlace(worldIn, pos);
				
				worldIn.setBlockState(pos, KamoiPlaster_Blocks.KAMOI_pink_sakura.getDefaultState()
						.with(H_FACING, state.get(H_FACING))
						.with(STAGE_1_4, state.get(STAGE_1_4)), 3);
				return ActionResultType.SUCCESS; } //6

			if (item == Items_Wablock.SHIKKUI_SH_gray) {
				CMEvents.Consume_1Item(playerIn, hand);
				CMEvents.soundStonePlace(worldIn, pos);
				
				worldIn.setBlockState(pos, KamoiPlaster_Blocks.KAMOI_gray_sakura.getDefaultState()
						.with(H_FACING, state.get(H_FACING))
						.with(STAGE_1_4, state.get(STAGE_1_4)), 3);
				return ActionResultType.SUCCESS; } //7

			if (item == Items_Wablock.SHIKKUI_SH_lightg) {
				CMEvents.Consume_1Item(playerIn, hand);
				CMEvents.soundStonePlace(worldIn, pos);
				
				worldIn.setBlockState(pos, KamoiPlaster_Blocks.KAMOI_lightg_sakura.getDefaultState()
						.with(H_FACING, state.get(H_FACING))
						.with(STAGE_1_4, state.get(STAGE_1_4)), 3);
				return ActionResultType.SUCCESS; } //8

			if (item == Items_Wablock.SHIKKUI_SH_cyan) {
				CMEvents.Consume_1Item(playerIn, hand);
				CMEvents.soundStonePlace(worldIn, pos);
				
				worldIn.setBlockState(pos, KamoiPlaster_Blocks.KAMOI_cyan_sakura.getDefaultState()
						.with(H_FACING, state.get(H_FACING))
						.with(STAGE_1_4, state.get(STAGE_1_4)), 3);
				return ActionResultType.SUCCESS; } //9

			if (item == Items_Wablock.SHIKKUI_SH_purple) {
				CMEvents.Consume_1Item(playerIn, hand);
				CMEvents.soundStonePlace(worldIn, pos);
				
				worldIn.setBlockState(pos, KamoiPlaster_Blocks.KAMOI_purple_sakura.getDefaultState()
						.with(H_FACING, state.get(H_FACING))
						.with(STAGE_1_4, state.get(STAGE_1_4)), 3);
				return ActionResultType.SUCCESS; } //10

			if (item == Items_Wablock.SHIKKUI_SH_blue) {
				CMEvents.Consume_1Item(playerIn, hand);
				CMEvents.soundStonePlace(worldIn, pos);
				
				worldIn.setBlockState(pos, KamoiPlaster_Blocks.KAMOI_blue_sakura.getDefaultState()
						.with(H_FACING, state.get(H_FACING))
						.with(STAGE_1_4, state.get(STAGE_1_4)), 3);
				return ActionResultType.SUCCESS; } //11

			if (item == Items_Wablock.SHIKKUI_SH_brown) {
				CMEvents.Consume_1Item(playerIn, hand);
				CMEvents.soundStonePlace(worldIn, pos);
				
				worldIn.setBlockState(pos, KamoiPlaster_Blocks.KAMOI_brown_sakura.getDefaultState()
						.with(H_FACING, state.get(H_FACING))
						.with(STAGE_1_4, state.get(STAGE_1_4)), 3);
				return ActionResultType.SUCCESS; } //12

			if (item == Items_Wablock.SHIKKUI_SH_green) {
				CMEvents.Consume_1Item(playerIn, hand);
				CMEvents.soundStonePlace(worldIn, pos);
				
				worldIn.setBlockState(pos, KamoiPlaster_Blocks.KAMOI_green_sakura.getDefaultState()
						.with(H_FACING, state.get(H_FACING))
						.with(STAGE_1_4, state.get(STAGE_1_4)), 3);
				return ActionResultType.SUCCESS; } //13

			if (item == Items_Wablock.SHIKKUI_SH_red) {
				CMEvents.Consume_1Item(playerIn, hand);
				CMEvents.soundStonePlace(worldIn, pos);
				
				worldIn.setBlockState(pos, KamoiPlaster_Blocks.KAMOI_red_sakura.getDefaultState()
						.with(H_FACING, state.get(H_FACING))
						.with(STAGE_1_4, state.get(STAGE_1_4)), 3);
				return ActionResultType.SUCCESS; } //14

			if (item == Items_Wablock.SHIKKUI_SH_black) {
				CMEvents.Consume_1Item(playerIn, hand);
				CMEvents.soundStonePlace(worldIn, pos);
				
				worldIn.setBlockState(pos, KamoiPlaster_Blocks.KAMOI_black_sakura.getDefaultState()
						.with(H_FACING, state.get(H_FACING))
						.with(STAGE_1_4, state.get(STAGE_1_4)), 3);
				return ActionResultType.SUCCESS; } //15

			/** 木材 **/
			if (item == Items.OAK_SLAB) {
				CMEvents.Consume_1Item(playerIn, hand);
				CMEvents.soundWoodPlace(worldIn, pos);
				
				worldIn.setBlockState(pos, KamoiPlanks_Blocks.KAMOI_oak_sakura.getDefaultState()
						.with(H_FACING, state.get(H_FACING))
						.with(STAGE_1_4, state.get(STAGE_1_4)), 3);
				return ActionResultType.SUCCESS; } //Oak

			if (item == Items.SPRUCE_SLAB) {
				CMEvents.Consume_1Item(playerIn, hand);
				CMEvents.soundWoodPlace(worldIn, pos);
				
				worldIn.setBlockState(pos, KamoiPlanks_Blocks.KAMOI_spru_sakura.getDefaultState()
						.with(H_FACING, state.get(H_FACING))
						.with(STAGE_1_4, state.get(STAGE_1_4)), 3);
				return ActionResultType.SUCCESS; } //Spruce

			if (item == Items.BIRCH_SLAB) {
				CMEvents.Consume_1Item(playerIn, hand);
				CMEvents.soundWoodPlace(worldIn, pos);
				
				worldIn.setBlockState(pos, KamoiPlanks_Blocks.KAMOI_bir_sakura.getDefaultState()
						.with(H_FACING, state.get(H_FACING))
						.with(STAGE_1_4, state.get(STAGE_1_4)), 3);
				return ActionResultType.SUCCESS; } //Birch

			if (item == Items.JUNGLE_SLAB) {
				CMEvents.Consume_1Item(playerIn, hand);
				CMEvents.soundWoodPlace(worldIn, pos);
				
				worldIn.setBlockState(pos, KamoiPlanks_Blocks.KAMOI_jun_sakura.getDefaultState()
						.with(H_FACING, state.get(H_FACING))
						.with(STAGE_1_4, state.get(STAGE_1_4)), 3);
				return ActionResultType.SUCCESS; } //Jungle

			if (item == Items.ACACIA_SLAB) {
				CMEvents.Consume_1Item(playerIn, hand);
				CMEvents.soundWoodPlace(worldIn, pos);
				
				worldIn.setBlockState(pos, KamoiPlanks_Blocks.KAMOI_aca_sakura.getDefaultState()
						.with(H_FACING, state.get(H_FACING))
						.with(STAGE_1_4, state.get(STAGE_1_4)), 3);
				return ActionResultType.SUCCESS; } //Acacia

			if (item == Items.DARK_OAK_SLAB) {
				CMEvents.Consume_1Item(playerIn, hand);
				CMEvents.soundWoodPlace(worldIn, pos);
				
				worldIn.setBlockState(pos, KamoiPlanks_Blocks.KAMOI_doak_sakura.getDefaultState()
						.with(H_FACING, state.get(H_FACING))
						.with(STAGE_1_4, state.get(STAGE_1_4)), 3);
				return ActionResultType.SUCCESS; } //DarkOak

			if (item == Items_Seasonal.SAKURA_slabhalf) {
				CMEvents.Consume_1Item(playerIn, hand);
				CMEvents.soundWoodPlace(worldIn, pos);
				
				worldIn.setBlockState(pos, KamoiPlanks_Blocks.KAMOI_saku_sakura.getDefaultState()
						.with(H_FACING, state.get(H_FACING))
						.with(STAGE_1_4, state.get(STAGE_1_4)), 3);
				return ActionResultType.SUCCESS; } //SAKURA

			if (item == Items_Seasonal.KAEDE_slabhalf) {
				CMEvents.Consume_1Item(playerIn, hand);
				CMEvents.soundWoodPlace(worldIn, pos);
				
				worldIn.setBlockState(pos, KamoiPlanks_Blocks.KAMOI_kae_sakura.getDefaultState()
						.with(H_FACING, state.get(H_FACING))
						.with(STAGE_1_4, state.get(STAGE_1_4)), 3);
				return ActionResultType.SUCCESS; } //KAEDE

			if (item == Items_Seasonal.ICHOH_slabhalf) {
				CMEvents.Consume_1Item(playerIn, hand);
				CMEvents.soundWoodPlace(worldIn, pos);
				
				worldIn.setBlockState(pos, KamoiPlanks_Blocks.KAMOI_ich_sakura.getDefaultState()
						.with(H_FACING, state.get(H_FACING))
						.with(STAGE_1_4, state.get(STAGE_1_4)), 3);
				return ActionResultType.SUCCESS; } //ICHOH
			
			if (itemstack.isEmpty()) {
				CMEvents.textNotSneak(worldIn, pos, playerIn);
				return ActionResultType.SUCCESS; }
		}

		/** 形状変化 **/
		if (playerIn.isSneaking() && itemstack.isEmpty()) {
			CMEvents.soundWoodPlace(worldIn, pos);
			worldIn.setBlockState(pos, state.cycle(STAGE_1_4));
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.PASS;
	}

	/* Clone Item in Creative. */
	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items_Seasonal.PILLARSLAB_saku);
	}

}
