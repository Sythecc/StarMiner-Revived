package star.miner;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import star.miner.block.ModBlocks;
//import star.miner.item.ModItems;

public final class ModTabs {

  public final static CreativeTabs StarMiner = new CreativeTabs("starminertab") {
    @SideOnly(Side.CLIENT)
    @Override
    public ItemStack getTabIconItem() {
      return new ItemStack(ModBlocks.ANTI_GRAVITY_PAD);
    }
  };


}