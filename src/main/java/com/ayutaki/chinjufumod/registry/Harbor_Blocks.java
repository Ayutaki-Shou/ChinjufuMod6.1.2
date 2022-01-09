package com.ayutaki.chinjufumod.registry;

import com.ayutaki.chinjufumod.RegisterHandler_CM;
import com.ayutaki.chinjufumod.blocks.harbor.CTruss;
import com.ayutaki.chinjufumod.blocks.harbor.Keikai;
import com.ayutaki.chinjufumod.blocks.harbor.Keiryukui;

import net.minecraft.block.Block;

public class Harbor_Blocks {

	public static Block KEIKAIBLOCK;

	public static Block KEIRYUKUI,KEIRYUKUI_b;

	public static Block TRUSS, TRUSS_white, TRUSS_orange, TRUSS_magenta,
								TRUSS_lightb, TRUSS_yellow, TRUSS_lime, TRUSS_pink,
								TRUSS_gray, TRUSS_cyan, TRUSS_purple, TRUSS_blue,
								TRUSS_brown, TRUSS_green, TRUSS_red, TRUSS_black;


	public static void init() {
		KEIKAIBLOCK = new Keikai();

		KEIRYUKUI = new Keiryukui().setRegistryName("block_keiryukui").setUnlocalizedName("block_keiryukui");
		KEIRYUKUI_b = new Keiryukui().setRegistryName("block_keiryukui_b").setUnlocalizedName("block_keiryukui_b");

		TRUSS = new CTruss().setRegistryName("block_ctruss").setUnlocalizedName("block_ctruss");
		TRUSS_white = new CTruss().setRegistryName("block_ctruss_white").setUnlocalizedName("block_ctruss_white");
		TRUSS_orange = new CTruss().setRegistryName("block_ctruss_orange").setUnlocalizedName("block_ctruss_orange");
		TRUSS_magenta = new CTruss().setRegistryName("block_ctruss_magenta").setUnlocalizedName("block_ctruss_magenta");
		TRUSS_lightb = new CTruss().setRegistryName("block_ctruss_lightb").setUnlocalizedName("block_ctruss_lightb");
		TRUSS_yellow = new CTruss().setRegistryName("block_ctruss_yellow").setUnlocalizedName("block_ctruss_yellow");
		TRUSS_lime = new CTruss().setRegistryName("block_ctruss_lime").setUnlocalizedName("block_ctruss_lime");
		TRUSS_pink = new CTruss().setRegistryName("block_ctruss_pink").setUnlocalizedName("block_ctruss_pink");
		TRUSS_gray = new CTruss().setRegistryName("block_ctruss_gray").setUnlocalizedName("block_ctruss_gray");
		TRUSS_cyan = new CTruss().setRegistryName("block_ctruss_cyan").setUnlocalizedName("block_ctruss_cyan");
		TRUSS_purple = new CTruss().setRegistryName("block_ctruss_purple").setUnlocalizedName("block_ctruss_purple");
		TRUSS_blue = new CTruss().setRegistryName("block_ctruss_blue").setUnlocalizedName("block_ctruss_blue");
		TRUSS_brown = new CTruss().setRegistryName("block_ctruss_brown").setUnlocalizedName("block_ctruss_brown");
		TRUSS_green = new CTruss().setRegistryName("block_ctruss_green").setUnlocalizedName("block_ctruss_green");
		TRUSS_red = new CTruss().setRegistryName("block_ctruss_red").setUnlocalizedName("block_ctruss_red");
		TRUSS_black = new CTruss().setRegistryName("block_ctruss_black").setUnlocalizedName("block_ctruss_black");

	}


	public static void register() {
		registerBlock(KEIKAIBLOCK);
		registerBlock(KEIRYUKUI);
		registerBlock(KEIRYUKUI_b);

		registerBlock(TRUSS);
		registerBlock(TRUSS_white);
		registerBlock(TRUSS_orange);
		registerBlock(TRUSS_magenta);
		registerBlock(TRUSS_lightb);
		registerBlock(TRUSS_yellow);
		registerBlock(TRUSS_lime);
		registerBlock(TRUSS_pink);
		registerBlock(TRUSS_gray);
		registerBlock(TRUSS_cyan);
		registerBlock(TRUSS_purple);
		registerBlock(TRUSS_blue);
		registerBlock(TRUSS_brown);
		registerBlock(TRUSS_green);
		registerBlock(TRUSS_red);
		registerBlock(TRUSS_black);


	}

	public static void registerBlock(Block block) {
		RegisterHandler_CM.Blocks.BLOCKS.add(block);
	}

}
