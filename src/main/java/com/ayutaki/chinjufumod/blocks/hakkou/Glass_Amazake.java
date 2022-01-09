package com.ayutaki.chinjufumod.blocks.hakkou;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class Glass_Amazake extends Base_Glass {

	/* Collision */
	protected static final VoxelShape AABB_BOX = Block.makeCuboidShape(6.8D, 0.0D, 6.8D, 9.2D, 3.2D, 9.2D);
	protected static final VoxelShape AABB_DOWN = Block.makeCuboidShape(6.8D, -8.0D, 6.8D, 9.2D, 0.1D, 9.2D);

	public Glass_Amazake(Block.Properties properties) {
		super(properties);
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {

		ItemStack itemstack = playerIn.getHeldItem(hand);
		int i = state.get(STAGE_0_2);

		if (i != 2) {
			/** Hand is empty. **/
			if (itemstack.isEmpty()) {
				CMEvents.soundDrink(worldIn, pos);
	
				if (i == 0) {
					playerIn.addPotionEffect(new EffectInstance(Effects.HASTE, 1200, 0)); }
				if (i == 1) {
					playerIn.addPotionEffect(new EffectInstance(Effects.HASTE, 1200, 0)); }
	
				worldIn.setBlockState(pos, state.with(Base_Glass.STAGE_0_2, Integer.valueOf(i + 1))); }
			
			if (!itemstack.isEmpty()) { CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		
		if (i == 2) { CMEvents.textIsEmpty(worldIn, pos, playerIn); }
		
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	/* Clone Item in Creative. */
	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		int i = state.get(STAGE_0_2);
		return (i == 0)? new ItemStack(Items_Teatime.AMAZAKEGLASS) : new ItemStack(Items_Teatime.YUNOMI);
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		boolean flag= !((Boolean)state.get(DOWN)).booleanValue();

		/** !down= true : false **/
		return flag? AABB_BOX : AABB_DOWN;
	}

}
