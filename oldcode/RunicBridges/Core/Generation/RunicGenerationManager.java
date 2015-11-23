package dmf444.RunicBridges.Core.Generation;


import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderFlat;

import java.util.Random;

public class RunicGenerationManager implements IWorldGenerator {


    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        if(!(chunkProvider instanceof ChunkProviderFlat)) {
            switch (world.provider.dimensionId) {
                case -1:
                    generateNether(world, random, chunkX * 16, chunkZ * 16);
                case 0:
                    generateSurface(world, random, chunkX * 16, chunkZ * 16);
                case 1:
                    generateEnd(world, random, chunkX * 16, chunkZ * 16);
            }
        }
    }

    private void generateEnd(World world, Random random, int x, int z) {

    }

    private void generateSurface(World world, Random random, int x, int z) {
        if(random.nextInt((6-1)+1) == 3){
            int Xcoord1 = x + random.nextInt(16); //where in chuck it generates
            int Ycoord1 = world.getTopSolidOrLiquidBlock(x, z); //arg = randge + = min
            int Zcoord1 = z + random.nextInt(16); //where in chunk it generates
            new RunicAltarGenerator().generate(world, random, Xcoord1, Ycoord1, Zcoord1);

        }

    }

    private void generateNether(World world, Random random, int x, int z) {

    }
}
