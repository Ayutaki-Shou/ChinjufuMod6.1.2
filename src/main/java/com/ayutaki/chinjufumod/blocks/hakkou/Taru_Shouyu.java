package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Taru_Shouyu extends Block {

	public static final String ID = "block_taru_hakkou_2";

	/* Property */
	/** 1=未発酵の醤油、2=醤油、 3=醤油、4=醤油、 5=醤油 **/
	/** 6=未発酵の米酢、7=米酢、8=米酢、9=米酢、10=米酢 **/
	/** 11=未乾燥のキノコ、12=乾燥キノコ **/
	/** 13=生海苔、14=板海苔 **/
	public static final PropertyInteger STAGE_1_14 = PropertyInteger.create("stage", 1, 14);

	public Taru_Shouyu() {
		super(Material.WOOD);
		setRegistryName(new ResourceLocation(ChinjufuMod.MOD_ID, ID));
		setUnlocalizedName(ID);

		setSoundType(SoundType.WOOD);
		setHardness(1.0F);
		setResistance(5.0F);
		/** ハーフ・椅子・机=2, 障子=1, ガラス戸・窓=0, web=1, ice=3 **/
		setLightOpacity(2);
	}

	/* TickRandom */
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {

		int i = ((Integer)state.getValue(STAGE_1_14)).intValue();
		if (i == 2 || i == 3 || i == 4 || i == 5 || i == 7 || i == 8 || i == 9 || i == 10 || i == 12 || i == 14) { }

		if (i == 1) { worldIn.setBlockState(pos, this.getDefaultState().withProperty(Taru_Shouyu.STAGE_1_14, Integer.valueOf(2))); }
		if (i == 6) { worldIn.setBlockState(pos, this.getDefaultState().withProperty(Taru_Shouyu.STAGE_1_14, Integer.valueOf(7))); }
		if (i == 11) { worldIn.setBlockState(pos, this.getDefaultState().withProperty(Taru_Shouyu.STAGE_1_14, Integer.valueOf(12))); }
		if (i == 13) { worldIn.setBlockState(pos, this.getDefaultState().withProperty(Taru_Shouyu.STAGE_1_14, Integer.valueOf(14))); }

		worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
	}

	@Override
	public int tickRate(World worldIn) {
		/** 1000tick = Minecraft内 1h = リアル時間 50秒 **/
		return 6000;
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		ItemStack itemstack = playerIn.getHeldItem(hand);
		Item item = itemstack.getItem();

		int i = ((Integer)state.getValue(STAGE_1_14)).intValue();

		/** Too early to collect **/
		if (i == 1 || i == 6 || i == 11 || i == 13) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		
		/** 1=未発酵の醤油、2=醤油、 3=醤油、4=醤油、 5=醤油 **/
		if (i >= 2 && i <= 5) {
			if (item == Items.GLASS_BOTTLE) {
				/** Collect with an Item **/
				CMEvents.Consume_1Item(playerIn, hand);
				CMEvents.soundBottleFill(worldIn, pos);
	
				if (itemstack.isEmpty()) {
					playerIn.inventory.addItemStackToInventory(new ItemStack(Items_Teatime.SHOUYU_bot_1, 1)); }
				else if (!playerIn.inventory.addItemStackToInventory(new ItemStack(Items_Teatime.SHOUYU_bot_1, 1))) {
					playerIn.dropItem(new ItemStack(Items_Teatime.SHOUYU_bot_1, 1), false); }
	
				if (i == 2) {
					/** 経験値取得 **/
					playerIn.addExperience(1);
					worldIn.playSound(null, pos, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 0.5F, 0.75F); }
	
				if (i != 5) { worldIn.setBlockState(pos, state.withProperty(STAGE_1_14, Integer.valueOf(i + 1)), 3); }
				if (i == 5) {
					worldIn.setBlockState(pos, Hakkou_Blocks.HAKKOUTARU.getDefaultState()
							.withProperty(Taru_Hakkou.STAGE_0_15, Integer.valueOf(0))); } }
			
			if (item != Items.GLASS_BOTTLE) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}

		/** 6=未発酵の米酢、7=米酢、8=米酢、9=米酢、10=米酢 **/
		if (i >= 7 && i <= 10) {
			if (item == Items.GLASS_BOTTLE) {
				/** Collect with an Item **/
				CMEvents.Consume_1Item(playerIn, hand);
				CMEvents.soundBottleFill(worldIn, pos);
	
				if (itemstack.isEmpty()) {
					playerIn.inventory.addItemStackToInventory(new ItemStack(Items_Teatime.KOMEZU_bot_1, 1)); }
				else if (!playerIn.inventory.addItemStackToInventory(new ItemStack(Items_Teatime.KOMEZU_bot_1, 1))) {
					playerIn.dropItem(new ItemStack(Items_Teatime.KOMEZU_bot_1, 1), false); }
	
				if (i == 7) {
					/** 経験値取得 **/
					playerIn.addExperience(1);
					worldIn.playSound(null, pos, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 0.5F, 0.75F); }
	
				if (i != 10) { worldIn.setBlockState(pos, state.withProperty(STAGE_1_14, Integer.valueOf(i + 1)), 3); }
				if (i == 10) {
					worldIn.setBlockState(pos, Hakkou_Blocks.HAKKOUTARU.getDefaultState()
							.withProperty(Taru_Hakkou.STAGE_0_15, Integer.valueOf(0))); } }
			
			if (item != Items.GLASS_BOTTLE) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		
		/** 11=未乾燥のキノコ、12=乾燥キノコ **/
		if (i == 12) {
			if (item == Items.GLASS_BOTTLE) {
				/** Collect with an Item **/
				CMEvents.Consume_1Item(playerIn, hand);
				CMEvents.soundTake(worldIn, pos);
	
				if (itemstack.isEmpty()) {
					playerIn.inventory.addItemStackToInventory(new ItemStack(Items_Teatime.DASHI_bot_1, 1)); }
				else if (!playerIn.inventory.addItemStackToInventory(new ItemStack(Items_Teatime.DASHI_bot_1, 1))) {
					playerIn.dropItem(new ItemStack(Items_Teatime.DASHI_bot_1, 1), false); }
	
				worldIn.setBlockState(pos, Hakkou_Blocks.HAKKOUTARU.getDefaultState()
						.withProperty(Taru_Hakkou.STAGE_0_15, Integer.valueOf(14))); }
			
			if (item != Items.GLASS_BOTTLE) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		
		/** 13=生海苔、14=板海苔 **/
		if (i == 14) {
			if (itemstack.isEmpty()) {
				
				CMEvents.soundTake_Pick(worldIn, pos);	
				playerIn.inventory.addItemStackToInventory(new ItemStack(Items_Teatime.NORI_I, 4, 0));
				/** Get EXP directly. **/
				playerIn.addExperience(1);
				worldIn.playSound(null, pos, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 0.5F, 0.75F);
	
				worldIn.setBlockState(pos, Hakkou_Blocks.HAKKOUTARU.getDefaultState()
						.withProperty(Taru_Hakkou.STAGE_0_15, Integer.valueOf(14))); }
			
			if (!itemstack.isEmpty()) { CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		
		/** 'true' to not put anything on top. **/
		return true;
	}


	/* Data value */
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(STAGE_1_14, Integer.valueOf(meta));
	}

	public int getMetaFromState(IBlockState state) {
		return ((Integer)state.getValue(STAGE_1_14)).intValue();
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { STAGE_1_14 });
	}

	public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
		return (14 - ((Integer)blockState.getValue(STAGE_1_14)).intValue()) * 2;
	}

	/* 上面に植木鉢やレッドストーンを置けるようにする */
	public boolean isTopSolid(IBlockState state) {
		return false;
	}

	/* 側面に松明などを置けるようにする */
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	/* Rendering */
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

		int i = ((Integer)state.getValue(STAGE_1_14)).intValue();

		/** 1=未発酵の醤油、2=醤油、 3=醤油、4=醤油、 5=醤油 未発酵はそのまま回収 **/
		/** 6=未発酵の米酢、7=米酢、8=米酢、9=米酢、10=米酢 発酵済みは樽が壊れると回収できない **/
		/** 11=未乾燥のキノコ、12=乾燥キノコ **/
		/** 13=生海苔、14=板海苔 **/
		if (i == 2 || i == 3 || i == 4 || i == 5|| i == 7 || i == 8 || i == 9 || i == 10) {
			stack.add(new ItemStack(Items_Teatime.HAKKOUTARU, 1, 0)); }
		if (i == 1) { stack.add(new ItemStack(Items_Teatime.SHOUYU_TARU, 1, 1)); }
		if (i == 6 ) { stack.add(new ItemStack(Items_Teatime.SHOUYU_TARU, 1, 2)); }

		if (i == 11) { stack.add(new ItemStack(Items_Teatime.SHOUYU_TARU, 1, 3)); }
		if (i == 12) {
			stack.add(new ItemStack(Items_Teatime.HAKKOUTARU, 1, 0));
			stack.add(new ItemStack(Blocks.BROWN_MUSHROOM, 8, 0)); }

		if (i == 13) { stack.add(new ItemStack(Items_Teatime.SHOUYU_TARU, 1, 4)); }
		if (i == 14) {
			stack.add(new ItemStack(Items_Teatime.HAKKOUTARU, 1, 0));
			stack.add(new ItemStack(Items_Teatime.NORI_I, 4, 0)); }

		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {

		int i = ((Integer)state.getValue(STAGE_1_14)).intValue();

		/** 1=未発酵の醤油、2=醤油、 3=醤油、4=醤油、 5=醤油 **/
		/** 6=未発酵の米酢、7=米酢、8=米酢、9=米酢、10=米酢 **/
		/** 11=未乾燥のキノコ、12=乾燥キノコ **/
		/** 13=生海苔、14=板海苔 **/
		if (i >= 1 && i <= 5) { return new ItemStack(Items_Teatime.SHOUYU_TARU, 1, 1); }
		if (i >= 6 && i <= 10) { return new ItemStack(Items_Teatime.SHOUYU_TARU, 1, 2); }
		if (i == 11 || i == 12) { return new ItemStack(Items_Teatime.SHOUYU_TARU, 1, 3); }
		return new ItemStack(Items_Teatime.SHOUYU_TARU, 1, 4);
	}

}