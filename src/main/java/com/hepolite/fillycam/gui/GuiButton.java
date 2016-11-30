package com.hepolite.fillycam.gui;

import com.hepolite.fillycam.ModInfo;
import net.minecraft.util.ResourceLocation;

import java.util.LinkedList;
import java.util.List;

public abstract class GuiButton extends GuiElement {
    // Control variables
    protected boolean isActive = false;

    protected ResourceLocation textureActive = null;
    protected ResourceLocation textureInactive = null;
    protected ResourceLocation textureHoverActive = null;
    protected ResourceLocation textureHoverInactive = null;

    // ////////////////////////////////////////////////////////////////////////////////////

    protected GuiButton(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void onRender(GuiScreenFillyCam parentGui, int mouseX, int mouseY) {
        if (isMouseOver(mouseX, mouseY)) {
            if (isActive)
                bindTexture(textureHoverActive);
            else
                bindTexture(textureHoverInactive);
        } else {
            if (isActive)
                bindTexture(textureActive);
            else
                bindTexture(textureInactive);
        }
        parentGui.renderTexturedRectangle(x, y, width, height);
    }

    /**
     * Returns true if the button is active
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Forces the activation state of the button to what the user specifies
     */
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    // ////////////////////////////////////////////////////////////////////////////////////
    // RADIO BUTTONS // RADIO BUTTONS // RADIO BUTTONS // RADIO BUTTONS // RADIO BUTTONS //
    // ////////////////////////////////////////////////////////////////////////////////////

    /**
     * The radio buttons will only allow one of the buttons in the group to be selected at the same time. When a button in the group is activated, all the others are deactivated
     */
    public static class GuiButtonRadiobox extends GuiButton {
        private List<GuiButtonRadiobox> buttons = new LinkedList<GuiButtonRadiobox>();

        public GuiButtonRadiobox(int x, int y, int width, int height) {
            super(x, y, width, height);

            textureActive = new ResourceLocation(ModInfo.TEXTURE_PATH, "textures/guis/button_radiobox_active.png");
            textureInactive = new ResourceLocation(ModInfo.TEXTURE_PATH, "textures/guis/button_radiobox_inactive.png");
            textureHoverActive = new ResourceLocation(ModInfo.TEXTURE_PATH, "textures/guis/button_radiobox_hover_active.png");
            textureHoverInactive = new ResourceLocation(ModInfo.TEXTURE_PATH, "textures/guis/button_radiobox_Hover_inactive.png");
        }

        public void addButtonToGroup(GuiButtonRadiobox otherButton) {
            if (otherButton == null)
                return;

            // Add all buttons to the new button, add the button to all other buttons in the group, add self to new button, finally add new button to self
            otherButton.buttons.addAll(buttons);
            for (GuiButtonRadiobox button : buttons)
                button.buttons.add(otherButton);
            otherButton.buttons.add(this);
            buttons.add(otherButton);
        }

        @Override
        public void onClicked(int mouseX, int mouseY) {
            setActive(true);
        }

        @Override
        public void setActive(boolean isActive) {
            for (GuiButtonRadiobox button : buttons)
                button.isActive = false;
            this.isActive = isActive;
        }
    }

    /**
     * The checkbox buttons will be toggleable
     */
    public static class GuiButtonCheckbox extends GuiButton {
        protected GuiButtonCheckbox(int x, int y, int width, int height) {
            super(x, y, width, height);

            textureActive = new ResourceLocation(ModInfo.TEXTURE_PATH, "textures/guis/button_checkbox_active.png");
            textureInactive = new ResourceLocation(ModInfo.TEXTURE_PATH, "textures/guis/button_checkbox_inactive.png");
            textureHoverActive = new ResourceLocation(ModInfo.TEXTURE_PATH, "textures/guis/button_checkbox_hover_active.png");
            textureHoverInactive = new ResourceLocation(ModInfo.TEXTURE_PATH, "textures/guis/button_checkbox_hover_inactive.png");
        }

        @Override
        public void onClicked(int mouseX, int mouseY) {
            isActive = !isActive;
        }
    }
}
