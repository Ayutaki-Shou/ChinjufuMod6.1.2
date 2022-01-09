package com.ayutaki.chinjufumod.blocks.crop;

import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.blocks.base.BaseFacingSapo;
import com.ayutaki.chinjufumod.blocks.base.CollisionHelper;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class SeedsBox extends BaseFacingSapo {

	public static final String ID = "block_seedsbox";

	private static final AxisAlignedBB AABB_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.375, 0.0, 0.25, 0.625, 0.5, 0.75);
	private static final AxisAlignedBB AABB_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.375, 0.0, 0.25, 0.625, 0.5, 0.75);
	private static final AxisAlignedBB AABB_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.375, 0.0, 0.25, 0.625, 0.5, 0.75);
	private static final AxisAlignedBB AABB_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.375, 0.0, 0.25, 0.625, 0.5, 0.75);
	private static final AxisAlignedBB[] AABB = { AABB_SOUTH, AABB_WEST, AABB_NORTH, AABB_EAST };

	public SeedsBox() {
		super(Material.GRASS);
		setRegistryName(new ResourceLocation(ChinjufuMod.MOD_ID, ID));
		setUnlocalizedName(ID);

		setSoundType(SoundType.PLANT);
		setHardness(0.5F);
		setResistance(1.0F);
		/** ハーフ・机=2, 障子・椅子=1, ガラス戸・窓=0, web=1, ice=3 **/
		setLightOpacity(1);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing facing = state.getValue(H_FACING);
		return AABB[facing.getHorizontalIndex()];
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		/** Have no collision. **/
		return NULL_AABB;
	}

	protected int getSaplingDropChance(IBlockState state) {
		return 10;
	}

	@Override
	public java.util.List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {

		java.util.List<ItemStack> stack = new java.util.ArrayList<ItemStack>();
		Random rand = worldIn instanceof World ? ((World)worldIn).rand : new Random();
		int chance = this.getSaplingDropChance(state);

		if (fortune > 0) {
			chance -= 11 << fortune;
			if (chance < 11) chance = 11;
		}

		if (rand.nextInt(chance) == 0)
			stack.add(new ItemStack(Items_Teatime.SEEDS_CABBAGE, 1, damageDropped(state)));

		if (rand.nextInt(chance) == 0)
			stack.add(new ItemStack(Items_Teatime.SEEDS_SOY, 1, damageDropped(state)));

		if (rand.nextInt(chance) == 0)
			stack.add(new ItemStack(Items_Teatime.SEEDS_SPINACH, 1, damageDropped(state)));

		if (rand.nextInt(chance) == 0)
			stack.add(new ItemStack(Items_Teatime.SEEDS_TOMATO, 1, damageDropped(state)));

		if (rand.nextInt(chance) == 0)
			stack.add(new ItemStack(Items_Teatime.SEEDS_CORN, 1, damageDropped(state)));

		if (rand.nextInt(chance) == 0)
			stack.add(new ItemStack(Items_Teatime.SEEDS_ONION, 1, damageDropped(state)));

		if (rand.nextInt(chance) == 0)
			stack.add(new ItemStack(Items_Teatime.SEEDS_RICE, 1, damageDropped(state)));

		if (rand.nextInt(chance) == 0)
			stack.add(new ItemStack(Items_Teatime.SEEDS_HAKUSAI, 1, damageDropped(state)));

		if (rand.nextInt(chance) == 0)
			stack.add(new ItemStack(Items_Teatime.CHANOKI, 1, damageDropped(state)));

		if (rand.nextInt(chance) == 0)
			stack.add(new ItemStack(Items_Teatime.BUDOUNOKI, 1, damageDropped(state)));

		if (rand.nextInt(chance) == 0)
			stack.add(new ItemStack(Items_Teatime.MIKAN, 1, damageDropped(state)));

		return stack;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	/* フェンスとの接続拒否 */
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

}
