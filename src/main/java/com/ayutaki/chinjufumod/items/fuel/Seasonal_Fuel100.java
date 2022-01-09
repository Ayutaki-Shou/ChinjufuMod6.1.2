package com.ayutaki.chinjufumod.items.fuel;

import com.ayutaki.chinjufumod.ItemGroups_CM;

import net.minecraft.block.Block;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Seasonal_Fuel100 extends BlockNamedItem {

	public Seasonal_Fuel100(Block block, Item.Properties builder) {
		super(block, builder.group(ItemGroups_CM.SEASONAL));
	}

	/* かまど燃焼時間 */
	@Override
	public int getBurnTime(ItemStack itemStack) {
		return 100;
	}

}
