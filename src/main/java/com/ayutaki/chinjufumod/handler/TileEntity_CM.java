package com.ayutaki.chinjufumod.handler;

import java.util.function.Supplier;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.registry.Furniture_Blocks;
import com.ayutaki.chinjufumod.registry.Kitchen_Blocks;
import com.ayutaki.chinjufumod.registry.School_Blocks;
import com.ayutaki.chinjufumod.tileentity.Oven_TileEntity;
import com.ayutaki.chinjufumod.tileentity.Stove_TileEntity;
import com.ayutaki.chinjufumod.tileentity.Tansu_TileEntity;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = ChinjufuMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TileEntity_CM {

	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, ChinjufuMod.MOD_ID);

	public static TileEntityType<Oven_TileEntity> KIT_OVEN = register("kitoven", Oven_TileEntity::new, Kitchen_Blocks.KIT_OVEN, Kitchen_Blocks.KIT_OVEN_B);
	public static TileEntityType<Stove_TileEntity> C_STOVE = register("cstove", Stove_TileEntity::new, School_Blocks.CSTOVE_top);
	public static TileEntityType<Tansu_TileEntity> TANSU = register("tansu", Tansu_TileEntity::new, Furniture_Blocks.TANSU_OAK, Furniture_Blocks.DOAK_TANSU, Furniture_Blocks.SPRUCE_TANSU);

	///* Register *///
	private static <T extends TileEntity> TileEntityType<T> register(String name, Supplier<T> factory, Block... blocks) {
		TileEntityType<T> tileEntityType = TileEntityType.Builder.of(factory, blocks).build(null);

		TILE_ENTITIES.register(name, () -> tileEntityType);
		return tileEntityType;
	}

}
