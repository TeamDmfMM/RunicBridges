package dmf444.RunicBridges.Core.init;

import cpw.mods.fml.common.registry.GameRegistry;
import dmf444.RunicBridges.Core.Lib.BlockLib;
import dmf444.RunicBridges.Core.blocks.*;
import dmf444.RunicBridges.Core.blocks.tileentity.TileLamp;
import dmf444.RunicBridges.Core.blocks.tileentity.TileRuneTeleportation;
import dmf444.RunicBridges.Core.blocks.tileentity.TileRunicAltar;
import net.minecraft.block.Block;

/**
 * Created by dmf444 on 9/27/2015.
 */
public class BlockLoader {

        public static Block runicAltar;
        public static Block runeBlock;
        public static Block runeTeleporter;
        public static Block lamp;
        public static Block invisibla;


        public static boolean Register=false;

        public static void initiateBlocks() {
            runicAltar = new BlockRunicAltar().setBlockName(BlockLib.bRunicAltar);
            runeBlock = new RuneEssenceBlock().setBlockName(BlockLib.bRuneEssence);
            runeTeleporter = new RuneTeleportationBlock().setBlockName(BlockLib.bTeleport);
            lamp = new BlockLamp().setBlockName(BlockLib.bLamp);
            invisibla = new BlockInvisble();



            registerBlocks();
        }
        private static void registerBlocks() {
            if(!Register){
                GameRegistry.registerBlock(runicAltar, BlockLib.bRunicAltar);
                GameRegistry.registerBlock(runeBlock, BlockLib.bRuneEssence);
                GameRegistry.registerBlock(runeTeleporter, BlockLib.bTeleport);
                GameRegistry.registerBlock(lamp, BlockLib.bLamp);
                GameRegistry.registerBlock(invisibla, BlockLib.bInivisbla);

            }
            Register=true;
        }

        public static void initTileEntity() {
            GameRegistry.registerTileEntity(TileRunicAltar.class, BlockLib.bRunicAltar);
            GameRegistry.registerTileEntity(TileRuneTeleportation.class, BlockLib.bTeleport);
            GameRegistry.registerTileEntity(TileLamp.class, BlockLib.bLamp);

        }

}
