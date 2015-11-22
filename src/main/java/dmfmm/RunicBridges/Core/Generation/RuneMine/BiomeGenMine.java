package dmfmm.RunicBridges.Core.Generation.RuneMine;

import net.minecraft.world.biome.BiomeGenBase;

import java.util.ArrayList;

/**
 * Created by dmf444 on 10/18/2015.
 */
public class BiomeGenMine extends BiomeGenBase {


    public BiomeGenMine(int par1) {
        super(par1);
        this.setBiomeName("RuneEssenceMine");
        this.spawnableMonsterList = new ArrayList<>();
        this.enableSnow = true;
    }
}
