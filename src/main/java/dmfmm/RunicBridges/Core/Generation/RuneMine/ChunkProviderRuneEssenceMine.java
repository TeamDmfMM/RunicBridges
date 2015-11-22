package dmfmm.RunicBridges.Core.Generation.RuneMine;

import dmfmm.RunicBridges.Core.init.BlockLoader;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;

import java.util.List;
import java.util.Random;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
public class ChunkProviderRuneEssenceMine implements IChunkProvider{

    public static World worldObj;
    private Random random;


    private int[][] roomPos;
    private int[][] linePos;



    public ChunkProviderRuneEssenceMine(World p, long a){
        worldObj = p;
        random = new Random(a);

        roomPos = new int[][] {
                new int[] {45, -65, 80, -29},
                new int[] {45, 45, 80, 80},
                new int[] {-65, 45, -29, 80},
                new int[] {-65, -65, -29, -29}
        };
        linePos = new int[][] {
                new int[] {26, 27, 45, 45},
                new int[] {-8, 27, -28, 45},
                new int[] {-9, -10, -28, -28},
                new int[] {-26, -10, 45, -28}
        };
    }

    private int toC(int v){
        if (v < 0){
            return 15 - (Math.abs(v + 1) % 16);
        }
        else {
            return Math.abs(v) % 16;
        }
    }

    private void makeWall(int x, int z, Chunk c, Block b){
        int l;
        for (int i = 9; i < 31; i++){
            l = i >> 4;
            ExtendedBlockStorage e = c.getBlockStorageArray()[l];

            if (e == null)
            {
                e = new ExtendedBlockStorage(i, !this.worldObj.provider.hasNoSky);
                c.getBlockStorageArray()[l] = e;
            }
            e.func_150818_a(x, i & 15, z, b);
            e.setExtBlockMetadata(x, i & 15, z, 0);
        }
    }

    @Override
    public boolean chunkExists(int p_73149_1_, int p_73149_2_) {
        return true;
    }

    public void makeWall(int x, int z, Chunk c){
        makeWall(toC(x), toC(z), c, Blocks.stone);
    }

    public boolean insideChunk(int cx, int cy, int px, int pz){
        int c = px >> 4;
        int abc = pz >> 4;
        if (c == cx && abc == cy){
            return true;
        }
        return false;
    }

    private void drawCorridor(boolean isX, int startX, int endX, int startY, int endY, Chunk c, int a, int b){
        if (startX > endX){
            int v = endX;
            endX = startX;
            startX = v;
        }
        if (startY > endY){
            int v = endY;
            endY = startY;
            startY = v;
        }
        if (isX){

            for (int x = startX; x <= endX; x ++){
                for (int y = startY; y <= endY; y ++){
                    if (x == startX || x == endX){
                        if (insideChunk(a, b, x, y)){
                            makeWall(toC(x), toC(y), c);
                        }
                    }
                    else {
                        if (insideChunk(a, b, x, y)){
                            makeWall(toC(x), toC(y), c, Blocks.air);
                        }
                    }
                }
            }
        }
        else {
            for (int x = startX; x <= endX; x ++){
                for (int y = startY; y <= endY; y ++){
                    if (y == startY || y == endY){
                        if (insideChunk(a, b, x, y)){
                            makeWall(toC(x), toC(y), c);
                        }
                    }
                    else {
                        if (insideChunk(a, b, x, y)){
                            makeWall(toC(x), toC(y), c, Blocks.air);
                        }
                    }
                }
            }
        }
    }

