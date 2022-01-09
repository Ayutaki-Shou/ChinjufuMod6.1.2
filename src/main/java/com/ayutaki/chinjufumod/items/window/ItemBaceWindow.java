package com.ayutaki.chinjufumod.items.window;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuModTabs;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBaceWindow extends Item {

	private final Block block;

	public ItemBaceWindow(Block block) {
		this.block = block;
		setCreativeTab(ChinjufuModTabs.CHINJUFU);
	}

	public boolean placeBlockAt(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side,
			float hitX, float hitY, float hitZ, IBlockState newState) {

		if (!worldIn.setBlockState(pos, newState, 11)) return false;

		IBlockState state = worldIn.getBlockState(pos);
		if (state.getBlock() == this.block) {
			setTileEntityNBT(worldIn, playerIn, pos, stack);
			this.block.onBlockPlacedBy(worldIn, pos, state, playerIn, stack);

			if (playerIn instanceof EntityPlayerMP)
				CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)playerIn, pos, stack);
		}

		return true;
	}

	public static boolean setTileEntityNBT(World worldIn, @Nullable EntityPlayer playerIn, BlockPos pos, ItemStack stackIn) {
		MinecraftServer minecraftserver = worldIn.getMinecraftServer();

		if (minecraftserver == null) {
			return false;
		}

		else {
			NBTTagCompound nbttagcompound = stackIn.getSubCompound("BlockEntityTag");

			if (nbttagcompound != null) {
				TileEntity tileentity = worldIn.getTileEntity(pos);

				if (tileentity != null) {
					if (!worldIn.isRemote && tileentity.onlyOpsCanSetNbt() && (playerIn == null || !playerIn.canUseCommandBlock())) {
						return false;
					}

					NBTTagCompound nbttagcompound1 = tileentity.writeToNBT(new NBTTagCompound());
					NBTTagCompound nbttagcompound2 = nbttagcompound1.copy();
					nbttagcompound1.merge(nbttagcompound);
					nbttagcompound1.setInteger("x", pos.getX());
					nbttagcompound1.setInteger("y", pos.getY());
					nbttagcompound1.setInteger("z", pos.getZ());

					if (!nbttagcompound1.equals(nbttagcompound2)) {
						tileentity.readFromNBT(nbttagcompound1);
						tileentity.markDirty();
						return true;
					}
				}
			}
			return false;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag advanced) {
		int meta = stack.getMetadata();
		tooltip.add(I18n.format("tips.block_window", meta));
	}

}