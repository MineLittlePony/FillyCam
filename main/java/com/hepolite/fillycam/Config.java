package com.hepolite.fillycam;

import java.util.HashMap;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class Config
{
	// Control variables
	private static Configuration config = null;

	// Core settings
	public enum Setting
	{
		MOD_ENABLED, DEBUG_ENABLED, CAMERA_OFFSET_HEIGHT, CAMERA_OFFSET_DISTANCE
	}

	private static HashMap<Setting, Property> settingsMap = new HashMap<Setting, Property>();

	// Non-config but useful values
	public static float playerHeight = -1.0f;

	/** Handles all config settings and stores/writes settings to disk */
	public static void handleConfig(FMLPreInitializationEvent event)
	{
		// Load up the configuration file for the mod
		config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		processConfig();
		save();
	}

	/** Actually loads up all configs from the configuration file */
	private static void processConfig()
	{
		settingsMap.put(Setting.MOD_ENABLED, config.get("Mod settings", "Enable mod", true, "Enables the mod if set to 'true' or disables it if set to 'false'"));
		settingsMap.put(Setting.DEBUG_ENABLED, config.get("Mod settings", "Enable debugmode", false, "Enables the debug mode if set to 'true' or disables it if set to 'false'"));

		settingsMap.put(Setting.CAMERA_OFFSET_HEIGHT, config.get("Camera settings", "Camera offset height", 0.0f, "Specifies the heigh offset for the camera, where 0.0 is the default offset height. The player height is 1.62, so a value of 1.7 would mean the camera is 8cm higher up than the player's eyes"));
		settingsMap.put(Setting.CAMERA_OFFSET_DISTANCE, config.get("Camera settings", "Camera offset distance", 0.0f, "Specifies the distance offset for the camera, where 0.0 is the default offset distance. The normal distance is 4.0, so a value of -1.5 would mean the camera is 2.5m away from the player in third-person view"));
	}

	/** Returns the value of the specified setting */
	public static float getFloat(Setting setting)
	{
		return (float) settingsMap.get(setting).getDouble();
	}

	/** Returns the value of the specified setting */
	public static boolean getBoolean(Setting setting)
	{
		return settingsMap.get(setting).getBoolean();
	}

	/** Assigns the value of the specified setting */
	public static void set(Setting setting, float value)
	{
		settingsMap.get(setting).set(value);
	}

	/** Assigns the value of the specified setting */
	public static void set(Setting setting, boolean value)
	{
		settingsMap.get(setting).set(value);
	}

	/** Saves the config file to disk */
	public static void save()
	{
		config.save();
	}
}
