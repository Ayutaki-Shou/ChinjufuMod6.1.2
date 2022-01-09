package com.ayutaki.chinjufumod.blocks.wallpane;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.ChinjufuModTabs;

import net.minecraft.block.SoundType;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.ResourceLocation;

public class WP_glass extends BaseSimpleWP {

	public WP_glass(String unlocalizedName) {
		super();
		setUnlocalizedName(unlocalizedName);
		setRegistryName(new ResourceLocation(ChinjufuMod.MOD_ID, unlocalizedName));
		setCreativeTab(ChinjufuModTabs.WALLPANEL);

		setSoundType(SoundType.GLASS);
		setHardness(1.0F);
		setResistance(1.0F);
		/** ハーフ・椅子・机=2, 障子=1, ガラス戸・窓=0, web=1, ice=3 **/
		setLightOpacity(0);
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

}
