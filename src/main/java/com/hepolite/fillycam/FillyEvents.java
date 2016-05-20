package com.hepolite.fillycam;

import com.hepolite.fillycam.Config.Setting;
import com.mumfrey.liteloader.transformers.event.ReturnEventInfo;

import net.minecraft.entity.player.EntityPlayer;

public class FillyEvents
{

	public static void changeEyeHeight(ReturnEventInfo<EntityPlayer, Float> e)
	{

		Config config = LiteModFillyCam.getInstance().getConfig();
		if (!config.getBoolean(Setting.MOD_ENABLED) || e.getSource().isPlayerSleeping())
		{
			return;
		}

		float f = e.getReturnValueF();
		f += config.getFloat(Setting.CAMERA_OFFSET_HEIGHT);

		e.setReturnValue(f);
	}
}
