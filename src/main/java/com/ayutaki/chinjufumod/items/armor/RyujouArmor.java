package com.ayutaki.chinjufumod.items.armor;

import com.ayutaki.chinjufumod.items.armor.model.RJModel;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class RyujouArmor extends ArmorItem {

	public RyujouArmor(IArmorMaterial material, EquipmentSlotType slot, Properties properties) {
		super(material, slot, properties);
	}

	@SuppressWarnings("unchecked")
	@OnlyIn(Dist.CLIENT)
	@Override
	public <Armor extends BipedModel<?>> Armor getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, Armor _default) {

		RJModel armorModel = new RJModel(1.0F);
		RJModel armorModelLegs = new RJModel(0.5F);

		if (itemStack != null) {

			if (armorModel != null) {
				armorModel.bipedHead.showModel = armorSlot == EquipmentSlotType.HEAD;
				armorModel.bipedHeadwear.showModel = armorSlot == EquipmentSlotType.HEAD;
				armorModel.bipedBody.showModel = (armorSlot == EquipmentSlotType.CHEST) || (armorSlot == EquipmentSlotType.CHEST);
				armorModel.bipedRightArm.showModel = armorSlot == EquipmentSlotType.CHEST;
				armorModel.bipedLeftArm.showModel = armorSlot == EquipmentSlotType.CHEST;
				armorModelLegs.bipedRightLeg.showModel = (armorSlot == EquipmentSlotType.LEGS) || (armorSlot == EquipmentSlotType.FEET);
				armorModelLegs.bipedLeftLeg.showModel = (armorSlot == EquipmentSlotType.LEGS) || (armorSlot == EquipmentSlotType.FEET);

				armorModel.isSneak = entityLiving.isSneaking();
				armorModel.isChild = entityLiving.isChild();

				armorModelLegs.isSneak = entityLiving.isSneaking();
				armorModelLegs.isChild = entityLiving.isChild();
			}
			return (Armor)armorModel;
		}
		return null;
	}

}
