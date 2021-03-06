package me.aidanmees.trivia.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import me.aidanmees.trivia.client.main.trivia;
//import me.aidanmees.trivia.client.modules.Hidden.Music;
import me.aidanmees.trivia.client.tools.ItemStackUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.CustomButton.GuiButtonDark;
import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;

public class GuiMusic extends GuiScreen {
	private GuiScreen previous;
	private GuiTextField nameOrId;
	private GuiTextField name;
	public static String URL = "http://www.cedarhome.org/mp3/songs/no%20one%20can%20stand.mp3";
	
	public GuiMusic(GuiScreen previous) {
		this.previous = previous;
	}

	@Override
	public void initGui() {
		Keyboard.enableRepeatEvents(true);
		this.buttonList.clear();
		this.buttonList.add(new GuiButtonDark(12, this.width / 2 , this.height / 5 + 110, "Stop"));
		this.buttonList.add(new GuiButtonDark(11, this.width / 2 - 150, this.height / 5 + 110, "Play"));
		this.buttonList.add(
				new GuiButtonDark(2, this.width / 2 - 75, this.height / 6 + 200, I18n.format("gui.done", new Object[0])));
		(this.nameOrId = new GuiTextField(0, this.fontRendererObj, this.width / 2 - 100, this.height / 5 + 30, 200, 20))
				.setText("http://stream01.iloveradio.de/iloveradio2.mp3");
		nameOrId.setMaxStringLength(Integer.MAX_VALUE);
		nameOrId.setText("http://stream01.iloveradio.de/iloveradio2.mp3");
		
	}

	@Override
	public void updateScreen() {
		this.nameOrId.updateCursorCounter();
		
		
	
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if (button.id == 11){
			
			//Music.music_link = nameOrId.getText();
			 URL = nameOrId.getText();
			if (!trivia.getModuleByName("Music").isToggled()) {
			 trivia.getModuleByName("Music").onEnable();
			}
			 this.mc.displayGuiScreen(this.previous);

	}
		if (button.id == 12){
			
			 trivia.getModuleByName("Music").onDisable();
			
			 this.mc.displayGuiScreen(this.previous);

	}
		if (button.id == 1){
			this.mc.displayGuiScreen(this.previous);

	}
	}
	public static String withColors(String identifier, String input) {
		String output = input;
		int index = output.indexOf(identifier);
		while (output.indexOf(identifier) != -1) {
			output = output.replace(identifier, "\247");
			index = output.indexOf(identifier);
		}
		return output;
	}



	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		this.nameOrId.textboxKeyTyped(typedChar, keyCode);
		
		super.keyTyped(typedChar, keyCode);
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		this.nameOrId.mouseClicked(mouseX, mouseY, mouseButton);
		
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();

		this.nameOrId.drawTextBox();
		
		
		this.drawCenteredString(this.fontRendererObj, "Name/ID", this.width / 2,
				this.nameOrId.yPosition - 15, -1);
		
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
}
