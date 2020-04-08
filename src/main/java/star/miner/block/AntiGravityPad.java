package star.miner.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
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

	protected static final AxisAlignedBB CARPET_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D);

	public AntiGravityPad() {
		super(Material.CLOTH);
		setUnlocalizedName(StarMinerMod.MODID + ".block_anti_gravity_pad");
		setRegistryName("anti_gravity_pad");
		setCreativeTab(ModTabs.StarMiner);
	}

	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return CARPET_AABB;
	}

	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if (entityIn instanceof EntityLivingBase){
			System.out.println("Applying levitation");
			((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 200));
		}
	}

	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	public boolean isFullCube(IBlockState state) {
		return false;
	}

	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return super.canPlaceBlockAt(worldIn, pos) && this.canBlockStay(worldIn, pos);
	}

	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		this.checkForDrop(worldIn, pos, state);
	}

	private boolean checkForDrop(World worldIn, BlockPos pos, IBlockState state) {
		if (!this.canBlockStay(worldIn, pos)) {
			this.dropBlockAsItem(worldIn, pos, state, 0);
			worldIn.setBlockToAir(pos);
			return false;
		} else {
			return true;
		}
	}

	private boolean canBlockStay(World worldIn, BlockPos pos) {
		return !worldIn.isAirBlock(pos.down());
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