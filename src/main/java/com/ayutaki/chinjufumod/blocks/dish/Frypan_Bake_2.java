package com.ayutaki.chinjufumod.blocks.dish;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.blocks.base.BaseStage4_Face;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Kitchen_Blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Frypan_Bake_2 extends BaseStage4_Face {

	/**1=牛丼、2=親子丼、3=カツ、4=カツ丼**/
	public static final String ID = "block_food_frypan_b2";

	public Frypan_Bake_2() {
		super(Material.WOOD);
		setRegistryName(new ResourceLocation(ChinjufuMod.MOD_ID, ID));
		setUnlocalizedName(ID);

		/*寸胴・フライパン*/
		setSoundType(SoundType.METAL);
		setHardness(1.0F);
		setResistance(5.0F);
		/** ハーフ・机=2, 障子・椅子=1, ガラス戸・窓=0, web=1, ice=3 **/
		setLightOpacity(0);
	}

	/*湯気のエフェクト*/
	@Override
	public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {

		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		World par1World = worldIn;
		int par2 = x;
		int par3 = y;
		int par4 = z;
		Random par5Random = rand;

		/**1=牛丼、2=親子丼、3=カツ、4=カツ丼**/
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		/* 煙・ジュー音 */
		if (i == 3) {

			if (worldIn.getBlockState(pos.down()).getBlock() == Blocks.LIT_FURNACE || worldIn
					.getBlockState(pos.down()).getBlock() == Kitchen_Blocks.LIT_KITSTOVE || worldIn
					.getBlockState(pos.down()).getBlock() == Kitchen_Blocks.LIT_KITOVEN || worldIn
					.getBlockState(pos.down()).getBlock() == Kitchen_Blocks.LIT_KITOVEN_B) {

				for (int la = 0; la < 1; ++la) {
					double d0 = (double) ((float) par2 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 0.01D;
					double d1 = ((double) ((float) par3 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 0.01D) + 0.5D;
					double d2 = (double) ((float) par4 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 0.01D;
					double d3 = 0.12D;
					double d4 = 0.17D;
					par1World.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - d4 + 0.25, d1 + d3 -0.5, d2, 0.0D, 0.0D, 0.0D);
				}
			}

			if (rand.nextDouble() < 0.1D) {

				if (worldIn.getBlockState(pos.down()).getBlock() == Blocks.LIT_FURNACE || worldIn
						.getBlockState(pos.down()).getBlock() == Kitchen_Blocks.LIT_KITSTOVE || worldIn
						.getBlockState(pos.down()).getBlock() == Kitchen_Blocks.LIT_KITOVEN || worldIn
						.getBlockState(pos.down()).getBlock() == Kitchen_Blocks.LIT_KITOVEN_B) {
					worldIn.playSound(x, y, z, SoundEvents_CM.JUU, SoundCategory.BLOCKS, 0.2F, 1.0F, false);
				}
			}
		}

		/* 湯気・グツグツ音 */
		if (i != 3) {

			if (worldIn.getBlockState(pos.down()).getBlock() == Blocks.LIT_FURNACE || worldIn
					.getBlockState(pos.down()).getBlock() == Kitchen_Blocks.LIT_KITSTOVE || worldIn
					.getBlockState(pos.down()).getBlock() == Kitchen_Blocks.LIT_KITOVEN || worldIn
					.getBlockState(pos.down()).getBlock() == Kitchen_Blocks.LIT_KITOVEN_B) {

				for (int la = 0; la < 1; ++la) {
					double d0 = (double) ((float) par2 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 0.01D;
					double d1 = ((double) ((float) par3 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 0.01D) + 0.5D;
					double d2 = (double) ((float) par4 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 0.01D;
					double d3 = 0.12D;
					double d4 = 0.17D;
					par1World.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, d0 - d4 + 0.25, d1 + d3 -0.5, d2, 0.0D, 0.0D, 0.0D);
				}
			}

			if (rand.nextDouble() < 0.1D) {

				if (worldIn.getBlockState(pos.down()).getBlock() == Blocks.LIT_FURNACE || worldIn
						.getBlockState(pos.down()).getBlock() == Kitchen_Blocks.LIT_KITSTOVE || worldIn
						.getBlockState(pos.down()).getBlock() == Kitchen_Blocks.LIT_KITOVEN || worldIn
						.getBlockState(pos.down()).getBlock() == Kitchen_Blocks.LIT_KITOVEN_B) {
					worldIn.playSound(x, y, z, SoundEvents_CM.GUTSUGUTSU, SoundCategory.BLOCKS, 0.2F, 1.0F, false);
				}
			}
		}
	}

	/*RightClick Action*/
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		ItemStack itemstack = playerIn.getHeldItem(hand);
		Item item = itemstack.getItem();

		/**1=牛丼、2=親子丼、3=カツ、4=カツ丼**/
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (i == 1) {
			if (item == Items_Teatime.DONBURI_MESHI) {
				/** Collect with an Item **/
				CMEvents.Consume_1Item(playerIn, hand);
				CMEvents.soundTake(worldIn, pos);
				/** Get EXP directly. **/
				playerIn.addExperience(1);
				worldIn.playSound(null, pos, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 0.5F, 0.75F);
	
				if (itemstack.isEmpty()) {
					playerIn.inventory.addItemStackToInventory(new ItemStack(Items_Teatime.DONBURI_GYU)); }
				else if (!playerIn.inventory.addItemStackToInventory(new ItemStack(Items_Teatime.DONBURI_GYU))) {
					playerIn.dropItem(new ItemStack(Items_Teatime.DONBURI_GYU), false); }
	
				worldIn.setBlockState(pos, Dish_Blocks.FRYPAN_kara.getDefaultState().withProperty(H_FACING, state.getValue(H_FACING))); }
			
			if (item != Items_Teatime.DONBURI_MESHI) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		
		if (i == 2) {
			if (item == Items_Teatime.DONBURI_MESHI) {
				/** Collect with an Item **/
				CMEvents.Consume_1Item(playerIn, hand);
				CMEvents.soundTake(worldIn, pos);
				/** Get EXP directly. **/
				playerIn.addExperience(1);
				worldIn.playSound(null, pos, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 0.5F, 0.75F);
	
				if (itemstack.isEmpty()) {
					playerIn.inventory.addItemStackToInventory(new ItemStack(Items_Teatime.DONBURI_OYAKO)); }
				else if (!playerIn.inventory.addItemStackToInventory(new ItemStack(Items_Teatime.DONBURI_OYAKO))) {
					playerIn.dropItem(new ItemStack(Items_Teatime.DONBURI_OYAKO), false); }
	
				worldIn.setBlockState(pos, Dish_Blocks.FRYPAN_kara.getDefaultState().withProperty(H_FACING, state.getValue(H_FACING))); }
			
			if (item != Items_Teatime.DONBURI_MESHI) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		
		if (i == 4) {
			if (item == Items_Teatime.DONBURI_MESHI) {
				/** Collect with an Item **/
				CMEvents.Consume_1Item(playerIn, hand);
				CMEvents.soundTake(worldIn, pos);
				/** Get EXP directly. **/
				playerIn.addExperience(1);
				worldIn.playSound(null, pos, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 0.5F, 0.75F);
	
				if (itemstack.isEmpty()) {
					playerIn.inventory.addItemStackToInventory(new ItemStack(Items_Teatime.DONBURI_KATSU)); }
				else if (!playerIn.inventory.addItemStackToInventory(new ItemStack(Items_Teatime.DONBURI_KATSU))) {
					playerIn.dropItem(new ItemStack(Items_Teatime.DONBURI_KATSU), false); }
	
				worldIn.setBlockState(pos, Dish_Blocks.FRYPAN_kara.getDefaultState().withProperty(H_FACING, state.getValue(H_FACING))); }
			
			if (item != Items_Teatime.DONBURI_MESHI) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		
		/** 'true' to not put anything on top. **/
		return true;
	}


	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.125D, 0.75D);
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		/** Have no collision. **/
		return NULL_AABB;
	}

	/* Rendering */
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();

		/**1=牛丼、2=親子丼、3=カツ、4=カツ丼**/
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (i == 1) { stack.add(new ItemStack(Items_Teatime.FRYPAN_BAKE_2, 1, 1)); }
		if (i == 2) { stack.add(new ItemStack(Items_Teatime.FRYPAN_BAKE_2, 1, 2)); }
		if (i == 3) { stack.add(new ItemStack(Items_Teatime.FRYPAN_BAKE_2, 1, 3)); }
		if (i == 4) { stack.add(new ItemStack(Items_Teatime.FRYPAN_BAKE_2, 1, 4)); }
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {

		/**1=牛丼、2=親子丼、3=カツ、4=カツ丼**/
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (i == 2) { return new ItemStack(Items_Teatime.FRYPAN_BAKE_2, 1, 2); }
		if (i == 3) { return new ItemStack(Items_Teatime.FRYPAN_BAKE_2, 1, 3); }
		if (i == 4) { return new ItemStack(Items_Teatime.FRYPAN_BAKE_2, 1, 4); }
		return new ItemStack(Items_Teatime.FRYPAN_BAKE_2, 1, 1);
	}

	/* フェンスとの接続拒否 */
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

}