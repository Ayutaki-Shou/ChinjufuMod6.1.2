package com.ayutaki.chinjufumod.blocks.wallpane;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuMod;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

public class StoneCM_Stairs extends BlockStairs {

	public StoneCM_Stairs(String unlocalizedName, IBlockState state) {
		super(state);
		setUnlocalizedName(unlocalizedName);
		setRegistryName(new ResourceLocation(ChinjufuMod.MOD_ID, unlocalizedName));

		setHardness(2.0F);
		setResistance(10.0F);
		setSoundType(SoundType.STONE);
		/** ハーフ・椅子・机=2, 障子=1, ガラス戸・窓=0, web=1, ice=3 **/
		setLightOpacity(1);

		this.useNeighborBrightness = true;
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

}