package com.ayutaki.chinjufumod.items.window;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.blocks.window.BaseWindowB;
import com.ayutaki.chinjufumod.blocks.window.WindowB_oak;
import com.ayutaki.chinjufumod.registry.Window_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ItemWindowB_oak extends ItemBaceWindowB {

	public ItemWindowB_oak() {
		super(Window_Blocks.WINDOWB_oak);
		setUnlocalizedName(WindowB_oak.ID);
		setRegistryName(new ResourceLocation(ChinjufuMod.MOD_ID, WindowB_oak.ID));
	}

	/* Place block */
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing,
			float hitX, float hitY, float hitZ) {

		IBlockState iblockstate = worldIn.getBlockState(pos);
		Block block = iblockstate.getBlock();

		if (!block.isReplaceable(worldIn, pos)) { pos = pos.offset(facing); }

			/* 4.0F / 360.0F) + 0.5D を 4.0F / 360.0F) + 2.5D に変えて180度回転 */
			int i = MathHelper.floor((double)(playerIn.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
			EnumFacing direction = EnumFacing.getHorizontal(i);
			ItemStack itemstack = playerIn.getHeldItem(hand);

		if (!itemstack.isEmpty() && playerIn.canPlayerEdit(pos, facing, itemstack) && worldIn.mayPlace(Window_Blocks.WINDOWB_oak, pos, false, facing, (Entity)null)) {

			IBlockState iblockstate1 = Window_Blocks.WINDOWB_oak.getDefaultState()
					.withProperty(BaseWindowB.H_FACING, direction)
					.withProperty(BaseWindowB.STAGE_1_4, Integer.valueOf(1));
			worldIn.setBlockState(pos, iblockstate1, 10);

			SoundType soundtype = iblockstate1.getBlock().getSoundType(iblockstate1, worldIn, pos, playerIn);
			worldIn.playSound((EntityPlayer)null, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);

			itemstack.shrink(1);
			return EnumActionResult.SUCCESS; }

		else {
			return EnumActionResult.FAIL;
		}
	}

}