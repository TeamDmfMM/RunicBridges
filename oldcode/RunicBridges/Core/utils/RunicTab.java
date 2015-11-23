package dmf444.RunicBridges.Core.utils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dmf444.RunicBridges.Core.Lib.ModInfo;
import dmf444.RunicBridges.Core.init.ItemLoader;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by David on 2015-11-02.
 */
public class RunicTab extends CreativeTabs {

    public static RunicTab INSTANCE = new RunicTab();
    private ItemStack stack;

    public RunicTab() {
        super(ModInfo.MODID);

    }

    @Override
    public Item getTabIconItem() {
        return ItemLoader.rune;
    }

    @SideOnly(Side.CLIENT)
    public ItemStack getIconItemStack()
    {
        if (this.stack == null)
        {
            this.stack = new ItemStack(this.getTabIconItem(), 1, 6);
        }

        return this.stack;
    }
}