    @Override
    public Chunk provideChunk(int p_73154_1_, int p_73154_2_) {
        Chunk chunk = new Chunk(this.worldObj, p_73154_1_, p_73154_2_);

        int l;
        if (p_73154_1_ >= -4 && p_73154_1_ <= 4 && p_73154_2_ >= -4 && p_73154_2_ <= 4  ) {



            for (int k = 0; k < 32; ++k) {
                Block block;
                if (k == 0){
                    block = Blocks.bedrock;
                }
                else if (k <= 8 && k != 0) {
                    block = Blocks.snow;
                }
                else if (k > 8 && k < 31){
                    block = Blocks.air;
                }
                else {
                    block = BlockLoader.invisibla;
                }
                l = k >> 4;
                ExtendedBlockStorage extendedblockstorage = chunk.getBlockStorageArray()[l];

                if (extendedblockstorage == null)
                {
                    extendedblockstorage = new ExtendedBlockStorage(k, !this.worldObj.provider.hasNoSky);
                    chunk.getBlockStorageArray()[l] = extendedblockstorage;
                }

                for (int i1 = 0; i1 < 16; ++i1)
                {
                    for (int j1 = 0; j1 < 16; ++j1)
                    {
                        if (p_73154_1_ == 0 && p_73154_2_ == 0 && i1 == 3 && j1 == 3 && k == 8){
                            //block = (Block) BlockLoader.runeTeleporter;
                        }

                        extendedblockstorage.func_150818_a(i1, k & 15, j1, block);
                        extendedblockstorage.setExtBlockMetadata(i1, k & 15, j1, 0);
                    }
                }
                genFeaturesToChunk(chunk, p_73154_1_, p_73154_2_);
            }


        }
        else if (p_73154_1_ >= -5 && p_73154_1_ <= 5 && p_73154_2_ >= -5 && p_73154_2_ <= 5  ) {
            for (int k = 0; k < 32; ++k) {
                Block block  = k == 0 ? Blocks.bedrock : Blocks.stone;

                l = k >> 4;
                ExtendedBlockStorage extendedblockstorage = chunk.getBlockStorageArray()[l];

                if (extendedblockstorage == null)
                {
                    extendedblockstorage = new ExtendedBlockStorage(k, !this.worldObj.provider.hasNoSky);
                    chunk.getBlockStorageArray()[l] = extendedblockstorage;
                }

                for (int i1 = 0; i1 < 16; ++i1)
                {
                    for (int j1 = 0; j1 < 16; ++j1)
                    {
                        extendedblockstorage.func_150818_a(i1, k & 15, j1, block);
                        extendedblockstorage.setExtBlockMetadata(i1, k & 15, j1, 0);
                    }
                }

            }
        }
        else if (p_73154_1_ >= -6 && p_73154_1_ <= 6 && p_73154_2_ >= -6 && p_73154_2_ <= 6  ) {
            for (int k = 0; k < 32; ++k) {
                Block block  = Blocks.bedrock;

                l = k >> 4;
                ExtendedBlockStorage extendedblockstorage = chunk.getBlockStorageArray()[l];

                if (extendedblockstorage == null)
                {
                    extendedblockstorage = new ExtendedBlockStorage(k, !this.worldObj.provider.hasNoSky);
                    chunk.getBlockStorageArray()[l] = extendedblockstorage;
                }

                for (int i1 = 0; i1 < 16; ++i1)
                {
                    for (int j1 = 0; j1 < 16; ++j1)
                    {
                        extendedblockstorage.func_150818_a(i1, k & 15, j1, block);
                        extendedblockstorage.setExtBlockMetadata(i1, k & 15, j1, 0);
                    }
                }

            }
        }
        else {

        }
        chunk.generateSkylightMap();
        return chunk;
    }

    @Override
    public Chunk loadChunk(int p_73158_1_, int p_73158_2_) {
        return provideChunk(p_73158_1_, p_73158_2_);
    }

    @Override
    public void populate(IChunkProvider provider, int x, int z) {
    }

    private void setBlockInChunk(Chunk chunk, int x, int y, int z, Block b){
        setBlockInChunk(chunk, x, y, z, b, 0);
    }
    private void setBlockInChunk(Chunk chunk, int x, int y, int z, Block b, int meta){
        x = toC(x);
        z = toC(z);
        int l;
        l = y >> 4;
        ExtendedBlockStorage e = chunk.getBlockStorageArray()[l];

        if (e == null)
        {
            e = new ExtendedBlockStorage(y, !this.worldObj.provider.hasNoSky);
            chunk.getBlockStorageArray()[l] = e;
        }
        e.func_150818_a(x, y & 15, z, b);
        e.setExtBlockMetadata(x, y & 15, z, meta);
    }

    public void sqaureAround(Chunk c, int a, int b, int x, int y){
        for (int x1 = x-3; x1 < x+3; x1++){
            for (int y1 = y-3; y1 < y+3; y1++){
                if (insideChunk(a, b, x, y)){
                    makeWall(toC(x1), toC(y1), c);
                }
            }
        }
        for (int x1 = x-2; x1 < x+2; x1++){
            for (int y1 = y-2; y1 < y+2; y1++){
                if (insideChunk(a, b, x, y)){
                    makeWall(toC(x1), toC(y1), c, Blocks.air);
                }
            }
        }
    }

