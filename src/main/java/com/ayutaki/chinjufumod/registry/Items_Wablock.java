package com.ayutaki.chinjufumod.registry;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.ItemGroups_CM;
import com.ayutaki.chinjufumod.items.addinfo.AddInfo_Item;
import com.ayutaki.chinjufumod.items.fuel.Wablock_noFuel;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = ChinjufuMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Items_Wablock {

	@SuppressWarnings("deprecation")
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, ChinjufuMod.MOD_ID);

	public static Item CLAYKAWARA = register("item_claykawara", new AddInfo_Item(new Item.Properties().group(ItemGroups_CM.WABLOCK)));
	public static Item SHOUSEKKAI = register("item_shousekkai_c", new AddInfo_Item(new Item.Properties().group(ItemGroups_CM.WABLOCK)));

	public static Item KAWARA_white = register("block_kawara_white", new Wablock_noFuel(JP_Blocks.KAWARA_white, new Item.Properties()));
	public static Item KAWARA_orange = register("block_kawara_orange", new Wablock_noFuel(JP_Blocks.KAWARA_orange, new Item.Properties()));
	public static Item KAWARA_magenta = register("block_kawara_magenta", new Wablock_noFuel(JP_Blocks.KAWARA_magenta, new Item.Properties()));
	public static Item KAWARA_lightb = register("block_kawara_lightb", new Wablock_noFuel(JP_Blocks.KAWARA_lightb, new Item.Properties()));
	public static Item KAWARA_yellow = register("block_kawara_yellow", new Wablock_noFuel(JP_Blocks.KAWARA_yellow, new Item.Properties()));
	public static Item KAWARA_lime = register("block_kawara_lime", new Wablock_noFuel(JP_Blocks.KAWARA_lime, new Item.Properties()));
	public static Item KAWARA_pink = register("block_kawara_pink", new Wablock_noFuel(JP_Blocks.KAWARA_pink, new Item.Properties()));
	public static Item KAWARA_gray = register("block_kawara_gray", new Wablock_noFuel(JP_Blocks.KAWARA_gray, new Item.Properties()));
	public static Item KAWARA_lightg = register("block_kawara_lightg", new Wablock_noFuel(JP_Blocks.KAWARA_lightg, new Item.Properties()));
	public static Item KAWARA_cyan = register("block_kawara_cyan", new Wablock_noFuel(JP_Blocks.KAWARA_cyan, new Item.Properties()));
	public static Item KAWARA_purple = register("block_kawara_purple", new Wablock_noFuel(JP_Blocks.KAWARA_purple, new Item.Properties()));
	public static Item KAWARA_blue = register("block_kawara_blue", new Wablock_noFuel(JP_Blocks.KAWARA_blue, new Item.Properties()));
	public static Item KAWARA_brown = register("block_kawara_brown", new Wablock_noFuel(JP_Blocks.KAWARA_brown, new Item.Properties()));
	public static Item KAWARA_green = register("block_kawara_green", new Wablock_noFuel(JP_Blocks.KAWARA_green, new Item.Properties()));
	public static Item KAWARA_red = register("block_kawara_red", new Wablock_noFuel(JP_Blocks.KAWARA_red, new Item.Properties()));
	public static Item KAWARA_black = register("block_kawara_black", new Wablock_noFuel(JP_Blocks.KAWARA_black, new Item.Properties()));

	public static Item KAWARA_ST_white = register("block_kst_white", new Wablock_noFuel(JP_Blocks.KAWARA_ST_white, new Item.Properties()));
	public static Item KAWARA_ST_orange = register("block_kst_orange", new Wablock_noFuel(JP_Blocks.KAWARA_ST_orange, new Item.Properties()));
	public static Item KAWARA_ST_magenta = register("block_kst_magenta", new Wablock_noFuel(JP_Blocks.KAWARA_ST_magenta, new Item.Properties()));
	public static Item KAWARA_ST_lightb = register("block_kst_lightb", new Wablock_noFuel(JP_Blocks.KAWARA_ST_lightb, new Item.Properties()));
	public static Item KAWARA_ST_yellow = register("block_kst_yellow", new Wablock_noFuel(JP_Blocks.KAWARA_ST_yellow, new Item.Properties()));
	public static Item KAWARA_ST_lime = register("block_kst_lime", new Wablock_noFuel(JP_Blocks.KAWARA_ST_lime, new Item.Properties()));
	public static Item KAWARA_ST_pink = register("block_kst_pink", new Wablock_noFuel(JP_Blocks.KAWARA_ST_pink, new Item.Properties()));
	public static Item KAWARA_ST_gray = register("block_kst_gray", new Wablock_noFuel(JP_Blocks.KAWARA_ST_gray, new Item.Properties()));
	public static Item KAWARA_ST_lightg = register("block_kst_lightg", new Wablock_noFuel(JP_Blocks.KAWARA_ST_lightg, new Item.Properties()));
	public static Item KAWARA_ST_cyan = register("block_kst_cyan", new Wablock_noFuel(JP_Blocks.KAWARA_ST_cyan, new Item.Properties()));
	public static Item KAWARA_ST_purple = register("block_kst_purple", new Wablock_noFuel(JP_Blocks.KAWARA_ST_purple, new Item.Properties()));
	public static Item KAWARA_ST_blue = register("block_kst_blue", new Wablock_noFuel(JP_Blocks.KAWARA_ST_blue, new Item.Properties()));
	public static Item KAWARA_ST_brown = register("block_kst_brown", new Wablock_noFuel(JP_Blocks.KAWARA_ST_brown, new Item.Properties()));
	public static Item KAWARA_ST_green = register("block_kst_green", new Wablock_noFuel(JP_Blocks.KAWARA_ST_green, new Item.Properties()));
	public static Item KAWARA_ST_red = register("block_kst_red", new Wablock_noFuel(JP_Blocks.KAWARA_ST_red, new Item.Properties()));
	public static Item KAWARA_ST_black = register("block_kst_black", new Wablock_noFuel(JP_Blocks.KAWARA_ST_black, new Item.Properties()));

	public static Item KAWARA_SH_white = register("block_ksh_white", new Wablock_noFuel(JP_Blocks.KAWARA_SH_white, new Item.Properties()));
	public static Item KAWARA_SH_orange = register("block_ksh_orange", new Wablock_noFuel(JP_Blocks.KAWARA_SH_orange, new Item.Properties()));
	public static Item KAWARA_SH_magenta = register("block_ksh_magenta", new Wablock_noFuel(JP_Blocks.KAWARA_SH_magenta, new Item.Properties()));
	public static Item KAWARA_SH_lightb = register("block_ksh_lightb", new Wablock_noFuel(JP_Blocks.KAWARA_SH_lightb, new Item.Properties()));
	public static Item KAWARA_SH_yellow = register("block_ksh_yellow", new Wablock_noFuel(JP_Blocks.KAWARA_SH_yellow, new Item.Properties()));
	public static Item KAWARA_SH_lime = register("block_ksh_lime", new Wablock_noFuel(JP_Blocks.KAWARA_SH_lime, new Item.Properties()));
	public static Item KAWARA_SH_pink = register("block_ksh_pink", new Wablock_noFuel(JP_Blocks.KAWARA_SH_pink, new Item.Properties()));
	public static Item KAWARA_SH_gray = register("block_ksh_gray", new Wablock_noFuel(JP_Blocks.KAWARA_SH_gray, new Item.Properties()));
	public static Item KAWARA_SH_lightg = register("block_ksh_lightg", new Wablock_noFuel(JP_Blocks.KAWARA_SH_lightg, new Item.Properties()));
	public static Item KAWARA_SH_cyan = register("block_ksh_cyan", new Wablock_noFuel(JP_Blocks.KAWARA_SH_cyan, new Item.Properties()));
	public static Item KAWARA_SH_purple = register("block_ksh_purple", new Wablock_noFuel(JP_Blocks.KAWARA_SH_purple, new Item.Properties()));
	public static Item KAWARA_SH_blue = register("block_ksh_blue", new Wablock_noFuel(JP_Blocks.KAWARA_SH_blue, new Item.Properties()));
	public static Item KAWARA_SH_brown = register("block_ksh_brown", new Wablock_noFuel(JP_Blocks.KAWARA_SH_brown, new Item.Properties()));
	public static Item KAWARA_SH_green = register("block_ksh_green", new Wablock_noFuel(JP_Blocks.KAWARA_SH_green, new Item.Properties()));
	public static Item KAWARA_SH_red = register("block_ksh_red", new Wablock_noFuel(JP_Blocks.KAWARA_SH_red, new Item.Properties()));
	public static Item KAWARA_SH_black = register("block_ksh_black", new Wablock_noFuel(JP_Blocks.KAWARA_SH_black, new Item.Properties()));

	public static Item DIRTWALL = register("block_dirtwall", new Wablock_noFuel(JP_Blocks.DIRTWALL, new Item.Properties()));
	public static Item DIRTWALL_stairs = register("block_dirtwall_st", new Wablock_noFuel(JP_Blocks.DIRTWALL_stairs, new Item.Properties()));
	public static Item DIRTWALL_SH = register("block_dirtwall_sh", new Wablock_noFuel(JP_Blocks.DIRTWALL_SH, new Item.Properties()));

	public static Item SHIKKUI_white = register("block_plaster_white", new Wablock_noFuel(JP_Blocks.SHIKKUI_white, new Item.Properties()));
	public static Item SHIKKUI_orange = register("block_plaster_orange", new Wablock_noFuel(JP_Blocks.SHIKKUI_orange, new Item.Properties()));
	public static Item SHIKKUI_magenta = register("block_plaster_magenta", new Wablock_noFuel(JP_Blocks.SHIKKUI_magenta, new Item.Properties()));
	public static Item SHIKKUI_lightb = register("block_plaster_lightb", new Wablock_noFuel(JP_Blocks.SHIKKUI_lightb, new Item.Properties()));
	public static Item SHIKKUI_yellow = register("block_plaster_yellow", new Wablock_noFuel(JP_Blocks.SHIKKUI_yellow, new Item.Properties()));
	public static Item SHIKKUI_lime = register("block_plaster_lime", new Wablock_noFuel(JP_Blocks.SHIKKUI_lime, new Item.Properties()));
	public static Item SHIKKUI_pink = register("block_plaster_pink", new Wablock_noFuel(JP_Blocks.SHIKKUI_pink, new Item.Properties()));
	public static Item SHIKKUI_gray = register("block_plaster_gray", new Wablock_noFuel(JP_Blocks.SHIKKUI_gray, new Item.Properties()));
	public static Item SHIKKUI_lightg = register("block_plaster_lightg", new Wablock_noFuel(JP_Blocks.SHIKKUI_lightg, new Item.Properties()));
	public static Item SHIKKUI_cyan = register("block_plaster_cyan", new Wablock_noFuel(JP_Blocks.SHIKKUI_cyan, new Item.Properties()));
	public static Item SHIKKUI_purple = register("block_plaster_purple", new Wablock_noFuel(JP_Blocks.SHIKKUI_purple, new Item.Properties()));
	public static Item SHIKKUI_blue = register("block_plaster_blue", new Wablock_noFuel(JP_Blocks.SHIKKUI_blue, new Item.Properties()));
	public static Item SHIKKUI_brown = register("block_plaster_brown", new Wablock_noFuel(JP_Blocks.SHIKKUI_brown, new Item.Properties()));
	public static Item SHIKKUI_green = register("block_plaster_green", new Wablock_noFuel(JP_Blocks.SHIKKUI_green, new Item.Properties()));
	public static Item SHIKKUI_red = register("block_plaster_red", new Wablock_noFuel(JP_Blocks.SHIKKUI_red, new Item.Properties()));
	public static Item SHIKKUI_black = register("block_plaster_black", new Wablock_noFuel(JP_Blocks.SHIKKUI_black, new Item.Properties()));

	public static Item SHIKKUI_ST_white = register("block_pst_white", new Wablock_noFuel(JP_Blocks.SHIKKUI_ST_white, new Item.Properties()));
	public static Item SHIKKUI_ST_orange = register("block_pst_orange", new Wablock_noFuel(JP_Blocks.SHIKKUI_ST_orange, new Item.Properties()));
	public static Item SHIKKUI_ST_magenta = register("block_pst_magenta", new Wablock_noFuel(JP_Blocks.SHIKKUI_ST_magenta, new Item.Properties()));
	public static Item SHIKKUI_ST_lightb = register("block_pst_lightb", new Wablock_noFuel(JP_Blocks.SHIKKUI_ST_lightb, new Item.Properties()));
	public static Item SHIKKUI_ST_yellow = register("block_pst_yellow", new Wablock_noFuel(JP_Blocks.SHIKKUI_ST_yellow, new Item.Properties()));
	public static Item SHIKKUI_ST_lime = register("block_pst_lime", new Wablock_noFuel(JP_Blocks.SHIKKUI_ST_lime, new Item.Properties()));
	public static Item SHIKKUI_ST_pink = register("block_pst_pink", new Wablock_noFuel(JP_Blocks.SHIKKUI_ST_pink, new Item.Properties()));
	public static Item SHIKKUI_ST_gray = register("block_pst_gray", new Wablock_noFuel(JP_Blocks.SHIKKUI_ST_gray, new Item.Properties()));
	public static Item SHIKKUI_ST_lightg = register("block_pst_lightg", new Wablock_noFuel(JP_Blocks.SHIKKUI_ST_lightg, new Item.Properties()));
	public static Item SHIKKUI_ST_cyan = register("block_pst_cyan", new Wablock_noFuel(JP_Blocks.SHIKKUI_ST_cyan, new Item.Properties()));
	public static Item SHIKKUI_ST_purple = register("block_pst_purple", new Wablock_noFuel(JP_Blocks.SHIKKUI_ST_purple, new Item.Properties()));
	public static Item SHIKKUI_ST_blue = register("block_pst_blue", new Wablock_noFuel(JP_Blocks.SHIKKUI_ST_blue, new Item.Properties()));
	public static Item SHIKKUI_ST_brown = register("block_pst_brown", new Wablock_noFuel(JP_Blocks.SHIKKUI_ST_brown, new Item.Properties()));
	public static Item SHIKKUI_ST_green = register("block_pst_green", new Wablock_noFuel(JP_Blocks.SHIKKUI_ST_green, new Item.Properties()));
	public static Item SHIKKUI_ST_red = register("block_pst_red", new Wablock_noFuel(JP_Blocks.SHIKKUI_ST_red, new Item.Properties()));
	public static Item SHIKKUI_ST_black = register("block_pst_black", new Wablock_noFuel(JP_Blocks.SHIKKUI_ST_black, new Item.Properties()));

	public static Item SHIKKUI_SH_white = register("block_psh_white", new Wablock_noFuel(JP_Blocks.SHIKKUI_SH_white, new Item.Properties()));
	public static Item SHIKKUI_SH_orange = register("block_psh_orange", new Wablock_noFuel(JP_Blocks.SHIKKUI_SH_orange, new Item.Properties()));
	public static Item SHIKKUI_SH_magenta = register("block_psh_magenta", new Wablock_noFuel(JP_Blocks.SHIKKUI_SH_magenta, new Item.Properties()));
	public static Item SHIKKUI_SH_lightb = register("block_psh_lightb", new Wablock_noFuel(JP_Blocks.SHIKKUI_SH_lightb, new Item.Properties()));
	public static Item SHIKKUI_SH_yellow = register("block_psh_yellow", new Wablock_noFuel(JP_Blocks.SHIKKUI_SH_yellow, new Item.Properties()));
	public static Item SHIKKUI_SH_lime = register("block_psh_lime", new Wablock_noFuel(JP_Blocks.SHIKKUI_SH_lime, new Item.Properties()));
	public static Item SHIKKUI_SH_pink = register("block_psh_pink", new Wablock_noFuel(JP_Blocks.SHIKKUI_SH_pink, new Item.Properties()));
	public static Item SHIKKUI_SH_gray = register("block_psh_gray", new Wablock_noFuel(JP_Blocks.SHIKKUI_SH_gray, new Item.Properties()));
	public static Item SHIKKUI_SH_lightg = register("block_psh_lightg", new Wablock_noFuel(JP_Blocks.SHIKKUI_SH_lightg, new Item.Properties()));
	public static Item SHIKKUI_SH_cyan = register("block_psh_cyan", new Wablock_noFuel(JP_Blocks.SHIKKUI_SH_cyan, new Item.Properties()));
	public static Item SHIKKUI_SH_purple = register("block_psh_purple", new Wablock_noFuel(JP_Blocks.SHIKKUI_SH_purple, new Item.Properties()));
	public static Item SHIKKUI_SH_blue = register("block_psh_blue", new Wablock_noFuel(JP_Blocks.SHIKKUI_SH_blue, new Item.Properties()));
	public static Item SHIKKUI_SH_brown = register("block_psh_brown", new Wablock_noFuel(JP_Blocks.SHIKKUI_SH_brown, new Item.Properties()));
	public static Item SHIKKUI_SH_green = register("block_psh_green", new Wablock_noFuel(JP_Blocks.SHIKKUI_SH_green, new Item.Properties()));
	public static Item SHIKKUI_SH_red = register("block_psh_red", new Wablock_noFuel(JP_Blocks.SHIKKUI_SH_red, new Item.Properties()));
	public static Item SHIKKUI_SH_black = register("block_psh_black", new Wablock_noFuel(JP_Blocks.SHIKKUI_SH_black, new Item.Properties()));

	public static Item NAMAKO_white = register("block_namako_white", new Wablock_noFuel(JP_Blocks.NAMAKO_white, new Item.Properties()));
	public static Item NAMAKO_orange = register("block_namako_orange", new Wablock_noFuel(JP_Blocks.NAMAKO_orange, new Item.Properties()));
	public static Item NAMAKO_magenta = register("block_namako_magenta", new Wablock_noFuel(JP_Blocks.NAMAKO_magenta, new Item.Properties()));
	public static Item NAMAKO_lightb = register("block_namako_lightb", new Wablock_noFuel(JP_Blocks.NAMAKO_lightb, new Item.Properties()));
	public static Item NAMAKO_yellow = register("block_namako_yellow", new Wablock_noFuel(JP_Blocks.NAMAKO_yellow, new Item.Properties()));
	public static Item NAMAKO_lime = register("block_namako_lime", new Wablock_noFuel(JP_Blocks.NAMAKO_lime, new Item.Properties()));
	public static Item NAMAKO_pink = register("block_namako_pink", new Wablock_noFuel(JP_Blocks.NAMAKO_pink, new Item.Properties()));
	public static Item NAMAKO_gray = register("block_namako_gray", new Wablock_noFuel(JP_Blocks.NAMAKO_gray, new Item.Properties()));
	public static Item NAMAKO_lightg = register("block_namako_lightg", new Wablock_noFuel(JP_Blocks.NAMAKO_lightg, new Item.Properties()));
	public static Item NAMAKO_cyan = register("block_namako_cyan", new Wablock_noFuel(JP_Blocks.NAMAKO_cyan, new Item.Properties()));
	public static Item NAMAKO_purple = register("block_namako_purple", new Wablock_noFuel(JP_Blocks.NAMAKO_purple, new Item.Properties()));
	public static Item NAMAKO_blue = register("block_namako_blue", new Wablock_noFuel(JP_Blocks.NAMAKO_blue, new Item.Properties()));
	public static Item NAMAKO_brown = register("block_namako_brown", new Wablock_noFuel(JP_Blocks.NAMAKO_brown, new Item.Properties()));
	public static Item NAMAKO_green = register("block_namako_green", new Wablock_noFuel(JP_Blocks.NAMAKO_green, new Item.Properties()));
	public static Item NAMAKO_red = register("block_namako_red", new Wablock_noFuel(JP_Blocks.NAMAKO_red, new Item.Properties()));
	public static Item NAMAKO_black = register("block_namako_black", new Wablock_noFuel(JP_Blocks.NAMAKO_black, new Item.Properties()));

	public static Item NAMAKO_ST_white = register("block_nst_white", new Wablock_noFuel(JP_Blocks.NAMAKO_ST_white, new Item.Properties()));
	public static Item NAMAKO_ST_orange = register("block_nst_orange", new Wablock_noFuel(JP_Blocks.NAMAKO_ST_orange, new Item.Properties()));
	public static Item NAMAKO_ST_magenta = register("block_nst_magenta", new Wablock_noFuel(JP_Blocks.NAMAKO_ST_magenta, new Item.Properties()));
	public static Item NAMAKO_ST_lightb = register("block_nst_lightb", new Wablock_noFuel(JP_Blocks.NAMAKO_ST_lightb, new Item.Properties()));
	public static Item NAMAKO_ST_yellow = register("block_nst_yellow", new Wablock_noFuel(JP_Blocks.NAMAKO_ST_yellow, new Item.Properties()));
	public static Item NAMAKO_ST_lime = register("block_nst_lime", new Wablock_noFuel(JP_Blocks.NAMAKO_ST_lime, new Item.Properties()));
	public static Item NAMAKO_ST_pink = register("block_nst_pink", new Wablock_noFuel(JP_Blocks.NAMAKO_ST_pink, new Item.Properties()));
	public static Item NAMAKO_ST_gray = register("block_nst_gray", new Wablock_noFuel(JP_Blocks.NAMAKO_ST_gray, new Item.Properties()));
	public static Item NAMAKO_ST_lightg = register("block_nst_lightg", new Wablock_noFuel(JP_Blocks.NAMAKO_ST_lightg, new Item.Properties()));
	public static Item NAMAKO_ST_cyan = register("block_nst_cyan", new Wablock_noFuel(JP_Blocks.NAMAKO_ST_cyan, new Item.Properties()));
	public static Item NAMAKO_ST_purple = register("block_nst_purple", new Wablock_noFuel(JP_Blocks.NAMAKO_ST_purple, new Item.Properties()));
	public static Item NAMAKO_ST_blue = register("block_nst_blue", new Wablock_noFuel(JP_Blocks.NAMAKO_ST_blue, new Item.Properties()));
	public static Item NAMAKO_ST_brown = register("block_nst_brown", new Wablock_noFuel(JP_Blocks.NAMAKO_ST_brown, new Item.Properties()));
	public static Item NAMAKO_ST_green = register("block_nst_green", new Wablock_noFuel(JP_Blocks.NAMAKO_ST_green, new Item.Properties()));
	public static Item NAMAKO_ST_red = register("block_nst_red", new Wablock_noFuel(JP_Blocks.NAMAKO_ST_red, new Item.Properties()));
	public static Item NAMAKO_ST_black = register("block_nst_black", new Wablock_noFuel(JP_Blocks.NAMAKO_ST_black, new Item.Properties()));

	public static Item NAMAKO_SH_white = register("block_nsh_white", new Wablock_noFuel(JP_Blocks.NAMAKO_SH_white, new Item.Properties()));
	public static Item NAMAKO_SH_orange = register("block_nsh_orange", new Wablock_noFuel(JP_Blocks.NAMAKO_SH_orange, new Item.Properties()));
	public static Item NAMAKO_SH_magenta = register("block_nsh_magenta", new Wablock_noFuel(JP_Blocks.NAMAKO_SH_magenta, new Item.Properties()));
	public static Item NAMAKO_SH_lightb = register("block_nsh_lightb", new Wablock_noFuel(JP_Blocks.NAMAKO_SH_lightb, new Item.Properties()));
	public static Item NAMAKO_SH_yellow = register("block_nsh_yellow", new Wablock_noFuel(JP_Blocks.NAMAKO_SH_yellow, new Item.Properties()));
	public static Item NAMAKO_SH_lime = register("block_nsh_lime", new Wablock_noFuel(JP_Blocks.NAMAKO_SH_lime, new Item.Properties()));
	public static Item NAMAKO_SH_pink = register("block_nsh_pink", new Wablock_noFuel(JP_Blocks.NAMAKO_SH_pink, new Item.Properties()));
	public static Item NAMAKO_SH_gray = register("block_nsh_gray", new Wablock_noFuel(JP_Blocks.NAMAKO_SH_gray, new Item.Properties()));
	public static Item NAMAKO_SH_lightg = register("block_nsh_lightg", new Wablock_noFuel(JP_Blocks.NAMAKO_SH_lightg, new Item.Properties()));
	public static Item NAMAKO_SH_cyan = register("block_nsh_cyan", new Wablock_noFuel(JP_Blocks.NAMAKO_SH_cyan, new Item.Properties()));
	public static Item NAMAKO_SH_purple = register("block_nsh_purple", new Wablock_noFuel(JP_Blocks.NAMAKO_SH_purple, new Item.Properties()));
	public static Item NAMAKO_SH_blue = register("block_nsh_blue", new Wablock_noFuel(JP_Blocks.NAMAKO_SH_blue, new Item.Properties()));
	public static Item NAMAKO_SH_brown = register("block_nsh_brown", new Wablock_noFuel(JP_Blocks.NAMAKO_SH_brown, new Item.Properties()));
	public static Item NAMAKO_SH_green = register("block_nsh_green", new Wablock_noFuel(JP_Blocks.NAMAKO_SH_green, new Item.Properties()));
	public static Item NAMAKO_SH_red = register("block_nsh_red", new Wablock_noFuel(JP_Blocks.NAMAKO_SH_red, new Item.Properties()));
	public static Item NAMAKO_SH_black = register("block_nsh_black", new Wablock_noFuel(JP_Blocks.NAMAKO_SH_black, new Item.Properties()));

	public static Item NAMAKOB_white = register("block_namako_b_white", new Wablock_noFuel(JP_Blocks.NAMAKOB_white, new Item.Properties()));
	public static Item NAMAKOB_orange = register("block_namako_b_orange", new Wablock_noFuel(JP_Blocks.NAMAKOB_orange, new Item.Properties()));
	public static Item NAMAKOB_magenta = register("block_namako_b_magenta", new Wablock_noFuel(JP_Blocks.NAMAKOB_magenta, new Item.Properties()));
	public static Item NAMAKOB_lightb = register("block_namako_b_lightb", new Wablock_noFuel(JP_Blocks.NAMAKOB_lightb, new Item.Properties()));
	public static Item NAMAKOB_yellow = register("block_namako_b_yellow", new Wablock_noFuel(JP_Blocks.NAMAKOB_yellow, new Item.Properties()));
	public static Item NAMAKOB_lime = register("block_namako_b_lime", new Wablock_noFuel(JP_Blocks.NAMAKOB_lime, new Item.Properties()));
	public static Item NAMAKOB_pink = register("block_namako_b_pink", new Wablock_noFuel(JP_Blocks.NAMAKOB_pink, new Item.Properties()));
	public static Item NAMAKOB_gray = register("block_namako_b_gray", new Wablock_noFuel(JP_Blocks.NAMAKOB_gray, new Item.Properties()));
	public static Item NAMAKOB_lightg = register("block_namako_b_lightg", new Wablock_noFuel(JP_Blocks.NAMAKOB_lightg, new Item.Properties()));
	public static Item NAMAKOB_cyan = register("block_namako_b_cyan", new Wablock_noFuel(JP_Blocks.NAMAKOB_cyan, new Item.Properties()));
	public static Item NAMAKOB_purple = register("block_namako_b_purple", new Wablock_noFuel(JP_Blocks.NAMAKOB_purple, new Item.Properties()));
	public static Item NAMAKOB_blue = register("block_namako_b_blue", new Wablock_noFuel(JP_Blocks.NAMAKOB_blue, new Item.Properties()));
	public static Item NAMAKOB_brown = register("block_namako_b_brown", new Wablock_noFuel(JP_Blocks.NAMAKOB_brown, new Item.Properties()));
	public static Item NAMAKOB_green = register("block_namako_b_green", new Wablock_noFuel(JP_Blocks.NAMAKOB_green, new Item.Properties()));
	public static Item NAMAKOB_red = register("block_namako_b_red", new Wablock_noFuel(JP_Blocks.NAMAKOB_red, new Item.Properties()));
	public static Item NAMAKOB_black = register("block_namako_b_black", new Wablock_noFuel(JP_Blocks.NAMAKOB_black, new Item.Properties()));

	public static Item NAMAKOB_ST_white = register("block_nst_b_white", new Wablock_noFuel(JP_Blocks.NAMAKOB_ST_white, new Item.Properties()));
	public static Item NAMAKOB_ST_orange = register("block_nst_b_orange", new Wablock_noFuel(JP_Blocks.NAMAKOB_ST_orange, new Item.Properties()));
	public static Item NAMAKOB_ST_magenta = register("block_nst_b_magenta", new Wablock_noFuel(JP_Blocks.NAMAKOB_ST_magenta, new Item.Properties()));
	public static Item NAMAKOB_ST_lightb = register("block_nst_b_lightb", new Wablock_noFuel(JP_Blocks.NAMAKOB_ST_lightb, new Item.Properties()));
	public static Item NAMAKOB_ST_yellow = register("block_nst_b_yellow", new Wablock_noFuel(JP_Blocks.NAMAKOB_ST_yellow, new Item.Properties()));
	public static Item NAMAKOB_ST_lime = register("block_nst_b_lime", new Wablock_noFuel(JP_Blocks.NAMAKOB_ST_lime, new Item.Properties()));
	public static Item NAMAKOB_ST_pink = register("block_nst_b_pink", new Wablock_noFuel(JP_Blocks.NAMAKOB_ST_pink, new Item.Properties()));
	public static Item NAMAKOB_ST_gray = register("block_nst_b_gray", new Wablock_noFuel(JP_Blocks.NAMAKOB_ST_gray, new Item.Properties()));
	public static Item NAMAKOB_ST_lightg = register("block_nst_b_lightg", new Wablock_noFuel(JP_Blocks.NAMAKOB_ST_lightg, new Item.Properties()));
	public static Item NAMAKOB_ST_cyan = register("block_nst_b_cyan", new Wablock_noFuel(JP_Blocks.NAMAKOB_ST_cyan, new Item.Properties()));
	public static Item NAMAKOB_ST_purple = register("block_nst_b_purple", new Wablock_noFuel(JP_Blocks.NAMAKOB_ST_purple, new Item.Properties()));
	public static Item NAMAKOB_ST_blue = register("block_nst_b_blue", new Wablock_noFuel(JP_Blocks.NAMAKOB_ST_blue, new Item.Properties()));
	public static Item NAMAKOB_ST_brown = register("block_nst_b_brown", new Wablock_noFuel(JP_Blocks.NAMAKOB_ST_brown, new Item.Properties()));
	public static Item NAMAKOB_ST_green = register("block_nst_b_green", new Wablock_noFuel(JP_Blocks.NAMAKOB_ST_green, new Item.Properties()));
	public static Item NAMAKOB_ST_red = register("block_nst_b_red", new Wablock_noFuel(JP_Blocks.NAMAKOB_ST_red, new Item.Properties()));
	public static Item NAMAKOB_ST_black = register("block_nst_b_black", new Wablock_noFuel(JP_Blocks.NAMAKOB_ST_black, new Item.Properties()));

	public static Item NAMAKOB_SH_white = register("block_nsh_b_white", new Wablock_noFuel(JP_Blocks.NAMAKOB_SH_white, new Item.Properties()));
	public static Item NAMAKOB_SH_orange = register("block_nsh_b_orange", new Wablock_noFuel(JP_Blocks.NAMAKOB_SH_orange, new Item.Properties()));
	public static Item NAMAKOB_SH_magenta = register("block_nsh_b_magenta", new Wablock_noFuel(JP_Blocks.NAMAKOB_SH_magenta, new Item.Properties()));
	public static Item NAMAKOB_SH_lightb = register("block_nsh_b_lightb", new Wablock_noFuel(JP_Blocks.NAMAKOB_SH_lightb, new Item.Properties()));
	public static Item NAMAKOB_SH_yellow = register("block_nsh_b_yellow", new Wablock_noFuel(JP_Blocks.NAMAKOB_SH_yellow, new Item.Properties()));
	public static Item NAMAKOB_SH_lime = register("block_nsh_b_lime", new Wablock_noFuel(JP_Blocks.NAMAKOB_SH_lime, new Item.Properties()));
	public static Item NAMAKOB_SH_pink = register("block_nsh_b_pink", new Wablock_noFuel(JP_Blocks.NAMAKOB_SH_pink, new Item.Properties()));
	public static Item NAMAKOB_SH_gray = register("block_nsh_b_gray", new Wablock_noFuel(JP_Blocks.NAMAKOB_SH_gray, new Item.Properties()));
	public static Item NAMAKOB_SH_lightg = register("block_nsh_b_lightg", new Wablock_noFuel(JP_Blocks.NAMAKOB_SH_lightg, new Item.Properties()));
	public static Item NAMAKOB_SH_cyan = register("block_nsh_b_cyan", new Wablock_noFuel(JP_Blocks.NAMAKOB_SH_cyan, new Item.Properties()));
	public static Item NAMAKOB_SH_purple = register("block_nsh_b_purple", new Wablock_noFuel(JP_Blocks.NAMAKOB_SH_purple, new Item.Properties()));
	public static Item NAMAKOB_SH_blue = register("block_nsh_b_blue", new Wablock_noFuel(JP_Blocks.NAMAKOB_SH_blue, new Item.Properties()));
	public static Item NAMAKOB_SH_brown = register("block_nsh_b_brown", new Wablock_noFuel(JP_Blocks.NAMAKOB_SH_brown, new Item.Properties()));
	public static Item NAMAKOB_SH_green = register("block_nsh_b_green", new Wablock_noFuel(JP_Blocks.NAMAKOB_SH_green, new Item.Properties()));
	public static Item NAMAKOB_SH_red = register("block_nsh_b_red", new Wablock_noFuel(JP_Blocks.NAMAKOB_SH_red, new Item.Properties()));
	public static Item NAMAKOB_SH_black = register("block_nsh_b_black", new Wablock_noFuel(JP_Blocks.NAMAKOB_SH_black, new Item.Properties()));

	public static Item DIRTWALL_WALL = register("block_dirtwall_wall", new Wablock_noFuel(JP_Blocks.DIRTWALL_WALL, new Item.Properties()));
	public static Item SHIKKUI_WALL_white = register("block_pwall_white", new Wablock_noFuel(JP_Blocks.SHIKKUI_WALL_white, new Item.Properties()));
	public static Item SHIKKUI_WALL_orange = register("block_pwall_orange", new Wablock_noFuel(JP_Blocks.SHIKKUI_WALL_orange, new Item.Properties()));
	public static Item SHIKKUI_WALL_magenta = register("block_pwall_magenta", new Wablock_noFuel(JP_Blocks.SHIKKUI_WALL_magenta, new Item.Properties()));
	public static Item SHIKKUI_WALL_lightb = register("block_pwall_lightb", new Wablock_noFuel(JP_Blocks.SHIKKUI_WALL_lightb, new Item.Properties()));
	public static Item SHIKKUI_WALL_yellow = register("block_pwall_yellow", new Wablock_noFuel(JP_Blocks.SHIKKUI_WALL_yellow, new Item.Properties()));
	public static Item SHIKKUI_WALL_lime = register("block_pwall_lime", new Wablock_noFuel(JP_Blocks.SHIKKUI_WALL_lime, new Item.Properties()));
	public static Item SHIKKUI_WALL_pink = register("block_pwall_pink", new Wablock_noFuel(JP_Blocks.SHIKKUI_WALL_pink, new Item.Properties()));
	public static Item SHIKKUI_WALL_gray = register("block_pwall_gray", new Wablock_noFuel(JP_Blocks.SHIKKUI_WALL_gray, new Item.Properties()));
	public static Item SHIKKUI_WALL_lightg = register("block_pwall_lightg", new Wablock_noFuel(JP_Blocks.SHIKKUI_WALL_lightg, new Item.Properties()));
	public static Item SHIKKUI_WALL_cyan = register("block_pwall_cyan", new Wablock_noFuel(JP_Blocks.SHIKKUI_WALL_cyan, new Item.Properties()));
	public static Item SHIKKUI_WALL_purple = register("block_pwall_purple", new Wablock_noFuel(JP_Blocks.SHIKKUI_WALL_purple, new Item.Properties()));
	public static Item SHIKKUI_WALL_blue = register("block_pwall_blue", new Wablock_noFuel(JP_Blocks.SHIKKUI_WALL_blue, new Item.Properties()));
	public static Item SHIKKUI_WALL_brown = register("block_pwall_brown", new Wablock_noFuel(JP_Blocks.SHIKKUI_WALL_brown, new Item.Properties()));
	public static Item SHIKKUI_WALL_green = register("block_pwall_green", new Wablock_noFuel(JP_Blocks.SHIKKUI_WALL_green, new Item.Properties()));
	public static Item SHIKKUI_WALL_red = register("block_pwall_red", new Wablock_noFuel(JP_Blocks.SHIKKUI_WALL_red, new Item.Properties()));
	public static Item SHIKKUI_WALL_black = register("block_pwall_black", new Wablock_noFuel(JP_Blocks.SHIKKUI_WALL_black, new Item.Properties()));

	public static Item NAMAKO_WALL_white = register("block_nwall_white", new Wablock_noFuel(JP_Blocks. NAMAKO_WALL_white, new Item.Properties()));
	public static Item NAMAKO_WALL_orange = register("block_nwall_orange", new Wablock_noFuel(JP_Blocks. NAMAKO_WALL_orange, new Item.Properties()));
	public static Item NAMAKO_WALL_magenta = register("block_nwall_magenta", new Wablock_noFuel(JP_Blocks. NAMAKO_WALL_magenta, new Item.Properties()));
	public static Item NAMAKO_WALL_lightb = register("block_nwall_lightb", new Wablock_noFuel(JP_Blocks. NAMAKO_WALL_lightb, new Item.Properties()));
	public static Item NAMAKO_WALL_yellow = register("block_nwall_yellow", new Wablock_noFuel(JP_Blocks. NAMAKO_WALL_yellow, new Item.Properties()));
	public static Item NAMAKO_WALL_lime = register("block_nwall_lime", new Wablock_noFuel(JP_Blocks. NAMAKO_WALL_lime, new Item.Properties()));
	public static Item NAMAKO_WALL_pink = register("block_nwall_pink", new Wablock_noFuel(JP_Blocks. NAMAKO_WALL_pink, new Item.Properties()));
	public static Item NAMAKO_WALL_gray = register("block_nwall_gray", new Wablock_noFuel(JP_Blocks. NAMAKO_WALL_gray, new Item.Properties()));
	public static Item NAMAKO_WALL_lightg = register("block_nwall_lightg", new Wablock_noFuel(JP_Blocks. NAMAKO_WALL_lightg, new Item.Properties()));
	public static Item NAMAKO_WALL_cyan = register("block_nwall_cyan", new Wablock_noFuel(JP_Blocks. NAMAKO_WALL_cyan, new Item.Properties()));
	public static Item NAMAKO_WALL_purple = register("block_nwall_purple", new Wablock_noFuel(JP_Blocks. NAMAKO_WALL_purple, new Item.Properties()));
	public static Item NAMAKO_WALL_blue = register("block_nwall_blue", new Wablock_noFuel(JP_Blocks. NAMAKO_WALL_blue, new Item.Properties()));
	public static Item NAMAKO_WALL_brown = register("block_nwall_brown", new Wablock_noFuel(JP_Blocks. NAMAKO_WALL_brown, new Item.Properties()));
	public static Item NAMAKO_WALL_green = register("block_nwall_green", new Wablock_noFuel(JP_Blocks. NAMAKO_WALL_green, new Item.Properties()));
	public static Item NAMAKO_WALL_red = register("block_nwall_red", new Wablock_noFuel(JP_Blocks. NAMAKO_WALL_red, new Item.Properties()));
	public static Item NAMAKO_WALL_black = register("block_nwall_black", new Wablock_noFuel(JP_Blocks. NAMAKO_WALL_black, new Item.Properties()));
	
	public static Item NAMAKOB_WALL_white = register("block_nwall_b_white", new Wablock_noFuel(JP_Blocks. NAMAKOB_WALL_white, new Item.Properties()));
	public static Item NAMAKOB_WALL_orange = register("block_nwall_b_orange", new Wablock_noFuel(JP_Blocks. NAMAKOB_WALL_orange, new Item.Properties()));
	public static Item NAMAKOB_WALL_magenta = register("block_nwall_b_magenta", new Wablock_noFuel(JP_Blocks. NAMAKOB_WALL_magenta, new Item.Properties()));
	public static Item NAMAKOB_WALL_lightb = register("block_nwall_b_lightb", new Wablock_noFuel(JP_Blocks. NAMAKOB_WALL_lightb, new Item.Properties()));
	public static Item NAMAKOB_WALL_yellow = register("block_nwall_b_yellow", new Wablock_noFuel(JP_Blocks. NAMAKOB_WALL_yellow, new Item.Properties()));
	public static Item NAMAKOB_WALL_lime = register("block_nwall_b_lime", new Wablock_noFuel(JP_Blocks. NAMAKOB_WALL_lime, new Item.Properties()));
	public static Item NAMAKOB_WALL_pink = register("block_nwall_b_pink", new Wablock_noFuel(JP_Blocks. NAMAKOB_WALL_pink, new Item.Properties()));
	public static Item NAMAKOB_WALL_gray = register("block_nwall_b_gray", new Wablock_noFuel(JP_Blocks. NAMAKOB_WALL_gray, new Item.Properties()));
	public static Item NAMAKOB_WALL_lightg = register("block_nwall_b_lightg", new Wablock_noFuel(JP_Blocks. NAMAKOB_WALL_lightg, new Item.Properties()));
	public static Item NAMAKOB_WALL_cyan = register("block_nwall_b_cyan", new Wablock_noFuel(JP_Blocks. NAMAKOB_WALL_cyan, new Item.Properties()));
	public static Item NAMAKOB_WALL_purple = register("block_nwall_b_purple", new Wablock_noFuel(JP_Blocks. NAMAKOB_WALL_purple, new Item.Properties()));
	public static Item NAMAKOB_WALL_blue = register("block_nwall_b_blue", new Wablock_noFuel(JP_Blocks. NAMAKOB_WALL_blue, new Item.Properties()));
	public static Item NAMAKOB_WALL_brown = register("block_nwall_b_brown", new Wablock_noFuel(JP_Blocks. NAMAKOB_WALL_brown, new Item.Properties()));
	public static Item NAMAKOB_WALL_green = register("block_nwall_b_green", new Wablock_noFuel(JP_Blocks. NAMAKOB_WALL_green, new Item.Properties()));
	public static Item NAMAKOB_WALL_red = register("block_nwall_b_red", new Wablock_noFuel(JP_Blocks. NAMAKOB_WALL_red, new Item.Properties()));
	public static Item NAMAKOB_WALL_black = register("block_nwall_b_black", new Wablock_noFuel(JP_Blocks. NAMAKOB_WALL_black, new Item.Properties()));
	
	public static Item DIRTWALL_SAMA = register("block_dirtwall_sama", new Wablock_noFuel(JP_Blocks.DIRTWALL_SAMA, new Item.Properties()));
	public static Item SHIKKUI_SAMA_white = register("block_sama_white", new Wablock_noFuel(JP_Blocks.SHIKKUI_SAMA_white, new Item.Properties()));
	public static Item SHIKKUI_SAMA_orange = register("block_sama_orange", new Wablock_noFuel(JP_Blocks.SHIKKUI_SAMA_orange, new Item.Properties()));
	public static Item SHIKKUI_SAMA_magenta = register("block_sama_magenta", new Wablock_noFuel(JP_Blocks.SHIKKUI_SAMA_magenta, new Item.Properties()));
	public static Item SHIKKUI_SAMA_lightb = register("block_sama_lightb", new Wablock_noFuel(JP_Blocks.SHIKKUI_SAMA_lightb, new Item.Properties()));
	public static Item SHIKKUI_SAMA_yellow = register("block_sama_yellow", new Wablock_noFuel(JP_Blocks.SHIKKUI_SAMA_yellow, new Item.Properties()));
	public static Item SHIKKUI_SAMA_lime = register("block_sama_lime", new Wablock_noFuel(JP_Blocks.SHIKKUI_SAMA_lime, new Item.Properties()));
	public static Item SHIKKUI_SAMA_pink = register("block_sama_pink", new Wablock_noFuel(JP_Blocks.SHIKKUI_SAMA_pink, new Item.Properties()));
	public static Item SHIKKUI_SAMA_gray = register("block_sama_gray", new Wablock_noFuel(JP_Blocks.SHIKKUI_SAMA_gray, new Item.Properties()));
	public static Item SHIKKUI_SAMA_lightg = register("block_sama_lightg", new Wablock_noFuel(JP_Blocks.SHIKKUI_SAMA_lightg, new Item.Properties()));
	public static Item SHIKKUI_SAMA_cyan = register("block_sama_cyan", new Wablock_noFuel(JP_Blocks.SHIKKUI_SAMA_cyan, new Item.Properties()));
	public static Item SHIKKUI_SAMA_purple = register("block_sama_purple", new Wablock_noFuel(JP_Blocks.SHIKKUI_SAMA_purple, new Item.Properties()));
	public static Item SHIKKUI_SAMA_blue = register("block_sama_blue", new Wablock_noFuel(JP_Blocks.SHIKKUI_SAMA_blue, new Item.Properties()));
	public static Item SHIKKUI_SAMA_brown = register("block_sama_brown", new Wablock_noFuel(JP_Blocks.SHIKKUI_SAMA_brown, new Item.Properties()));
	public static Item SHIKKUI_SAMA_green = register("block_sama_green", new Wablock_noFuel(JP_Blocks.SHIKKUI_SAMA_green, new Item.Properties()));
	public static Item SHIKKUI_SAMA_red = register("block_sama_red", new Wablock_noFuel(JP_Blocks.SHIKKUI_SAMA_red, new Item.Properties()));
	public static Item SHIKKUI_SAMA_black = register("block_sama_black", new Wablock_noFuel(JP_Blocks.SHIKKUI_SAMA_black, new Item.Properties()));
	
	public static Item KAWARA_WALL_white = register("block_kwall_white", new Wablock_noFuel(JP_Blocks.KAWARA_WALL_white, new Item.Properties()));
	public static Item KAWARA_WALL_orange = register("block_kwall_orange", new Wablock_noFuel(JP_Blocks.KAWARA_WALL_orange, new Item.Properties()));
	public static Item KAWARA_WALL_magenta = register("block_kwall_magenta", new Wablock_noFuel(JP_Blocks.KAWARA_WALL_magenta, new Item.Properties()));
	public static Item KAWARA_WALL_lightb = register("block_kwall_lightb", new Wablock_noFuel(JP_Blocks.KAWARA_WALL_lightb, new Item.Properties()));
	public static Item KAWARA_WALL_yellow = register("block_kwall_yellow", new Wablock_noFuel(JP_Blocks.KAWARA_WALL_yellow, new Item.Properties()));
	public static Item KAWARA_WALL_lime = register("block_kwall_lime", new Wablock_noFuel(JP_Blocks.KAWARA_WALL_lime, new Item.Properties()));
	public static Item KAWARA_WALL_pink = register("block_kwall_pink", new Wablock_noFuel(JP_Blocks.KAWARA_WALL_pink, new Item.Properties()));
	public static Item KAWARA_WALL_gray = register("block_kwall_gray", new Wablock_noFuel(JP_Blocks.KAWARA_WALL_gray, new Item.Properties()));
	public static Item KAWARA_WALL_lightg = register("block_kwall_lightg", new Wablock_noFuel(JP_Blocks.KAWARA_WALL_lightg, new Item.Properties()));
	public static Item KAWARA_WALL_cyan = register("block_kwall_cyan", new Wablock_noFuel(JP_Blocks.KAWARA_WALL_cyan, new Item.Properties()));
	public static Item KAWARA_WALL_purple = register("block_kwall_purple", new Wablock_noFuel(JP_Blocks.KAWARA_WALL_purple, new Item.Properties()));
	public static Item KAWARA_WALL_blue = register("block_kwall_blue", new Wablock_noFuel(JP_Blocks.KAWARA_WALL_blue, new Item.Properties()));
	public static Item KAWARA_WALL_brown = register("block_kwall_brown", new Wablock_noFuel(JP_Blocks.KAWARA_WALL_brown, new Item.Properties()));
	public static Item KAWARA_WALL_green = register("block_kwall_green", new Wablock_noFuel(JP_Blocks.KAWARA_WALL_green, new Item.Properties()));
	public static Item KAWARA_WALL_red = register("block_kwall_red", new Wablock_noFuel(JP_Blocks.KAWARA_WALL_red, new Item.Properties()));
	public static Item KAWARA_WALL_black = register("block_kwall_black", new Wablock_noFuel(JP_Blocks.KAWARA_WALL_black, new Item.Properties()));
	
	///* Register *///
	private static Item register(String name, Item item) {
		ITEMS.register(name, () -> item);
		return item;
	}

}
