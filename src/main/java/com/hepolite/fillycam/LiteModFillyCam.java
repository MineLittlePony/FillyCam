package com.hepolite.fillycam;

import com.hepolite.fillycam.gui.GuiScreenFillyCam;
import com.mumfrey.liteloader.Tickable;
import com.mumfrey.liteloader.core.LiteLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

import java.io.File;

public class LiteModFillyCam implements Tickable {

    private static final String NAME = "FillyCam";
    private static final String VERSION = "@VERSION@";

    private KeyBinding keyGui = new KeyBinding("Opens the FillyCam GUI", 66, "FillyCam");

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getVersion() {
        return VERSION;
    }

    @Override
    public void init(File configPath) {
        LiteLoader.getInstance().registerExposable(Config.INSTANCE, null);
        LiteLoader.getInput().registerKeyBinding(this.keyGui);
    }

    @Override
    public void onTick(Minecraft mc, float partialTicks, boolean inGame, boolean clock) {
        if (this.keyGui.isPressed()) {
            mc.displayGuiScreen(new GuiScreenFillyCam());
        }
    }


    @Override
    public void upgradeSettings(String version, File configPath, File oldConfigPath) {
    }

}
