package dmfmm.RunicBridges.Core.Generation.WizardTower;

import cpw.mods.fml.common.registry.VillagerRegistry;
import dmfmm.RunicBridges.Core.Generation.RuneMine.TeleporterRuneEssenceMine;
import dmfmm.RunicBridges.Core.init.ItemLoader;
import dmfmm.RunicBridges.Core.items.LeaveDimensionToken;
import dmfmm.RunicBridges.Core.utils.Pos;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by David on 10/25/2015.
 */
public class EntityWizard extends EntityVillager{

    private boolean allowTeleport ;

    public EntityWizard(World world) {
        super(world, 4412);
        allowTeleport = false;

    }

    public EntityWizard(World world, Pos pos){
        super(world, 4412);
        allowTeleport = false;
        spawnAt(pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public boolean interact(EntityPlayer player) {
        ItemStack itemstack = player.inventory.getCurrentItem();
        boolean flag = itemstack != null && itemstack.getItem() == Items.spawn_egg;

        if (!flag && this.isEntityAlive() && !this.isTrading() && !this.isChild() && !player.isSneaking()) {
            if (this.allowTeleport) {
                if (player.ridingEntity == null && player.riddenByEntity == null && player instanceof EntityPlayerMP) {
                    EntityPlayerMP thePlayer = (EntityPlayerMP) player;
                    MinecraftServer server = MinecraftServer.getServer();
                    if (player instanceof EntityPlayerMP) {
                        if (thePlayer.timeUntilPortal > 0) {
                            thePlayer.timeUntilPortal = 10;
                        } else if (thePlayer.dimension != -4412) {
                            thePlayer.inventory.addItemStackToInventory(LeaveDimensionToken.createLeaveToken(thePlayer));
                            thePlayer.timeUntilPortal = 10;
                            thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, -4412, new TeleporterRuneEssenceMine(server.worldServerForDimension(-4412)));
                        }
                    }
                }
                return true;
            } else {
                if(itemstack != null) {
                    if (itemstack.getItem().equals(Items.emerald) && itemstack.stackSize >= 5) {
                        itemstack.stackSize -= 5;
                        this.allowTeleport = true;
                        if (!this.worldObj.isRemote) {
                            player.addChatComponentMessage(new ChatComponentText("[Great Wizard] Thank you for your payment. You may now teleport to the Rune Essence Mine by right-clicking me again!"));
                        }
                    }
                }else{
                    if (!this.worldObj.isRemote) {
                        player.addChatComponentMessage(new ChatComponentText("[Great Wizard] Payment must first be made before I can teleport you to the Rune Essence Mine. Please have 5 emeralds in your hand and right-click me again!"));
                    }
                }
            }
        }
        return false;
    }

    public void writeEntityToNBT(NBTTagCompound tag)
    {
        super.writeEntityToNBT(tag);
        tag.setBoolean("teleport", allowTeleport);
    }

    public void readEntityFromNBT(NBTTagCompound tag)
    {
        super.readEntityFromNBT(tag);
        allowTeleport = tag.getBoolean("teleport");
    }

    public static class TradeHandler implements VillagerRegistry.IVillageTradeHandler {

        @Override
        public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList r, Random random) {
                r.clear();
                r.add(new MerchantRecipe(new ItemStack(Items.emerald, 5), null, new ItemStack(ItemLoader.rune)));

        }

        public final static TradeHandler INSTANCE = new TradeHandler();

    }

    public void spawnAt(int x, int y, int z){
        setPosition(x, y, z);

    }
}
