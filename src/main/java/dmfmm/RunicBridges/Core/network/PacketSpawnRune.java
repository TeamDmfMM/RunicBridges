package dmfmm.RunicBridges.Core.network;


import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class PacketSpawnRune implements IMessage{


    private ItemStack stack;

    private int x;
    private int y;
    private int z;


    public PacketSpawnRune(){
        //DO NOTHING.....
    }


    public PacketSpawnRune(ItemStack itemStack, int x, int y, int z){
        this.stack = itemStack;
        this.x = x;
        this.y = y;
        this.z = z;
    }


    @Override
    public void fromBytes(ByteBuf buf) {
        stack = ByteBufUtils.readItemStack(buf);
        x = ByteBufUtils.readVarInt(buf, 5);
        y = ByteBufUtils.readVarInt(buf, 5);
        z = ByteBufUtils.readVarInt(buf, 5);


    }


    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeItemStack(buf, stack);
        ByteBufUtils.writeVarInt(buf, x, 5);
        ByteBufUtils.writeVarInt(buf, y, 5);
        ByteBufUtils.writeVarInt(buf, z, 5);



    }


    public static class Handler implements IMessageHandler<PacketSpawnRune, IMessage> {


        @Override
        public IMessage onMessage(PacketSpawnRune message, MessageContext ctx) {

            World world = MinecraftServer.getServer().worldServers[0];
            EntityItem item = new EntityItem(world, message.x, message.y, message.z, message.stack);
            boolean carls = true;
            if(!world.isRemote && carls){;
                world.spawnEntityInWorld(item);
                carls = false;
            }
            return null;
        }
    }
}
