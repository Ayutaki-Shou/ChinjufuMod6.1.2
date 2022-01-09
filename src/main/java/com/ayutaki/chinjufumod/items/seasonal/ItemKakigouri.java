package com.ayutaki.chinjufumod.items.seasonal;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.blocks.dish.BaseStage4_FaceDown;
import com.ayutaki.chinjufumod.blocks.dish.Kakigouri;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemKakigouri extends ItemFood {

	public ItemKakigouri(int amount, float saturation, boolean isWolfFood) {
		super(amount, saturation, isWolfFood);
		setCreativeTab(ChinjufuModTabs.SEASONAL);
		setUnlocalizedName(Kakigouri.ID);
		setRegistryName(new ResourceLocation(ChinjufuMod.MOD_ID, Kakigouri.ID));

		setAlwaysEdible();
	}

	/* 食べた時の処理 */
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {

		EntityPlayer playerIn = (EntityPlayer)entityLiving;
		playerIn.getFoodStats().addStats(this, stack);

		if (entityLiving instanceof EntityPlayer) {
			worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
			this.onFoodEaten(stack, worldIn, playerIn);
			playerIn.addStat(StatList.getObjectUseStats(this));

			if (playerIn instanceof EntityPlayerMP) {
				CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP)playerIn, stack);
			}
		}

		/** アイテムを返す **/
		if (playerIn == null || !playerIn.capabilities.isCreativeMode) {
			if (stack.isEmpty()) { return new ItemStack(Items_Teatime.Item_DISH, 1, 7); }
			else if (!playerIn.inventory.addItemStackToInventory(new ItemStack(Items_Teatime.Item_DISH, 1, 7))) {
				playerIn.dropItem(new ItemStack(Items_Teatime.Item_DISH, 1, 7), false); }

			stack.shrink(1);
		}
		return stack;
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		/** ポーションエフェクトの追加 一口100 通常 120 **/
		playerIn.addPotionEffect(new PotionEffect(MobEffects.SPEED, 1250, 0));
	}

	/* Place block */
	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing,
			float hitX, float hitY, float hitZ) {

		IBlockState iblockstate = worldIn.getBlockState(pos);
		Block block = iblockstate.getBlock();

		if (!block.isReplaceable(worldIn, pos)) { pos = pos.offset(facing); }

			/* 4.0F / 360.0F) + 0.5D を 4.0F / 360.0F) + 2.5D に変えて180度回転 */
			int i = MathHelper.floor((double)(playerIn.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
			EnumFacing direction = EnumFacing.getHorizontal(i);
			ItemStack itemstack = playerIn.getHeldItem(hand);

			if (!itemstack.isEmpty() && facing == EnumFacing.UP && playerIn.canPlayerEdit(pos, facing, itemstack) && worldIn
					.mayPlace(Dish_Blocks.KAKIGOURI_block, pos, false, facing, (Entity)null) && (playerIn.isSneaking() || playerIn.isRiding())) {

				/** 設置するブロックのクラス **/
				IBlockState iblockstate1 = Dish_Blocks.KAKIGOURI_block.getDefaultState()
						.withProperty(BaseStage4_FaceDown.H_FACING, direction)
						.withProperty(BaseStage4_FaceDown.STAGE_1_4, Integer.valueOf(1));
				worldIn.setBlockState(pos, iblockstate1, 10);

				SoundType soundtype = iblockstate1.getBlock().getSoundType(iblockstate1, worldIn, pos, playerIn);
				worldIn.playSound((EntityPlayer)null, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);

				itemstack.shrink(1);
				return EnumActionResult.SUCCESS;
			}

		else {
			return EnumActionResult.FAIL;
		}
	}

	/* ToolTip */
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag advanced) {
		int meta = stack.getMetadata();
		tooltip.add(I18n.format("tips.block_kakigouri", meta));
		tooltip.add(I18n.format("tips.block_simpledish", meta));
	}

}
