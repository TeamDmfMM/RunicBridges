package dmfmm.noki.multiplecamera.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import dmfmm.noki.multiplecamera.ModInfo;
import dmfmm.noki.multiplecamera.MultipleCameraCore;
import dmfmm.noki.multiplecamera.camera.CameraManagerServer;
import dmfmm.noki.multiplecamera.tile.TileMultipleCamera;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


/**********
 * @class BlockMultipleCamera
 *
 * @description
 * @description_en
 * 
 * @see
 */
public class BlockMultipleCamera extends BlockContainer {
	
	//******************************//
	// define member variables.
	//******************************//
	private IIcon icon;
	private IIcon iconFront;
	private IIcon iconFrontOff;
	
	
	//******************************//
	// define member methods.
	//******************************//
	public BlockMultipleCamera(String unlocalizedName, CreativeTabs tab) {
		
		super(Material.glass);
		this.setBlockName(unlocalizedName);
		this.setHardness(0.3F);
		this.setStepSound(soundTypeGlass);
		this.setCreativeTab(tab);
		this.setBlockTextureName(ModInfo.ID.toLowerCase() + ":" + unlocalizedName);

	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		
		MultipleCameraCore.log("on createNewTileEntity");
			return new TileMultipleCamera();
		
	}
	
	@Override
	public boolean isOpaqueCube() {
		
		return false;
		
	}
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		
		super.onBlockAdded(world, x, y, z);
		MultipleCameraCore.log("onBlockAdded");
		if(world.isRemote) {
			return;
		}
		TileEntity tile = world.getTileEntity(x, y, z);
		if(tile == null || !(tile instanceof TileMultipleCamera)) {
			MultipleCameraCore.log("tile is null");
			return;
		}
		((TileMultipleCamera)tile).setYaw(0F);
		((TileMultipleCamera)tile).setPitch(0F);
		((TileMultipleCamera)tile).createCamera();
		
	}

	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		
		super.onBlockPlacedBy(world, x, y, z, entity, stack);
		MultipleCameraCore.log("onBlockPlacedBy");
		
		// for textures.
		int dir = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
		world.setBlockMetadataWithNotify(x, y, z, dir, 3);
		
		if(world.isRemote) {
			return;
		}
        
		// for tile.
		TileEntity tile = world.getTileEntity(x, y, z);
		if(tile == null || !(tile instanceof TileMultipleCamera)) {
			return;
		}
		((TileMultipleCamera)tile).resetCameraInfo();
		
		if(!(entity instanceof EntityPlayer)) {
			return;
		}
		((TileMultipleCamera)tile).setUuid(((EntityPlayer)entity).getPersistentID());;
//		world.markBlockForUpdate(x, y, z);
		
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata,
			float f1, float f2, float f3) {
		
		if(world.isRemote) {
			return true;
		}
		
		TileEntity tile = world.getTileEntity(x, y, z);
		if(tile == null || !(tile instanceof TileMultipleCamera)) {
			return true;
		}
		if(((TileMultipleCamera)tile).isOwner(player.getPersistentID()) == false) {
			return true;
		}
		((TileMultipleCamera)tile).switchActivated();
		
		return true;
		
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int metadata) {
		
		TileEntity tile = world.getTileEntity(x, y, z);
		if(tile != null && tile instanceof TileMultipleCamera) {
			CameraManagerServer.removeCamera(((TileMultipleCamera)tile).getCameraId());
		}
		
//		super.breakBlock(world, x, y, z, block, metadata);
		world.removeTileEntity(x, y, z);
        
	}
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		
		this.icon = iconRegister.registerIcon(this.getTextureName());
		this.iconFront = iconRegister.registerIcon(this.getTextureName() + "_front");
		this.iconFrontOff = iconRegister.registerIcon(this.getTextureName() + "_front_off");
		
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		
		metadata = MathHelper.clamp_int(metadata, 0, 7);
		int metaSide = metadata & 3;
		if((metaSide == 2 && side == 2) || (metaSide == 3 && side == 5)
				|| (metaSide == 0 && side == 3) || (metaSide == 1 && side == 4)) {
			if(metadata >= 4) {
				return this.iconFrontOff;
			}
			else {
				return this.iconFront;
			}
		}
		return this.icon;
		
	}
	
}
