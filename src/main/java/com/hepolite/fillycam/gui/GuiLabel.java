package com.hepolite.fillycam.gui;

import net.minecraft.util.ResourceLocation;

public class GuiLabel extends GuiElement {
    private String name = "Unnamed Label";
    private int color = 16777215;

    private ResourceLocation texture = new ResourceLocation(TEXTURE_PATH, "textures/guis/label.png");

    public GuiLabel(int x, int y, int width, int height, String name, byte colorRed, byte colorGreen, byte colorBlue) {
        super(x, y, width, height);
        this.name = name;
        this.color = colorBlue + 256 * (colorGreen + 256 * colorRed);
    }

    @Override
    public void onRender(GuiScreenFillyCam parentGui, int mouseX, int mouseY) {
        bindTexture(texture);
        parentGui.renderTexturedRectangle(x, y, width, height);
        parentGui.drawCenteredString(fontRenderer, name, x + width / 2, y + (height - 8) / 2, color);
    }

    /**
     * Returns the name of the label
     */
    public String getName() {
        return name;
    }

    /**
     * Allows the name of the label to be changed
     */
    public void setName(String name) {
        this.name = name;
    }
}
