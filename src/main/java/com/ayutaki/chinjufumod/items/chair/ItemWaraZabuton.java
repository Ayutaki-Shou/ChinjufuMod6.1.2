package com.ayutaki.chinjufumod.items.chair;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.blocks.chair.WaraZabuton;
import com.ayutaki.chinjufumod.items.base.ItemBlockBace;
import com.ayutaki.chinjufumod.registry.JPDeco_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemWaraZabuton extends ItemBlockBace {

	public ItemWaraZabuton() {
		super(JPDeco_Blocks.WARAZABUTON);
		setUnlocalizedName(WaraZabuton.ID);
		setRegistryName(new ResourceLocation(ChinjufuMod.MOD_ID, WaraZabuton.ID));

		setCreativeTab(ChinjufuModTabs.WADECO);
	}

	@Override
	public int getItemBurnTime(ItemStack itemstack) {
		return 100;
	}

	/* Place block */
	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing,
			float hitX, float hitY, float hitZ) {

		IBlockState iblockstate = worldIn.getBlockState(pos);
		Block block = iblockstate.getBlock();

		if (!block.isReplaceable(worldIn, pos)) { pos = pos.offset(facing); }

		ItemStack itemstack = playerIn.getHeldItem(hand);

		if (!itemstack.isEmpty() && playerIn.canPlayerEdit(pos, facing, itemstack) && worldIn.mayPlace(JPDeco_Blocks.WARAZABUTON, pos, false, facing, (Entity)null)) {

			/** 設置するブロックのクラス **/
			IBlockState iblockstate1 = JPDeco_Blocks.WARAZABUTON.getDefaultState();
			worldIn.setBlockState(pos, iblockstate1, 10);

			SoundType soundtype = iblockstate1.getBlock().getSoundType(iblockstate1, worldIn, pos, playerIn);
			worldIn.playSound((EntityPlayer)null, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);

			itemstack.shrink(1);
			return EnumActionResult.SUCCESS;
		}

		else {
			return EnumActionResult.FAIL;
		}
	}

	/* ToolTip */
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag advanced) {
		int meta = stack.getMetadata();
		tooltip.add(I18n.format("tips.block_mzabuton", meta));
	}

}
