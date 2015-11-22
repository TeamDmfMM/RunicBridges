package dmfmm.noki.multiplecamera.camera;

import java.util.UUID;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


public class EntityCamera extends EntityLivingBase {
	
	public UUID uuid;
	public boolean isTurtle;
	public float YAWOFF;
	public float PITCHOFF;

	public EntityCamera(World world, double posX, double posY, double posZ, float yaw, float pitch, float offsetY, float offsetP, UUID uuid, boolean isTurtle) {
		
		super(world);
		this.setLocationAndAngles(posX, posY, posZ, yaw, pitch);
		this.setPositionAndRotation(posX, posY, posZ, yaw + offsetY, pitch + offsetP);
		this.yOffset = 0.0F;
		this.uuid = uuid;
		this.isTurtle = false;
		this.YAWOFF = offsetY;
		this.PITCHOFF = offsetP;
		
	}
	
	public void setCameraInfo(World world, double posX, double posY, double posZ, float yaw, float pitch, float offsetY, float offsetP) {
		
		this.setWorld(world);
		this.setLocationAndAngles(posX, posY, posZ, yaw, pitch);
		this.setPositionAndRotation(posX, posY, posZ, yaw + offsetY, pitch + offsetP);

		
	}

	@Override
	public ItemStack getHeldItem() {
		
		return null;
		
	}

	@Override
	public ItemStack getEquipmentInSlot(int slotNumber) {
		
		return null;
		
	}

	@Override
	public void setCurrentItemOrArmor(int slotNum, ItemStack stack) {
		
	}

	@Override
	public ItemStack[] getLastActiveItems() {
		
		return null;
		
	}

}
