package dmfmm.noki.multiplecamera.packet;

import java.util.UUID;

import dmfmm.noki.multiplecamera.MultipleCameraCore;
import dmfmm.noki.multiplecamera.camera.CameraManagerClient;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;


/**********
 * @class PacketChunkLoad
 *
 * @description
 * @description_en
 * 
 * @see
 */
public class PacketChunkLoadResponse implements IMessage {

	//******************************//
	// define member variables.
	//******************************//
	public ByteBuf data;

	
	//******************************//
	// define member methods.
	//******************************//
	public PacketChunkLoadResponse() {
		
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		
		this.data = buf;
		
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
				
	}
	public static class PacketChunkLoadResponseHandler implements IMessageHandler<PacketChunkLoadResponse, IMessage> {

		@Override
		public IMessage onMessage(PacketChunkLoadResponse message, MessageContext ctx) {
			
			MultipleCameraCore.log("on message of PacketChunkLoadResponseHandler.");
			CameraManagerClient.loadRenderers();
			
			return null;
			
		}

	}
}
