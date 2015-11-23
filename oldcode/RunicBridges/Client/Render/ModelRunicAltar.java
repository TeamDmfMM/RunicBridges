package dmf444.RunicBridges.Client.Render;


import dmf444.RunicBridges.Core.Lib.GuiLib;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

@SideOnly(Side.CLIENT)
public class ModelRunicAltar {

    private IModelCustom modelJM;

    public ModelRunicAltar(){
        modelJM = AdvancedModelLoader.loadModel(GuiLib.ModelRA);
    }

    public void render(){
        modelJM.renderAll();
    }
}