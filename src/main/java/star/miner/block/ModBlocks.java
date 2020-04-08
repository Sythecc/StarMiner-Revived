package star.miner.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder("starminer")
public final class ModBlocks {

	// will get defined as RegisterBlocks runs
	public static final Block ANTI_GRAVITY_PAD = null;
	// public static final Block YOUR_OTHER_BLOCK = null;

	private static Item RegisterItemBlock(Block block) {
		return new ItemBlock(block).setRegistryName(block.getRegistryName());
	}

	@Mod.EventBusSubscriber(modid = "starminer")
	public static class RegistryEvents { // subscribes to the event bus

		@SubscribeEvent // filters the RegisterBlocks event
		public static void registerBlocks(final RegistryEvent.Register<Block> event) {
			event.getRegistry().registerAll(new AntiGravityPad()
			// new SomeBlock(),
			);
		}

		@SubscribeEvent
		public static void registerItemBlocks(final RegistryEvent.Register<Item> event) {
			event.getRegistry().registerAll(RegisterItemBlock(ANTI_GRAVITY_PAD)
			// RegisterItemBlock(YOUR_OTHER_BLOCK),
			);
		}
	}
}