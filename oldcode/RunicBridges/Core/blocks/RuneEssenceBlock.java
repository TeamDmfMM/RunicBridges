package dmf444.RunicBridges.Core.blocks;

import dmf444.RunicBridges.Core.Generation.RuneMine.TeleporterRuneEssenceMine;
import dmf444.RunicBridges.Core.Lib.BlockLib;
import dmf444.RunicBridges.Core.Lib.ModInfo;
import dmf444.RunicBridges.Core.init.ItemLoader;
import dmf444.RunicBridges.Core.items.LeaveDimensionToken;
import dmf444.RunicBridges.RunicBridges;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

/**
 * Created by dmf444 on 10/18/2015.
 */
public class RuneEssenceBlock extends Block {

    public RuneEssenceBlock(){
        super(Material.rock);
        this.setCreativeTab(RunicBridges.tab);
        this.setHardness(2.0F);
        this.setBlockTextureName(ModInfo.MODID + ":"+ BlockLib.bRuneEssence);
    }


    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity){
        if(entity.ridingEntity == null && entity.riddenByEntity == null && entity instanceof EntityPlayerMP){



            EntityPlayerMP thePlayer = (EntityPlayerMP) entity;

            MinecraftServer server = MinecraftServer.getServer();
            if(entity instanceof EntityPlayerMP){
                if(thePlayer.timeUntilPortal > 0){
                    thePlayer.timeUntilPortal = 10;

                }else if(thePlayer.dimension != -4412){
                    if(thePlayer.inventory.getFirstEmptyStack() != -1) {
                        thePlayer.inventory.addItemStackToInventory(LeaveDimensionToken.createLeaveToken(thePlayer));
                    }
                    thePlayer.timeUntilPortal = 10;
                    thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, -4412, new TeleporterRuneEssenceMine(server.worldServerForDimension(-4412)));

                }else{
                    thePlayer.timeUntilPortal = 10;
                    thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, 0, new TeleporterRuneEssenceMine(server.worldServerForDimension(0)));
                }

            }

        }
    }



    public void breakBlock(World world, int x, int y, int z, Block block, int meta){
        super.breakBlock(world, x, y, z, block, meta);

    }

    public Item getItemDropped()
    {
        return ItemLoader.runeEssence;
    }

}