    public void line(int x,int y,int x2, int y2, Chunk c, int a, int b) {
        int w = x2 - x ;
        int h = y2 - y ;
        int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0 ;
        if (w<0) dx1 = -1 ; else if (w>0) dx1 = 1 ;
        if (h<0) dy1 = -1 ; else if (h>0) dy1 = 1 ;
        if (w<0) dx2 = -1 ; else if (w>0) dx2 = 1 ;
        int longest = Math.abs(w) ;
        int shortest = Math.abs(h) ;
        if (!(longest>shortest)) {
            longest = Math.abs(h) ;
            shortest = Math.abs(w) ;
            if (h<0) dy2 = -1 ; else if (h>0) dy2 = 1 ;
            dx2 = 0 ;
        }
        int numerator = longest >> 1 ;
        for (int i=0;i<=longest;i++) {
            sqaureAround(c, a, b, x, y);
            numerator += shortest ;
            if (!(numerator<longest)) {
                numerator -= longest ;
                x += dx1 ;
                y += dy1 ;
            } else {
                x += dx2 ;
                y += dy2 ;
            }
        }
    }

    private void genCircle(int ox, int oy, int radius, Chunk c, int a, int b, int oz){
        for (int y=-radius; y<=radius; y++){
            for(int x=-radius; x<=radius; x++) {
                if (x * x + y * y <= radius * radius){
                    if (insideChunk(a, b, x + ox, y + oy)){
                        setBlockInChunk(c, toC(x +ox), oz, toC(y +oy), BlockLoader.runeBlock);
                    }
                }
            }
        }
    }
    private void genRailSQ(int ox, int oy, Chunk c, int a, int b, int oz){
        int radius = 9;
        //SIDE 1
        if(insideChunk(c.xPosition, c.zPosition, ox+radius, oy-2)){
            for(int i=0; i<8; i++){
                setBlockInChunk(c, toC(ox+radius), oz, toC(oy-1-i), Blocks.rail, 0);
                //setBlockInChunk(c, toC(ox+radius), oz, toC(oy+i), Blocks.rail, 0);
            }
            setBlockInChunk(c, toC(ox+radius), oz, toC(oy-1-8), Blocks.rail, 7);
            for(int d=0; d<10;d++){
                setBlockInChunk(c, toC(ox-radius-8+d), oz, toC(oy+7), Blocks.rail, 1);
            }
        }

        if(insideChunk(c.xPosition, c.zPosition, ox+radius, oy)){
            for(int i=0; i<10; i++){
                setBlockInChunk(c, toC(ox+radius), oz, toC(oy-1+i), Blocks.rail, 0);
                //setBlockInChunk(c, toC(ox+radius), oz, toC(oy+i), Blocks.rail, 0);
            }
            setBlockInChunk(c, toC(ox+radius), oz, toC(oy+9), Blocks.rail, 8);
            for(int d=0; d<10;d++){
                setBlockInChunk(c, toC(ox-radius-8+d), oz, toC(oy+9), Blocks.rail, 1);
            }
        }

        //SIDE 2
        if(insideChunk(c.xPosition, c.zPosition, ox-radius, oy-2)){
            for(int i=0; i<8; i++){
                setBlockInChunk(c, toC(ox-radius), oz, toC(oy-1-i), Blocks.rail, 0);
                //setBlockInChunk(c, toC(ox+radius), oz, toC(oy+i), Blocks.rail, 0);
            }
            setBlockInChunk(c, toC(ox-radius), oz, toC(oy-1-8), Blocks.rail, 6);
            for(int d=0; d<7;d++){
                setBlockInChunk(c, toC(ox-radius+1+d), oz, toC(oy+7), Blocks.rail, 1);
            }
        }

        if(insideChunk(c.xPosition, c.zPosition, ox-radius, oy)){
            for(int i=0; i<10; i++){
                setBlockInChunk(c, toC(ox-radius), oz, toC(oy-1+i), Blocks.rail, 0);
                //setBlockInChunk(c, toC(ox+radius), oz, toC(oy+i), Blocks.rail, 0);
            }
            setBlockInChunk(c, toC(ox-radius), oz, toC(oy+9), Blocks.rail, 9);
            for(int d=0; d<7;d++){
                setBlockInChunk(c, toC(ox-radius+1+d), oz, toC(oy+9), Blocks.rail, 1);
            }
        }




    }
    private void genMound(Chunk c, int a, int b, int x, int z, int basesize, int maxheight){
        int maxup = Math.min(maxheight / 4, 9);
        int up = 0;
        int size = basesize;
        int height = 8;
        while (height < maxheight) {
            up += 1;
            height += 1;
            if (up == maxup) {
                up = 0;
                maxup -= 1;
                size -= 1;
                if (size < 2) {
                    size = 2;
                }
                if (maxup < 2) {
                    maxup = 2;
                }
            }
            genCircle(x, z, size, c, a, b, height);
            //genRailSQ(x, z, c, a, b, height);
        }

    }



