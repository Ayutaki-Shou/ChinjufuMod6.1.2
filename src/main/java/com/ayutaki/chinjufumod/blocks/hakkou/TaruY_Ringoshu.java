package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TaruY_Ringoshu extends BlockLog {

	public static final String ID = "block_taru_ringoshu_f";

	/* Property */
	public static final PropertyInteger STAGE_0_3 = PropertyInteger.create("stage", 0, 3);

	/** 0=未発酵、1=シードル、2=熟成シードル、3=空樽 **/
	public TaruY_Ringoshu() {
		super();
		setRegistryName(new ResourceLocation(ChinjufuMod.MOD_ID, ID));
		setUnlocalizedName(ID);

		setCreativeTab(ChinjufuModTabs.TEATIME);

		setSoundType(SoundType.WOOD);
		setHardness(1.0F);
		setResistance(5.0F);
		/** ハーフ・椅子・机=2, 障子=1, ガラス戸・窓=0, web=1, ice=3 **/
		setLightOpacity(2);

		setDefaultState(this.blockState.getBaseState()
				.withProperty(STAGE_0_3, Integer.valueOf(0))
				.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
	}

	/* TickRandom */
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {

		int i = ((Integer)state.getValue(STAGE_0_3)).intValue();
		if (i == 2 || i == 3) { }

		if (i == 0) {
			worldIn.setBlockState(pos, state.withProperty(TaruY_Ringoshu.STAGE_0_3, Integer.valueOf(1))); }

		if (i == 1) {
			worldIn.setBlockState(pos, state.withProperty(TaruY_Ringoshu.STAGE_0_3, Integer.valueOf(2))); }

		worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
	}

	@Override
	public int tickRate(World worldIn) {
		/** 1000tick = Minecraft内 1h = リアル時間 50秒 **/
		return 12000;
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side,
			float hitX, float hitY, float hitZ) {

		int i = ((Integer)state.getValue(STAGE_0_3)).intValue();

		ItemStack itemstack = playerIn.getHeldItem(hand);
		Item item = itemstack.getItem();
		int k;
		k = itemstack.getMetadata();
		/** 0=未発酵、1=シードル、2=熟成シードル、3=空樽 **/
		
		/** Too early to collect **/
		if (i == 0) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		
		if (i == 1) {
			if (item == Items_Teatime.Item_DISH && k == 8) {
				/** Collect with an Item **/
				CMEvents.Consume_1Item(playerIn, hand);
				CMEvents.soundSakeBottleFill(worldIn, pos);
				/** Get EXP directly. **/
				playerIn.addExperience(1);
				worldIn.playSound(null, pos, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 0.5F, 0.75F);
	
				if (itemstack.isEmpty()) {
					playerIn.inventory.addItemStackToInventory(new ItemStack(Items_Teatime.CIDERBOT)); }
				else if (!playerIn.inventory.addItemStackToInventory(new ItemStack(Items_Teatime.CIDERBOT))) {
					playerIn.dropItem(new ItemStack(Items_Teatime.CIDERBOT), false); }
	
				worldIn.setBlockState(pos, this.getDefaultState()
						.withProperty(LOG_AXIS, state.getValue(LOG_AXIS))
						.withProperty(TaruY_Ringoshu.STAGE_0_3, Integer.valueOf(3))); }
			
			if (item != Items_Teatime.Item_DISH || k != 8) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}

		if (i == 2) {
			if (i == 2 && item == Items_Teatime.Item_DISH && k == 8) {
				/** Collect with an Item **/
				CMEvents.Consume_1Item(playerIn, hand);
				CMEvents.soundSakeBottleFill(worldIn, pos);
				/** Get EXP directly. **/
				playerIn.addExperience(1);
				worldIn.playSound(null, pos, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 0.5F, 0.75F);
	
				if (itemstack.isEmpty()) {
					playerIn.inventory.addItemStackToInventory(new ItemStack(Items_Teatime.JUKUCIDERBOT)); }
				else if (!playerIn.inventory.addItemStackToInventory(new ItemStack(Items_Teatime.JUKUCIDERBOT))) {
					playerIn.dropItem(new ItemStack(Items_Teatime.JUKUCIDERBOT), false); }
	
				worldIn.setBlockState(pos, this.getDefaultState()
						.withProperty(LOG_AXIS, state.getValue(LOG_AXIS))
						.withProperty(TaruY_Ringoshu.STAGE_0_3, Integer.valueOf(3))); }
			
			if (item != Items_Teatime.Item_DISH || k != 8) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		
		if (i == 3) { CMEvents.textIsEmpty(worldIn, pos, playerIn); }
		
		/** 'true' to not put anything on top. **/
		return true;
	}


	/* Data value */
	public IBlockState getStateFromMeta(int meta) {
		IBlockState iblockstate = this.getDefaultState();

		switch (meta) {
			case 0:
				iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y).withProperty(STAGE_0_3, 0);
				break;
			case 1:
				iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.X).withProperty(STAGE_0_3, 0);
				break;
			case 2:
				iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z).withProperty(STAGE_0_3, 0);
				break;
			case 3:
				iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y).withProperty(STAGE_0_3, 1);
				break;
			case 4:
				iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.X).withProperty(STAGE_0_3, 1);
				break;
			case 5:
				iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z).withProperty(STAGE_0_3, 1);
				break;
			case 6:
				iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y).withProperty(STAGE_0_3, 2);
				break;
			case 7:
				iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.X).withProperty(STAGE_0_3, 2);
				break;
			case 8:
				iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z).withProperty(STAGE_0_3, 2);
				break;
			case 9:
				iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y).withProperty(STAGE_0_3, 3);
				break;
			case 10:
				iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.X).withProperty(STAGE_0_3, 3);
				break;
			case 11:
				iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z).withProperty(STAGE_0_3, 3);
				break;
			default:
				iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE).withProperty(STAGE_0_3, 0);
		}
		return iblockstate;
	}

	@SuppressWarnings("incomplete-switch")
	public int getMetaFromState(IBlockState state) {
		int i = 0;

		switch ((BlockLog.EnumAxis)state.getValue(LOG_AXIS)) {
			case X:
				i |= 0;
				break;
			case Z:
				i |= 1;
				break;
			case Y:
				i |= 2;
		}

		int j = state.getValue(STAGE_0_3);
		i += j*3;
		return i;
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { LOG_AXIS, STAGE_0_3 });
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

	/* Drop Item and Clone Item. */
	public boolean canSilkHarvest(World worldIn, EntityPlayer playerIn, int x, int y, int z, int metadata) {
		return false;
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();

		/** 0=未発酵、1=シードル、2=熟成シードル、3=空樽 **/
		int i = ((Integer)state.getValue(STAGE_0_3)).intValue();

		if (i == 0) { stack.add(new ItemStack(Items_Teatime.RINGOSHU_TARU, 1, 0)); }

		if (i == 1 || i == 2) {
			stack.add(new ItemStack(Items.APPLE, 7, 0));
			stack.add(new ItemStack(Items_Teatime.HAKKOUTARU, 1, 0)); }

		if (i == 3) { stack.add(new ItemStack(Items_Teatime.HAKKOUTARU, 1, 0)); }
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		/** 0=未発酵、1=シードル、2=熟成シードル、3=空樽 **/
		int i = ((Integer)state.getValue(STAGE_0_3)).intValue();

		if (i == 3) { return new ItemStack(Items_Teatime.HAKKOUTARU, 1, 0); }
		return new ItemStack(Items_Teatime.RINGOSHU_TARU, 1, 0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag advanced) {
		int meta = stack.getMetadata();
		tooltip.add(I18n.format("tips.block_taru_ringoshu", meta));
	}

}
