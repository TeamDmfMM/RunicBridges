package dmfmm.RunicBridges.Core.Generation.RuneMine;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;

/**
 * Created by David on 11/9/2015.
 */
public class GenRails {

    public static void genRailSQ1(int ox, int oy, Chunk c, int a, int b, int oz) {
        int radius = 9;
        //SIDE 1
        if (insideChunk(c.xPosition, c.zPosition, ox + radius, oy - 2)) {
            for (int i = 0; i < 8; i++) {
                setBlockInChunk(c, toC(ox + radius), oz, toC(oy - 1 - i), Blocks.rail, 0);
                //setBlockInChunk(c, toC(ox+radius), oz, toC(oy+i), Blocks.rail, 0);
            }
            setBlockInChunk(c, toC(ox + radius), oz, toC(oy - 1 - 8), Blocks.rail, 7);
            for (int d = 0; d < 10; d++) {
                setBlockInChunk(c, toC(ox - radius - 8 + d), oz, toC(oy + 7), Blocks.rail, 1);
            }
        }

        if (insideChunk(c.xPosition, c.zPosition, ox + radius, oy)) {
            for (int i = 0; i < 10; i++) {
                setBlockInChunk(c, toC(ox + radius), oz, toC(oy - 1 + i), Blocks.rail, 0);
                //setBlockInChunk(c, toC(ox+radius), oz, toC(oy+i), Blocks.rail, 0);
            }
            setBlockInChunk(c, toC(ox + radius), oz, toC(oy + 9), Blocks.rail, 8);
            for (int d = 0; d < 10; d++) {
                setBlockInChunk(c, toC(ox - radius - 8 + d), oz, toC(oy + 9), Blocks.rail, 1);
            }
        }

        //SIDE 2
        if (insideChunk(c.xPosition, c.zPosition, ox - radius, oy - 2)) {
            for (int i = 0; i < 8; i++) {
                setBlockInChunk(c, toC(ox - radius), oz, toC(oy - 1 - i), Blocks.rail, 0);
                //setBlockInChunk(c, toC(ox+radius), oz, toC(oy+i), Blocks.rail, 0);
            }
            setBlockInChunk(c, toC(ox - radius), oz, toC(oy - 1 - 8), Blocks.rail, 6);
            for (int d = 0; d < 7; d++) {
                setBlockInChunk(c, toC(ox - radius + 1 + d), oz, toC(oy + 7), Blocks.rail, 1);
            }
        }

        if (insideChunk(c.xPosition, c.zPosition, ox - radius, oy)) {
            for (int i = 0; i < 10; i++) {
                setBlockInChunk(c, toC(ox - radius), oz, toC(oy - 1 + i), Blocks.rail, 0);
                //setBlockInChunk(c, toC(ox+radius), oz, toC(oy+i), Blocks.rail, 0);
            }
            setBlockInChunk(c, toC(ox - radius), oz, toC(oy + 9), Blocks.rail, 9);
            for (int d = 0; d < 7; d++) {
                setBlockInChunk(c, toC(ox - radius + 1 + d), oz, toC(oy + 9), Blocks.rail, 1);
            }
        }

    }

    public static void genRailSQ2(int ox, int oy, Chunk c, int a, int b, int oz) {
        int radius = 9;
        //SIDE 1
        if (insideChunk(c.xPosition, c.zPosition, ox + radius, oy - 2)) {
            for (int i = 0; i < 8; i++) {
                setBlockInChunk(c, toC(ox + radius), oz, toC(oy - 1 - i), Blocks.rail, 0);
                //setBlockInChunk(c, toC(ox+radius), oz, toC(oy+i), Blocks.rail, 0);
            }
            setBlockInChunk(c, toC(ox + radius), oz, toC(oy - 1 - 8), Blocks.rail, 7);
            for (int d = 0; d < 8; d++) {
                setBlockInChunk(c, toC(ox - radius - 6 + d), oz, toC(oy + 7), Blocks.rail, 1);
            }
        }

        if (insideChunk(c.xPosition, c.zPosition, ox + radius, oy)) {
            for (int i = 0; i < 10; i++) {
                setBlockInChunk(c, toC(ox + radius), oz, toC(oy - 1 + i), Blocks.rail, 0);
                //setBlockInChunk(c, toC(ox+radius), oz, toC(oy+i), Blocks.rail, 0);
            }
            setBlockInChunk(c, toC(ox + radius), oz, toC(oy + 9), Blocks.rail, 8);
            for (int d = 0; d < 8; d++) {
                setBlockInChunk(c, toC(ox - radius - 6 + d), oz, toC(oy + 9), Blocks.rail, 1);
            }
        }

        //SIDE 2
        if (insideChunk(c.xPosition, c.zPosition, ox - radius, oy - 2)) {
            for (int i = 0; i < 8; i++) {
                setBlockInChunk(c, toC(ox - radius), oz, toC(oy - 1 - i), Blocks.rail, 0);
                //setBlockInChunk(c, toC(ox+radius), oz, toC(oy+i), Blocks.rail, 0);
            }
            setBlockInChunk(c, toC(ox - radius), oz, toC(oy - 1 - 8), Blocks.rail, 6);
            for (int d = 0; d < 9; d++) {
                setBlockInChunk(c, toC(ox - radius + 1 + d), oz, toC(oy + 7), Blocks.rail, 1);
            }
        }

        if (insideChunk(c.xPosition, c.zPosition, ox - radius, oy)) {
            for (int i = 0; i < 10; i++) {
                setBlockInChunk(c, toC(ox - radius), oz, toC(oy - 1 + i), Blocks.rail, 0);
                //setBlockInChunk(c, toC(ox+radius), oz, toC(oy+i), Blocks.rail, 0);
            }
            setBlockInChunk(c, toC(ox - radius), oz, toC(oy + 9), Blocks.rail, 9);
            for (int d = 0; d < 9; d++) {
                setBlockInChunk(c, toC(ox - radius + 1 + d), oz, toC(oy + 9), Blocks.rail, 1);
            }
        }
    }

