package dmfmm.RunicBridges.Client.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

/**
 * Created by David on 10/25/2015.
 */
public class TickEventClient {

    public static int ticks = 0;

    @SubscribeEvent
    public void tick(TickEvent.ClientTickEvent e) {
        if(e.phase == TickEvent.Phase.END) {
            GuiScreen gui = Minecraft.getMinecraft().currentScreen;
            if (gui == null || !gui.doesGuiPauseGame()) {
                ticks++;
            }
        }
    }
}
