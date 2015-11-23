package dmf444.RunicBridges.Client.Render;


import dmf444.RunicBridges.Core.Lib.GuiLib;
import net.minecraft.client.model.ModelVillager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by David on 10/26/2015.
 */
public class RenderWizard extends RenderLiving {

    protected ModelWizard villagerModel;


    public RenderWizard() {
        super(new ModelWizard(), 0.5F);
        this.villagerModel = (ModelWizard)this.mainModel;
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return GuiLib.TextureWizard;
    }

    protected int shouldRenderPass(EntityVillager entityVillager, int p_77032_2_, float p_77032_3_)
    {
        return -1;
    }
    public void doRender(EntityVillager villager, double x, double y, double z, float p_76986_8_, float p_76986_9_)
    {
        GL11.glEnable(GL11.GL_BLEND);
        super.doRender((EntityLiving)villager, x, y, z, p_76986_8_, p_76986_9_);
        GL11.glDisable(GL11.GL_BLEND);
    }
    protected void renderEquippedItems(EntityVillager p_77029_1_, float p_77029_2_)
    {
        super.renderEquippedItems(p_77029_1_, p_77029_2_);
    }
    protected void preRenderCallback(EntityVillager p_77041_1_, float p_77041_2_)
    {
        float f1 = 0.9375F;

        if (p_77041_1_.getGrowingAge() < 0)
        {
            f1 = (float)((double)f1 * 0.5D);
            this.shadowSize = 0.25F;
        }
        else
        {
            this.shadowSize = 0.5F;
        }

        GL11.glScalef(f1, f1, f1);
    }
    public void doRender(EntityLiving p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.doRender((EntityVillager)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_)
    {
        this.preRenderCallback((EntityVillager)p_77041_1_, p_77041_2_);
    }


    protected int shouldRenderPass(EntityLivingBase entityLivingBase, int par1, float par3)
    {
        return this.shouldRenderPass((EntityVillager)entityLivingBase, par1, par3);
    }

    protected void renderEquippedItems(EntityLivingBase p_77029_1_, float p_77029_2_)
    {
        this.renderEquippedItems((EntityVillager)p_77029_1_, p_77029_2_);
    }

    public void doRender(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.doRender((EntityVillager)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.doRender((EntityVillager)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
}
