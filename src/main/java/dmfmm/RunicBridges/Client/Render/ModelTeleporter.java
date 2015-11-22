package dmfmm.RunicBridges.Client.Render;


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Teleporter - dmf444
 * Created using Tabula 4.1.1
 */
public class ModelTeleporter extends ModelBase {
    public ModelRenderer Block;
    public ModelRenderer shape22;
    public ModelRenderer shape21;
    public ModelRenderer shape23;
    public ModelRenderer shape24;

    public ModelTeleporter() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.shape23 = new ModelRenderer(this, 0, 0);
        this.shape23.setRotationPoint(0.0F, 4.0F, 0.0F);
        this.shape23.addBox(-7.0F, -2.4F, -7.0F, 14, 4, 0, 0.0F);
        this.setRotateAngle(shape23, 0.5009094953223726F, 0.0F, 0.0F);
        this.shape22 = new ModelRenderer(this, 0, 0);
        this.shape22.setRotationPoint(0.0F, 4.0F, 0.0F);
        this.shape22.addBox(-7.0F, -2.4F, -7.0F, 14, 4, 0, 0.0F);
        this.setRotateAngle(shape22, 0.5009094953223726F, 3.141592653589793F, 0.0F);
        this.Block = new ModelRenderer(this, 0, 32);
        this.Block.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.Block.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
        this.shape24 = new ModelRenderer(this, 0, 0);
        this.shape24.setRotationPoint(0.0F, 4.0F, 0.0F);
        this.shape24.addBox(-6.6F, -2.4F, -7.0F, 13, 4, 0, 0.0F);
        this.setRotateAngle(shape24, 0.5009094953223726F, 1.5707963267948966F, 0.0F);
        this.shape21 = new ModelRenderer(this, 0, 0);
        this.shape21.setRotationPoint(0.0F, 4.0F, 0.0F);
        this.shape21.addBox(-6.6F, -2.4F, -7.0F, 13, 4, 0, 0.0F);
        this.setRotateAngle(shape21, 0.5009094953223726F, -1.5707963267948966F, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.shape23.render(f5);
        this.shape22.render(f5);
        this.Block.render(f5);
        this.shape24.render(f5);
        this.shape21.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
