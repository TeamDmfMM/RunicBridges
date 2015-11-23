package dmf444.RunicBridges.Core.utils;

/**
 * Created by dmf444 on 10/5/2015.
 */
public class Pos {

    private static int[] position = new int[3];

    public Pos(int x, int y, int z){
        position[0] = x;
        position[1] = y;
        position[2] = z;
    }

    public static int getX(){
        return position[0];
    }
    public static int getY(){
        return position[1];
    }
    public static int getZ(){
        return position[2];
    }
    public static void setX(int x) { position[0] = x;}
    public static void setY(int y) { position[1] = y;}
    public static void setZ(int z) { position[2] = z;}

    public static void debugCoords(){RBLog.fatal("X:" +position[0] + " Y:" + position[1]+ " Z:"+ position[2]);}
}
