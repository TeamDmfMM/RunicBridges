package dmfmm.noki.multiplecamera.camera;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import dmfmm.noki.multiplecamera.MultipleCameraCore;
import dmfmm.noki.multiplecamera.event.EventRenderScreen;
import dmfmm.noki.multiplecamera.packet.PacketCameraGet;
import dmfmm.noki.multiplecamera.packet.PacketHandler;

import java.util.ArrayList;
import java.util.HashMap;

//import cpw.mods.fml.relauncher.Side;
//import cpw.mods.fml.relauncher.SideOnly;


//@SideOnly(Side.CLIENT)
public class CameraManagerClient {
	
	private static HashMap<Integer, EntityCamera> clientCameras = new HashMap<Integer, EntityCamera>();
	private static ArrayList<EntityCamera> orderedClientCameras = new ArrayList<EntityCamera>();
	private static int clientId = -1;
	
	
	public static void setNextClientCamera() {
		
		if(clientId < 0 || orderedClientCameras.size() == 0) {
			return;
		}
		
		clientId = (clientId + 1) % orderedClientCameras.size();
		setViewCamera(orderedClientCameras.get(clientId));
		
	}
	
	public static void setPrevClientCamera() {
		
		if(clientId < 0 || orderedClientCameras.size() == 0) {
			return;
		}
		
/*		if(clientId == 0) {
			clientId = orderedClientCameras.size()-1;
		}
		else {
			clientId = clientId - 1;
		}*/
		int div = orderedClientCameras.size();
		clientId = (clientId - 1 + div) % div;
		setViewCamera(orderedClientCameras.get(clientId));
		
	}
	
	public static void setClientCamerasFromServer() {
		
		PacketHandler.instance.sendToServer(new PacketCameraGet());
		
	}
	
	public static void setClientCameras(HashMap<Integer, EntityCamera> cameras) {
		
		clientCameras = cameras;
		
	}
	
	public static void setOrderedClientCameras(ArrayList<EntityCamera> cameras) {
		
		orderedClientCameras = cameras;
		
	}
	
	public static EntityCamera getOrderedCamera(int id) {
		
		if(0 <= id && id <= orderedClientCameras.size()) {
			return orderedClientCameras.get(id);
		}
		return null;
		
	}
	
	public static void setClientId(int id) {
		
		clientId = id;
		
	}
	
	public static int getClientId() {
		
		return clientId;
		
	}
	
	public static int getSize() {
		
		return clientCameras.size();
		
	}
	
	public static void setViewCamera(EntityLivingBase camera) {
		
		if(camera instanceof EntityPlayer) {
			EventRenderScreen.activated = false;
		}
		else {
			EventRenderScreen.activated = true;
			
/*			int dimensionId = camera.worldObj.provider.dimensionId;
			int chunkPosX = (int)camera.posX >> 4;
			int chunkPosZ = (int)camera.posZ >> 4;
			int range = MultipleCameraCore.proxy.getRenderDistanceChunks();
			UUID uuid = Minecraft.getMinecraft().thePlayer.getPersistentID();
			PacketHandler.instance.sendToServer(new PacketChunkLoad(dimensionId, chunkPosX, chunkPosZ, range, uuid));*/
		}
		
		Minecraft mc = Minecraft.getMinecraft();
		
		mc.renderViewEntity = camera;
		mc.entityRenderer.updateRenderer();
		mc.entityRenderer.updateCameraAndRender(0.1F);
		if(mc.renderGlobal != null) {
			mc.renderGlobal.setWorldAndLoadRenderers(mc.theWorld);
		}
		MultipleCameraCore.log("finished to set camera");
//		mc.renderGlobal.loadRenderers();

	}
	
	public static void resetClientCameras() {
		
		orderedClientCameras = new ArrayList<EntityCamera>();
		setClientId(-1);
		setViewCamera(Minecraft.getMinecraft().thePlayer);
		
	}
	
	public static void syncCamera(int id,
			int dimensionId, double posX, double posY, double posZ, float yaw, float pitch) {
		
		if(clientCameras.containsKey(id)) {
			EntityCamera camera = clientCameras.get(id);
			if(camera.worldObj.provider.dimensionId == dimensionId) {
				camera.setLocationAndAngles(posX, posY, posZ, yaw, pitch);
				camera.setPositionAndRotation(posX, posY, posZ, yaw, pitch);
			}
			else {
				setClientCamerasFromServer();
			}
		}
		
	}
	
	public static void loadRenderers() {
		
		Minecraft.getMinecraft().renderGlobal.loadRenderers();
		
	}
	
}
