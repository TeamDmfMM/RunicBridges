package dmf444.RunicBridges.Core.blocks;

import dmf444.RunicBridges.Core.Lib.BlockLib;
import dmf444.RunicBridges.Core.Lib.ModInfo;
import dmf444.RunicBridges.Core.init.BlockLoader;
import dmf444.RunicBridges.Core.init.ItemLoader;
import dmf444.RunicBridges.RunicBridges;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by dmf444 on 10/18/2015.
 */
public class RuneEssenceBlock extends Block {

    public RuneEssenceBlock(){
        super(Material.rock);
        this.setCreativeTab(RunicBridges.tab);
        this.setHardness(0.0F);
        this.setBlockTextureName(ModInfo.MODID + ":"+ BlockLib.bRuneEssence);
    }


    public void breakBlock(World world, int x, int y, int z, Block block, int meta){
        super.breakBlock(world, x, y, z, block, meta);
        if(true) {
            if (block.equals(BlockLoader.runeBlock)) {
                if (!world.isRemote)
                    world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(this.getItemDropped())));
                world.setBlock(x, y, z, BlockLoader.runeBlock);
            }
        }
    }

    public Item getItemDropped()
    {
        return ItemLoader.runeEssence;
    }
}
