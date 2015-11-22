package dmfmm.RunicBridges.Core.Generation.RuneMine;

import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
public class WorldProviderRuneEssence extends WorldProvider{

    @Override
    protected void registerWorldChunkManager() {
        this.worldChunkMgr = new WorldChunkManagerHell(DimBiomReg.biome, 500f);
        this.dimensionId = -4412;


    }

    @Override
    public Vec3 getFogColor(float p_76562_1_, float p_76562_2_) {
        return Vec3.createVectorHelper(0.95d, 0.95d, 0.95d);
    }

    public boolean isSurfaceWorld()
    {
        return false;
    }

    @Override
    public float calculateCelestialAngle(long p_76563_1_, float p_76563_3_) {
        return 0.0f;
    }

    @Override
    public double getVoidFogYFactor() {
        return 1.0D;
    }

    @Override
    public Vec3 getSkyColor(Entity cameraEntity, float partialTicks) {
        return Vec3.createVectorHelper(1.0d, 1.0d, 1.0d);
    }

    public IChunkProvider createChunkGenerator() {
        return new ChunkProviderRuneEssenceMine(worldObj, worldObj.getSeed());
    }

    @Override
    public String getDimensionName() {
        return "Rune Essence Mine";
    }
}
