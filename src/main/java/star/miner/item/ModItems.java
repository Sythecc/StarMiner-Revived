package star.miner.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import star.miner.StarMinerMod;

//
//@GameRegistry.ObjectHolder(StarMinerMod.MODID)
//public final class ModItems { // holds all the registered items
//
//	  // they get defined when the registerItems event runs so i can reuse those class variables
//	  public static final Item YOUR_ITEM_ID = null;
//	  public static final Item ANOTHER_ITEM = null;
//
//	  @Mod.EventBusSubscriber(modid = YOUR.MODID)
//	  public static class RegisterEvents {
//
//	    @SubscribeEvent
//	    public static void registerItems(RegistryEvent.Register<Item> event) {
//	      event.getRegistry().registerAll( // only calling new item instances when registering, not before / after
//	        new MyItem(),
//	        new OtherItem(),
//	      );
//	    }
//	  }
//
//	}
//}