    public static void genRailSQ3(int ox, int oy, Chunk c, int a, int b, int oz) {
        int radius = 9;
        //SIDE 1
        //if (insideChunk(c.xPosition, c.zPosition, ox + radius, oy - 2)) {
            for (int i = 0; i < 9; i++) {
                if (insideChunk(c.xPosition, c.zPosition, ox+radius, oy- i))setBlockInChunk(c, toC(ox + radius), oz, toC(oy  - i), Blocks.rail, 0);
                //setBlockInChunk(c, toC(ox+radius), oz, toC(oy+i), Blocks.rail, 0);
            }
            if (insideChunk(c.xPosition, c.zPosition, ox + radius, oy - 1 -8))setBlockInChunk(c, toC(ox + radius), oz, toC(oy - 1 - 8), Blocks.rail, 7);
            for (int d = 0; d < 17; d++) {
                if (insideChunk(c.xPosition, c.zPosition, ox - 8 + d, 54))setBlockInChunk(c, toC(ox - 8 + d), oz, toC(54), Blocks.rail, 1);
            }
        //}

        //if (insideChunk(c.xPosition, c.zPosition, ox + radius, oy + radius)) {
            for (int i = 0; i < 10; i++) {
                if (insideChunk(c.xPosition, c.zPosition, ox + radius, oy + i))setBlockInChunk(c, toC(ox + radius), oz, toC(oy + i), Blocks.rail, 0);
                //setBlockInChunk(c, toC(ox+radius), oz, toC(oy+i), Blocks.rail, 0);
            }
            if (insideChunk(c.xPosition, c.zPosition, ox + radius, oy + radius)) setBlockInChunk(c, toC(ox + radius), oz, toC(oy + radius), Blocks.rail, 8);
            for (int d = 0; d < 8; d++) {
                if (insideChunk(c.xPosition, c.zPosition, ox - radius - 6 + d, oy + radius)) setBlockInChunk(c, toC(ox - radius - 6 + d), oz, toC(oy + radius), Blocks.rail, 1);
            }
        //}

        //SIDE 2
        //if (insideChunk(c.xPosition, c.zPosition, ox - radius, oy - 2)) {
            for (int i = 0; i < 9; i++) {
                if (insideChunk(c.xPosition, c.zPosition, ox - radius, oy - i))setBlockInChunk(c, toC(ox - radius), oz, toC(oy - i), Blocks.rail, 0);
                //setBlockInChunk(c, toC(ox+radius), oz, toC(oy+i), Blocks.rail, 0);
            }
            if (insideChunk(c.xPosition, c.zPosition, ox - radius, oy - 1- 8))setBlockInChunk(c, toC(ox - radius), oz, toC(oy - 1 - 8), Blocks.rail, 6);

        //}

        //if (insideChunk(c.xPosition, c.zPosition, ox - radius, oy +1)) {
            for (int i = 0; i < 8; i++) {
                if (insideChunk(c.xPosition, c.zPosition, ox -radius, oy + 1 + i))setBlockInChunk(c, toC(ox - radius), oz, toC(oy + 1 + i), Blocks.rail, 0);
            }
            if (insideChunk(c.xPosition, c.zPosition, ox - radius, oy + 9))setBlockInChunk(c, toC(ox - radius), oz, toC(oy + 9), Blocks.rail, 9);
            for (int d = 0; d < 9; d++) {
               if (insideChunk(c.xPosition, c.zPosition, ox - radius + 1 +d, oy + radius)) setBlockInChunk(c, toC(ox - radius +1 + d), oz, toC(oy + radius), Blocks.rail, 1);
            }
        //}
    }


