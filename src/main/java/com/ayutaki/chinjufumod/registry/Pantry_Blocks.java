package com.ayutaki.chinjufumod.registry;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.blocks.pantry.CanTea;
import com.ayutaki.chinjufumod.blocks.pantry.Chadutsu;
import com.ayutaki.chinjufumod.blocks.pantry.Pantry_Box;
import com.ayutaki.chinjufumod.blocks.pantry.Pantry_Empty;
import com.ayutaki.chinjufumod.blocks.pantry.Pantry_Sack;
import com.ayutaki.chinjufumod.blocks.pantry.Tawara;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = ChinjufuMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Pantry_Blocks {

	@SuppressWarnings("deprecation")
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, ChinjufuMod.MOD_ID);

	public static Block BOX_H_EMPTY = register("block_boxh_empty", new Pantry_Empty(Block.Properties.create(Material.WOOD)
			.hardnessAndResistance(1.0F, 3.0F).sound(SoundType.WOOD).notSolid()));
	public static Block BOX_H_EMPTY2 = register("block_boxh_empty2", new Pantry_Empty(Block.Properties.create(Material.WOOD)
			.hardnessAndResistance(1.0F, 3.0F).sound(SoundType.WOOD).notSolid()));
	public static Block BOX_H_EMPTY3 = register("block_boxh_empty3", new Pantry_Empty(Block.Properties.create(Material.WOOD)
			.hardnessAndResistance(1.0F, 3.0F).sound(SoundType.PLANT).notSolid()));

	public static Block BOX_H_APPLE = register("block_boxh_apple", pantryBox());
	public static Block BOX_H_BEEF = register("block_boxh_beef", pantryBox());
	public static Block BOX_H_BEETROOT = register("block_boxh_beetroot", pantryBox());
	public static Block BOX_H_BREAD = register("block_boxh_bread", pantryBox());
	public static Block BOX_H_CARROT = register("block_boxh_carrot", pantryBox());
	public static Block BOX_H_CHICKEN = register("block_boxh_chicken", pantryBox());
	public static Block BOX_H_CHORUS = register("block_boxh_chorus", pantryBox());
	public static Block BOX_H_COCO = register("block_boxh_coco", pantrySack());
	public static Block BOX_H_COD = register("block_boxh_cod", pantryBox());
	public static Block BOX_H_EGG = register("block_boxh_egg", pantryBox());

	public static Block BOX_H_FISH = register("block_boxh_fish", pantryBox());

	public static Block BOX_H_FLOUR = register("block_boxh_flour", pantrySack());
	public static Block BOX_H_MUTTON = register("block_boxh_mutton", pantryBox());
	public static Block BOX_H_PORK = register("block_boxh_pork", pantryBox());
	public static Block BOX_H_POTATO = register("block_boxh_potato", pantryBox());
	public static Block BOX_H_RABBIT = register("block_boxh_rabbit", pantryBox());
	public static Block BOX_H_SALMON = register("block_boxh_salmon", pantryBox());
	public static Block BOX_H_SWBERRY = register("block_boxh_swberry", pantryBox());

	public static Block BOX_H_CABBAGE = register("block_boxh_cabbage", pantryBox());
	public static Block BOX_H_HAKUSAI = register("block_boxh_hakusai", pantryBox());
	public static Block BOX_H_CHERRY = register("block_boxh_cherry", pantryBox());
	public static Block BOX_H_CITRUS = register("block_boxh_citrus", pantryBox());
	public static Block BOX_H_CORN = register("block_boxh_corn", pantryBox());
	public static Block BOX_H_GRAPE = register("block_boxh_grape", pantryBox());
	public static Block BOX_H_ONION = register("block_boxh_onion", pantryBox());
	public static Block BOX_H_RICE = register("block_boxh_rice", pantrySack());
	public static Block BOX_H_SOY = register("block_boxh_soy", pantrySack());
	public static Block BOX_H_SPINACH = register("block_boxh_spinach", pantryBox());
	public static Block BOX_H_TOMATO = register("block_boxh_tomato", pantryBox());
	public static Block BOX_H_TGREEN = register("block_boxh_tgreen", pantrySack());
	public static Block BOX_H_TRED = register("block_boxh_tred", pantrySack());

	public static Block CHADUTSU = register("block_tea_chadutsu", new Chadutsu(Block.Properties.create(Material.WOOD)
			.hardnessAndResistance(1.0F, 3.0F).sound(SoundType.STONE).notSolid()));
	public static Block CANTEA = register("block_tea_can", new CanTea(Block.Properties.create(Material.WOOD)
			.hardnessAndResistance(1.0F, 3.0F).sound(SoundType.STONE).notSolid()));
	public static Block TAWARA = register("block_tawara_cm", new Tawara(Block.Properties.create(Material.WOOD)
			.hardnessAndResistance(1.0F, 3.0F).sound(SoundType.PLANT).notSolid()));

	/* Share variables */
	private static Pantry_Box pantryBox() {
		return new Pantry_Box(Block.Properties.create(Material.WOOD).hardnessAndResistance(1.0F, 3.0F).sound(SoundType.WOOD).notSolid());
	}

	private static Pantry_Sack pantrySack() {
		return new Pantry_Sack(Block.Properties.create(Material.WOOD).hardnessAndResistance(1.0F, 3.0F).sound(SoundType.PLANT).notSolid());
	}

	///* Register *///
	private static Block register(String name, Block block) {
		BLOCKS.register(name, () -> block);
		return block;
	}

}
