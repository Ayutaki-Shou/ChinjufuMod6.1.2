package com.ayutaki.chinjufumod.blocks.crop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Chanoki extends Block {

	public static final String ID = "block_wood_chanoki_4";

	/* Property */
	public static final PropertyInteger STAGE_0_4 = PropertyInteger.create("stage", 0, 4);

	/*木完成*/
	public Chanoki() {
		super(Material.WOOD);
		setRegistryName(new ResourceLocation(ChinjufuMod.MOD_ID, ID));
		setUnlocalizedName(ID);

		setSoundType(SoundType.WOOD);
		setHardness(1.0F);
		setResistance(5.0F);
		/** ハーフ・机=2, 障子・椅子=1, ガラス戸・窓=0, web=1, ice=3 **/
		setLightOpacity(1);
	}

	/* 成長 */
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {

		int i = ((Integer)state.getValue(STAGE_0_4)).intValue();

		if (worldIn.getBlockState(pos.down()).getBlock() == Blocks.DIRT && worldIn
				.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(8) == 0) {

			if (i != 4) { worldIn.setBlockState(pos, state.withProperty(STAGE_0_4, Integer.valueOf(i + 1))); }

			if (i == 4) { }
		}
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		ItemStack itemstack = playerIn.getHeldItem(hand);
		Item item = itemstack.getItem();
		int k;
		k = itemstack.getMetadata();

		int i = ((Integer)state.getValue(STAGE_0_4)).intValue();

		/** Too early to collect **/
		if (i != 4) {
			if (item == Items.DYE && k == 15) {
				CMEvents.Consume_1Item(playerIn, hand);
				
				for(int n = 0; n < 15; ++n) {
					double d0 = worldIn.rand.nextGaussian() * 0.02D;
					double d1 = worldIn.rand.nextGaussian() * 0.02D;
					double d2 = worldIn.rand.nextGaussian() * 0.02D;
					worldIn.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, pos.getX() + worldIn.rand.nextFloat(), pos.getY() +worldIn.rand.nextFloat(), pos.getZ() + worldIn.rand.nextFloat(), d0, d1, d2); }
				
				if (i < 3) { worldIn.setBlockState(pos, state.withProperty(STAGE_0_4, Integer.valueOf(i + 2))); }
				if (i == 3) { worldIn.setBlockState(pos, state.withProperty(STAGE_0_4, Integer.valueOf(4))); }
			}
			
			if ((item == Items.DYE && k != 15) || item != Items.DYE) {
				if (!itemstack.isEmpty()) { CMEvents.textNotHave(worldIn, pos, playerIn); }
				
				if (itemstack.isEmpty()) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
			}
		}

		/** Can harvest **/
		if (i == 4) {
			if (itemstack.isEmpty()) {
				CMEvents.soundTake_Pick(worldIn, pos);
				playerIn.inventory.addItemStackToInventory(new ItemStack(Items_Teatime.CHABA, 4, 0));
				
				worldIn.setBlockState(pos, state.withProperty(STAGE_0_4, Integer.valueOf(i - 1))); }
		
			if (!itemstack.isEmpty()) { CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		/** 'true' to not put anything on top. **/
		return true;
	}


	/* 土指定 */
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block neighborBlock, BlockPos fromPos) {

		if (worldIn.getBlockState(pos.down()).getBlock() != Blocks.DIRT) {
			this.dropBlockAsItem(worldIn, pos, state, 0);
			worldIn.setBlockToAir(pos); }

		else { }
	}

	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		int i = ((Integer)state.getValue(STAGE_0_4)).intValue();

		if (i == 1) { return new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 0.875D, 0.875D); }
		if (i == 2) { return new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.9375D, 0.9375D); }
		if (i == 3) { return new AxisAlignedBB(0.03125D, 0.0D, 0.03125D, 0.96875D, 0.96875D, 0.96875D); }
		if (i == 4) { return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D); }
		return new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.8125D, 0.75D, 0.8125D);
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		/** Have no collision. **/
		return NULL_AABB;
	}

	/* Data value */
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(STAGE_0_4, Integer.valueOf(meta));
	}

	public int getMetaFromState(IBlockState state) {
		return ((Integer)state.getValue(STAGE_0_4)).intValue();
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { STAGE_0_4 });
	}

	public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
		return (4 - ((Integer)blockState.getValue(STAGE_0_4)).intValue()) * 2;
	}

	/* 描写指定 */
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	/*Drop Item and Clone Item.*/
	public boolean canSilkHarvest(World worldIn, EntityPlayer playerIn, int x, int y, int z, int metadata) {
		return false;
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();

		int i = ((Integer)state.getValue(STAGE_0_4)).intValue();
		if (i != 4) { stack.add(new ItemStack(Items_Teatime.CHANOKI, 1, 0)); }
		if (i == 4) { stack.add(new ItemStack(Items_Teatime.CHANOKI, 3, 0)); }
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Teatime.CHANOKI);
	}

}
