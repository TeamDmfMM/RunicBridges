package dmfmm.noki.multiplecamera.packet;

import java.util.UUID;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.play.server.S21PacketChunkData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import dmfmm.noki.multiplecamera.MultipleCameraCore;
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
public class PacketChunkLoad implements IMessage {

	//******************************//
	// define member variables.
	//******************************//
	public ByteBuf data;
	
	public int dimensionId;
	public int chunkPosX;
	public int chunkPosZ;
	public int renderDistance;
	public UUID uuid;

	
	//******************************//
	// define member methods.
	//******************************//
	public PacketChunkLoad() {
		
	}
	
	public PacketChunkLoad(int dimensionId, int chunkPosX, int chunkPosZ, int renderDistance, UUID uuid) {
		
		this.dimensionId = dimensionId;
		this.chunkPosX = chunkPosX;
		this.chunkPosZ = chunkPosZ;
		this.renderDistance = renderDistance;
		this.uuid = uuid;
		
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		
		this.data = buf;
		this.dimensionId = buf.readInt();
		this.chunkPosX = buf.readInt();
		this.chunkPosZ = buf.readInt();
		this.renderDistance = buf.readInt();
		
		int length = buf.readInt();
		char[] chars = new char[length];
		for(int i=0; i<length; i++) {
			chars[i] = buf.readChar();
		}
		this.uuid = UUID.fromString(new String(chars));
		
		MultipleCameraCore.log("dimensionId is %s.", this.dimensionId);
		MultipleCameraCore.log("chunkPosX is %s.", this.chunkPosX);
		MultipleCameraCore.log("chunkPosZ is %s.", this.chunkPosZ);
		MultipleCameraCore.log("renderDistance is %s.", this.renderDistance);
		MultipleCameraCore.log("uuid is %s.", this.uuid.toString());
		
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		
		buf.writeInt(this.dimensionId);
		buf.writeInt(this.chunkPosX);
		buf.writeInt(this.chunkPosZ);
		buf.writeInt(this.renderDistance);
		
		String uuidString = this.uuid.toString();
		int length = uuidString.length();
		buf.writeInt(length);
		for(int i=0; i<length; i++) {
			buf.writeChar(uuidString.charAt(i));
		}
		
		MultipleCameraCore.log("dimensionId is %s.", this.dimensionId);
		MultipleCameraCore.log("chunkPosX is %s.", this.chunkPosX);
		MultipleCameraCore.log("chunkPosZ is %s.", this.chunkPosZ);
		MultipleCameraCore.log("renderDistance is %s.", this.renderDistance);
		MultipleCameraCore.log("uuid is %s.", this.uuid.toString());
		
	}
	public static class PacketChunkLoadHandler implements IMessageHandler<PacketChunkLoad, PacketChunkLoadResponse> {

		@Override
		public PacketChunkLoadResponse onMessage(PacketChunkLoad message, MessageContext ctx) {
			
			WorldServer world = MinecraftServer.getServer().worldServerForDimension(message.dimensionId);
			NetHandlerPlayServer handler = ((EntityPlayerMP)world.func_152378_a(message.uuid)).playerNetServerHandler;
			
			for(int x=message.chunkPosX-message.renderDistance; x<=message.chunkPosX+message.renderDistance;x++) {
				for(int z=message.chunkPosZ-message.renderDistance; z<=message.chunkPosZ+message.renderDistance;z++) {
					Chunk chunk = world.getChunkFromChunkCoords(x, z);
					handler.sendPacket(new S21PacketChunkData(chunk, false, 1));
					MultipleCameraCore.log("sended packet.");
				}
			}
			
			return new PacketChunkLoadResponse();
			
		}

	}
}
