package dmfmm.RunicBridges.Client.Render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

/**
 * Hat - dmf444
 * Created using Tabula 4.1.1
 */
public class ModelWizard extends ModelBase {
    public ModelRenderer leftVillagerLeg;
    public ModelRenderer villagerBody0;
    public ModelRenderer villagerBody1;
    public ModelRenderer villagerArms0;
    public ModelRenderer villagerArms1;
    public ModelRenderer rightVillagerLeg;
    public ModelRenderer villagerHead;
    public ModelRenderer villagerHeadChild;
    public ModelRenderer villagerArms2;
    public ModelRenderer Piece3;
    public ModelRenderer Base;
    public ModelRenderer Buckle;
    public ModelRenderer Piece1;
    public ModelRenderer Piece2;
    public ModelRenderer Piece5;
    public ModelRenderer Piece4;
    public ModelRenderer monacleStem;
    public ModelRenderer Monacle;

    public ModelWizard() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.villagerBody1 = new ModelRenderer(this, 64, 38);
        this.villagerBody1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.villagerBody1.addBox(-4.0F, 0.0F, -3.0F, 8, 18, 6, 0.5F);
        this.villagerHeadChild = new ModelRenderer(this, 88, 0);
        this.villagerHeadChild.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.villagerHeadChild.addBox(-1.0F, -1.0F, -6.0F, 2, 4, 2, 0.0F);
        this.Buckle = new ModelRenderer(this, 32, 8);
        this.Buckle.setRotationPoint(0.0F, -12.900000000000029F, 0.0F);
        this.Buckle.addBox(-4.0F, 0.0F, -4.0F, 8, 3, 8, 0.0F);
        this.Piece3 = new ModelRenderer(this, 48, 0);
        this.Piece3.setRotationPoint(0.0F, -17.1F, 2.0F);
        this.Piece3.addBox(-2.0F, 0.0F, -2.0F, 4, 2, 4, 0.0F);
        this.setRotateAngle(Piece3, -0.7740535232594852F, 0.0F, 0.0F);
        this.Base = new ModelRenderer(this, 0, 19);
        this.Base.setRotationPoint(0.0F, -9.9F, 0.0F);
        this.Base.addBox(-6.0F, 0.0F, -6.0F, 12, 1, 12, 0.0F);
        this.villagerHead = new ModelRenderer(this, 64, 0);
        this.villagerHead.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.villagerHead.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.0F);
        this.leftVillagerLeg = new ModelRenderer(this, 64, 22);
        this.leftVillagerLeg.mirror = true;
        this.leftVillagerLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.leftVillagerLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.rightVillagerLeg = new ModelRenderer(this, 64, 22);
        this.rightVillagerLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.rightVillagerLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.Piece4 = new ModelRenderer(this, 16, 0);
        this.Piece4.setRotationPoint(0.0F, -18.1F, 4.0F);
        this.Piece4.addBox(-1.5F, 0.0F, -1.5F, 3, 3, 3, 0.0F);
        this.setRotateAngle(Piece4, -1.1383037381507017F, 0.0F, 0.0F);
        this.villagerArms2 = new ModelRenderer(this, 104, 38);
        this.villagerArms2.setRotationPoint(0.0F, 3.0F, -1.0F);
        this.villagerArms2.addBox(-4.0F, 2.0F, -2.0F, 8, 4, 4, 0.0F);
        this.setRotateAngle(villagerArms2, -0.7499679795819634F, 0.0F, 0.0F);
        this.villagerArms1 = new ModelRenderer(this, 108, 22);
        this.villagerArms1.setRotationPoint(0.0F, 3.0F, -1.0F);
        this.villagerArms1.addBox(4.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F);
        this.setRotateAngle(villagerArms1, -0.7499679795819634F, 0.0F, 0.0F);
        this.Piece5 = new ModelRenderer(this, 0, 0);
        this.Piece5.setRotationPoint(0.0F, -17.6F, 5.5F);
        this.Piece5.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(Piece5, -1.730144887501979F, 0.0F, 0.0F);
        this.Piece2 = new ModelRenderer(this, 19, 7);
        this.Piece2.setRotationPoint(0.0F, -16.00000000000001F, 0.9F);
        this.Piece2.addBox(-2.5F, 0.0F, -2.5F, 5, 2, 5, 0.0F);
        this.setRotateAngle(Piece2, -0.5462880558742251F, 0.0F, 0.0F);
        this.villagerBody0 = new ModelRenderer(this, 80, 20);
        this.villagerBody0.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.villagerBody0.addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6, 0.0F);
        this.villagerArms0 = new ModelRenderer(this, 108, 22);
        this.villagerArms0.setRotationPoint(0.0F, 3.0F, -1.0F);
        this.villagerArms0.addBox(-8.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F);
        this.setRotateAngle(villagerArms0, -0.7499679795819634F, 0.0F, 0.0F);
        this.monacleStem = new ModelRenderer(this, 40, 0);
        this.monacleStem.setRotationPoint(-1.6F, -8.0F, 0.0F);
        this.monacleStem.addBox(-1.0F, -1.0F, -6.0F, 1, 3, 1, 0.0F);
        this.Piece1 = new ModelRenderer(this, 0, 9);
        this.Piece1.setRotationPoint(0.0F, -15.100000000000017F, 0.2F);
        this.Piece1.addBox(-3.0F, 0.0F, -3.0F, 6, 3, 6, 0.0F);
        this.setRotateAngle(Piece1, -0.22759093446006054F, 0.0F, 0.0F);
        this.Monacle = new ModelRenderer(this, 30, 0);
        this.Monacle.setRotationPoint(-2.7F, -5.0F, 0.0F);
        this.Monacle.addBox(-1.0F, -1.0F, -6.0F, 3, 3, 1, 0.0F);
        this.villagerHead.addChild(this.villagerHeadChild);
        this.villagerHead.addChild(this.Buckle);
        this.villagerHead.addChild(this.Piece3);
        this.villagerHead.addChild(this.Base);
        this.villagerHead.addChild(this.Piece4);
        //this.villagerHead.addChild(this.villagerArms2);
        this.villagerHead.addChild(this.Piece5);
        this.villagerHead.addChild(this.Piece2);
        this.villagerHead.addChild(this.monacleStem);
        this.villagerHead.addChild(this.Piece1);
        this.villagerHead.addChild(this.Monacle);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.villagerBody1.render(f5);
        this.villagerHead.render(f5);
        this.leftVillagerLeg.render(f5);
        this.rightVillagerLeg.render(f5);
        this.villagerArms1.render(f5);
        this.villagerBody0.render(f5);
        this.villagerArms2.render(f5);
        this.villagerArms0.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
    {
        this.villagerHead.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
        this.villagerHead.rotateAngleX = p_78087_5_ / (180F / (float)Math.PI);
        this.villagerArms2.rotationPointY = 3.0F;
        this.villagerArms2.rotationPointZ = -1.0F;
        this.villagerArms2.rotateAngleX = -0.75F;
        this.rightVillagerLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_ * 0.5F;
        this.leftVillagerLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 1.4F * p_78087_2_ * 0.5F;
        this.rightVillagerLeg.rotateAngleY = 0.0F;
        this.leftVillagerLeg.rotateAngleY = 0.0F;
    }
}
