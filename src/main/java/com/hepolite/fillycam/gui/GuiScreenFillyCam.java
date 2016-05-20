package com.hepolite.fillycam.gui;

import java.util.LinkedList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.hepolite.fillycam.Config;
import com.hepolite.fillycam.Config.Setting;
import com.hepolite.fillycam.gui.GuiButton.GuiButtonCheckbox;
import com.hepolite.fillycam.gui.GuiButton.GuiButtonRadiobox;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class GuiScreenFillyCam extends GuiScreen
{
	// Control variables
	private int x = 0;
	private int y = 0;

	private List<GuiElement> elements = new LinkedList<GuiElement>();

	// Core gui elements
	private GuiButton buttonEnableMod = null;
	private GuiLabel buttonEnableModLabel = null;

	private GuiScrollbar scrollbarCameraHeightOffset = null;
	private GuiLabel scrollbarCameraHeightOffsetLabel = null;
	private GuiLabel scrollbarCameraHeightOffsetInfo = null;
	private GuiButton scrollbarCameraHeightOffsetReset = null;

	private GuiScrollbar scrollbarCameraDistanceOffset = null;
	private GuiLabel scrollbarCameraDistanceOffsetLabel = null;
	private GuiLabel scrollbarCameraDistanceOffsetInfo = null;
	private GuiButton scrollbarCameraDistanceOffsetReset = null;

	private GuiButtonRadiobox buttonPresetDefault = null;
	private GuiLabel buttonPresetDefaultLabel = null;
	private GuiButtonRadiobox buttonPresetAlicorn = null;
	private GuiLabel buttonPresetAlicornLabel = null;
	private GuiButtonRadiobox buttonPresetStallion = null;
	private GuiLabel buttonPresetStallionLabel = null;
	private GuiButtonRadiobox buttonPresetMare = null;
	private GuiLabel buttonPresetMareLabel = null;
	private GuiButtonRadiobox buttonPresetFilly = null;
	private GuiLabel buttonPresetFillyLabel = null;
	private GuiButtonRadiobox buttonPresetCustom = null;
	private GuiLabel buttonPresetCustomLabel = null;

	// ///////////////////////////////////////////////////////////////////////////////////////
	// CORE FUNCTIONALITY // CORE FUNCTIONALITY // CORE FUNCTIONALITY // CORE FUNCTIONALITY //
	// ///////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void initGui()
	{
		Keyboard.enableRepeatEvents(true);

		// Position the gui near the middle of the screen, with size 160 * 200
		x = width / 2 - 126;
		y = height / 2 - 116;

		// Build up the gui
		elements.clear();
		int rowX = x + 5;

		buttonEnableModLabel = new GuiLabel(rowX, y, 120, 16, "Enable FillyCam", (byte) 255, (byte) 255, (byte) 255);
		buttonEnableMod = new GuiButtonCheckbox(rowX + 124, y, 16, 16);
		addElement(buttonEnableMod);
		addElement(buttonEnableModLabel);
		buttonEnableMod.setActive(Config.INSTANCE.getBoolean(Setting.MOD_ENABLED));

		scrollbarCameraHeightOffsetLabel = new GuiLabel(rowX, y + 39, 120, 16, "Camera Height", (byte) 255, (byte) 255, (byte) 255);
		scrollbarCameraHeightOffset = new GuiScrollbar(rowX + 124, y + 30, 120, 16, 6);
		scrollbarCameraHeightOffsetInfo = new GuiLabel(rowX + 124, y + 48, 102, 16, "Height: ???", (byte) 255, (byte) 255, (byte) 255);
		scrollbarCameraHeightOffsetReset = new GuiButtonRadiobox(rowX + 228, y + 48, 16, 16);
		addElement(scrollbarCameraHeightOffset);
		addElement(scrollbarCameraHeightOffsetLabel);
		addElement(scrollbarCameraHeightOffsetInfo);
		addElement(scrollbarCameraHeightOffsetReset);
		scrollbarCameraHeightOffset.setValue(Config.INSTANCE.getFloat(Setting.CAMERA_OFFSET_HEIGHT) / 1.55f);
		scrollbarCameraHeightOffsetReset.setActive(false);

		scrollbarCameraDistanceOffsetLabel = new GuiLabel(rowX, y + 77, 120, 16, "Camera Distance", (byte) 255, (byte) 255, (byte) 255);
		scrollbarCameraDistanceOffset = new GuiScrollbar(rowX + 124, y + 68, 120, 16, 6);
		scrollbarCameraDistanceOffsetInfo = new GuiLabel(rowX + 124, y + 86, 102, 16, "Distance: ???", (byte) 255, (byte) 255, (byte) 255);
		scrollbarCameraDistanceOffsetReset = new GuiButtonRadiobox(rowX + 228, y + 86, 16, 16);
		addElement(scrollbarCameraDistanceOffset);
		addElement(scrollbarCameraDistanceOffsetLabel);
		addElement(scrollbarCameraDistanceOffsetInfo);
		addElement(scrollbarCameraDistanceOffsetReset);
		scrollbarCameraDistanceOffset.setValue(Config.INSTANCE.getFloat(Setting.CAMERA_OFFSET_DISTANCE) / 3.5f);
		scrollbarCameraDistanceOffsetReset.setActive(false);

		buttonPresetDefault = new GuiButtonRadiobox(rowX + 124, y + 120, 16, 16);
		buttonPresetDefaultLabel = new GuiLabel(rowX, y + 120, 120, 16, "Default Preset", (byte) 255, (byte) 255, (byte) 255);
		buttonPresetAlicorn = new GuiButtonRadiobox(rowX + 124, y + 139, 16, 16);
		buttonPresetAlicornLabel = new GuiLabel(rowX, y + 139, 120, 16, "Alicorn Preset", (byte) 255, (byte) 255, (byte) 255);
		buttonPresetStallion = new GuiButtonRadiobox(rowX + 124, y + 158, 16, 16);
		buttonPresetStallionLabel = new GuiLabel(rowX, y + 158, 120, 16, "Stallion Preset", (byte) 255, (byte) 255, (byte) 255);
		buttonPresetMare = new GuiButtonRadiobox(rowX + 124, y + 177, 16, 16);
		buttonPresetMareLabel = new GuiLabel(rowX, y + 177, 120, 16, "Mare Preset", (byte) 255, (byte) 255, (byte) 255);
		buttonPresetFilly = new GuiButtonRadiobox(rowX + 124, y + 196, 16, 16);
		buttonPresetFillyLabel = new GuiLabel(rowX, y + 196, 120, 16, "Filly Preset", (byte) 255, (byte) 255, (byte) 255);
		buttonPresetCustom = new GuiButtonRadiobox(rowX + 124, y + 215, 16, 16);
		buttonPresetCustomLabel = new GuiLabel(rowX, y + 215, 120, 16, "Custom Settings", (byte) 255, (byte) 255, (byte) 255);
		addElement(buttonPresetDefault);
		addElement(buttonPresetDefaultLabel);
		addElement(buttonPresetAlicorn);
		addElement(buttonPresetAlicornLabel);
		addElement(buttonPresetStallion);
		addElement(buttonPresetStallionLabel);
		addElement(buttonPresetMare);
		addElement(buttonPresetMareLabel);
		addElement(buttonPresetFilly);
		addElement(buttonPresetFillyLabel);
		addElement(buttonPresetCustom);
		addElement(buttonPresetCustomLabel);
		buttonPresetDefault.addButtonToGroup(buttonPresetAlicorn);
		buttonPresetDefault.addButtonToGroup(buttonPresetStallion);
		buttonPresetDefault.addButtonToGroup(buttonPresetMare);
		buttonPresetDefault.addButtonToGroup(buttonPresetFilly);
		buttonPresetDefault.addButtonToGroup(buttonPresetCustom);
	}

	@Override
	public void onGuiClosed()
	{
		Keyboard.enableRepeatEvents(false);
		Config.INSTANCE.save();
	}

	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		super.drawScreen(mouseX, mouseY, partialTicks);

		// Detect the changes due to selecting a preset
		if (buttonPresetDefault.isActive())
		{
			scrollbarCameraHeightOffset.setValue(0.0f);
			scrollbarCameraDistanceOffset.setValue(0.0f);
		}
		else if (buttonPresetAlicorn.isActive())
		{
			scrollbarCameraHeightOffset.setValue(0.0f);
			scrollbarCameraDistanceOffset.setValue(0.16f);
		}
		else if (buttonPresetStallion.isActive())
		{
			scrollbarCameraHeightOffset.setValue(-0.0889f);
			scrollbarCameraDistanceOffset.setValue(0.1f);
		}
		else if (buttonPresetMare.isActive())
		{
			scrollbarCameraHeightOffset.setValue(-0.267f);
			scrollbarCameraDistanceOffset.setValue(0.0f);
		}
		else if (buttonPresetFilly.isActive())
		{
			scrollbarCameraHeightOffset.setValue(-0.622f);
			scrollbarCameraDistanceOffset.setValue(-0.156f);
		}

		// Render all elements and update them
		for (GuiElement element : elements)
		{
			element.onUpdate(mouseX, mouseY);
			element.onRender(this, mouseX, mouseY);
		}

		// Reset changes with the scrollbar reset buttons
		if (scrollbarCameraHeightOffsetReset.isActive())
		{
			scrollbarCameraHeightOffset.setValue(0.0f);
			scrollbarCameraHeightOffsetReset.setActive(false);
		}
		if (scrollbarCameraDistanceOffsetReset.isActive())
		{
			scrollbarCameraDistanceOffset.setValue(0.0f);
			scrollbarCameraDistanceOffsetReset.setActive(false);
		}

		// Actually set the config values
		Config.INSTANCE.set(Setting.MOD_ENABLED, buttonEnableMod.isActive());
		Config.INSTANCE.set(Setting.CAMERA_OFFSET_HEIGHT, 1.55f * scrollbarCameraHeightOffset.getValue());
		Config.INSTANCE.set(Setting.CAMERA_OFFSET_DISTANCE, 3.5f * scrollbarCameraDistanceOffset.getValue());

		// Update visual information
		scrollbarCameraHeightOffsetInfo.setName(String.format("Height: %.2fm", 1.62f + Config.INSTANCE.getFloat(Setting.CAMERA_OFFSET_HEIGHT)));
		scrollbarCameraDistanceOffsetInfo.setName(String.format("Distance: %.1fm", 4.0f + Config.INSTANCE.getFloat(Setting.CAMERA_OFFSET_DISTANCE)));

		// Detect if the custom button should be on or not
		float h = scrollbarCameraHeightOffset.getValue();
		float d = scrollbarCameraDistanceOffset.getValue();
		if (h == 0.0f && d == 0.0f)
			buttonPresetDefault.setActive(true);
		else if (h == 0.0f && d == 0.16f)
			buttonPresetAlicorn.setActive(true);
		else if (h == -0.0889f && d == 0.1f)
			buttonPresetStallion.setActive(true);
		else if (h == -0.267f && d == 0.0f)
			buttonPresetMare.setActive(true);
		else if (h == -0.622f && d == -0.156f)
			buttonPresetFilly.setActive(true);
		else
			buttonPresetCustom.setActive(true);
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton)
	{
		// Process all elements
		for (GuiElement element : elements)
		{
			if (element.isMouseOver(mouseX, mouseY))
				element.onClicked(mouseX, mouseY);
		}
	}

	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state)
	{
		// Process all elements
		for (GuiElement element : elements)
			element.onMouseReleased(mouseX, mouseY);
	}

	// ////////////////////////////////////////////////////////////////////////////////////

	/** Adds a new element to the menu */
	public void addElement(GuiElement element)
	{
		if (element != null)
			elements.add(element);
	}

	// ////////////////////////////////////////////////////////////////////////////////////

	/** Renders a rectangle to the screen with the specified size and position */
	public void renderTexturedRectangle(double x, double y, double width, double height)
	{
		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer buffer = tessellator.getBuffer();
		buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		buffer.pos(x, y + height, zLevel).tex(0.0, 1.0).endVertex();
		buffer.pos(x + width, y + height, zLevel).tex(1.0, 1.0).endVertex();
		buffer.pos(x + width, y, zLevel).tex(1.0, 0.0).endVertex();
		buffer.pos(x, y, zLevel).tex(0.0, 0.0).endVertex();
		tessellator.draw();
	}
}
