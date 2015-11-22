package dmfmm.RunicBridges.Core.blocks;


import net.minecraft.block.material.Material;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
public class BlockInvisble extends net.minecraft.block.Block {
    public BlockInvisble() {
        super(Material.air);
        this.setBlockName("invisbla");

    }

    @Override
    public int getRenderType() {
        return 0;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int getLightOpacity() {
        return 0;
    }


}
