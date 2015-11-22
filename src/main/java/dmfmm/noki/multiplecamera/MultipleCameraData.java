package dmfmm.noki.multiplecamera;

import dmfmm.RunicBridges.RunicBridges;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Timer;
import dmfmm.noki.multiplecamera.block.BlockMultipleCamera;
import dmfmm.noki.multiplecamera.item.ItemViewer;
import dmfmm.noki.multiplecamera.tile.TileMultipleCamera;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


/**********
 * @class MultipleCameraData
 *
 * @description
 * @description_en
 */
public class MultipleCameraData {


	
	// timer.
	@SideOnly(Side.CLIENT)
	public static Timer timer;
	
	
	//******************************//
	// define member methods.
	//******************************//
	/*public static void setModData(FMLPreInitializationEvent event) {
		
		tab = new TabMultipleCamera();
		

		
	}*/
	public static Block multipleCamera;
	public static Item multipleViewer;
	public static void registerBlocks() {
		
		multipleCamera = new BlockMultipleCamera("Camera", RunicBridges.tab);
		GameRegistry.registerBlock(multipleCamera, "Camera");
		GameRegistry.registerTileEntity(TileMultipleCamera.class, "Camera");

		
	}
	
	public static void registerItems() {
		
		multipleViewer = new ItemViewer("viewer", RunicBridges.tab);
		GameRegistry.registerItem(multipleViewer, "viewer");
		
	}
	/*
	public static void registerRecipes() {
				
		GameRegistry.addRecipe(new ItemStack(multipleCamera, 1, 0),
				"xxx", "yzy", "xwx", 'x', Blocks.planks, 'y', Items.gold_nugget, 'z', Blocks.glass_pane, 'w', Items.ender_pearl);
		GameRegistry.addRecipe(new ItemStack(multipleViewer, 1, 0),
				" x ", " y ", " z ", 'x', Items.iron_ingot, 'y', Blocks.glass_pane, 'z', Items.ender_pearl);
		
	}*/
	
		
	/*public static class TabMultipleCamera extends CreativeTabs {
		
		//******************************
		// define member variables.
		//******************************
		public static String label = "MultipleCamera";

		
		//******************************
		// define member methods.
		//******************************
		public TabMultipleCamera() {
			
			super(label);
			
		}
		
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			
			return multipleViewer;

		}
		
	}*/

}
