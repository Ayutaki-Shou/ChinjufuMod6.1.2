package com.ayutaki.chinjufumod.blocks.dish;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Zundou_Shio extends BaseZundou_2Stage {

	/** 1=塩水、2=塩湯 **/
	public Zundou_Shio(Block.Properties properties) {
		super(properties);
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {

		ItemStack itemstack = playerIn.getHeldItem(hand);
		Item item = itemstack.getItem();

		int i = state.get(STAGE_1_2);
		/** 1=塩水、2=塩湯 **/

		if (i == 2) {
			if (item == Items_Teatime.PASTA_nama) {
				CMEvents.Consume_1Item(playerIn, hand);
				CMEvents.soundWaterUse(worldIn, pos);
				
				worldIn.setBlockState(pos, Dish_Blocks.ZUNDOU_PASTA.getDefaultState().with(H_FACING, state.get(H_FACING))
						.with(BaseZundou_4Stage.STAGE_1_4, Integer.valueOf(2)));
						/** 1=生パスタ水、2=生パスタ湯、3＝茹でパスタ水、4=茹でパスタ湯 **/ }
	
			if (item == Items.COD) {
				CMEvents.Consume_1Item(playerIn, hand);
				CMEvents.soundWaterUse(worldIn, pos);
				
				worldIn.setBlockState(pos, Dish_Blocks.ZUNDOU_FISH.getDefaultState().with(H_FACING, state.get(H_FACING))
						.with(BaseZundou_4Stage.STAGE_1_4, Integer.valueOf(2)));
						/** 1=生魚水、塩湯→2=生魚湯、3=煮干し水、4=煮干し湯 **/ }
			
			if (item != Items_Teatime.PASTA_nama || item != Items.COD) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		
		/** Too early to use **/
		if (i != 2) { CMEvents.textEarlyUse(worldIn, pos, playerIn); }
		
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {

		int i = state.get(STAGE_1_2);
		/** 1=塩水、2=塩湯 **/

		if (isCooking(worldIn, pos)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(worldIn) + (20 * rand.nextInt(5)));
			if (i == 1) { worldIn.setBlockState(pos, state.with(STAGE_1_2, Integer.valueOf(2))); }
			if (i != 1) { } }

		if (!isCooking(worldIn, pos)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, 100);
			if (i == 2) { worldIn.setBlockState(pos, state.with(STAGE_1_2, Integer.valueOf(1))); }
			if (i != 2) { } }

		if (inWater(state, worldIn, pos)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60);
			CMEvents.soundBubble(worldIn, pos);
			worldIn.setBlockState(pos, Dish_Blocks.ZUNDOU.getDefaultState()
					.with(H_FACING, state.get(H_FACING))
					.with(Zundou.STAGE_1_2, Integer.valueOf(2))
					.with(Zundou.WATERLOGGED, state.get(WATERLOGGED)), 3); }
		
		else { }
	}

	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag tipFlag) {
		super.addInformation(stack, worldIn, tooltip, tipFlag);
		tooltip.add((new TranslationTextComponent("tips.block_zundou_shiomizu")).applyTextStyle(TextFormatting.GRAY));
	}

}
