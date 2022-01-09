package com.ayutaki.chinjufumod.blocks.wallpane;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseStage3_FaceWater;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.WallPane_Blocks;
import com.ayutaki.chinjufumod.registry.Wood_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

public class WallPane_Stage3 extends BaseStage3_FaceWater {

	/* Collision */
	protected static final VoxelShape AABB_SOUTH = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D);
	protected static final VoxelShape AABB_WEST = Block.makeCuboidShape(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape AABB_NORTH = Block.makeCuboidShape(0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape AABB_EAST = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D);

	public WallPane_Stage3(Block.Properties properties) {
		super(properties);
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {

		ItemStack itemstack = playerIn.getHeldItem(hand);

		if (itemstack.isEmpty()) {
			if (playerIn.isSneaking()) {

				if (this == WallPane_Blocks.WP_LOG_oak || this == WallPane_Blocks.WP_LOG_spru || this == WallPane_Blocks.
						WP_LOG_bir || this == WallPane_Blocks.WP_LOG_jun || this == WallPane_Blocks.WP_LOG_aca || this == WallPane_Blocks
						.WP_LOG_doak || this == Wood_Blocks.WP_LOG_sakura || this == Wood_Blocks.WP_LOG_kaede || this == Wood_Blocks
						.WP_LOG_ichoh || this == WallPane_Blocks.WP_BAMBOO || this == WallPane_Blocks.WP_BAMBOO_Y || this == WallPane_Blocks.WP_BAMBOO_K) {
					CMEvents.soundWoodPlace(worldIn, pos); }
	
				else { CMEvents.soundStonePlace(worldIn, pos); }
	
				worldIn.setBlockState(pos, state.cycle(STAGE_1_3));
				return ActionResultType.SUCCESS; }
			
			if (!playerIn.isSneaking()) {
				CMEvents.textNotSneak(worldIn, pos, playerIn);
				return ActionResultType.SUCCESS; }
		}
		
		return ActionResultType.PASS;
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

		Direction direction = state.get(H_FACING);
		switch(direction) {
		case SOUTH:
			return AABB_SOUTH;
		case WEST:
			return AABB_WEST;
		case NORTH:
		default:
			return AABB_NORTH;
		case EAST:
			return AABB_EAST;
		}
	}

	/* 採取適正ツール */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.PICKAXE;
	}

	@Override
	public int getHarvestLevel(BlockState state) {
		return 0;
	}

	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag tipFlag) {
		super.addInformation(stack, worldIn, tooltip, tipFlag);
		tooltip.add((new TranslationTextComponent("tips.wp_stage3")).applyTextStyle(TextFormatting.GRAY));
	}

}
