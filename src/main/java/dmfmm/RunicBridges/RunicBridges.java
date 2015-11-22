package dmfmm.RunicBridges;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import dmfmm.RunicBridges.Core.CommonProxy;
import dmfmm.RunicBridges.Core.Generation.RuneMine.DimBiomReg;
import dmfmm.RunicBridges.Core.Generation.RunicGenerationManager;
import dmfmm.RunicBridges.Core.Generation.WizardTower.WizardInit;
import dmfmm.RunicBridges.Core.Lib.ModInfo;
import dmfmm.RunicBridges.Core.events.BlockBrokenRuneEssence;
import dmfmm.RunicBridges.Core.init.BlockLoader;
import dmfmm.RunicBridges.Core.init.ItemLoader;
import dmfmm.RunicBridges.Core.network.PacketManager;
import dmfmm.RunicBridges.Core.utils.RunicTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = ModInfo.MODID, name=ModInfo.Mname, version = ModInfo.VERSION)
public class RunicBridges
{
    @Mod.Instance(value = "RunicBridges")
    public static RunicBridges instance;

    @SidedProxy(clientSide= ModInfo.Clientproxy, serverSide= ModInfo.Serverproxy)
    public static CommonProxy proxy;



    public static final CreativeTabs tab = RunicTab.INSTANCE;

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event)
    {
        BlockLoader.initiateBlocks();
        ItemLoader.initiateItems();
        DimBiomReg.registerBiome();
        DimBiomReg.registerDimension();

        PacketManager.init();
        GameRegistry.registerWorldGenerator(new RunicGenerationManager(), 2);
        MinecraftForge.EVENT_BUS.register(new BlockBrokenRuneEssence());
        MinecraftForge.EVENT_BUS.register(new DimBiomReg());

    }


    @Mod.EventHandler
    public void load(FMLInitializationEvent event){
        BlockLoader.initTileEntity();
        WizardInit.init();

        proxy.registerRenderers();
    }

}
