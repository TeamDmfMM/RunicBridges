package dmf444.RunicBridges.Core.Generation.RuneMine;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.WorldEvent;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
public class DimBiomReg {

    public static BiomeGenBase biome;

    public static void registerBiome(){
        biome = new BiomeGenMine(137).setTemperatureRainfall(0.0f, 0.0f);
        BiomeDictionary.registerBiomeType(biome, BiomeDictionary.Type.COLD);
    }
    public static void registerDimension(){
        DimensionManager.registerProviderType(-4412, WorldProviderRuneEssence.class, true);
        DimensionManager.registerDimension(-4412, -4412);
    }


    @SubscribeEvent
    public void makeErSnow(WorldEvent.Load e){
        if(e.world.provider.dimensionId == -4412){
            if(!e.world.isRaining()){
                e.world.setRainStrength(1.0F);
            }
        }
    }

}