    public static void genRailSQ4(int ox, int oy, Chunk c, int a, int b, int oz) {
        int radius = 9;
        //SIDE 1

            for (int i = 0; i < 9; i++) {
                if (insideChunk(c.xPosition, c.zPosition, ox + radius, oy - i))setBlockInChunk(c, toC(ox + radius), oz, toC(oy  - i), Blocks.rail, 0);
                //setBlockInChunk(c, toC(ox+radius), oz, toC(oy+i), Blocks.rail, 0);
            }
            if (insideChunk(c.xPosition, c.zPosition, ox + radius, oy - 1 - 8))setBlockInChunk(c, toC(ox + radius), oz, toC(oy - 1 - 8), Blocks.rail, 7);
            for (int d = 0; d < 8; d++) {
                if (insideChunk(c.xPosition, c.zPosition, ox - radius - 6 + d, oy + 7))setBlockInChunk(c, toC(ox - radius - 6 + d), oz, toC(oy + 7), Blocks.rail, 1);
            }



            for (int i = 0; i < 10; i++) {
                if (insideChunk(c.xPosition, c.zPosition, ox + radius, oy + i))setBlockInChunk(c, toC(ox + radius), oz, toC(oy + i), Blocks.rail, 0);
                //setBlockInChunk(c, toC(ox+radius), oz, toC(oy+i), Blocks.rail, 0);
            }
            if (insideChunk(c.xPosition, c.zPosition, ox + radius, oy + radius))setBlockInChunk(c, toC(ox + radius), oz, toC(oy + radius), Blocks.rail, 8);
            for (int d = 0; d < 8; d++) {
             if (insideChunk(c.xPosition, c.zPosition, ox - radius - 6 + d, oy + radius))   setBlockInChunk(c, toC(ox - radius - 6 + d), oz, toC(oy + radius), Blocks.rail, 1);
            }


        //SIDE 2
        //if (insideChunk(c.xPosition, c.zPosition, ox - radius, oy - 2)) {
            for (int i = 0; i < 9; i++) {
                if (insideChunk(c.xPosition, c.zPosition, ox- radius, oy- i))setBlockInChunk(c, toC(ox - radius), oz, toC(oy - i), Blocks.rail, 0);
                //setBlockInChunk(c, toC(ox+radius), oz, toC(oy+i), Blocks.rail, 0);
            }
            if (insideChunk(c.xPosition, c.zPosition, ox - radius, oy - 1 - 8))setBlockInChunk(c, toC(ox - radius), oz, toC(oy - 1 - 8), Blocks.rail, 6);
            for (int d = 0; d < 9; d++) {
                if (insideChunk(c.xPosition, c.zPosition, ox - radius + 1 + d, oy + 7))setBlockInChunk(c, toC(ox - radius + 1 + d), oz, toC(oy + 7), Blocks.rail, 1);
            }
        //}

        //if (insideChunk(c.xPosition, c.zPosition, ox - radius, oy +1)) {
            for (int i = 0; i < 8; i++) {
               if (insideChunk(c.xPosition, c.zPosition, ox - radius, oy + 1 + i)) setBlockInChunk(c, toC(ox - radius), oz, toC(oy + 1 + i), Blocks.rail, 0);
            }
            if (insideChunk(c.xPosition, c.zPosition, ox - radius, oy + 9)) setBlockInChunk(c, toC(ox - radius), oz, toC(oy + 9), Blocks.rail, 9); //
            for (int d = 0; d < 9; d++) {
                if (insideChunk(c.xPosition, c.zPosition, ox - radius +1 + d, oy +radius)) setBlockInChunk(c, toC(ox - radius +1 + d), oz, toC(oy + radius), Blocks.rail, 1);
            }
        //}
    }

    private static int toC(int v){
        if (v < 0){
            return 15 - (Math.abs(v + 1) % 16);
        }
        else {
            return Math.abs(v) % 16;
        }
    }
    public static boolean insideChunk(int cx, int cy, int px, int pz){
        int c = px >> 4;
        int abc = pz >> 4;
        if (c == cx && abc == cy){
            return true;
        }
        return false;
    }
    private static void setBlockInChunk(Chunk chunk, int x, int y, int z, Block b, int meta){
        int l;
        l = y >> 4;
        ExtendedBlockStorage e = chunk.getBlockStorageArray()[l];

        if (e == null)
        {
            e = new ExtendedBlockStorage(y, !ChunkProviderRuneEssenceMine.worldObj.provider.hasNoSky);
            chunk.getBlockStorageArray()[l] = e;
        }
        e.func_150818_a(x, y & 15, z, b);
        e.setExtBlockMetadata(x, y & 15, z, meta);
    }


}
