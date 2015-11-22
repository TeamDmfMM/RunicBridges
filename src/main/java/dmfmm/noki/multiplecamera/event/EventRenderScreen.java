package dmfmm.noki.multiplecamera.event;

import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;
import net.minecraftforge.client.event.RenderHandEvent;
import dmfmm.noki.multiplecamera.MultipleCameraCore;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


/**********
 * @class EventWorldRender
 *
 * @description
 * @description_en
 */
@SideOnly(Side.CLIENT)
public class EventRenderScreen {
	
	//******************************//
	// define member variables.
	//******************************//
	public static boolean activated = false;
	public static int count;

	
	//******************************//
	// define member methods.
	//******************************//
	@SubscribeEvent
	public void onRenderHand(RenderHandEvent event) {
		
		if(activated == true) {
			event.setCanceled(true);
		}
		
	}
	
	@SubscribeEvent
	public void onRenderScreenPre(Pre event) {
		
		count = (count+1)%20;
		if(activated == true && event.type != RenderGameOverlayEvent.ElementType.ALL) {
			event.setCanceled(true);
		}
		
	}
	
	@SubscribeEvent
	public void onRenderBlocks(net.minecraftforge.client.event.RenderWorldEvent.Pre event) {
		
		if(count == 0) {
			MultipleCameraCore.log("render chunk pos is %s/%s/%s.", event.renderer.posX, event.renderer.posY, event.renderer.posZ);
		}
		
	}
	
}
