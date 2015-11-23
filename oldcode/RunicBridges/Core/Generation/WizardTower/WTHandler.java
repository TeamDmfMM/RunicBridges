package dmf444.RunicBridges.Core.Generation.WizardTower;

import cpw.mods.fml.common.registry.VillagerRegistry.IVillageCreationHandler;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;

import java.util.List;
import java.util.Random;

/**
 * Created by David on 10/27/2015.
 */
public class WTHandler implements IVillageCreationHandler {


    @Override
    public PieceWeight getVillagePieceWeight(Random random, int i) {
        return new PieceWeight(WizardTower.class, 4, 1);
    }

    @Override
    public Class<?> getComponentClass() {
        return WizardTower.class;
    }

    @Override
    public Object buildComponent(PieceWeight villagePiece, StructureVillagePieces.Start startPiece, List pieces, Random random, int x, int y, int z, int p4, int p5) {
        return WizardTower.buildComponent(startPiece, pieces, random, x, y, z, p4, p5);
    }

}
