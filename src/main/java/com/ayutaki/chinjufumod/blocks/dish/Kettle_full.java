package com.ayutaki.chinjufumod.blocks.dish;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.blocks.base.BaseStage2_Face;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Furniture_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Kitchen_Blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
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

public class Kettle_full extends BaseStage2_Face {

	/** 1=水、2=沸騰 **/
	public static final String ID = "block_kettle_full";

	public Kettle_full() {
		super(Material.WOOD);
		setRegistryName(new ResourceLocation(ChinjufuMod.MOD_ID, ID));
		setUnlocalizedName(ID);

		setSoundType(SoundType.METAL);
		setHardness(1.0F);
		setResistance(5.0F);
		/** ハーフ・机=2, 障子・椅子=1, ガラス戸・窓=0, web=1, ice=3 **/
		setLightOpacity(1);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		int i = ((Integer)state.getValue(STAGE_1_2)).intValue();
		if (i == 1) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		/** 'true' to not put anything on top. **/
		return true;
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

		/** 1=水、2=沸騰 **/
		int i = ((Integer)state.getValue(STAGE_1_2)).intValue();

		if (i == 2) {
			if (worldIn.getBlockState(pos.down()).getBlock() == Blocks.LIT_FURNACE || worldIn
					.getBlockState(pos.down()).getBlock() == Furniture_Blocks.LIT_CSTOVE_top || worldIn
					.getBlockState(pos.down()).getBlock() == Kitchen_Blocks.LIT_KITSTOVE || worldIn
					.getBlockState(pos.down()).getBlock() == Kitchen_Blocks.LIT_KITOVEN || worldIn
					.getBlockState(pos.down()).getBlock() == Kitchen_Blocks.LIT_KITOVEN_B || worldIn
					.getBlockState(pos.down()).getBlock() == Kitchen_Blocks.LIT_IRORI) {

				for (int la = 0; la < 1; ++la) {
					double d0 = (double) ((float) par2 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 0.01D;
					double d1 = ((double) ((float) par3 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 0.01D) + 0.5D;
					double d2 = (double) ((float) par4 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 0.01D;
					double d3 = 0.12D;
					double d4 = 0.17D;
					par1World.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, d0 - d4 + 0.25, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
				}
			}

			if (rand.nextDouble() < 0.1D) {

				if (worldIn.getBlockState(pos.down()).getBlock() == Blocks.LIT_FURNACE || worldIn
						.getBlockState(pos.down()).getBlock() == Furniture_Blocks.LIT_CSTOVE_top || worldIn
						.getBlockState(pos.down()).getBlock() == Kitchen_Blocks.LIT_KITSTOVE || worldIn
						.getBlockState(pos.down()).getBlock() == Kitchen_Blocks.LIT_KITOVEN || worldIn
						.getBlockState(pos.down()).getBlock() == Kitchen_Blocks.LIT_KITOVEN_B || worldIn
						.getBlockState(pos.down()).getBlock() == Kitchen_Blocks.LIT_IRORI) {

					worldIn.playSound(x, y, z, SoundEvents_CM.GUTSUGUTSU, SoundCategory.BLOCKS, 0.5F, 1.0F, false);
				}
			}
		}

		if (i != 2) {
			if (rand.nextDouble() < 0.1D) {

				if (worldIn.getBlockState(pos.down()).getBlock() == Blocks.LIT_FURNACE || worldIn
						.getBlockState(pos.down()).getBlock() == Furniture_Blocks.LIT_CSTOVE_top || worldIn
						.getBlockState(pos.down()).getBlock() == Kitchen_Blocks.LIT_KITSTOVE || worldIn
						.getBlockState(pos.down()).getBlock() == Kitchen_Blocks.LIT_KITOVEN || worldIn
						.getBlockState(pos.down()).getBlock() == Kitchen_Blocks.LIT_KITOVEN_B || worldIn
						.getBlockState(pos.down()).getBlock() == Kitchen_Blocks.LIT_IRORI) {

					worldIn.playSound(x, y, z, SoundEvents_CM.GUTSUGUTSU, SoundCategory.BLOCKS, 0.5F, 1.0F, false);
				}
			}
		}
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		/** 1=水、2=沸騰 **/
		int i = ((Integer)state.getValue(STAGE_1_2)).intValue();

		if (i == 1) {
			if (worldIn.getBlockState(pos.down()).getBlock() == Blocks.LIT_FURNACE || worldIn
					.getBlockState(pos.down()).getBlock() == Furniture_Blocks.LIT_CSTOVE_top || worldIn
					.getBlockState(pos.down()).getBlock() == Kitchen_Blocks.LIT_KITSTOVE || worldIn
					.getBlockState(pos.down()).getBlock() == Kitchen_Blocks.LIT_KITOVEN || worldIn
					.getBlockState(pos.down()).getBlock() == Kitchen_Blocks.LIT_KITOVEN_B || worldIn
					.getBlockState(pos.down()).getBlock() == Kitchen_Blocks.LIT_IRORI) {

			worldIn.setBlockState(pos, this.getDefaultState()
					.withProperty(H_FACING, state.getValue(H_FACING))
					.withProperty(BaseStage2_Face.STAGE_1_2, Integer.valueOf(2)));
			}
		}

		if (i != 1) { }

		worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
	}

	@Override
	public int tickRate(World worldIn) {
		/** 1000tick = Minecraft内 1h = リアル時間 50秒 **/
		return 1000;
	}

	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.4375D, 0.75D);
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		/** Have no collision. **/
		return NULL_AABB;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();

		int i = ((Integer)state.getValue(STAGE_1_2)).intValue();

		if (i == 1) { stack.add(new ItemStack(Items_Teatime.KETTLE_full, 1, 0)); }
		if (i != 1) { stack.add(new ItemStack(Items_Teatime.Item_YAKAN_boil, 1, 0)); }
		return stack;
	}

	/* 経験値ドロップ */
	@Override
	public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess worldIn, BlockPos pos, int fortune) {
		int i = ((Integer)state.getValue(STAGE_1_2)).intValue();
		if (i != 1) { return 1; }
		return 0;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Teatime.KETTLE_full, 1, 0);
	}

	/* フェンスとの接続拒否 */
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

}
