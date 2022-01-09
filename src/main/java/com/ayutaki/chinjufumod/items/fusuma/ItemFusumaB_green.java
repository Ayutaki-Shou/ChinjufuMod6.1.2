package com.ayutaki.chinjufumod.items.fusuma;

import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.blocks.fusuma.FusumaB_green;
import com.ayutaki.chinjufumod.registry.doors.Fusuma_Blocks;

import net.minecraft.util.ResourceLocation;

public class ItemFusumaB_green extends ItemBaseFusuma {

	public ItemFusumaB_green() {
		super(Fusuma_Blocks.FUSUMAB_green);
		setCreativeTab(ChinjufuModTabs.WADECO);
		setUnlocalizedName(FusumaB_green.ID);
		setRegistryName(new ResourceLocation(ChinjufuMod.MOD_ID, FusumaB_green.ID));
	}

}
