package com.ayutaki.chinjufumod.items.armor;

import java.util.function.Supplier;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/* minecraft.item.ArmorMaterial.class */
public enum CMArmorMaterial implements IArmorMaterial {

	FUBUKI("fubuki", 17, new int[]{ 2, 6, 7, 2 }, 10, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> {
		return Ingredient.of(Items.IRON_INGOT); }),

	KASUMI("kasumi", 17, new int[]{ 2, 6, 7, 2 }, 10, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> {
		return Ingredient.of(Items.IRON_INGOT); }),

	SHIGURE("shigure", 17, new int[]{ 2, 6, 7, 2 }, 10, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> {
		return Ingredient.of(Items.IRON_INGOT); }),

	SHIRATSUYU("shiratsuyu", 17, new int[]{ 2, 6, 7, 2 }, 10, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> {
		return Ingredient.of(Items.IRON_INGOT); }),

	/* 0.42×45=18.9 */
	YURA("yura", 19, new int[]{ 2, 6, 7, 3 }, 10, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> {
		return Ingredient.of(Items.IRON_INGOT); }),
	/* 0.42×48=20.1 */
	SENDAI("sendai", 20, new int[]{ 2, 6, 7, 2 }, 10, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> {
		return Ingredient.of(Items.IRON_INGOT); }),

	/* 0.42×50=21 */
	RJ("ryujou", 21, new int[]{ 2, 6, 7, 3 }, 10, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> {
		return Ingredient.of(Items.IRON_INGOT); }),
	/* 0.42×50=21 */
	MOGAMI("mogami", 21, new int[]{ 3, 6, 7, 3 }, 10, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> {
		return Ingredient.of(Items.IRON_INGOT); }),
	/* 0.42×59=24.78 */
	TONE("tone", 25, new int[]{ 2, 6, 7, 2 }, 10, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> {
		return Ingredient.of(Items.IRON_INGOT); }),

	/* 0.42×72=30.24 */
	KAGA("kaga", 30, new int[]{ 2, 6, 7, 2 }, 10, SoundEvents.ARMOR_EQUIP_IRON, 1.0F, 0.1F, () -> {
		return Ingredient.of(Items.IRON_INGOT); }),
	/* 0.42×69=28.98 */
	AKAGI("akagi", 29, new int[]{ 2, 6, 7, 3 }, 10, SoundEvents.ARMOR_EQUIP_IRON, 1.0F, 0.1F, () -> {
		return Ingredient.of(Items.IRON_INGOT); }),
	/* 33÷82≒0.4 調整値+0.02 ダイヤモンド相当を前提 */
	KONGOU("kongou", 33, new int[]{ 3, 6, 8, 3 }, 10, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.1F, () -> {
		return Ingredient.of(Items.IRON_INGOT); }),

	AKASHISANTA("santaakashi", 6, new int[]{ 1, 2, 2, 1 }, 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> {
		return Ingredient.of(Items_Chinjufu.EMBLEM_C); }),
	KUMANOSANTA("santakumano", 6, new int[]{ 1, 2, 2, 1 }, 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> {
		return Ingredient.of(Items_Chinjufu.EMBLEM_C); }),
	SUZUYASANTA("santasuzuya", 6, new int[]{ 1, 2, 2, 1 }, 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> {
		return Ingredient.of(Items_Chinjufu.EMBLEM_C); }),
	RYUJOUSANTA("santaryujou", 6, new int[]{ 1, 2, 2, 1 }, 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> {
		return Ingredient.of(Items_Chinjufu.EMBLEM_C); }),
	TEITOKUSANTA("santattk", 6, new int[]{ 1, 2, 2, 1 }, 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> {
		return Ingredient.of(Items_Chinjufu.EMBLEM_C); }),

	IKADUCHIYKT("ykt_ikaduchi", 6, new int[]{ 1, 2, 2, 1 }, 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> {
		return Ingredient.of(Items_Chinjufu.EMBLEM_C); }),
	INADUMAYKT("ykt_inaduma", 6, new int[]{ 1, 2, 2, 1 }, 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> {
		return Ingredient.of(Items_Chinjufu.EMBLEM_C); }),
	HAMAKAZEYKT("ykt_hamakaze", 6, new int[]{ 1, 2, 2, 1 }, 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> {
		return Ingredient.of(Items_Chinjufu.EMBLEM_C); }),
	URAKAZEYKT("ykt_urakaze", 6, new int[]{ 1, 2, 2, 1 }, 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> {
		return Ingredient.of(Items_Chinjufu.EMBLEM_C); }),
	KAWAKAZEYKT("ykt_kawakaze", 6, new int[]{ 1, 2, 2, 1 }, 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> {
		return Ingredient.of(Items_Chinjufu.EMBLEM_C); }),
	OBOROYKT("ykt_oboro", 6, new int[]{ 1, 2, 2, 1 }, 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> {
		return Ingredient.of(Items_Chinjufu.EMBLEM_C); }),
	TTOKUYKT("ykt_ttoku", 6, new int[]{ 1, 2, 2, 1 }, 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> {
		return Ingredient.of(Items_Chinjufu.EMBLEM_C); }),
	TTOKUYKTB("ykt_ttokub", 6, new int[]{ 1, 2, 2, 1 }, 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> {
		return Ingredient.of(Items_Chinjufu.EMBLEM_C); } );

	private static final int[] HEALTH_PER_SLOT = new int[]{ 13, 15, 16, 11 };
	private final String name;
	private final int durabilityMultiplier; //防具の耐久度 革5 鉄15 金7 ダイヤモンド33
	private final int[] slotProtections; //各部の防御ポイント 革{ 1, 2, 3, 1 }、鉄{ 2, 5, 6, 2 }、金{ 1, 3, 5, 2 }、ダイヤモンド{ 3, 6, 8, 3 }
	private final int enchantmentValue; //数値が大きいほど良いエンチャントが付き易い 革15、鉄9、金25、ダイヤモンド10
	private final SoundEvent sound;
	private final float toughness; //ダメージカット率をより上げる デフォルト0 ダイヤモンドのみ2
	private final LazyValue<Ingredient> repairIngredient;
	private final float knockbackResistance;

	private CMArmorMaterial(String name, int maxDamageFactor, int[] damageReduction, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairMaterialSupplier) {
		this.name = ChinjufuMod.MOD_ID + ":" + name;
		this.durabilityMultiplier = maxDamageFactor;
		this.slotProtections = damageReduction;
		this.enchantmentValue = enchantability;
		this.sound = equipSound;
		this.toughness = toughness;
		this.repairIngredient = new LazyValue<>(repairMaterialSupplier);
		this.knockbackResistance = knockbackResistance;
	}

	public int getDurabilityForSlot(EquipmentSlotType slot) {
		return HEALTH_PER_SLOT[slot.getIndex()] * this.durabilityMultiplier;
	}

	public int getDefenseForSlot(EquipmentSlotType slot) {
		return this.slotProtections[slot.getIndex()];
	}

	public int getEnchantmentValue() {
		return this.enchantmentValue;
	}

	public SoundEvent getEquipSound() {
		return this.sound;
	}

	public Ingredient getRepairIngredient() {
		return this.repairIngredient.get();
	}

	@OnlyIn(Dist.CLIENT)
	public String getName() {
		return this.name;
	}

	public float getToughness() {
		return this.toughness;
	}

	public float getKnockbackResistance() {
		return this.knockbackResistance;
	}

}