package dmfmm.noki.multiplecamera.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import dmfmm.noki.multiplecamera.ModInfo;
import dmfmm.noki.multiplecamera.MultipleCameraCore;



public class ItemViewer extends Item {
	
	//******************************//
	// define member variables.
	//******************************//
	
	
	//******************************//
	// define member methods.
	//******************************//
	public ItemViewer(String unlocalizedName, CreativeTabs tab) {
		
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(tab);
		this.setTextureName(ModInfo.ID.toLowerCase() + ":" + unlocalizedName);
		
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		
/*		if(world.isRemote == true) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiViewer(player));
		}*/
		MultipleCameraCore.proxy.openItemClientGui(player);
		
		return stack;
		
	}

}
