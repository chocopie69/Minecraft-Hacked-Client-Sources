package me.robbanrobbin.jigsaw.gui.custom.clickgui;

import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2d;

import java.awt.Color;
import java.awt.Point;

import org.apache.commons.codec.language.bm.Rule.RPattern;
import org.darkstorm.minecraft.gui.util.RenderUtil;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import me.robbanrobbin.jigsaw.client.main.Jigsaw;
import me.robbanrobbin.jigsaw.gui.custom.clickgui.utils.GuiUtils;
import me.robbanrobbin.jigsaw.module.Module;
import net.minecraft.client.Minecraft;

public class ModuleButton extends Component {
	
	private Module mod;
	private SettingContainer settingContainer;
	private String title;
	public float time = 0f;
	public float preTime = 0f;
	public boolean increasingTime = true;
	public float waitForTime = 0f;
	public boolean preModToggled;

	public boolean hovered;

	@Override
	public void update() {
		if(preModToggled != mod.isToggled()) {
			preModToggled = mod.isToggled();
			if(mod.isToggled()) {
				resetTime();
				increasingTime = true;
			}
			else {
				increasingTime = false;
			}
		}
		if(waitForTime > 0f) {
			waitForTime -= 0.1f;
			time = 0f;
			preTime = 0f;
		}
		else {
			preTime = time;
			if(increasingTime) {
				time += (4f / ((time * 0.03f) + 1f));
			}
			else {
				time -= 1f;
			}
			if(time > 10f) {
				time = 10f;
			}
			if(time < 0f) {
				time = 0f;
			}
		}
		if (mod.getKeyboardKey() == Keyboard.KEY_NONE) {
			this.setTitle(mod.getName());
		} else {
			this.setTitle(mod.getName() + "[" + Keyboard.getKeyName(mod.getKeyboardKey()) + "]");
		}
		settingContainer.modeMatchedSearch = false;
		if(!Jigsaw.getSearch().isEmpty() && mod.getModes().length > 1) {
			boolean found = false;
			for(String s : mod.getModes()) {
				if(s.toLowerCase().indexOf(Jigsaw.getSearch().toLowerCase()) != -1) {
					found = true;
				}
			}
			if(found) {
				settingContainer.modeMatchedSearch = true;
			}
		}
	}

