package dmf444.RunicBridges.Core.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dmf444.RunicBridges.Client.ClientProxy;
import dmf444.RunicBridges.Core.blocks.tileentity.TileLamp;
import dmf444.RunicBridges.RunicBridges;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import static net.minecraftforge.common.util.ForgeDirection.*;

/**
 * Created by David on 11/1/2015.
 */
public class BlockLamp extends BlockContainer {


    public BlockLamp() {
        super(Material.glass);
        this.setLightLevel(1.0F);
        this.setCreativeTab(RunicBridges.tab);
    }


    @SideOnly(Side.CLIENT)
    public int getRenderType()
    {
        return -1;
    }
    public boolean isOpaqueCube()
    {
        return false;
    }
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public boolean canRenderInPass(int pass) {
        ClientProxy.renderPass = pass;

        return true;
    }

    @Override
    public int getRenderBlockPass() {
        return 1;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileLamp();
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        return AxisAlignedBB.getBoundingBox(
                (double)x + this.minX +0.37F,
                (double)y + this.minY,
                (double)z + this.minZ+0.37F,
                (double)x + this.maxX -0.37F,
                (double)y + this.maxY - 0.2F,
                (double)z + this.maxZ -0.37F
        );
    }

    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
    {
        return AxisAlignedBB.getBoundingBox(
                (double)x + this.minX +0.37F,
                (double)y + this.minY,
                (double)z + this.minZ+0.37F,
                (double)x + this.maxX -0.37F,
                (double)y + this.maxY - 0.2F,
                (double)z + this.maxZ -0.37F
        );
    }
    public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 startVec, Vec3 endVec3)
    {
        int l = world.getBlockMetadata(x, y, z) & 7;
        float f = 0.15F;

        if (l == 1)
        {
            this.setBlockBounds(0.0F, 0.2F, 0.5F - f, f * 2.0F, 0.8F, 0.5F + f);
        }
        else if (l == 2)
        {
            this.setBlockBounds(1.0F - f * 2.0F, 0.2F, 0.5F - f, 1.0F, 0.8F, 0.5F + f);
        }
        else if (l == 3)
        {
            this.setBlockBounds(0.5F - f, 0.2F, 0.0F, 0.5F + f, 0.8F, f * 2.0F);
        }
        else if (l == 4)
        {
            this.setBlockBounds(0.37F, 0.37F, 1.0F - f * 2.0F, 0.5F + f, 0.9F, 1.0F);
        }
        else
        {
            f = 0.1F;
            this.setBlockBounds(0.37F, 0.37F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
        }

        return super.collisionRayTrace(world, x, y, z, startVec, endVec3);
    }

    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
    {
        int j1 = meta;

        if (side == 1 && this.func_150107_m(world, x, y - 1, z))
        {
            j1 = 5;
        }

        if (side == 2 && world.isSideSolid(x, y, z + 1, NORTH, true))
        {
            j1 = 4;
        }

        if (side == 3 && world.isSideSolid(x, y, z - 1, SOUTH, true))
        {
            j1 = 3;
        }

        if (side == 4 && world.isSideSolid(x + 1, y, z, WEST, true))
        {
            j1 = 2;
        }

        if (side == 5 && world.isSideSolid(x - 1, y, z, EAST, true))
        {
            j1 = 1;
        }

        return j1;
    }

    private boolean func_150107_m(World world, int x, int y, int z)
    {
        if (World.doesBlockHaveSolidTopSurface(world, x, y, z))
        {
            return true;
        }
        else
        {
            Block block = world.getBlock(x, y, z);
            return block.canPlaceTorchOnTop(world, x, y, z);
        }
    }
}
