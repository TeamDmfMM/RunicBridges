package dmfmm.noki.multiplecamera.packet;

import dmfmm.noki.multiplecamera.ModInfo;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;



public class PacketHandler {
	

	public static final SimpleNetworkWrapper instance = NetworkRegistry.INSTANCE.newSimpleChannel(ModInfo.ID);

	public static void registerPackets() {
		
		instance.registerMessage(PacketCameraGet.PacketCameraGetHandler.class, PacketCameraGet.class, 0, Side.SERVER);
		instance.registerMessage(PacketCameraGetResponse.PacketCameraGetResponseHandler.class, PacketCameraGetResponse.class, 1, Side.CLIENT);
		instance.registerMessage(PacketCameraSyncClient.PacketCameraSyncClientHandler.class, PacketCameraSyncClient.class, 2, Side.CLIENT);
		instance.registerMessage(PacketChunkLoad.PacketChunkLoadHandler.class, PacketChunkLoad.class, 3, Side.SERVER);
		instance.registerMessage(PacketChunkLoadResponse.PacketChunkLoadResponseHandler.class, PacketChunkLoadResponse.class, 4, Side.CLIENT);
		
	}

}
