package dmfmm.noki.multiplecamera.gui;

import dmfmm.noki.multiplecamera.item.ItemViewer;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import dmfmm.noki.multiplecamera.ModInfo;
import dmfmm.noki.multiplecamera.camera.CameraManagerClient;


/**********
 * @class GuiViewer.
 *
 * @description
 * @description_en
 * 
 * @see ItemViewer , CameraManagerClient.
 */
public class GuiViewer extends GuiScreen {
	
	//******************************//
	// define member variables.
	//******************************//
	private static final ResourceLocation texture = new ResourceLocation(ModInfo.ID.toLowerCase(), "textures/gui/viewer.png");
	private static final double scale = 0.2D;
	
	private GuiButton nextButton;
	private GuiButton prevButton;
	
	// currently not used.
	@SuppressWarnings("unused")
	private EntityPlayer player;
	
	
	//******************************//
	// define member methods.
	//******************************//
	public GuiViewer(EntityPlayer player) {
		
		this.player = player;
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void initGui() {
		
		this.buttonList.clear();
		
		int position = (MathHelper.ceiling_double_int(this.width*scale) - 64) / 2;
		this.nextButton = new PagingButton(1, this.width-position-64, 10, I18n.format("gui.viewer.next"));
		this.prevButton = new PagingButton(2, position, 10, I18n.format("gui.viewer.prev"));
		this.buttonList.add(this.nextButton);
		this.buttonList.add(this.prevButton);
		
		this.updateButtons();
		
		CameraManagerClient.setClientCamerasFromServer();
		
	}
	
	public void updateButtons() {
		
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {
		
		if(button.id == 1) {
			CameraManagerClient.setNextClientCamera();
		}
		else if(button.id == 2) {
			CameraManagerClient.setPrevClientCamera();
		}
		
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		Minecraft mc = Minecraft.getMinecraft();
		int id = CameraManagerClient.getClientId();
		int size = CameraManagerClient.getSize();
		
		//	draw margins.
		if(id < 0) {
			this.drawRect(0, 0, this.width, this.height, 0, 0, 0, 0.7F);
		}
		else {
			this.drawRect(0, 0, MathHelper.ceiling_double_int(this.width*scale), this.height, 0, 0, 0, 0.7F);
			this.drawRect(MathHelper.ceiling_double_int(this.width-this.width*scale), 0,
					this.width, this.height, 0, 0, 0, 0.7F);
		}
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		// draw gui.
		int position = (MathHelper.ceiling_double_int(this.width*scale) - 74) / 2;
		this.mc.getTextureManager().bindTexture(texture);
		this.drawTexturedModalRect(position, 30, 0, 17, 74, 34);
		
		//	draw camera number.
		if(id >= 0) {
			mc.fontRenderer.drawString(I18n.format("gui.viewer.camera", id+1, size), position+3, 35, 14737632);
			if(CameraManagerClient.getOrderedCamera(id).isTurtle) {
				mc.fontRenderer.drawString(I18n.format("gui.viewer.turtle"), position+3, 45, 14737632);
			}
		}
		else {
			String string = I18n.format("gui.viewer.nocamera");
			this.fontRendererObj.drawString(string,
					this.width/2-this.fontRendererObj.getStringWidth(string)/2, this.height/2, 14737632);
		}
		
//		this.fontRendererObj.drawString(I18n.format("gui.purse.name"), (this.width-pageWidth)/2+48, 50+6, 4210752);
		
		super.drawScreen(mouseX, mouseY, partialTicks);
		
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		
		return false;
		
	}
	
	@Override
	public void onGuiClosed() {
		
		CameraManagerClient.resetClientCameras();
		
	}
	
	private void drawRect(int startX, int startY, int endX, int endY, int r, int g, int b, float t) {
		
		Tessellator tessellator = Tessellator.instance;
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		GL11.glColor4f((float)r/255.0F, (float)g/255.0F, (float)b/255.0F, t);
		tessellator.startDrawingQuads();
		tessellator.addVertex((double)startX, (double)endY, 0.0D);
		tessellator.addVertex((double)endX, (double)endY, 0.0D);
		tessellator.addVertex((double)endX, (double)startY, 0.0D);
		tessellator.addVertex((double)startX, (double)startY, 0.0D);
		tessellator.draw();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);
		
	}
	
	private class PagingButton extends GuiButton {
		
		public PagingButton(int buttonID, int x, int y, String name) {
			
			super(buttonID, x, y, 64, 16, name);
			
		}
		
		public void drawButton(Minecraft mc, int mouseX, int mouseY) {
			
			if(this.visible) {
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				mc.getTextureManager().bindTexture(GuiViewer.texture);
	            GL11.glEnable(GL11.GL_BLEND);
	            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
	            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	            
				int x = 0;
				int y = 0;
				
				this.drawTexturedModalRect(this.xPosition, this.yPosition, x, y, this.width, this.height);
	            this.drawCenteredString(Minecraft.getMinecraft().fontRenderer, this.displayString,
	            		this.xPosition + this.width / 2, this.yPosition + (this.height-8) / 2, 14737632);
			}
			
		}
		
	}
	
}
