package star.miner.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.GetCollisionBoxesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import star.miner.ModTabs;
import star.miner.StarMinerMod;

import java.util.Random;

import javax.annotation.Nullable;

public final class AntiGravityPad extends Block {
	
	protected static final AxisAlignedBB AntiGrav_UP_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D);
	protected static final AxisAlignedBB AntiGrav_DOWN_AABB = new AxisAlignedBB(0.0D, 1.0D, 0.0D, 1.0D, 0.9375D, 1.0D);
	protected static final AxisAlignedBB AntiGrav_NORTH_AABB = new AxisAlignedBB(0.0D, 0.0D, 1.0D, 1.0D, 1.0D, 0.9375D);
	protected static final AxisAlignedBB AntiGrav_SOUTH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.0625D);
	protected static final AxisAlignedBB AntiGrav_WEST_AABB = new AxisAlignedBB(1.0D, 0.0D, 0.0D, 0.9375D, 1.0D, 1.0D);
	protected static final AxisAlignedBB AntiGrav_EAST_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0625D, 1.0D, 1.0D);

	public static final PropertyEnum<EnumFacing> FACING = PropertyDirection.create("facing");

	public AntiGravityPad() {
		super(Material.CLOTH);
		setUnlocalizedName(StarMinerMod.MODID + ".block_anti_gravity_pad");
		setRegistryName("anti_gravity_pad");
		setCreativeTab(ModTabs.StarMiner);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.UP));
	}

	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		switch (state.getValue(FACING)) {
		case UP:
			return AntiGrav_UP_AABB;
		case DOWN:
			return AntiGrav_DOWN_AABB;
		case NORTH:
			return AntiGrav_NORTH_AABB;
		case SOUTH:
			return AntiGrav_SOUTH_AABB;
		case WEST:
			return AntiGrav_WEST_AABB;
		case EAST:
			return AntiGrav_EAST_AABB;
		}
		return null;
	}

	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if (entityIn instanceof EntityLivingBase) {
			//System.out.println("Applying levitation");
			//((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 200));
		}
		switch (state.getValue(FACING)) {
		case UP:
			entityIn.addVelocity(0, 5, 0);
		case DOWN:
			entityIn.addVelocity(0, -5, 0);
		case NORTH:
			entityIn.addVelocity(5, 0, 0);
		case SOUTH:
			entityIn.addVelocity(-5, 0, 0);
		case WEST:
			entityIn.addVelocity(0, 0, 5);
		case EAST:
			entityIn.addVelocity(0, 0, -5);
		}
	}

	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	public boolean isFullCube(IBlockState state) {
		return false;
	}

	private boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
		switch (state.getValue(FACING)) {
		case UP:
			return !worldIn.isAirBlock(pos.down());
		case DOWN:
			return !worldIn.isAirBlock(pos.up());
		case NORTH:
			return !worldIn.isAirBlock(pos.south());
		case SOUTH:
			return !worldIn.isAirBlock(pos.north());
		case WEST:
			return !worldIn.isAirBlock(pos.east());
		case EAST:
			return !worldIn.isAirBlock(pos.west());
		}
		return !worldIn.isAirBlock(pos.down());
	}

	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		this.checkForDrop(worldIn, pos, state);
	}

	private boolean checkForDrop(World worldIn, BlockPos pos, IBlockState state) {
		if (!this.canBlockStay(worldIn, pos, state)) {
			this.dropBlockAsItem(worldIn, pos, state, 0);
			worldIn.setBlockToAir(pos);
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Called by ItemBlocks just before a block is actually set in the world, to
	 * allow for adjustments to the IBlockstate
	 */
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING, facing);
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing) state.getValue(FACING)).getIndex();
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing enumfacing = EnumFacing.getFront(meta);
		return this.getDefaultState().withProperty(FACING, enumfacing);
	}

	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		for (int i = 0; i < 3; ++i) {
			int j = rand.nextInt(2) * 2 - 1;
			int k = rand.nextInt(2) * 2 - 1;
			double d0 = (double) pos.getX() + 0.5D + 0.25D * (double) j;
			double d1 = (double) ((float) pos.getY() + rand.nextFloat());
			double d2 = (double) pos.getZ() + 0.5D + 0.25D * (double) k;
			double d3 = (double) (rand.nextFloat() * (float) j);
			double d4 = ((double) rand.nextFloat() - 0.5D) * 0.125D;
			double d5 = (double) (rand.nextFloat() * (float) k);
			worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, d3, d4, d5);
		}
	}

}