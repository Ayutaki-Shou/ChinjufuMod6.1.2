package com.ayutaki.chinjufumod.items.hakkou;

import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ItemGroups_CM;
import com.ayutaki.chinjufumod.blocks.crop.Enden;
import com.ayutaki.chinjufumod.registry.Crop_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CauldronBlock;
import net.minecraft.block.IBucketPickupHandler;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.server.MinecraftServer;
import net.minecraft.state.IProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/* BucketItem に BlockItem を追加して、設置処理で分岐。BlockItem への BucketItem は大釜問題にぶつかる*/
public class Mizuoke_Item extends BucketItem {

	private final Fluid containedBlock;
	private final Block block;

	@SuppressWarnings("deprecation")
	public Mizuoke_Item(Fluid containedFluidIn, Block blockIn, Item.Properties builder) {
		super(containedFluidIn, builder.group(ItemGroups_CM.TEATIME));
		this.block = blockIn;
		this.containedBlock = containedFluidIn;
	}

	/* かまど燃焼時間 */
	@Override
	public int getBurnTime(ItemStack stackIn) {
		Item item = stackIn.getItem();

		if (item == Items_Teatime.MIZUOKE) { return 100; }
		else { return 0; }
	}

	/* 水を入れる BucketItem */
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack stackIn = playerIn.getHeldItem(handIn);
		RayTraceResult raytraceresult = rayTrace(worldIn, playerIn, this.containedBlock == Fluids.EMPTY ? RayTraceContext.FluidMode.SOURCE_ONLY : RayTraceContext.FluidMode.NONE);
		ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onBucketUse(playerIn, worldIn, stackIn, raytraceresult);

		boolean mode = playerIn.abilities.isCreativeMode;

		BlockRayTraceResult blockResult = (BlockRayTraceResult)raytraceresult;
		BlockPos posIn = blockResult.getPos();
		Direction direction = blockResult.getFace();
		BlockPos posIn_1 = posIn.offset(direction);
		BlockState stateIn_1 = worldIn.getBlockState(posIn);
		Block block = stateIn_1.getBlock();

		if (ret != null) return ret;

		if (raytraceresult.getType() == RayTraceResult.Type.MISS) { return ActionResult.resultPass(stackIn); }

		if (raytraceresult.getType() != RayTraceResult.Type.BLOCK) { return ActionResult.resultPass(stackIn); }

