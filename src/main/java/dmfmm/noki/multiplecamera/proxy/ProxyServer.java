package dmfmm.noki.multiplecamera.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import dmfmm.noki.multiplecamera.event.EventJoinWorld;


/**********
 * @class ProxyServer
 *
 * @description サーバ用proxyクラス。
 * @description_en Proxy class for Server.
 */
public class ProxyServer implements ProxyCommon {
	
	//******************************//
	// define member variables.
	//******************************//


	//******************************//
	// define member methods.
	//******************************//
	@Override
	public void registerEvents() {
		
		MinecraftForge.EVENT_BUS.register(new EventJoinWorld());
		
	}
	
	@Override
	public World getClientWorld() {
		
		return null;
		
	}
	
	@Override
	public EntityPlayer getClientPlayer() {
		
		return null;
		
	}
	
	@Override
	public int getRenderDistanceChunks() {
		
		return 0;
		
	}
	
	@Override
	public void openItemClientGui(EntityPlayer player) {
		
	}
	
	@Override
	public void getTimer() {
		
	}

}