    private void genFeaturesToChunk(Chunk chunk, int p_73154_1_, int p_73154_2_){



                  /*  for (int i = -64; i < -62; i++){
                        for (int j = -64; j < -62; j++) {
                            if (insideChunk(p_73154_1_, p_73154_2_, i, j)){
                                this.setBlockInChunk(chunk, toC(i), 8, toC(j), BlockLoader.runeTeleporter);
                            }
                        }
                    }
        for (int i = -64; i < -62; i++){
            for (int j = 78; j < 80; j++) {
                if (insideChunk(p_73154_1_, p_73154_2_, i, j)){
                    this.setBlockInChunk(chunk, toC(i), 8, toC(j), BlockLoader.runeTeleporter);
                }
            }
        }
        for (int i = 78; i < 80; i++){
            for (int j = -64; j < -62; j++) {
                if (insideChunk(p_73154_1_, p_73154_2_, i, j)){
                    this.setBlockInChunk(chunk, toC(i), 8, toC(j), BlockLoader.runeTeleporter);
                }
            }
        }
        for (int i = 78; i < 80; i++){
            for (int j = 78; j < 80; j++) {
                if (insideChunk(p_73154_1_, p_73154_2_, i, j)){
                    this.setBlockInChunk(chunk, toC(i), 8, toC(j), BlockLoader.runeTeleporter);
                }
            }
        } // 80, -64*/
        if (insideChunk(p_73154_1_, p_73154_2_, -64, -64)){
            this.setBlockInChunk(chunk, toC(-64), 8, toC(-64), BlockLoader.runeTeleporter);
        }
        if (insideChunk(p_73154_1_, p_73154_2_, -64, 79)){
            this.setBlockInChunk(chunk, toC(-64), 8, toC(79), BlockLoader.runeTeleporter);
        }
        if (insideChunk(p_73154_1_, p_73154_2_, 79, 79)){
            this.setBlockInChunk(chunk, toC(79), 8, toC(79), BlockLoader.runeTeleporter);
        }
        if (insideChunk(p_73154_1_, p_73154_2_, 79, -64)){
            this.setBlockInChunk(chunk, toC(79), 8, toC(-64), BlockLoader.runeTeleporter);
        }



        for (int roomID = 0; roomID < 4; roomID ++){
            int[] room = this.roomPos[roomID];
            for (int xCoord = this.roomPos[roomID][0]; xCoord <= this.roomPos[roomID][2]; xCoord ++){
                for (int yCoord = this.roomPos[roomID][1]; yCoord <= this.roomPos[roomID][3]; yCoord ++){
                    if (xCoord == room[0] || xCoord == room[2] || yCoord == room[1] || yCoord == room[3]){
                        if (insideChunk(p_73154_1_, p_73154_2_, xCoord, yCoord)){
                            this.makeWall(toC(xCoord), toC(yCoord), chunk);
                        }
                    }
                }
            }
        }
         // inner room

            for (int wall1x = -9; wall1x < 27; wall1x++) {
                for (int wall1y = -19+8; wall1y < 20+8; wall1y++) {
                    if (insideChunk(p_73154_1_, p_73154_2_, wall1x, wall1y)) {
                        this.makeWall(toC(wall1x), toC(wall1y), chunk);
                    }
                }
            }
            for (int wall1x = -16+8; wall1x < 18+8; wall1x++) {
                for (int wall1y = -18+8; wall1y < 19+8; wall1y++) {
                    if (insideChunk(p_73154_1_, p_73154_2_, wall1x, wall1y)) {
                        this.makeWall(toC(wall1x), toC(wall1y), chunk, Blocks.air);
                    }
                }
            }


         // outer rooms
        drawCorridor(true, -9, 1, -11, -64, chunk, p_73154_1_, p_73154_2_);
        drawCorridor(false, 1, 45, -65, -58, chunk, p_73154_1_, p_73154_2_);
        drawCorridor(false, 26, 80, -11, 1, chunk, p_73154_1_, p_73154_2_);
        drawCorridor(true, 80, 71, 1, 45, chunk, p_73154_1_, p_73154_2_);
        drawCorridor(true, 26, 14, 27, 80, chunk, p_73154_1_, p_73154_2_);
        drawCorridor(false, 14, -29, 80, 71, chunk, p_73154_1_, p_73154_2_);
        drawCorridor(false, -9, -64, 27, 19, chunk, p_73154_1_, p_73154_2_);
        drawCorridor(true, -65, -58, 19, -29, chunk, p_73154_1_, p_73154_2_);

        genMound(chunk, p_73154_1_, p_73154_2_, -47, -47, 7, 31);
        genRails(chunk, p_73154_1_, p_73154_2_, -47, -47);
       // GenRails.genRailSQ1(-47, -47, chunk, p_73154_1_, p_73154_2_, 9);
        genMound(chunk, p_73154_1_, p_73154_2_, 63, 63, 7, 31);
       genRails(chunk, p_73154_1_, p_73154_2_, 63, 63);
        genMound(chunk, p_73154_1_, p_73154_2_, 63, -47, 7, 31);
        genRails(chunk, p_73154_1_, p_73154_2_, 63, -47);
        genMound(chunk, p_73154_1_, p_73154_2_, -47, 63, 7, 31);
        genRails(chunk, p_73154_1_, p_73154_2_, -47, 63);
    }


