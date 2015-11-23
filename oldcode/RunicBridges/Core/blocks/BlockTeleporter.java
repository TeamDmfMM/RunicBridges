package dmf444.RunicBridges.Core.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
public class BlockTeleporter extends Block {
    public BlockTeleporter(Material material) {
        super(material);
    }

    public static enum TeleColor {
        BLUE(1),
        RED(2),
        GREEN(3),
        PURPLE(4),
        BROWN(5),
        WHITE(6),
        YELLOW(7),
        GRAY(8);

        private final int num;
        TeleColor(int num){
            this.num = num;
        }

        public int toInt(){
            return this.num;
        }

        public static TeleColor fromInt(int x){
            for (TeleColor possible : this.getDeclaringClass().getEnumConstants()){
                if (possible.toInt() == x){
                    return possible;
                }
            }
        }

    }

    public static class TeleColorSet {

    }


}
