package com.ayutaki.chinjufumod.blocks.school;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseFacingWater;
import com.ayutaki.chinjufumod.entity.SitableEntity;
import com.ayutaki.chinjufumod.handler.CMEvents;

import net.minecraft.block.AbstractBlock;
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
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SchoolChair extends BaseFacingWater {

	/* Collision */
	protected static final VoxelShape BASE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 7.0D, 14.0D);
	
	protected static final VoxelShape AABB_SOUTH = VoxelShapes.or(BASE, Block.box(2.0D, 7.0D, 2.0D, 14.0D, 16.0D, 4.0D));
	protected static final VoxelShape AABB_WEST = VoxelShapes.or(BASE, Block.box(12.0D, 7.0D, 2.0D, 14.0D, 16.0D, 14.0D));
	protected static final VoxelShape AABB_NORTH = VoxelShapes.or(BASE, Block.box(2.0D, 7.0D, 12.0D, 14.0D, 16.0D, 14.0D));
	protected static final VoxelShape AABB_EAST = VoxelShapes.or(BASE, Block.box(2.0D, 7.0D, 2.0D, 4.0D, 16.0D, 14.0D));

	public SchoolChair(AbstractBlock.Properties properties) {
		super(properties);
	}

	/* RightClick Action */
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {

		CMEvents.soundSitChair(worldIn, pos);
		return SitableEntity.create(worldIn, pos, 0.22, playerIn);
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		Direction direction = state.getValue(H_FACING);

		switch (direction) {
		case NORTH :
		default : return AABB_NORTH;
		case SOUTH : return AABB_SOUTH;
		case EAST : return AABB_EAST;
		case WEST : return AABB_WEST;
		} // switch
	}

	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag tipFlag) {
		super.appendHoverText(stack, worldIn, tooltip, tipFlag);
		tooltip.add((new TranslationTextComponent("tips.block_isu")).withStyle(TextFormatting.GRAY));
	}

}
