package com.ayutaki.chinjufumod.items.armor.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ArmorStandEntity;

public class RJModel extends BipedModel<LivingEntity> {

	private ModelRenderer GISOU1;
	private ModelRenderer GISOU2;
	private ModelRenderer GISOU5;
	private ModelRenderer GISOU6;
	private ModelRenderer GISOU7;

	public RJModel(float modelSize) {
		super(modelSize, 0.0F, 64, 120);

		this.texWidth = 64;
		this.texHeight = 120;

		/* 電探と煙突 */
		this.GISOU1 = new ModelRenderer(this, 0, 71);
		this.GISOU1.addBox(-11.0F, -10.0F, 4.01F, 22, 28, 1);
		this.GISOU1.setPos(0F, 0F, 0F);

		/* レギンス用の薄いボディ */
		this.GISOU2 = new ModelRenderer(this, 0, 52);
		this.GISOU2.addBox(-4.5F, -0.5F, -2.5F, 9, 14, 5);
		this.GISOU2.setPos(0F, 0F, 0F);

		/* レギンス左足 */
		this.GISOU5= new ModelRenderer(this, 0, 100);
		this.GISOU5.addBox(-2.5F, -1.01F, -2.5F, 5, 8, 5);
		this.GISOU5.setPos(0F, 0F, 0F);

		/* レギンス右足 */
		this.GISOU6= new ModelRenderer(this, 0, 100);
		this.GISOU6.mirror = true;
		this.GISOU6.addBox(-2.5F, -1.01F, -2.5F, 5, 8, 5);
		this.GISOU6.setPos(0F, 0F, 0F);

		/* 龍驤バイザー */
		this.GISOU7= new ModelRenderer(this, 20, 100);
		this.GISOU7.addBox(-5.0F, -10.0F, -8.1F, 10, 5, 3);
		this.GISOU7.setPos( 0F, 0F, 0F);

		/* ボディ(＝チェストプレート)の子として位置づける */
		body.addChild(GISOU1);
		body.addChild(GISOU2);

		leftLeg.addChild(GISOU5);
		rightLeg.addChild(GISOU6);
		head.addChild(GISOU7);

	}

	protected void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}

	/* アーマースタンドでの向き不一致を防止 */
	public void setupAnim(LivingEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

		if (entityIn instanceof ArmorStandEntity) {
			ArmorStandEntity armorstand = (ArmorStandEntity) entityIn;
			this.head.xRot = ((float)Math.PI / 180F) * armorstand.getHeadPose().getX();
			this.head.yRot = ((float)Math.PI / 180F) * armorstand.getHeadPose().getY();
			this.head.zRot = ((float)Math.PI / 180F) * armorstand.getHeadPose().getZ();
			this.head.setPos(0.0F, 1.0F, 0.0F);
			this.body.xRot = ((float)Math.PI / 180F) * armorstand.getBodyPose().getX();
			this.body.yRot = ((float)Math.PI / 180F) * armorstand.getBodyPose().getY();
			this.body.zRot = ((float)Math.PI / 180F) * armorstand.getBodyPose().getZ();
			this.leftArm.xRot = ((float)Math.PI / 180F) * armorstand.getLeftArmPose().getX();
			this.leftArm.yRot = ((float)Math.PI / 180F) * armorstand.getLeftArmPose().getY();
			this.leftArm.zRot = ((float)Math.PI / 180F) * armorstand.getLeftArmPose().getZ();
			this.rightArm.xRot = ((float)Math.PI / 180F) * armorstand.getRightArmPose().getX();
			this.rightArm.yRot = ((float)Math.PI / 180F) * armorstand.getRightArmPose().getY();
			this.rightArm.zRot = ((float)Math.PI / 180F) * armorstand.getRightArmPose().getZ();
			this.leftLeg.xRot = ((float)Math.PI / 180F) * armorstand.getLeftLegPose().getX();
			this.leftLeg.yRot = ((float)Math.PI / 180F) * armorstand.getLeftLegPose().getY();
			this.leftLeg.zRot = ((float)Math.PI / 180F) * armorstand.getLeftLegPose().getZ();
			this.leftLeg.setPos(1.9F, 11.0F, 0.0F);
			this.rightLeg.xRot = ((float)Math.PI / 180F) * armorstand.getRightLegPose().getX();
			this.rightLeg.yRot = ((float)Math.PI / 180F) * armorstand.getRightLegPose().getY();
			this.rightLeg.zRot = ((float)Math.PI / 180F) * armorstand.getRightLegPose().getZ();
			this.rightLeg.setPos(-1.9F, 11.0F, 0.0F);
			this.hat.copyFrom(this.head);
		}

		else super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
	}

}
