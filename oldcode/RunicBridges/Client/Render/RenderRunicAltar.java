package dmf444.RunicBridges.Client.Render;

import dmf444.RunicBridges.Core.Lib.GuiLib;
import dmf444.RunicBridges.Core.blocks.tileentity.TileRunicAltar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by dmf444 on 9/27/2015.
 */
public class RenderRunicAltar extends TileEntitySpecialRenderer {

    private final ModelRunicAltar RAmodel = new ModelRunicAltar();
    private final ModelRunicAltar RAmodel2 = new ModelRunicAltar();

    public RenderRunicAltar(){
    }

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
        if(te instanceof TileRunicAltar) {
            if(((TileRunicAltar)te).shouldDisplay()) {
                GL11.glPushMatrix();
                GL11.glTranslatef((float) x + 0.5F, (float) y, (float) z + 0.5F);
                int i = te.getBlockMetadata();
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

                ResourceLocation textures = (GuiLib.TextureRA);
                Minecraft.getMinecraft().renderEngine.bindTexture(textures);

                    GL11.glPushMatrix();
                        GL11.glScalef(3F, 3F, 3F);
                        GL11.glTranslatef(0.23F, 0F, 0.1F);
                        this.RAmodel.render();
                    GL11.glPopMatrix();

                GL11.glPushMatrix();
                GL11.glEnable(GL11.GL_LIGHTING);
                int j = this.colorize(TileRunicAltar.RuneType.getRune(((TileRunicAltar) te).getRuneType()-1));
                float f1 = (float)(j >> 16 & 255) / 255.0F;
                float f2 = (float)(j >> 8 & 255) / 255.0F;
                float f3 = (float)(j & 255) / 255.0F;
                GL11.glColor4f(f1 * 1.0F, f2 *  1.0F, f3 *  1.0F, 1.0F);
                Minecraft.getMinecraft().renderEngine.bindTexture(GuiLib.TextureRAc);
                GL11.glScalef(3F, 3F, 3F);
                GL11.glTranslatef(0.23F, 0F, 0.1F);
                this.RAmodel2.render();
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glPopMatrix();

                GL11.glPopMatrix();
            }
        }
    }


    private int colorize(TileRunicAltar.RuneType rune){
        switch (rune){
            case FIRE:
                return 11743532; //DONE
            case AIR:
                return 16777200; //DONE
            case MIND:
                return 3887386; //DONE
            case WATER:
                return 2508683; //DONE
            case EARTH:
                return 11229501; //DONE
            case BODY:
                return 12903679; //DONE
            case COSMIC:
                return 5243047;
            default:
                return 197379;
        }
    }

}
