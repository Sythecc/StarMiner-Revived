package star.miner;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import net.minecraftforge.fml.relauncher.Side;

import star.miner.block.*;
//import your.items.*;


@Mod.EventBusSubscriber(value = Side.CLIENT,modid = StarMinerMod.MODID) // Only client side, models on the server is dumb
public final class ModelRegisterer {

  private static void registerItemBlockRender(Block block) {
    registerItemRender(Item.getItemFromBlock(block));
  }

  private static void registerItemRender(Item item) { // helper
    ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
  }

  @SubscribeEvent // Making models for my blocks and items
  public static void registerRenders(ModelRegistryEvent event) {
    // Makes the inventory item blocks rendered, basically adding models to the itemBlocks
    registerItemBlockRender(ModBlocks.ANTI_GRAVITY_PAD);
    //registerItemBlockRender(YOUR_OTHER_BLOCK);

    //registerItemRender(YOUR_ITEM_ID);
   // registerItemRender(ANOTHER_ITEM);
  }

}