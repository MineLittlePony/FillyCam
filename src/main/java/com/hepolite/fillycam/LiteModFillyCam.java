package com.hepolite.fillycam;

import java.io.File;

import com.hepolite.fillycam.Config.Setting;
import com.hepolite.fillycam.gui.GuiScreenFillyCam;
import com.mumfrey.liteloader.Tickable;
import com.mumfrey.liteloader.core.LiteLoader;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

public class LiteModFillyCam implements Tickable
{

	private static LiteModFillyCam instance;

	private KeyBinding keyGui = new KeyBinding("Opens the FillyCam GUI", 66, "FillyCam");
	private Config config;

	@Override
	public String getName()
	{
		return ModInfo.NAME;
	}

	@Override
	public String getVersion()
	{
		return ModInfo.VERSION;
	}

	public static LiteModFillyCam getInstance()
	{
		return instance;
	}

	public Config getConfig()
	{
		return config;
	}

	@Override
	public void init(File configPath)
	{
		instance = this;
		LiteLoader.getInstance().registerExposable(config = new Config(), null);
		LiteLoader.getInput().registerKeyBinding(this.keyGui);
	}

	@Override
	public void onTick(Minecraft mc, float partialTicks, boolean inGame, boolean clock)
	{
		if (this.keyGui.isPressed())
		{
			mc.displayGuiScreen(new GuiScreenFillyCam());
		}

		if (!config.getBoolean(Setting.MOD_ENABLED))
		{
			this.setCameraDistance(4.0F);
		} else
		{
			this.setCameraDistance(4.0F + config.getFloat(Setting.CAMERA_OFFSET_DISTANCE));
		}
	}

	public void setCameraDistance(float distance)
	{
		FillyFields.thirdPersonDistance.set(Minecraft.getMinecraft().entityRenderer, distance);
	}

	@Override
	public void upgradeSettings(String version, File configPath, File oldConfigPath)
	{
	}

}
