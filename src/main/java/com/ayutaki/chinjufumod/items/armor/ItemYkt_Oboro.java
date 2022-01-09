package com.ayutaki.chinjufumod.items.armor;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.items.armor.model.ModelYukataBody;
import com.ayutaki.chinjufumod.registry.Items_ArmorMaterial;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemYkt_Oboro extends ItemArmor {

	public ItemYkt_Oboro(int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(Items_ArmorMaterial.OBOROYKT, renderIndexIn, equipmentSlotIn);
		setCreativeTab(ChinjufuModTabs.SEASONAL);
	}

	/* アーマーモデル */
	@Override
	@SideOnly(Side.CLIENT) /* ←サーバーで処理されないように、Side.CLIENTを入れる */
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack,
			EntityEquipmentSlot armorSlot, ModelBiped _default) {

		if (!itemStack.isEmpty()) {

			if (itemStack.getItem() instanceof ItemArmor) {

				ModelYukataBody armorModel = new ModelYukataBody(1.0f);
				ModelYukataBody armorModelLegs = new ModelYukataBody(0.5f);

				armorModel.bipedHead.showModel = armorSlot == EntityEquipmentSlot.HEAD;
				armorModel.bipedHeadwear.showModel = armorSlot == EntityEquipmentSlot.HEAD;
				armorModel.bipedBody.showModel = (armorSlot == EntityEquipmentSlot.CHEST) || (armorSlot == EntityEquipmentSlot.CHEST);
				armorModel.bipedRightArm.showModel = armorSlot == EntityEquipmentSlot.CHEST;
				armorModel.bipedLeftArm.showModel = armorSlot == EntityEquipmentSlot.CHEST;
				armorModelLegs.bipedRightLeg.showModel = (armorSlot == EntityEquipmentSlot.LEGS) || (armorSlot == EntityEquipmentSlot.FEET);
				armorModelLegs.bipedLeftLeg.showModel = (armorSlot == EntityEquipmentSlot.LEGS) || (armorSlot == EntityEquipmentSlot.FEET);

				armorModel.isSneak = entityLiving.isSneaking();
				armorModel.isRiding = entityLiving.isRiding();
				armorModel.isChild = entityLiving.isChild();

				armorModelLegs.isSneak = entityLiving.isSneaking();
				armorModelLegs.isRiding = entityLiving.isRiding();
				armorModelLegs.isChild = entityLiving.isChild();

				return armorModel;
			}
		}
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag advanced) {
		int meta = stack.getMetadata();

		if (this == Items_Seasonal.OBOROYKT_HELMET) { }

		if (this == Items_Seasonal.OBOROYKT_CHESTPLATE) {
			tooltip.add(I18n.format("tips.item_ykt", meta));
		}

		if (this == Items_Seasonal.OBOROYKT_LEGGINGS) {
			tooltip.add(I18n.format("tips.item_ykt", meta));
		}
	}

	/* Items needed for repair. */
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {

		if (this == Items_Seasonal.OBOROYKT_HELMET) {
			return (repair.getItem() == Item.getItemFromBlock(Blocks.YELLOW_FLOWER));
		}
		return (repair.getItem() == Items_Seasonal.TANMONO);
	}

}
