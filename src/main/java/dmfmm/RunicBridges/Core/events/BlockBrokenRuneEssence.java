package dmfmm.RunicBridges.Core.events;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import dmfmm.RunicBridges.Core.Lib.BlockLib;
import dmfmm.RunicBridges.Core.init.BlockLoader;
import dmfmm.RunicBridges.Core.init.ItemLoader;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
public class BlockBrokenRuneEssence {


    @SubscribeEvent
    public void onStoneBrokenInDimension(BlockEvent.BreakEvent event){
        if (event.block == Blocks.stone){
            if (event.world.provider.dimensionId == -4412){
                if (event.getPlayer() != null) {
                    if (!event.getPlayer().capabilities.isCreativeMode) {
                        event.setCanceled(true);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onStonePlacedInDimension(PlayerInteractEvent event){
        if (event.world.provider.dimensionId == -4412 && event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK){
            if (event.entityPlayer.getHeldItem() != null && event.entityPlayer.getHeldItem().getItem() instanceof ItemBlock){
                if (Block.getBlockFromItem(event.entityPlayer.getHeldItem().getItem()) == Blocks.stone){
                    if (!event.entityPlayer.capabilities.isCreativeMode){
                        event.setResult(Event.Result.DENY);
                        event.setCanceled(true);
                    }
                }
            }
        }
    }


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
