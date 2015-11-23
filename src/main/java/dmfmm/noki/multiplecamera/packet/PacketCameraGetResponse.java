package dmfmm.noki.multiplecamera.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import dmfmm.noki.multiplecamera.MultipleCameraCore;
import dmfmm.noki.multiplecamera.camera.CameraManagerClient;
import dmfmm.noki.multiplecamera.camera.CameraManagerServer;
import dmfmm.noki.multiplecamera.camera.EntityCamera;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**********
 * @class PacketCameraGetResponse
 *
 * @description
 * @description_en
 * 
 * @see
 */
public class PacketCameraGetResponse implements IMessage {


	public ByteBuf data;
	public HashMap<Integer, EntityCamera> cameras;
	public ArrayList<EntityCamera> orderedCameras;

	public PacketCameraGetResponse() {
		
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		
		MultipleCameraCore.log("PacketCameraGetResponse, fromBytes.");
		
		this.data = buf;
		this.cameras = new HashMap<Integer, EntityCamera>();
		int times = buf.readInt();
		
		for(int i=0; i < times; i++) {
			int dimensionId = buf.readInt();
//			World world = Minecraft.getMinecraft().theWorld;
			double posX = buf.readDouble();
			double posY = buf.readDouble();
			double posZ = buf.readDouble();			
			float yaw = buf.readFloat();
			float pitch = buf.readFloat();
			float YAWOFF = buf.readFloat();
			float PITHCOFF = buf.readFloat();
			boolean isTurtle = buf.readBoolean();
			int length = buf.readInt();
			StringBuilder s = new StringBuilder();
			for (int j = 0; j <length; j++){
				s.append(buf.readChar());
			}
			UUID uuid = UUID.fromString(s.toString());
			int id = buf.readInt();
			
			World world = MultipleCameraCore.proxy.getClientWorld();
			MultipleCameraCore.log("player dimensionId is %s.", world.provider.dimensionId);
			MultipleCameraCore.log("camera dimensionId is %s.", dimensionId);
			if(world == null || world.provider.dimensionId != dimensionId) {
				continue;
			}
//			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			EntityPlayer player = MultipleCameraCore.proxy.getClientPlayer();
			if(player == null) {
				continue;
			}
//			int range = Minecraft.getMinecraft().gameSettings.renderDistanceChunks;
			int chunkX = (int)Math.floor(posX) >> 4;
			int chunkZ = (int)Math.floor(posZ) >> 4;
			int range = MultipleCameraCore.proxy.getRenderDistanceChunks();
/*			if(chunkX < player.chunkCoordX-range || player.chunkCoordX+range < chunkX
					|| chunkZ < player.chunkCoordZ-range || player.chunkCoordZ+range < chunkZ) {
				continue;
			}*/
			
			this.cameras.put(id, new EntityCamera(world, posX, posY, posZ, yaw, pitch, YAWOFF, PITHCOFF, uuid, isTurtle));
		}
		
		this.orderedCameras = new ArrayList<EntityCamera>(this.cameras.values());
		
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		
		MultipleCameraCore.log("PacketCameraGetResponse, toBytes.");
		
		HashMap<Integer, EntityCamera> cameras = CameraManagerServer.getCameras();
		
		buf.writeInt(cameras.size());
		MultipleCameraCore.log("camera size is %s.", cameras.size());
		for(Map.Entry<Integer, EntityCamera> each: cameras.entrySet()) {

			buf.writeInt(each.getValue().worldObj.provider.dimensionId);
			MultipleCameraCore.log("dimensionId, toBytes() is %s.", each.getValue().worldObj.provider.dimensionId);
			buf.writeDouble(each.getValue().posX);
			buf.writeDouble(each.getValue().posY);
			buf.writeDouble(each.getValue().posZ);
			buf.writeFloat(each.getValue().rotationYaw);
			buf.writeFloat(each.getValue().rotationPitch);
			buf.writeFloat(each.getValue().YAWOFF);
			buf.writeFloat(each.getValue().PITCHOFF);
			buf.writeBoolean(each.getValue().isTurtle);
			buf.writeInt(each.getValue().uuid.toString().length());



			for (int i = 0; i < each.getValue().uuid.toString().length(); i++){
				buf.writeChar(each.getValue().uuid.toString().charAt(i));
			}
			buf.writeInt(each.getKey());

		}
		
	}
	public static class PacketCameraGetResponseHandler implements IMessageHandler<PacketCameraGetResponse, IMessage> {

		@Override
		public IMessage onMessage(PacketCameraGetResponse message, MessageContext ctx) {
			
			MultipleCameraCore.log("PacketCameraGetResponseHandler, onMessage.");
			CameraManagerClient.setClientCameras(message.cameras);
			CameraManagerClient.setOrderedClientCameras(message.orderedCameras);
			if(message.cameras.size() != 0) {
				MultipleCameraCore.log("camera exists.");
				CameraManagerClient.setClientId(0);
				CameraManagerClient.setViewCamera(message.orderedCameras.get(0));
			}
			else {
				MultipleCameraCore.log("camera doesn't exist.");
				CameraManagerClient.setClientId(-1);
			}
			
			return null;
			
		}

	}
}
