package dmfmm.RunicBridges.Core.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dmfmm.RunicBridges.Core.Lib.ItemLib;
import dmfmm.RunicBridges.Core.Lib.ModInfo;
import dmfmm.RunicBridges.RunicBridges;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

import java.util.List;

/**
 * Created by dmf444 on 10/4/2015.
 */
public class Rune extends Item{

    public static final String[] runeTypes = new String[]{"fire", "air", "mind", "water", "earth", "body", "cosmic", "chaos", "astral", "nature", "law", "death", "blood", "soul"};

    @SideOnly(Side.CLIENT)
    private IIcon[] iconArray;

    public Rune(){
        super();
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setCreativeTab(RunicBridges.tab);
        this.setUnlocalizedName(ItemLib.runes);
    }

    public String getUnlocalizedName(ItemStack stack)
    {
        int i = MathHelper.clamp_int(stack.getItemDamage(), 0, 13);
        return super.getUnlocalizedName() + "_" + runeTypes[i];
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta)
    {
        int j = MathHelper.clamp_int(meta, 0, 13);
        return this.iconArray[j];
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List l)
    {
        for (int i = 0; i < 13; ++i)
        {
            l.add(new ItemStack(item, 1, i));
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister regex)
    {
        this.iconArray = new IIcon[runeTypes.length];

        for (int i = 0; i < runeTypes.length; ++i)
        {
            this.iconArray[i] = regex.registerIcon(ModInfo.MODID + ":"+ItemLib.runes + "_" + runeTypes[i]);
        }
    }


}
