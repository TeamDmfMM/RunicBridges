package dmfmm.noki.multiplecamera.packet;

import net.minecraft.world.World;
import dmfmm.noki.multiplecamera.camera.CameraManagerClient;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;


/**********
 * @class PacketCameraSyncClient
 *
 * @description
 * @description_en
 * 
 * @see
 */
public class PacketCameraSyncClient implements IMessage {

	//******************************//
	// define member variables.
	//******************************//
	public ByteBuf data;
	public int id;
	public int dimensionId;
	public double posX;
	public double posY;
	public double posZ;
	public float yaw;
	public float pitch;

	
	//******************************//
	// define member methods.
	//******************************//
	public PacketCameraSyncClient() {
		
	}
	
	public PacketCameraSyncClient(int id, World world, double posX, double posY, double posZ, float yaw, float pitch) {
		
		this.id = id;
		this.dimensionId = world.provider.dimensionId;
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
		this.yaw = yaw;
		this.pitch = pitch;
		
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		
		this.data = buf;
		this.id = buf.readInt();
		this.dimensionId = buf.readInt();
		this.posX = buf.readDouble();
		this.posY = buf.readDouble();
		this.posZ = buf.readDouble();
		this.yaw = buf.readFloat();
		this.pitch = buf.readFloat();
		
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		
		buf.writeInt(this.id);
		buf.writeInt(this.dimensionId);
		buf.writeDouble(this.posX);
		buf.writeDouble(this.posY);
		buf.writeDouble(this.posZ);
		buf.writeFloat(this.yaw);
		buf.writeFloat(this.pitch);
		
	}
	
	public static class PacketCameraSyncClientHandler implements IMessageHandler<PacketCameraSyncClient, IMessage> {

		@Override
		public IMessage onMessage(PacketCameraSyncClient message, MessageContext ctx) {
			
			CameraManagerClient.syncCamera(message.id, message.dimensionId,
					message.posX, message.posY, message.posZ, message.yaw, message.pitch);
			return null;
			
		}

	}

}
