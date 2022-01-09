package com.ayutaki.chinjufumod.registry;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.blocks.furniture.Andon;
import com.ayutaki.chinjufumod.blocks.futon.Futon;
import com.ayutaki.chinjufumod.blocks.gakki.Wadaiko_Large;
import com.ayutaki.chinjufumod.blocks.gakki.Wadaiko_Small;
import com.ayutaki.chinjufumod.blocks.jpblock.BambooCube;
import com.ayutaki.chinjufumod.blocks.jpblock.BambooFence;
import com.ayutaki.chinjufumod.blocks.jpblock.BambooFenceGate;
import com.ayutaki.chinjufumod.blocks.jpblock.BambooPressurePlate;
import com.ayutaki.chinjufumod.blocks.jpblock.BambooSlab;
import com.ayutaki.chinjufumod.blocks.jpblock.BambooStairs;
import com.ayutaki.chinjufumod.blocks.jpblock.Tatami;
import com.ayutaki.chinjufumod.blocks.jpblock.Tatami_Y;
import com.ayutaki.chinjufumod.blocks.season.Door_CM;
import com.ayutaki.chinjufumod.blocks.season.TrapDoor_CM;
import com.ayutaki.chinjufumod.blocks.season.WoodButton_CM;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = ChinjufuMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class JPdeco_Blocks {

	@SuppressWarnings("deprecation")
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, ChinjufuMod.MOD_ID);

	public static Block TATAMI_H = register("block_tatamih", tatami());
	public static Block TATAMI_H_white = register("block_tatamih_white", tatami());
	public static Block TATAMI_H_orange = register("block_tatamih_orange", tatami());
	public static Block TATAMI_H_magenta = register("block_tatamih_magenta", tatami());
	public static Block TATAMI_H_lightb = register("block_tatamih_lightb", tatami());
	public static Block TATAMI_H_yellow = register("block_tatamih_yellow", tatami());
	public static Block TATAMI_H_lime = register("block_tatamih_lime", tatami());
	public static Block TATAMI_H_pink = register("block_tatamih_pink", tatami());
	public static Block TATAMI_H_gray = register("block_tatamih_gray", tatami());
	public static Block TATAMI_H_lightg = register("block_tatamih_lightg", tatami());
	public static Block TATAMI_H_cyan = register("block_tatamih_cyan", tatami());
	public static Block TATAMI_H_purple = register("block_tatamih_purple", tatami());
	public static Block TATAMI_H_blue = register("block_tatamih_blue", tatami());
	public static Block TATAMI_H_brown = register("block_tatamih_brown", tatami());
	public static Block TATAMI_H_green = register("block_tatamih_green", tatami());
	public static Block TATAMI_H_red = register("block_tatamih_red", tatami());
	public static Block TATAMI_H_black = register("block_tatamih_black", tatami());

	public static Block TATAMI_HY = register("block_tatamih_y", tatamiY());
	public static Block TATAMI_HY_white = register("block_tatamih_y_white", tatamiY());
	public static Block TATAMI_HY_orange = register("block_tatamih_y_orange", tatamiY());
	public static Block TATAMI_HY_magenta = register("block_tatamih_y_magenta", tatamiY());
	public static Block TATAMI_HY_lightb = register("block_tatamih_y_lightb", tatamiY());
	public static Block TATAMI_HY_yellow = register("block_tatamih_y_yellow", tatamiY());
	public static Block TATAMI_HY_lime = register("block_tatamih_y_lime", tatamiY());
	public static Block TATAMI_HY_pink = register("block_tatamih_y_pink", tatamiY());
	public static Block TATAMI_HY_gray = register("block_tatamih_y_gray", tatamiY());
	public static Block TATAMI_HY_lightg = register("block_tatamih_y_lightg", tatamiY());
	public static Block TATAMI_HY_cyan = register("block_tatamih_y_cyan", tatamiY());
	public static Block TATAMI_HY_purple = register("block_tatamih_y_purple", tatamiY());
	public static Block TATAMI_HY_blue = register("block_tatamih_y_blue", tatamiY());
	public static Block TATAMI_HY_brown = register("block_tatamih_y_brown", tatamiY());
	public static Block TATAMI_HY_green = register("block_tatamih_y_green", tatamiY());
	public static Block TATAMI_HY_red = register("block_tatamih_y_red", tatamiY());
	public static Block TATAMI_HY_black = register("block_tatamih_y_black", tatamiY());

	public static Block TAKECUBE = register("block_bamboo_cube", bambooCube());
	public static Block TAKECUBE_Y = register("block_bamboo_y_cube", bambooCube());
	public static Block TAKECUBE_K = register("block_bamboo_k_cube", bambooCube());
	public static Block TAKE_ST = register("block_bamboo_stairs", bambooStairs());
	public static Block TAKE_STY = register("block_bamboo_y_stairs", bambooStairs());
	public static Block TAKE_STK = register("block_bamboo_k_stairs", bambooStairs());
	public static Block TAKE_SH = register("block_bamboo_slab", bambooSlab());
	public static Block TAKE_SHY = register("block_bamboo_y_slab", bambooSlab());
	public static Block TAKE_SHK = register("block_bamboo_k_slab", bambooSlab());

	public static Block TAKEFENCE = register("block_bamboo_fence", bambooFence());
	public static Block TAKEFENCE_Y = register("block_bamboo_y_fence", bambooFence());
	public static Block TAKEFENCE_K = register("block_bamboo_k_fence", bambooFence());
	public static Block TAKEFENCEGATE = register("block_bamboo_fencegate", bambooFenceGate());
	public static Block TAKEFENCEGATE_Y = register("block_bamboo_y_fencegate", bambooFenceGate());
	public static Block TAKEFENCEGATE_K = register("block_bamboo_k_fencegate", bambooFenceGate());
	public static Block TAKEDOOR = register("block_bamboo_door", bambooDoor());
	public static Block TAKEDOOR_Y = register("block_bamboo_y_door", bambooDoor());
	public static Block TAKEDOOR_K = register("block_bamboo_k_door", bambooDoor());

	public static Block TAKE_TRAPDOOR = register("block_bamboo_trapdoor", bambooTrapDoor());
	public static Block TAKE_TRAPDOOR_Y = register("block_bamboo_y_trapdoor", bambooTrapDoor());
	public static Block TAKE_TRAPDOOR_K = register("block_bamboo_k_trapdoor", bambooTrapDoor());
	public static Block TAKE_PLATE = register("block_bamboo_plate", bambooPressurePlate());
	public static Block TAKE_PLATE_Y = register("block_bamboo_y_plate", bambooPressurePlate());
	public static Block TAKE_PLATE_K = register("block_bamboo_k_plate", bambooPressurePlate());
	public static Block TAKE_BUTTON = register("block_bamboo_button", bambooButton());
	public static Block TAKE_BUTTON_Y = register("block_bamboo_y_button", bambooButton());
	public static Block TAKE_BUTTON_K = register("block_bamboo_k_button", bambooButton());

	public static Block ANDON_white = register("block_andon_white", andon());
	public static Block ANDON_orange = register("block_andon_orange", andon());
	public static Block ANDON_magenta = register("block_andon_magenta", andon());
	public static Block ANDON_lightb = register("block_andon_lightb", andon());
	public static Block ANDON_yellow = register("block_andon_yellow", andon());
	public static Block ANDON_lime = register("block_andon_lime", andon());
	public static Block ANDON_pink = register("block_andon_pink", andon());
	public static Block ANDON_gray = register("block_andon_gray", andon());
	public static Block ANDON_lightg = register("block_andon_lightg", andon());
	public static Block ANDON_cyan = register("block_andon_cyan", andon());
	public static Block ANDON_purple = register("block_andon_purple", andon());
	public static Block ANDON_blue = register("block_andon_blue", andon());
	public static Block ANDON_brown = register("block_andon_brown", andon());
	public static Block ANDON_green = register("block_andon_green", andon());
	public static Block ANDON_red = register("block_andon_red", andon());
	public static Block ANDON_black = register("block_andon_black", andon());

	public static Block FUTON_white = register("block_futon_c_white", futon());
	public static Block FUTON_orange = register("block_futon_c_orange", futon());
	public static Block FUTON_magenta = register("block_futon_c_magenta", futon());
	public static Block FUTON_lightb = register("block_futon_c_lightb", futon());
	public static Block FUTON_yellow = register("block_futon_c_yellow", futon());
	public static Block FUTON_lime = register("block_futon_c_lime", futon());
	public static Block FUTON_pink = register("block_futon_c_pink", futon());
	public static Block FUTON_gray = register("block_futon_c_gray", futon());
	public static Block FUTON_lightg = register("block_futon_c_lightg", futon());
	public static Block FUTON_cyan = register("block_futon_c_cyan", futon());
	public static Block FUTON_purple = register("block_futon_c_purple", futon());
	public static Block FUTON_blue = register("block_futon_c_blue", futon());
	public static Block FUTON_brown = register("block_futon_c_brown", futon());
	public static Block FUTON_green = register("block_futon_c_green", futon());
	public static Block FUTON_red = register("block_futon_c_red", futon());
	public static Block FUTON_black = register("block_futon_c_black", futon());

	public static Block WADAIKO = register("block_wadaiko", new Wadaiko_Large(Block.Properties.create(Material.WOOD)
			.hardnessAndResistance(1.0F, 1.0F).sound(SoundType.WOOD).notSolid()));
	public static Block WADAIKO_small = register("block_wadaiko_small", new Wadaiko_Small(Block.Properties.create(Material.WOOD)
			.hardnessAndResistance(1.0F, 1.0F).sound(SoundType.WOOD).notSolid()));

	/* Share variables */
	private static Tatami tatami() {
		return new Tatami(Block.Properties.create(Material.WOOD).hardnessAndResistance(1.0F, 3.0F).sound(SoundType.PLANT).notSolid());
	}

	private static Tatami_Y tatamiY() {
		return new Tatami_Y(Block.Properties.create(Material.WOOD).hardnessAndResistance(1.0F, 3.0F).sound(SoundType.PLANT).notSolid());
	}

	private static BambooCube bambooCube() {
		return new BambooCube(Block.Properties.create(Material.WOOD).hardnessAndResistance(1.0F, 3.0F).sound(SoundType.WOOD).notSolid());
	}

	private static BambooStairs bambooStairs() {
		return new BambooStairs(TAKECUBE.getDefaultState(), Block.Properties.create(Material.WOOD)
				.hardnessAndResistance(1.0F, 3.0F).sound(SoundType.WOOD).notSolid());
	}

	private static BambooSlab bambooSlab() {
		return new BambooSlab(Block.Properties.create(Material.WOOD).hardnessAndResistance(1.0F, 3.0F).sound(SoundType.WOOD).notSolid());
	}

	private static BambooFence bambooFence() {
		return new BambooFence(Block.Properties.create(Material.WOOD).hardnessAndResistance(1.0F, 3.0F).sound(SoundType.WOOD).notSolid());
	}

	private static BambooFenceGate bambooFenceGate() {
		return new BambooFenceGate(Block.Properties.create(Material.WOOD).hardnessAndResistance(1.0F, 3.0F).sound(SoundType.WOOD).notSolid());
	}

	private static Door_CM bambooDoor() {
		return new Door_CM(Block.Properties.create(Material.WOOD).hardnessAndResistance(1.0F, 3.0F).sound(SoundType.WOOD).notSolid());
	}

	private static TrapDoor_CM bambooTrapDoor() {
		return new TrapDoor_CM(Block.Properties.create(Material.WOOD).hardnessAndResistance(1.0F, 3.0F).sound(SoundType.WOOD).notSolid());
	}

	private static BambooPressurePlate bambooPressurePlate() {
		return new BambooPressurePlate(BambooPressurePlate.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD)
				.hardnessAndResistance(0.5F).sound(SoundType.WOOD).notSolid());
	}

	private static WoodButton_CM bambooButton() {
		return new WoodButton_CM(Block.Properties.create(Material.WOOD).hardnessAndResistance(0.5F).sound(SoundType.WOOD).notSolid());
	}


	private static Andon andon() {
		return new Andon(Block.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F, 1.0F).sound(SoundType.WOOD).notSolid());
	}

	private static Futon futon() {
		return new Futon(Block.Properties.create(Material.WOOL).hardnessAndResistance(1.0F, 1.0F).sound(SoundType.CLOTH).notSolid());
	}

	///* Register *///
	private static Block register(String name, Block block) {
		BLOCKS.register(name, () -> block);
		return block;
	}

}