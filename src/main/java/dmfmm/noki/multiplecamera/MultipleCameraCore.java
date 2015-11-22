package dmfmm.noki.multiplecamera;

import dmfmm.noki.multiplecamera.packet.PacketHandler;
import dmfmm.noki.multiplecamera.proxy.ProxyCommon;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.Metadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.relauncher.Side;


@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION, dependencies = ModInfo.DEPENDENCY)
public class MultipleCameraCore {
	
	//******************************//
	// define member variables.
	//******************************//
	//	core.
	@Instance(value = ModInfo.ID)
	public static MultipleCameraCore instance;
	@Metadata
	public static ModMetadata metadata;	//	extract from mcmod.info file, not java internal coding.

	@SidedProxy(clientSide = ModInfo.PROXY_LOCATION+"ProxyClient", serverSide = ModInfo.PROXY_LOCATION+"ProxyServer")
	public static ProxyCommon proxy;

	public static boolean debugFlag = true;

	
	//******************************//
	// define member methods.
	//******************************//
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		//MultipleCameraData.getConfig(event);
		//MultipleCameraPacketHandler.registerPre();
		//MultipleCameraData.setModData(event);
		MultipleCameraData.registerBlocks();
		MultipleCameraData.registerItems();


		PacketHandler.registerPackets();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		
		proxy.registerEvents();
		
		//MultipleCameraData.registerRecipes();
		
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
		//	nothing to do.
		proxy.getTimer();
		
	}

	public static void log(String message, Object... data) {
		
		if(debugFlag) {
			FMLLog.fine("[MultipleCamera:LOG] " + message, data);
		}
		
	}

}
