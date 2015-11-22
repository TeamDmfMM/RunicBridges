package dmfmm.noki.multiplecamera.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;


/**********
 * @class ProxyCommon
 *
 * @description 共通proxyクラス。
 * @description_en Interface of proxy classes.
 */
public interface ProxyCommon {
	
	//******************************//
	// define member variables.
	//******************************//

	
	//******************************//
	// define member methods.
	//******************************//
	public abstract void registerEvents();
	public abstract World getClientWorld();
	public abstract EntityPlayer getClientPlayer();
	public abstract int getRenderDistanceChunks();
	public abstract void openItemClientGui(EntityPlayer player);
	public abstract void getTimer();
	
}
