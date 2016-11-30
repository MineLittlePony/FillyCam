package com.hepolite.fillycam.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;

public abstract class GuiElement {
    // Control variables
    protected int x = 0;
    protected int y = 0;
    protected int width = 0;
    protected int height = 0;

    protected FontRenderer fontRenderer = null;

    /**
     * Initializes the base settings for the element
     */
    protected GuiElement(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.fontRenderer = Minecraft.getMinecraft().fontRendererObj;
    }

    /**
     * Handles the draw event for the given button
     */
    public abstract void onRender(GuiScreenFillyCam parentGui, int mouseX, int mouseY);

    /**
     * Handles the case of updating the element
     */
    public void onUpdate(int mouseX, int mouseY) {
    }

    /**
     * Handle for when the element was clicked by the user
     */
    public void onClicked(int mouseX, int mouseY) {
    }

    /**
     * Called when the user released the mouse
     */
    public void onMouseReleased(int mouseX, int mouseY) {
    }

    // ///////////////////////////////////////////////////////////////////////////////
    // MISC HELPERS // MISC HELPERS // MISC HELPERS // MISC HELPERS // MISC HELPERS //
    // ///////////////////////////////////////////////////////////////////////////////

    /**
     * Returns true if the mouse is hovering over the element
     */
    public boolean isMouseOver(int mouseX, int mouseY) {
        return (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height);
    }

    /**
     * Binds the given texture to the Minecraft render engine
     */
    public void bindTexture(ResourceLocation resource) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(resource);
    }
}
