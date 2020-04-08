package star.miner;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;


@Mod(modid = StarMinerMod.MODID, name = StarMinerMod.NAME, version = StarMinerMod.VERSION, acceptedMinecraftVersions = "[1.12.2]")
public final class StarMinerMod
{
    public static final String MODID = "starminer";
    public static final String NAME = "StarMiner Revived Mod";
    public static final String VERSION = "0.0.1";

    @Mod.Instance
    public static StarMinerMod instance;

    //public static final ShenanigansTabs Tabs = new ShenanigansTabs();

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog(); // e.getModLog() returns logger instance with modid
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
    }

    @Mod.EventHandler
    public void init(FMLServerStartingEvent event)
    {
        //event.registerServerCommand(new ModCommands.EchoCommand());
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

    }
}