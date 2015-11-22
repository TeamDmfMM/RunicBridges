package dmfmm.RunicBridges.Client.Render;

import dmfmm.RunicBridges.Client.ClientProxy;
import dmfmm.RunicBridges.Core.Lib.GuiLib;
import dmfmm.RunicBridges.Core.blocks.tileentity.TileLamp;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by David on 11/1/2015.
 */
public class RenderLampBlock extends TileEntitySpecialRenderer{


    private final ModelLamp lamp = new ModelLamp();

    public RenderLampBlock(){
    }


    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float parttick) {
        if (tile instanceof TileLamp) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float) x + 0.5F, (float) y, (float) z + 0.5F);
            int i = tile.getBlockMetadata();
            short short1 = 0;
            short s2 = 0;

            switch(i){
                case 5://On Floor
                    short1 = 0;
                    s2 = 0;
                    break;
                case 4:
                    short1 = -90;
                    s2 = 0;
                    break;
                case 3:
                    short1 = -90;
                    s2 = 180;
                    break;
                case 2:
                    short1 = -90;
                    s2 = 90;
                    break;
                case 1:
                    short1 = -90;
                    s2 = -90;
                    break;
            }
            if (short1 != 0){
                //ROTATE TO WALL
                GL11.glRotatef(s2, 0.0F, 1.0F, 0.0F);
                GL11.glTranslated(0, 0.5, 0.5);
            }
            GL11.glRotatef((float) short1, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslated(0D, -1.5D, 0D);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_CULL_FACE);
            //GL11.pu;
            ResourceLocation textures = (GuiLib.TextureLamp);
            Minecraft.getMinecraft().renderEngine.bindTexture(textures);
            if (ClientProxy.renderPass == 1) {this.lamp.renderSolid((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);}
            else {this.lamp.renderTransparent((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);}
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_CULL_FACE);
            GL11.glPopMatrix();
        }
    }
}
