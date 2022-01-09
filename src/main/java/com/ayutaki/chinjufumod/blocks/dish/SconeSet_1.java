package com.ayutaki.chinjufumod.blocks.dish;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.blocks.base.BaseStage4_Face;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class SconeSet_1 extends BaseStage4_Face {

	public static final String ID = "block_food_sconeset_1";

	public SconeSet_1() {
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

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		ItemStack itemstack = playerIn.getHeldItem(hand);
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		/** Hand is Empty. **/
		if (itemstack.isEmpty()) {
			CMEvents.soundItemPick(worldIn, pos);

			if (i == 1) {
				playerIn.inventory.addItemStackToInventory(new ItemStack(Items_Teatime.EGGSAND, 1));
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i + 1)), 3); }

			if (i == 2) {
				playerIn.inventory.addItemStackToInventory(new ItemStack(Items_Teatime.CHICKENSAND, 1));
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i + 1)), 3); }

			if (i == 3) {
				playerIn.inventory.addItemStackToInventory(new ItemStack(Items_Teatime.EGGSAND, 1));
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i + 1)), 3); }

			if (i == 4) {
				playerIn.inventory.addItemStackToInventory(new ItemStack(Items_Teatime.CHICKENSAND, 1));
				worldIn.setBlockState(pos, Dish_Blocks.SCONESET_a.getDefaultState().withProperty(H_FACING, state.getValue(H_FACING))); }
		}
		
		if (!itemstack.isEmpty()) { CMEvents.textFullItem(worldIn, pos, playerIn); }
		
		/** 'true' to not put anything on top. **/
		return true;
	}

	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.8125D, 0.65625D, 0.8125D);
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		/** Have no collision. **/
		return NULL_AABB;
	}

	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();

		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (i == 1) {
			stack.add(new ItemStack(Items_Teatime.SCONESET_1, 1, 0));
		}

		if (i == 2) {
			stack.add(new ItemStack(Items_Teatime.SCONESET_kara, 1, 0));
			stack.add(new ItemStack(Items_Teatime.CAKE, 2, 0));
			stack.add(new ItemStack(Items_Teatime.SCONE, 2, 0));
			stack.add(new ItemStack(Items_Teatime.CHICKENSAND, 2, 0));
			stack.add(new ItemStack(Items_Teatime.EGGSAND, 1, 0));
		}

		if (i == 3) {
			stack.add(new ItemStack(Items_Teatime.SCONESET_kara, 1, 0));
			stack.add(new ItemStack(Items_Teatime.CAKE, 2, 0));
			stack.add(new ItemStack(Items_Teatime.SCONE, 2, 0));
			stack.add(new ItemStack(Items_Teatime.CHICKENSAND, 1, 0));
			stack.add(new ItemStack(Items_Teatime.EGGSAND, 1, 0));
		}

		if (i == 4) {
			stack.add(new ItemStack(Items_Teatime.SCONESET_kara, 1, 0));
			stack.add(new ItemStack(Items_Teatime.CAKE, 2, 0));
			stack.add(new ItemStack(Items_Teatime.SCONE, 2, 0));
			stack.add(new ItemStack(Items_Teatime.CHICKENSAND, 1, 0));
		}
			return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Teatime.SCONESET_1, 1, 0);
	}

	/* フェンスとの接続拒否 */
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

}
