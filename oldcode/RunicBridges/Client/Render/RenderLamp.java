package dmf444.RunicBridges.Client.Render;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import dmf444.RunicBridges.Client.ClientProxy;
import dmf444.RunicBridges.Core.Lib.GuiLib;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

/**
 * Created by David on 11/1/2015.
 */
public class RenderLamp implements ISimpleBlockRenderingHandler {
    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        GL11.glPushMatrix();
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        GL11.glScaled(2,2,2);
        GL11.glTranslated(0D, -1.15D, 0D);
        ModelLamp lamp = new ModelLamp();
        Minecraft.getMinecraft().renderEngine.bindTexture(GuiLib.TextureLamp);
         if (ClientProxy.renderPass == 1) {lamp.renderSolid((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);}
        else {lamp.renderTransparent((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);}
        GL11.glPopMatrix();
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        return false;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    @Override
    public int getRenderId() {
        return ClientProxy.LampRenderer;
    }
}
