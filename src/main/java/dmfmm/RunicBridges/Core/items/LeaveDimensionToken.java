package dmfmm.RunicBridges.Core.items;

import dmfmm.RunicBridges.Core.Lib.ItemLib;
import dmfmm.RunicBridges.Core.Lib.ModInfo;
import dmfmm.RunicBridges.Core.init.ItemLoader;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
public class LeaveDimensionToken extends Item{
    public LeaveDimensionToken(){
        super();
        this.setUnlocalizedName(ItemLib.token);
        this.setTextureName(ModInfo.MODID +":"+ItemLib.token);

    }

    public static ItemStack createLeaveToken(EntityPlayer player){
        ItemStack t = new ItemStack(ItemLoader.leaveToken);
        NBTTagCompound a = new NBTTagCompound();
        a.setInteger("X", (int)player.posX);
        a.setInteger("Y", (int)player.posY+2);
        a.setInteger("Z", (int)player.posZ);
        t.setTagCompound(a);
        return t;
    }
}
