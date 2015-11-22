package dmfmm.noki.multiplecamera.tile;

import java.util.UUID;

import dmfmm.noki.multiplecamera.block.BlockMultipleCamera;
import scala.reflect.internal.Trees.This;

import com.mojang.authlib.GameProfile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.management.PlayerManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayerFactory;
import dmfmm.noki.multiplecamera.MultipleCameraCore;
import dmfmm.noki.multiplecamera.camera.CameraManagerServer;
import dmfmm.noki.multiplecamera.camera.EntityCamera;


public class TileMultipleCamera extends TileEntity {
	
	//******************************//
	// define member variables.
	//******************************//
	protected int cameraId;
	
	private float yaw;
	private float pitch;
	private UUID uuid = null;
	private EntityPlayerMP cameraPlayer;

	protected boolean activated;
	private boolean initialized = false;
	public static float offsetYAW = 0F;
	public static float offsetPitch = 0F;
	
	private static final String nbtPitch = "MultipleCamera:pitch";
	private static final String nbtActivated = "MultipleCamera:activated";
	private static final String nbtUuid = "MultipleCamera:uuid";
	
	
	//******************************//
	// define member methods.
	//******************************//
	public TileMultipleCamera() {
		
	}
/*	public TileMultipleCamera(World world, int metadata) {
		
		this.worldObj = world;
		this.blockMetadata = metadata;
		
		if(world.isRemote && this.activated == false) {
			PacketHandler.instance.sendToServer(new PacketTileSync(world, this.xCoord, this.yCoord, this.zCoord));
		}
		
	}*/
	
	public void createCamera() {
		this.cameraId = CameraManagerServer.addCamera(this.worldObj, (double)this.xCoord+0.5D, (double)this.yCoord-1.0D, (double)this.zCoord+0.5D, this.getBlockMetadata()*90, 0, this.offsetYAW, this.offsetPitch, this.uuid, false);
		this.activated = true;
		this.initialized = true;

		
	}
	
	public void resetCameraInfo() {
		
		EntityCamera camera = CameraManagerServer.getCamera(this.cameraId);
		if(camera == null) {
			return;
		}
		camera.setCameraInfo(this.worldObj, (double)this.xCoord+0.5D, (double)this.yCoord-1.0D, (double)this.zCoord+0.5D, this.getBlockMetadata()*90, 0F, this.offsetYAW, this.offsetPitch);
		
		if(this.cameraPlayer == null) {
			this.cameraPlayer = FakePlayerFactory.get((WorldServer)this.worldObj,
					new GameProfile(null, "CameraPlayer:"+this.cameraId));
			this.cameraPlayer.setLocationAndAngles((double)this.xCoord+0.5D, (double)this.yCoord-1.0D, (double)this.zCoord+0.5D, this.yaw, this.pitch);
			this.cameraPlayer.setPositionAndRotation((double)this.xCoord+0.5D, (double)this.yCoord-1.0D, (double)this.zCoord+0.5D, this.yaw + this.offsetYAW, this.pitch + this.offsetPitch);
			if(this.cameraPlayer == null) {
				MultipleCameraCore.log("camer player is null.");
			}
			else {
				MultipleCameraCore.log("camer player is not null.");
			}
			((WorldServer)this.worldObj).getPlayerManager().addPlayer(this.cameraPlayer);
		}
		
	}
	
	public void setUuid(UUID uuid) {
		
		this.uuid = uuid;
		
	}
	
	public boolean isOwner(UUID uuid) {
		
		if(this.uuid.equals(uuid)) {
			return true;
		}
		return false;		
		
	}
	
	public int getCameraId() {
		
		return this.cameraId;
		
	}
	
	public void switchActivated() {
		
		if(this.activated == true) {
			CameraManagerServer.removeCamera(this.cameraId);
			this.cameraId = -1;
			this.activated = false;
			this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, this.getBlockMetadata()+4, 3);
		}
		else {
			this.cameraId = CameraManagerServer.addCamera(this.worldObj,(double)this.xCoord+0.5D, (double)this.yCoord-1.0D, (double)this.zCoord+0.5D, this.getBlockMetadata()*90, 0, this.offsetYAW, this.offsetPitch, this.uuid, false);
			this.activated = true;
			this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, this.getBlockMetadata()-4, 3);
		}
	
	}
	
	@Override
	public void onChunkUnload() {
		
		if(this.worldObj == null || this.worldObj.isRemote) {
			return;
		}
		CameraManagerServer.removeCamera(this.cameraId);
		this.cameraId = -1;
		this.initialized = false;
		
	}
	
	@Override
	public void updateEntity() {
		
		if(this.worldObj == null || this.worldObj.isRemote) {
			return;
		}
		
		if(this.activated == true && this.initialized == false) {
			this.createCamera();
		}
		//System.out.println("PITCH: " + this.offsetPitch);
		//System.out.println("YAW: " + this.offsetYAW);
	}
		
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		
		super.readFromNBT(nbt);
		this.pitch = nbt.getFloat(nbtPitch);
		this.activated = nbt.getBoolean(nbtActivated);
		//this.offsetPitch = nbt.getFloat("Pitch");
		//this.offsetYAW = nbt.getFloat("YAW");
		
		String uuidString = nbt.getString(nbtUuid);
		if(!uuidString.equals("")) {
			this.uuid = UUID.fromString(uuidString);
		}
		
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		
		super.writeToNBT(nbt);
		nbt.setFloat(nbtPitch, this.pitch);
		//nbt.setFloat("YAW", this.offsetYAW);
		//nbt.setFloat("Pitch", this.offsetPitch);
		nbt.setBoolean(nbtActivated, this.activated);
		if(this.uuid != null) {
			nbt.setString(nbtUuid, this.uuid.toString());
		}
		
	}
	
	@Override
	public Packet getDescriptionPacket() {
		
		NBTTagCompound nbttagcompound = new NBTTagCompound();
		this.writeToNBT(nbttagcompound);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 3, nbttagcompound);
		
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
		
		this.readFromNBT(packet.func_148857_g());
		
	}
	
	public static void setPitch(Float f){
		offsetPitch = f;
	}
	public static void setYaw(Float y){
		offsetYAW = y;
	}
	public static float getOPitch(){
		return offsetPitch;
	}
	public static float getOYaw(){
		return offsetYAW;
	}
	public void setPitchupdate(Float f){
		offsetPitch = f;
		CameraManagerServer.updateCamera(cameraId, this.worldObj, this.xCoord, this.yCoord, this.zCoord, this.yaw, this.pitch, this.offsetYAW, f);
	}
	public void setYawupdate(Float y){
		offsetYAW = y;
		CameraManagerServer.updateCamera(cameraId, this.worldObj, this.xCoord, this.yCoord, this.zCoord, this.yaw, this.pitch, y, this.offsetPitch);
	}
}
