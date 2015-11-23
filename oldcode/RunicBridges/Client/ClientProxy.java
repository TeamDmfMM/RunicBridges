package dmf444.RunicBridges.Client;


import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import dmf444.RunicBridges.Client.Render.*;
import dmf444.RunicBridges.Client.events.TickEventClient;
import dmf444.RunicBridges.Core.CommonProxy;
import dmf444.RunicBridges.Core.Generation.WizardTower.EntityWizard;
import dmf444.RunicBridges.Core.blocks.tileentity.TileLamp;
import dmf444.RunicBridges.Core.blocks.tileentity.TileRuneTeleportation;
import dmf444.RunicBridges.Core.blocks.tileentity.TileRunicAltar;

public class ClientProxy extends CommonProxy {

    public static int renderPass;
    public static int LampRenderer = RenderingRegistry.getNextAvailableRenderId();

    public void registerRenderers() {
        FMLCommonHandler.instance().bus().register(new TickEventClient());
        ClientRegistry.bindTileEntitySpecialRenderer(TileRunicAltar.class, new RenderRunicAltar());
        ClientRegistry.bindTileEntitySpecialRenderer(TileRuneTeleportation.class, new RenderTeleporter());
        ClientRegistry.bindTileEntitySpecialRenderer(TileLamp.class, new RenderLampBlock());
        RenderingRegistry.registerEntityRenderingHandler(EntityWizard.class, new RenderWizard());


        RenderingRegistry.registerBlockHandler(LampRenderer, new RenderLamp());
    }


}
