package dmfmm.noki.multiplecamera.camera;

import java.util.HashMap;
import java.util.UUID;

import net.minecraft.world.World;
import dmfmm.noki.multiplecamera.packet.PacketCameraSyncClient;
import dmfmm.noki.multiplecamera.packet.PacketHandler;


public class CameraManagerServer {
	
	private static HashMap<Integer, EntityCamera> cameras = new HashMap<Integer, EntityCamera>();
	private static int id = 0;
	
	public static int addCamera(World world, double posX, double posY, double posZ, float yaw, float pitch, float offsetY, float offsetP, UUID uuid, boolean isTurtle) {
		
		int currentID = id;
		cameras.put(currentID, new EntityCamera(world, posX, posY, posZ, yaw, pitch, offsetY, offsetP, uuid, isTurtle));
		id++;
		
		return currentID;
		
	}
	
	public static void removeCamera(int id) {
		
		if(cameras.containsKey(id)) {
			cameras.remove(id);
		}
		
	}
	
	public static EntityCamera getCamera(int id) {
		
		if(cameras.containsKey(id)) {
			return cameras.get(id);
		}
		return null;
		
	}
	
	public static HashMap<Integer, EntityCamera> getCameras() {
		
		return cameras;
		
	}
	
	public static void updateCamera(int id, World world, double posX, double posY, double posZ, float yaw, float pitch, float offset1, float offset2) {
		
		EntityCamera camera = getCamera(id);
		if(camera != null) {
			camera.setCameraInfo(world, posX, posY, posZ, yaw, pitch, offset1, offset2);
		}
		PacketHandler.instance.sendToAll(new PacketCameraSyncClient(id, world, posX, posY, posZ, yaw, pitch));
		
	}

}
