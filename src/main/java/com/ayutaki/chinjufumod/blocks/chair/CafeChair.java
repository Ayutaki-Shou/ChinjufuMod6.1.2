package com.ayutaki.chinjufumod.blocks.chair;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseWaterLoggable;
import com.ayutaki.chinjufumod.entity.SitableEntity;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.color.Base_ItemHake;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
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

public class CafeChair extends BaseWaterLoggable {

	/* Collision */
	protected static final VoxelShape AABB_BOX = VoxelShapes.or(Block.makeCuboidShape(4.0D, 9.0D, 4.0D, 12.0D, 10.0D, 12.0D),
			Block.makeCuboidShape(3.0D, 7.0D, 3.0D, 13.0D, 9.0D, 13.0D),
			Block.makeCuboidShape(7.0D, 1.0D, 7.0D, 9.0D, 7.0D, 9.0D),
			Block.makeCuboidShape(7.0D, 0.0D, 3.0D, 9.0D, 1.0D, 13.0D),
			Block.makeCuboidShape(3.0D, 0.0D, 7.0D, 7.0D, 1.0D, 9.0D),
			Block.makeCuboidShape(9.0D, 0.0D, 7.0D, 13.0D, 1.0D, 9.0D));

	public CafeChair(Block.Properties properties) {
		super(properties);
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {

		ItemStack itemstack = playerIn.getHeldItem(hand);
		Item item = itemstack.getItem();

		if (item instanceof Base_ItemHake) { return ActionResultType.PASS; }

		else {
			CMEvents.soundKinuzure(worldIn, pos);
			return SitableEntity.create(worldIn, pos, 0.4, playerIn);
		}
	}

	/* 窒息 */
	@Override
	public boolean causesSuffocation(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return false;
	}

	/* 立方体 */
	@Override
	public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return false;
	}

	/* モブ湧き */
	@Override
	public boolean canEntitySpawn(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
		return false;
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return AABB_BOX;
	}

	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag tipFlag) {
		super.addInformation(stack, worldIn, tooltip, tipFlag);
		tooltip.add((new TranslationTextComponent("tips.block_isu")).applyTextStyle(TextFormatting.GRAY));
	}

}