    // generates wall things. use x


    public void genRails(Chunk c, int a, int b, int x, int y){

        // +x east
        // -x west
        // +z south
        // -z north
        int X;
        int Z;
        for (X = x-8; X < x+9; X++){
            Z = y-9;

            if (insideChunk(a, b, X, Z)){
                setBlockInChunk(c, X, 9, Z, Blocks.rail, 1);
            }

            Z = y+9;

            if (insideChunk(a, b, X, Z)){
                setBlockInChunk(c, X, 9, Z, Blocks.rail, 1);
            }
        }
        X = x - 9;
        Z = y - 9;
        if (insideChunk(a, b, X, Z)){
            setBlockInChunk(c, X, 9, Z, Blocks.rail, 6);
        }
        X = x + 9;
        if (insideChunk(a, b, X, Z)){
            setBlockInChunk(c, X, 9, Z, Blocks.rail, 7);
        }
        for (Z = y-8; Z < y+9; Z++){
            X = x-9;

            if (insideChunk(a, b, X, Z)){
                setBlockInChunk(c, X, 9, Z, Blocks.rail, 0);
            }

            X = x+9;

            if (insideChunk(a, b, X, Z)){
                setBlockInChunk(c, X, 9, Z, Blocks.rail, 0);
            }
        }
        X = x - 9;
        Z = y + 9;
        if (insideChunk(a, b, X, Z)){
            setBlockInChunk(c, X, 9, Z, Blocks.rail, 9);
        }
        X = x + 9;
        if (insideChunk(a, b, X, Z)){
            setBlockInChunk(c, X, 9, Z, Blocks.rail, 8);
        }
    }

    @Override
    public boolean saveChunks(boolean p_73151_1_, IProgressUpdate p_73151_2_) {
        return true;
    }

    @Override
    public boolean unloadQueuedChunks() {
        return false;
    }

    @Override
    public boolean canSave() {
        return true;
    }

    @Override
    public String makeString() {
        return "RuneEssecenceMineLevelSource";
    }

    @Override
    public List getPossibleCreatures(EnumCreatureType p_73155_1_, int p_73155_2_, int p_73155_3_, int p_73155_4_) {
        BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(p_73155_2_, p_73155_4_);
        return biomegenbase.getSpawnableList(p_73155_1_);
    }

    @Override
    public ChunkPosition func_147416_a(World p_147416_1_, String p_147416_2_, int p_147416_3_, int p_147416_4_, int p_147416_5_) {
        return null;
    }

    @Override
    public int getLoadedChunkCount() {
        return 0;
    }

    @Override
    public void recreateStructures(int p_82695_1_, int p_82695_2_) {

    }

    @Override
    public void saveExtraData() {

    }
}