	@Override
	public void draw() {
		float time = (this.preTime + (this.time - this.preTime) * Minecraft.getMinecraft().timer.renderPartialTicks);
		boolean drawSearchColor = false;
		GuiUtils.translate(this, false);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		glDisable(GL_CULL_FACE);
//		GL11.glColor4f(0.15f, 0.15f, 0.15f, 0.91f - Jigsaw.getClickGuiManager().getAlpha());
		if(!Jigsaw.getSearch().isEmpty() && this.title.toLowerCase().indexOf(Jigsaw.getSearch().toLowerCase()) != -1) {
			drawSearchColor = true;
		}
		GL11.glLineWidth(1f);
		GL11.glColor4f(0.8f, 0.1f, 0.1f, 0.91f);
		if(drawSearchColor) {
			GL11.glColor4f(0.3f, 0.7f, 0.2f, 0.8f - Jigsaw.getClickGuiManager().getAlpha());
		}
		glBegin(GL11.GL_QUADS);
		{
			glVertex2d(0, 0);
			glVertex2d(0, getHeight());
			glVertex2d(getWidth() * time / 10f, getHeight());
			glVertex2d(getWidth() * time / 10f, 0);
		}
		glEnd();
		GL11.glColor4f(0.1f, 0.1f, 0.1f, 0.91f);
		if(drawSearchColor) {
			GL11.glColor4f(0.3f, 0.7f, 0.2f, 0.8f - Jigsaw.getClickGuiManager().getAlpha());
		}
		glBegin(GL11.GL_QUADS);
		{
			glVertex2d(time * getWidth() / 10f, 0);
			glVertex2d(time * getWidth() / 10f, getHeight());
			glVertex2d(getWidth(), getHeight());
			glVertex2d(getWidth(), 0);
		}
		glEnd();
		if(hovered && !mod.isToggled() && time == 0f) {
			GL11.glColor4f(0.8f, 0.1f, 0.1f, 0.91f - Jigsaw.getClickGuiManager().getAlpha());
			glBegin(GL11.GL_QUADS);
			{
				glVertex2d(0, 0);
				glVertex2d(0, getHeight());
				glVertex2d(2, getHeight());
				glVertex2d(2, 0);
			}
			glEnd();
		}
		if(mod.hasCustomSettings()) {
			GL11.glColor4f(0.8f, 0.2f, 0.2f, 0.8f);
			glBegin(GL11.GL_QUADS);
			{
				glVertex2d(getWidth() - 2, 0);
				glVertex2d(getWidth() - 2, getHeight());
				glVertex2d(getWidth(), getHeight());
				glVertex2d(getWidth(), 0);
			}
			glEnd();
		}
		if(!mod.isToggled()) {
			GL11.glColor4f(0.3f, 0.3f, 0.3f, 0.8f - Jigsaw.getClickGuiManager().getAlpha());
//			glBegin(GL11.GL_LINES);
//			{
//				glVertex2d(0, 0);
//				glVertex2d(getWidth(), 0);
//			}
//			glEnd();
			GL11.glColor4f(0.1f, 0.1f, 0.1f, 0.8f - Jigsaw.getClickGuiManager().getAlpha());
			if(settingContainer.modeMatchedSearch) {
				GL11.glColor4f(0.3f, 0.7f, 0.2f, 0.8f - Jigsaw.getClickGuiManager().getAlpha());
				glBegin(GL11.GL_LINES);
				{
					glVertex2d(0, getHeight() - 0.5);
					glVertex2d(getWidth() - (mod.hasCustomSettings() ? 2 : 0), getHeight() - 0.5);
				}
				glEnd();
			}
		}
		else {
			GL11.glColor4f(0.3f, 0.3f, 0.3f, 0.8f - Jigsaw.getClickGuiManager().getAlpha());
			if(settingContainer.modeMatchedSearch) {
				GL11.glColor4f(0.3f, 0.7f, 0.2f, 0.8f - Jigsaw.getClickGuiManager().getAlpha());
				glBegin(GL11.GL_LINES);
				{
					glVertex2d(0, getHeight() - 0.5);
					glVertex2d(getWidth(), getHeight() - 0.5);
				}
				glEnd();
			}
//			glBegin(GL11.GL_LINES);
//			{
//				glVertex2d(0, getHeight() - 0.5);
//				glVertex2d(getWidth(), getHeight() - 0.5);
//			}
//			glEnd();
			GL11.glColor4f(0.1f, 0.1f, 0.1f, 0.8f - Jigsaw.getClickGuiManager().getAlpha());
//			glBegin(GL11.GL_LINES);
//			{
//				glVertex2d(0, 0);
//				glVertex2d(getWidth(), 0);
//			}
//			glEnd();
		}
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		if(mod != null) {
			fontRenderer.drawStringWithShadow(title, (float) (getWidth() / 2 - fontRenderer.getStringWidth(title) / 2), 1.5f, RenderUtil.toRGBA(new Color(1f, 1f, 1f, 1f - Jigsaw.getClickGuiManager().getAlpha())));
		}
		GuiUtils.translate(this, true);
		if(hovered) {
			Point mouse = RenderUtil.calculateMouseLocation();
			mouse.translate(0, 1);
			glDisable(GL_TEXTURE_2D);
			// TODO Jigsaw tooltips
			if (mod != null && mod.description != "") {
				glColor4f(0.8f, 0.2f, 0.2f, 0.9f);
				GL11.glTranslated(0, 0, 1);
				GL11.glEnable(GL11.GL_DEPTH_TEST);
				int mousey = (int) (mouse.y - 10);
				int mousex = mouse.x;
				int realMouseX = RenderUtil.calculateMouseLocation().x;
				int testWidth = (fontRenderer.getStringWidth(mod.description) + 28);
				if(realMouseX > Minecraft.getMinecraft().displayWidth / 2 - testWidth) {
					mousex = mousex - realMouseX + (Minecraft.getMinecraft().displayWidth / 2) - testWidth;
				}
				glBegin(GL_QUADS);
				{
					int width = mousex + 3
							+ fontRenderer.getStringWidth(mod.description) + 2;
					int height = mousey + fontRenderer.FONT_HEIGHT;
					glVertex2d(mousex + 20, mousey - 1);
					glVertex2d(width + 20, mousey - 1);
					glVertex2d(width + 20, height);
					glVertex2d(mousex + 20, height);
				}
				glEnd();
				glColor4f(0, 0, 0, 1);
				GL11.glLineWidth(2);
				glBegin(GL11.GL_LINE_LOOP);
				{
					int width = mousex + 3
							+ fontRenderer.getStringWidth(mod.description) + 2;
					int height = mousey + fontRenderer.FONT_HEIGHT;
					glVertex2d(mousex + 20, mousey - 1);
					glVertex2d(width + 20, mousey - 1);
					glVertex2d(width + 20, height);
					glVertex2d(mousex + 20, height);
				}
				glEnd();
				GL11.glLineWidth(1);
				fontRenderer.drawStringWithShadow(mod.description, mousex + 22, mousey - 1,
						RenderUtil.toRGBA(Color.WHITE));
				GL11.glTranslated(0, 0, -1);
			}
			glEnable(GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_BLEND);
			glEnable(GL_CULL_FACE);
			hovered = false;
		}
	}
	
	public void setSettingContainer(SettingContainer settingContainer) {
		this.settingContainer = settingContainer;
	}
	
	public SettingContainer getSettingContainer() {
		return settingContainer;
	}
	
	public Module getMod() {
		return mod;
	}
	
	public void setMod(Module mod) {
		this.mod = mod;
		this.preModToggled = mod.isToggled();
	}
	
	@Override
	public void onClicked(double x, double y, int button) {
		super.onClicked(x, y, button);
		if(button == 0) {
			mod.toggle();
			
		}
		if(button == 1) {
			settingContainer.setExtended(!settingContainer.extended);
			this.getParent().layoutChildren();
		}
	}

	@Override
	public double getPreferedWidth() {
		return fontRenderer.getStringWidth(title) + 8;
	}

	@Override
	public double getPreferedHeight() {
		return fontRenderer.FONT_HEIGHT + 3;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void resetTime() {
		time = 1.3f;
		preTime = 1.3f;
	}
	
}