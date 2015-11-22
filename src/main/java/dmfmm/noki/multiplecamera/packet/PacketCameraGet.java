package dmfmm.noki.multiplecamera.packet;

import dmfmm.noki.multiplecamera.MultipleCameraCore;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;


/**********
 * @class PacketCameraGet
 *
 * @description
 * @description_en
 * 
 * @see
 */
public class PacketCameraGet implements IMessage {

	public PacketCameraGet() {}


	public ByteBuf data;

	@Override
	public void fromBytes(ByteBuf buf) {
		
		MultipleCameraCore.log("PacketCameraGet, fromBytes.");
		this.data = buf;
		
	}
	
	@Override
	public void toBytes(ByteBuf buf) {

		MultipleCameraCore.log("PacketCameraGet, toBytes.");

	}
	public static class PacketCameraGetHandler implements IMessageHandler<PacketCameraGet, PacketCameraGetResponse> {

		@Override
		public PacketCameraGetResponse onMessage(PacketCameraGet message, MessageContext ctx) {

			//MultipleCameraCore.log("PacketCameraGetHandler, onMessage.");
			return new PacketCameraGetResponse();

		}

	}
}
