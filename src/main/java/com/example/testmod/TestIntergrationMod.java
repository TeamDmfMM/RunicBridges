package com.example.testmod;

//import hardcorequesting.quests.Quest;
import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = TestIntergrationMod.MODID, version = TestIntergrationMod.VERSION)
public class TestIntergrationMod
{
    public static final String MODID = "testmod";
    public static final String VERSION = "1.0";

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code

        System.out.println("DIRT BLOCK >> "+Blocks.dirt.getUnlocalizedName());
    }
}