		else {
			if (!playerIn.isSneaking()) {
				if (worldIn.isBlockModifiable(playerIn, posIn) && playerIn.canPlayerEdit(posIn_1, direction, stackIn)) {

					if (this.containedBlock == Fluids.EMPTY) {
						/** 大釜からの給水 **/
						if (stateIn_1.getBlock() == Blocks.CAULDRON) {
							int level = stateIn_1.get(CauldronBlock.LEVEL);

							if (level >= 2) {
								playerIn.addStat(Stats.USE_CAULDRON);

								worldIn.playSound(playerIn, posIn, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
								((CauldronBlock)block).setWaterLevel(worldIn, posIn, stateIn_1, level - 2);
								if (!playerIn.inventory.addItemStackToInventory(new ItemStack(Items_Teatime.MIZUOKE_full))) {
									playerIn.dropItem(new ItemStack(Items_Teatime.MIZUOKE_full), false); }

								if (!mode) { stackIn.shrink(1); }
								if (mode) { }
								return ActionResult.resultSuccess(stackIn);
							}

							if (level == 1) {
								playerIn.addStat(Stats.USE_CAULDRON);
								worldIn.playSound(playerIn, posIn, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
								((CauldronBlock)block).setWaterLevel(worldIn, posIn, stateIn_1, 0);
								return ActionResult.resultSuccess(stackIn);
							}
							return ActionResult.resultPass(stackIn);
						}

						/** 溶岩と水 **/
						if (stateIn_1.getBlock() instanceof IBucketPickupHandler) {

							Fluid fluid = ((IBucketPickupHandler)stateIn_1.getBlock()).pickupFluid(worldIn, posIn, stateIn_1);
							if (fluid != Fluids.EMPTY) {

								if (fluid == Fluids.LAVA) {
									worldIn.playSound(playerIn, posIn, SoundEvents.ITEM_BUCKET_FILL_LAVA, SoundCategory.BLOCKS, 1.0F, 1.0F);
									worldIn.playSound(null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.ENTITY_GENERIC_BURN, SoundCategory.PLAYERS, 1.0F, 1.0F);
									playerIn.addPotionEffect(new EffectInstance(Effects.INSTANT_DAMAGE, 1, 0));

									int i = posIn.getX();
									int j = posIn.getY();
									int k = posIn.getZ();

									for(int l = 0; l < 8; ++l) {
										worldIn.addParticle(ParticleTypes.LARGE_SMOKE, (double)i + Math.random(), (double)j + Math.random(), (double)k + Math.random(), 0.0D, 0.0D, 0.0D); }

									if (!playerIn.inventory.addItemStackToInventory(new ItemStack(Items.AIR))) {
										playerIn.dropItem(new ItemStack(Items.AIR), false); }

									if (!mode) { stackIn.shrink(1); }
									if (mode) { }
								}

								else {
									playerIn.addStat(Stats.ITEM_USED.get(this));
										worldIn.playSound(playerIn, posIn, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
									if (!playerIn.inventory.addItemStackToInventory(new ItemStack(Items_Teatime.MIZUOKE_full))) {
										playerIn.dropItem(new ItemStack(Items_Teatime.MIZUOKE_full), false); }

									if (!mode) { stackIn.shrink(1); }
									if (mode) { }
								}
								return ActionResult.resultSuccess(stackIn);
							}
						}
					}//empty


					else {
						/** 砂を塩田に変える **/
						if (stateIn_1.getBlock() == Blocks.SAND && direction == Direction.UP) {
							worldIn.playSound(playerIn, posIn, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
							worldIn.setBlockState(posIn, Crop_Blocks.ENDEN.getDefaultState().with(Enden.WET_1_9, Integer.valueOf(1)), 3);

							if (!mode) { stackIn.shrink(1);
								if (!playerIn.inventory.addItemStackToInventory(new ItemStack(Items_Teatime.MIZUOKE))) {
									playerIn.dropItem(new ItemStack(Items_Teatime.MIZUOKE), false); } }
							if (mode) { }
							return ActionResult.resultSuccess(stackIn);
						}

						/** 大釜への注水 **/
						if (stateIn_1.getBlock() == Blocks.CAULDRON) {
							int level = stateIn_1.get(CauldronBlock.LEVEL);

							if (level != 3) {
								playerIn.addStat(Stats.FILL_CAULDRON);
								worldIn.playSound(playerIn, posIn, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);

								if (level == 2) { ((CauldronBlock)block).setWaterLevel(worldIn, posIn, stateIn_1, 3); }
								else { ((CauldronBlock)block).setWaterLevel(worldIn, posIn, stateIn_1, level + 2); }

								if (!mode) { stackIn.shrink(1);
									if (!playerIn.inventory.addItemStackToInventory(new ItemStack(Items_Teatime.MIZUOKE))) {
										playerIn.dropItem(new ItemStack(Items_Teatime.MIZUOKE), false); } }
								if (mode) { }
								return ActionResult.resultSuccess(stackIn);
							}
							return ActionResult.resultPass(stackIn);
						}

						else {
							BlockState stateIn_2 = worldIn.getBlockState(posIn);
							BlockPos posIn_2 = canBlockContainFluid(worldIn, posIn, stateIn_2) ? posIn : posIn_1;

							if (this.emptyBucket(playerIn, worldIn, posIn_2, blockResult)) {
								this.checkExtraContent(worldIn, stackIn, posIn_2);

								if (playerIn instanceof ServerPlayerEntity) {
									CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity)playerIn, posIn_2, stackIn); }

								playerIn.addStat(Stats.ITEM_USED.get(this));
								if (!mode) { stackIn.shrink(1);
									if (!playerIn.inventory.addItemStackToInventory(new ItemStack(Items_Teatime.MIZUOKE))) {
										playerIn.dropItem(new ItemStack(Items_Teatime.MIZUOKE), false); } }
								if (mode) { }

								return ActionResult.resultSuccess(stackIn); }

							else { return ActionResult.resultPass(stackIn); }
						}
					}//water

				}
			}//!sneaking

			return ActionResult.resultPass(stackIn);
		}
	}

	public void checkExtraContent(World worldIn, ItemStack stackIn, BlockPos posIn) { }

	private boolean canBlockContainFluid(World worldIn, BlockPos posIn, BlockState stateIn) {
		return stateIn.getBlock() instanceof ILiquidContainer && ((ILiquidContainer)stateIn.getBlock()).canContainFluid(worldIn, posIn, stateIn, this.containedBlock);
	}

	@SuppressWarnings("deprecation")
	public boolean emptyBucket(@Nullable PlayerEntity playerIn, World worldIn, BlockPos posIn, @Nullable BlockRayTraceResult result) {
		if (!(this.containedBlock instanceof FlowingFluid)) {
			return false;
		}

		else {
			BlockState stateIn = worldIn.getBlockState(posIn);
			Material material = stateIn.getMaterial();
			boolean flag = stateIn.isReplaceable(this.containedBlock);
			boolean canContainFluid = canBlockContainFluid(worldIn, posIn, stateIn);

			if (stateIn.isAir() || flag || canContainFluid) {

				/** in Nether **/
				if (worldIn.dimension.doesWaterVaporize() && this.containedBlock.isIn(FluidTags.WATER)) {
					int i = posIn.getX();
					int j = posIn.getY();
					int k = posIn.getZ();
					worldIn.playSound(playerIn, posIn, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);

					for(int l = 0; l < 8; ++l) {
						worldIn.addParticle(ParticleTypes.LARGE_SMOKE, (double)i + Math.random(), (double)j + Math.random(), (double)k + Math.random(), 0.0D, 0.0D, 0.0D);
					}
				}

				/** WATERLOGGED **/
				else if (canContainFluid) {
					if (((ILiquidContainer)stateIn.getBlock()).receiveFluid(worldIn, posIn, stateIn, ((FlowingFluid)this.containedBlock).getStillFluidState(false))) {
						worldIn.playSound(playerIn, posIn, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
					}
				}

				else {
					if (!worldIn.isRemote && flag && !material.isLiquid()) { worldIn.destroyBlock(posIn, true); }

					worldIn.playSound(playerIn, posIn, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
					worldIn.setBlockState(posIn, this.containedBlock.getDefaultState().getBlockState(), 11);
				}
				return true;
			}

			else {
				return result == null ? false : this.tryPlaceContainedLiquid(playerIn, worldIn, result.getPos().offset(result.getFace()), (BlockRayTraceResult)null);
			}
		}
	}

	/* 牛乳を汲む ShearsItem, CowEntity */
	@Override
	public boolean itemInteractionForEntity(ItemStack stackIn, net.minecraft.entity.player.PlayerEntity playerIn, LivingEntity entity, net.minecraft.util.Hand handIn) {

		boolean mode = playerIn.abilities.isCreativeMode;

		if (entity.world.isRemote) return false;

		if (stackIn.getItem() == Items_Teatime.MIZUOKE) {

			if (entity instanceof CowEntity && !mode && !entity.isChild()) {

				entity.playSound(SoundEvents.ENTITY_COW_MILK, 2.0F, 1.0F);

				if (!playerIn.inventory.addItemStackToInventory(new ItemStack(Items_Teatime.MIZUOKE_Milk))) {
					playerIn.dropItem(new ItemStack(Items_Teatime.MIZUOKE_Milk), false); }

				/* 消費を最後に回す */
				stackIn.shrink(1);
				return true;
			}
			return false;
		}
		return false;
	}


	//////* BlockItem *///////////////////////////////////////////////
	/** 設置処理の分岐 **/
	public ActionResultType onItemUse(ItemUseContext context) {
		PlayerEntity playerIn = context.getPlayer();

		if (context.getFace() == Direction.UP && playerIn.isSneaking()) {
			return this.tryPlace(new BlockItemUseContext(context)); }

		else {
			return this.onItemRightClick(context.getWorld(), context.getPlayer(), context.getHand()).getType(); }
	}

	public ActionResultType tryPlace(BlockItemUseContext context) {

		if (!context.canPlace()) { return ActionResultType.FAIL; }

		else {
			BlockItemUseContext blockitemusecontext = this.getBlockItemUseContext(context);

			if (blockitemusecontext == null) { return ActionResultType.FAIL; }

			else {
				BlockState stateIn = this.getStateForPlacement(blockitemusecontext);

				if (stateIn == null) { return ActionResultType.FAIL; }

				else if (!this.placeBlock(blockitemusecontext, stateIn)) { return ActionResultType.FAIL; }

				else {
					BlockPos posIn = blockitemusecontext.getPos();
					World world = blockitemusecontext.getWorld();
					PlayerEntity playerIn = blockitemusecontext.getPlayer();
					ItemStack stackIn = blockitemusecontext.getItem();
					BlockState stateIn_1 = world.getBlockState(posIn);
					Block block = stateIn_1.getBlock();
					if (block == stateIn.getBlock()) {
						stateIn_1 = this.getBlockStateTag(posIn, world, stackIn, stateIn_1);
						this.onBlockPlaced(posIn, world, playerIn, stackIn, stateIn_1);
						block.onBlockPlacedBy(world, posIn, stateIn_1, playerIn, stackIn);
						if (playerIn instanceof ServerPlayerEntity) {
							CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity)playerIn, posIn, stackIn);
						}
					}

					SoundType soundtype = stateIn_1.getSoundType(world, posIn, context.getPlayer());
					world.playSound(playerIn, posIn, this.getPlaceSound(stateIn_1, world, posIn, context.getPlayer()), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
					stackIn.shrink(1);
					return ActionResultType.SUCCESS;
				}
			}
		}
	}

	@Deprecated //Forge: Use more sensitive version {@link BlockItem#getPlaceSound(BlockState, IBlockReader, BlockPos, Entity) }
	protected SoundEvent getPlaceSound(BlockState state) {
		return state.getSoundType().getPlaceSound();
	}

	protected SoundEvent getPlaceSound(BlockState state, World world, BlockPos pos, PlayerEntity entity) {
		return state.getSoundType(world, pos, entity).getPlaceSound();
	}

	@Nullable
	public BlockItemUseContext getBlockItemUseContext(BlockItemUseContext context) {
		return context;
	}

	protected boolean onBlockPlaced(BlockPos pos, World worldIn, @Nullable PlayerEntity playerIn, ItemStack stack, BlockState state) {
		return setTileEntityNBT(worldIn, playerIn, pos, stack);
	}

	@Nullable
	protected BlockState getStateForPlacement(BlockItemUseContext context) {
		BlockState stateIn = this.getBlock().getStateForPlacement(context);
		return stateIn != null && this.canPlace(context, stateIn) ? stateIn : null;
	}

	private BlockState getBlockStateTag(BlockPos pos, World worldIn, ItemStack stack, BlockState state) {
		BlockState stateIn = state;
		CompoundNBT compoundnbt = stack.getTag();
		if (compoundnbt != null) {
			CompoundNBT compoundnbt1 = compoundnbt.getCompound("BlockStateTag");
			StateContainer<Block, BlockState> statecontainer = state.getBlock().getStateContainer();

			for(String s : compoundnbt1.keySet()) {
				IProperty<?> iproperty = statecontainer.getProperty(s);
				if (iproperty != null) {
					String s1 = compoundnbt1.get(s).getString();
					stateIn = comBlockState(stateIn, iproperty, s1);
				}
			}
		}

		if (stateIn != state) {
			worldIn.setBlockState(pos, stateIn, 2);
		}

		return stateIn;
	}

	private static <T extends Comparable<T>> BlockState comBlockState(BlockState state, IProperty<T> property, String string) {
		return property.parseValue(string).map((mapper) -> {
			return state.with(property, mapper);
		}).orElse(state);
	}

	protected boolean canPlace(BlockItemUseContext context, BlockState state) {
		PlayerEntity playerIn = context.getPlayer();
		ISelectionContext iselectioncontext = playerIn == null ? ISelectionContext.dummy() : ISelectionContext.forEntity(playerIn);
		return (!this.checkPosition() || state.isValidPosition(context.getWorld(), context.getPos())) && context.getWorld().func_226663_a_(state, context.getPos(), iselectioncontext);
	}

	protected boolean checkPosition() {
		return true;
	}

	protected boolean placeBlock(BlockItemUseContext context, BlockState state) {
		return context.getWorld().setBlockState(context.getPos(), state, 11);
	}

	public static boolean setTileEntityNBT(World worldIn, @Nullable PlayerEntity playerIn, BlockPos pos, ItemStack stackIn) {
		MinecraftServer minecraftserver = worldIn.getServer();
		if (minecraftserver == null) {
			return false;
		}

		else {
			CompoundNBT compoundnbt = stackIn.getChildTag("BlockEntityTag");
			if (compoundnbt != null) {
				TileEntity tileentity = worldIn.getTileEntity(pos);
				if (tileentity != null) {
					if (!worldIn.isRemote && tileentity.onlyOpsCanSetNbt() && (playerIn == null || !playerIn.canUseCommandBlock())) {
						return false;
					}

					CompoundNBT compoundnbt1 = tileentity.write(new CompoundNBT());
					CompoundNBT compoundnbt2 = compoundnbt1.copy();
					compoundnbt1.merge(compoundnbt);
					compoundnbt1.putInt("x", pos.getX());
					compoundnbt1.putInt("y", pos.getY());
					compoundnbt1.putInt("z", pos.getZ());
					if (!compoundnbt1.equals(compoundnbt2)) {
						tileentity.read(compoundnbt1);
						tileentity.markDirty();
						return true;
					}
				}
			}
			return false;
		}
	}

	/* getNameTextComponent に影響するためコメントアウト
	public String getTranslationKey() {
		return this.getBlock().getTranslationKey();
	}

	ItemGroup に影響するためコメントアウト
	public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
		if (this.isInGroup(group)) {
			this.getBlock().fillItemGroup(group, items);
		}
	}*/

	public Block getBlock() {
		return this.getBlockRaw() == null ? null : this.getBlockRaw().delegate.get();
	}

	private Block getBlockRaw() {
		return this.block;
	}

	public void addToBlockToItemMap(Map<Block, Item> blockToItemMap, Item itemIn) {
		blockToItemMap.put(this.getBlock(), itemIn);
	}

	public void removeFromBlockToItemMap(Map<Block, Item> blockToItemMap, Item itemIn) {
		blockToItemMap.remove(this.getBlock());
	}

	/* アイテムは @Nullable World worldIn、ブロックは @Nullable IBlockReader worldIn*/
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag tipFlag) {
		super.addInformation(stack, worldIn, tooltip, tipFlag);
		tooltip.add((new TranslationTextComponent("tips.block_mizuoke")).applyTextStyle(TextFormatting.GRAY));
		tooltip.add((new TranslationTextComponent("tips.block_simpledish")).applyTextStyle(TextFormatting.GRAY));
	}

}
