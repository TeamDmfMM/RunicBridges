package dmf444.RunicBridges.Core.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import dmf444.RunicBridges.Core.Lib.ModInfo;

/**
 * Created by dmf444 on 10/17/2015.
 */
public class PacketManager {
    public static SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(ModInfo.MODID);


    public static void init(){
        network.registerMessage(PacketSpawnRune.Handler.class, PacketSpawnRune.class, 0, Side.SERVER);
    }

}
