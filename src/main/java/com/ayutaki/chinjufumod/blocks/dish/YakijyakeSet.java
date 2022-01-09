package com.ayutaki.chinjufumod.blocks.dish;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.blocks.base.CollisionHelper;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class YakijyakeSet extends BaseStage4_FaceDown {

	public static final String ID = "block_food_yakijyaketei_1";

	private static final AxisAlignedBB AABB_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.1875, 0.0, 0.125, 0.6875, 0.1875, 0.875);
	private static final AxisAlignedBB AABB_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.1875, 0.0, 0.125, 0.6875, 0.1875, 0.875);
	private static final AxisAlignedBB AABB_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.1875, 0.0, 0.125, 0.6875, 0.1875, 0.875);
	private static final AxisAlignedBB AABB_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.1875, 0.0, 0.125, 0.6875, 0.1875, 0.875);

	private static final AxisAlignedBB AABB_DOWN_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.1875, -0.5, 0.125, 0.6875, 0.01, 0.875);
	private static final AxisAlignedBB AABB_DOWN_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.1875, -0.5, 0.125, 0.6875, 0.01, 0.875);
	private static final AxisAlignedBB AABB_DOWN_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.1875, -0.5, 0.125, 0.6875, 0.01, 0.875);
	private static final AxisAlignedBB AABB_DOWN_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.1875, -0.5, 0.125, 0.6875, 0.01, 0.875);

	public YakijyakeSet() {
		super(Material.WOOD);
		setRegistryName(new ResourceLocation(ChinjufuMod.MOD_ID, ID));
		setUnlocalizedName(ID);

		/*鍋・皿*/
		setSoundType(SoundType.STONE);
		setHardness(1.0F);
		setResistance(5.0F);
		/** ハーフ・机=2, 障子・椅子=1, ガラス戸・窓=0, web=1, ice=3 **/
		setLightOpacity(0);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		ItemStack itemstack = playerIn.getHeldItem(hand);
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		/** 素手の時に食べる **/
		if (itemstack.isEmpty()) {
			CMEvents.soundEat(worldIn, pos);

			if (i == 1) {
				/** 採掘6000/20=300秒 1秒＝20 満腹は2で肉メモリの1個分 レシピ変更分加算+200 **/
				((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.HASTE, 6200, 0));
				((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.SATURATION, 4, 0));
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i + 1)), 3); }

			if (i == 2) {
				/** 即時回復は0,0でよい 満腹は2で肉メモリの1個分 **/
				((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 0, 0));
				((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.SATURATION, 4, 0));
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i + 1)), 3); }

			if (i == 3) {
				/** リジェネは3600 即時回復は0,0でよい 満腹は2で肉メモリの1個分 レシピ変更分加算+200 **/
				((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 0, 0));
				((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.SATURATION, 4, 0));
				((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 3800, 0));
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i + 1)), 3); }

			if (i == 4) {
				/** 耐性は3600 即時回復は0,0でよい 満腹は2で肉メモリの1個分 レシピ変更分加算+200 **/
				((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 0, 0));
				((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.SATURATION, 4, 0));
				((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 3800, 1));
				worldIn.setBlockState(pos, Dish_Blocks.FOODKARA_TEI.getDefaultState()
						.withProperty(H_FACING, state.getValue(H_FACING))
						.withProperty(BaseStage4_FaceDown.STAGE_1_4, Integer.valueOf(3))); }
		}
		
		if (!itemstack.isEmpty()) { CMEvents.textFullItem(worldIn, pos, playerIn); }
		
		/** 'true' to not put anything on top. **/
		return true;
	}

	/* Collision*/
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {

		state = state.getActualState(source, pos);
		EnumFacing direction = (EnumFacing)state.getValue(H_FACING);
		boolean flag= !((Boolean)state.getValue(DOWN)).booleanValue();

		switch (direction) {
		case EAST:
		default:
			/** !down= true : false **/
			return flag? AABB_EAST : AABB_DOWN_EAST;

		case SOUTH:
			return flag? AABB_SOUTH : AABB_DOWN_SOUTH;

		case WEST:
			return flag? AABB_WEST : AABB_DOWN_WEST;

		case NORTH:
			return flag? AABB_NORTH : AABB_DOWN_NORTH;
		}
	}

	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();

		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (i == 1) { stack.add(new ItemStack(Items_Teatime.YAKIJYAKETEI, 1, 0)); }
		if (i != 1) {
			stack.add(new ItemStack(Items_Teatime.Item_DISH, 1, 3));
			stack.add(new ItemStack(Items_Teatime.Item_DISH, 1, 4));
			stack.add(new ItemStack(Items_Teatime.Item_SARA, 2, 0));
			stack.add(new ItemStack(Items_Teatime.Item_DISH, 1, 1)); }
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Teatime.YAKIJYAKETEI, 1, 0);
	}

}
