package com.hepolite.fillycam;

import java.util.Map;

import com.google.common.collect.Maps;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mumfrey.liteloader.core.LiteLoader;
import com.mumfrey.liteloader.modconfig.Exposable;
import com.mumfrey.liteloader.modconfig.ExposableOptions;

@ExposableOptions(filename = "fillycam")
public enum Config implements Exposable
{
	INSTANCE;

	// Core settings
	public enum Setting
	{
		MOD_ENABLED, DEBUG_ENABLED, CAMERA_OFFSET_HEIGHT, CAMERA_OFFSET_DISTANCE
	}

	@Expose
	@SerializedName("settings")
	private Map<Setting, Object> settingsMap = Maps.newEnumMap(Setting.class);

	private Config()
	{
		settingsMap.put(Setting.MOD_ENABLED, true);
		settingsMap.put(Setting.DEBUG_ENABLED, false);

		settingsMap.put(Setting.CAMERA_OFFSET_HEIGHT, 0F);
		settingsMap.put(Setting.CAMERA_OFFSET_DISTANCE, 0F);
	}

	/** Returns the value of the specified setting */
	@SuppressWarnings("unchecked")
	public <T> T get(Setting setting, T fallback)
	{
		T t = (T) settingsMap.get(setting);
		if (t == null)
		{
			t = fallback;
			settingsMap.put(setting, t);
		}
		return t;
	}

	/** Returns the value of the specified setting */
	public boolean getBoolean(Setting setting)
	{
		return get(setting, false);
	}

	/** Returns the value of the specified setting */
	public float getFloat(Setting setting)
	{
		return this.<Number> get(setting, 0F).floatValue();
	}

	/** Assigns the value of the specified setting */
	public <T> void set(Config.Setting setting, T value)
	{
		settingsMap.put(setting, value);
	}

	/** Saves the config file to disk */
	public void save()
	{
		LiteLoader.getInstance().writeConfig(this);
	}

}
