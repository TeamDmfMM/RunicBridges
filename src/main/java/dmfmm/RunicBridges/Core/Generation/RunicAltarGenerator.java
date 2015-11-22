package dmfmm.RunicBridges.Core.Generation;


import dmfmm.RunicBridges.Core.blocks.tileentity.TileRunicAltar;
import dmfmm.RunicBridges.Core.init.BlockLoader;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import java.util.Random;

public class RunicAltarGenerator {

    public RunicAltarGenerator(){
    }



    public boolean generate(World world, Random rand, int x, int y, int z){
        if(world.isAirBlock(x , y, z) && this.isReplaceable(world, x, y, z) && world.getBlock(x, y-1, z) != Blocks.water){
            //Altar
            world.setBlock(x, y, z, BlockLoader.runicAltar);
            ((TileRunicAltar)world.getTileEntity(x, y, z)).displayTable();
            world.markBlockForUpdate(x, y, z);

            world.setBlock(x + 1, y, z, BlockLoader.runicAltar);
            ((TileRunicAltar)world.getTileEntity(x+1, y, z)).syncTo(world, x,y,z);
            world.markBlockForUpdate(x+1, y, z);

            world.setBlock(x, y, z+1, BlockLoader.runicAltar);
            ((TileRunicAltar)world.getTileEntity(x, y, z+1)).syncTo(world, x,y,z);
            world.markBlockForUpdate(x, y, z+1);

            world.setBlock(x+1, y, z+1, BlockLoader.runicAltar);
            ((TileRunicAltar)world.getTileEntity(x+1, y, z+1)).syncTo(world, x,y,z);
            world.markBlockForUpdate(x+1, y, z+1);

            //Floor
            for(int c=0;c < 4; c++){
                for(int w=0; w< 4; w++){
                    world.setBlock((x-1)  + c, y - 1, (z-1)  + w, Blocks.brick_block);
                }
            }

            //Pillars




            return true;
        }
        return false;
    }


    protected boolean isReplaceable(World world, int x, int y, int z)
    {
        Block block = world.getBlock(x, y, z);
        return block.isAir(world, x, y, z) || block.isLeaves(world, x, y, z) || block.isWood(world, x, y, z) || OtherBlocks(block);
    }
    protected boolean OtherBlocks(Block block)
    {
        return block.getMaterial() == Material.air || block.getMaterial() == Material.leaves || block == Blocks.grass || block == Blocks.dirt || block == Blocks.log || block == Blocks.log2 || block == Blocks.sapling || block == Blocks.vine;
    }
}
