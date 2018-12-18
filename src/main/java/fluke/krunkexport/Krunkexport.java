package fluke.krunkexport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fluke.krunkexport.init.ModItems;
import fluke.krunkexport.proxy.CommonProxy;
import fluke.krunkexport.util.Reference;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION, acceptableRemoteVersions="*")
public class Krunkexport 
{
	public static final Logger LOGGER = LogManager.getLogger(Reference.MODID);

	@Instance
	public static Krunkexport instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@EventHandler
	public static void preInit(FMLInitializationEvent event)
	{
		ModItems.init();
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event)
	{
		proxy.init();
	}
	
	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event)
	{
	}
}