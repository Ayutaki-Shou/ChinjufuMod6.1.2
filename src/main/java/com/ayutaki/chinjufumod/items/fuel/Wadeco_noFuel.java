package com.ayutaki.chinjufumod.items.fuel;

import com.ayutaki.chinjufumod.ItemGroups_CM;

import net.minecraft.block.Block;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;

public class Wadeco_noFuel extends BlockNamedItem {

	public Wadeco_noFuel(Block block, Item.Properties builder) {
		super(block, builder.tab(ItemGroups_CM.WADECO));
	}

	/* BurnTime in a Furnace */

}