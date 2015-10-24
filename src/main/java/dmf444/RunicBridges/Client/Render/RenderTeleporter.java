package dmf444.RunicBridges.Client.Render;

import dmf444.RunicBridges.Core.Lib.GuiLib;
import dmf444.RunicBridges.Core.blocks.tileentity.TileRuneTeleportation;
import dmf444.RunicBridges.Core.blocks.tileentity.TileRunicAltar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by David on 10/24/2015.
 */
public class RenderTeleporter extends TileEntitySpecialRenderer{

    ModelTeleporter model = new ModelTeleporter();

    public RenderTeleporter(){

    }


    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float meta) {
        if(tile instanceof TileRuneTeleportation) {
                GL11.glPushMatrix();
                GL11.glTranslatef((float) x + 0.5F, (float) y, (float) z + 0.5F);
                int i = tile.getBlockMetadata();
                short short1 = 0;

                if (i == 2) {
                    short1 = 360;
                }
                if (i == 3) {
                    short1 = 180;
                }

                if (i == 4) {
                    short1 = 90; //-90
                }
                if (i == 5) {
                    short1 = -90; //90
                }
                GL11.glRotatef((float) short1, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
                ResourceLocation textures = (GuiLib.TextureRT);
                Minecraft.getMinecraft().renderEngine.bindTexture(textures);

                GL11.glPushMatrix();
                //GL11.glScalef(3F, 3F, 3F);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_CULL_FACE);
            GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glTranslatef(0F, -1.5F, 0F);
            TileRuneTeleportation tilez = (TileRuneTeleportation) tile;
            this.model.shape21.rotateAngleY += -Math.sin(tilez.scot);
            this.model.shape22.rotateAngleY += -Math.sin(tilez.scot);
            this.model.shape23.rotateAngleY += -Math.sin(tilez.scot);
            this.model.shape24.rotateAngleY += -Math.sin(tilez.scot);

                this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glPopMatrix();


                GL11.glPopMatrix();
        }
    }
}
