package dmf444.RunicBridges.Core.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import dmf444.RunicBridges.Core.Lib.BlockLib;
import dmf444.RunicBridges.Core.init.BlockLoader;
import dmf444.RunicBridges.Core.init.ItemLoader;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
public class BlockBrokenRuneEssence {


    @SubscribeEvent
    public void onRuneEssenceBroken(BlockEvent.BreakEvent event){
        if (event.block != BlockLoader.runeBlock){
            return;
        }

        if (event.getPlayer().capabilities.isCreativeMode){
            return;
        }
        else {
            World world = event.world;
            int x = event.x;
            int y = event.y;
            int z = event.z;
            if (!world.isRemote)
                world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(ItemLoader.runeEssence)));
            world.spawnParticle("blockdust_"+ BlockLib.bRuneEssence+"_0", x,y,z,x,y,z);
            world.setBlock(x, y, z, BlockLoader.runeBlock);
            event.setCanceled(true);
        }
    }

}
