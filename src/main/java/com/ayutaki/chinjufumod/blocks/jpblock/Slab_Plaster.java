package com.ayutaki.chinjufumod.blocks.jpblock;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.registry.JP_Blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Slab_Plaster extends Base_Slab_JP {

	public Slab_Plaster(AbstractBlock.Properties properties) {
		super(properties);
	}

	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag tipFlag) {
		super.appendHoverText(stack, worldIn, tooltip, tipFlag);
		if (this != JP_Blocks.DIRTWALL_SH) {
			tooltip.add((new TranslationTextComponent("tips.block_plaster")).withStyle(TextFormatting.GRAY)); }
	}

}
