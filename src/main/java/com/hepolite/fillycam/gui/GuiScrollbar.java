package com.hepolite.fillycam.gui;

import net.minecraft.util.ResourceLocation;

public class GuiScrollbar extends GuiElement {
    private boolean selected = false;
    private float value = 0.0f;

    private int markerWidth = 0;

    private ResourceLocation textureBackground = new ResourceLocation(TEXTURE_PATH, "textures/guis/label.png");
    private ResourceLocation textureMarker = new ResourceLocation(TEXTURE_PATH, "textures/guis/scrollbar_marker.png");

    public GuiScrollbar(int x, int y, int width, int height, int markerWidth) {
        super(x, y, width, height);
        this.markerWidth = markerWidth;
    }

    @Override
    public void onRender(GuiScreenFillyCam parentGui, int mouseX, int mouseY) {
        bindTexture(textureBackground);
        parentGui.renderTexturedRectangle(x, y, width, height);
        bindTexture(textureMarker);
        parentGui.renderTexturedRectangle(x + 2 + (int) (0.5f * (value + 1.0f) * (width - markerWidth - 2)), y + 2, markerWidth, height - 4);
    }

    @Override
    public void onUpdate(int mouseX, int mouseY) {
        if (selected)
            setValue(2.0f * (mouseX - x) / width - 1.0f);
    }

    @Override
    public void onClicked(int mouseX, int mouseY) {
        selected = true;
    }

    @Override
    public void onMouseReleased(int mouseX, int mouseY) {
        selected = false;
    }

    /**
     * Returns the scrollbar value, from -1.0f to 1.0f
     */
    public float getValue() {
        return value;
    }

    /**
     * Assigns the given value to the scrollbar, from -1.0f to 1.0f
     */
    public void setValue(float value) {
        this.value = Math.min(1.0f, Math.max(-1.0f, value));
    }
}
