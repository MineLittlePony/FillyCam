package com.hepolite.fillycam;

import java.lang.reflect.Field;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.input.Keyboard;

import com.hepolite.fillycam.Config.Setting;
import com.hepolite.fillycam.gui.GuiScreenFillyCam;

@SideOnly(Side.CLIENT)
@Mod(modid = ModInfo.MODID, name = ModInfo.NAME, version = ModInfo.VERSION, dependencies = ModInfo.DEPENDENCIES)
public class FillyCam
{
	// Set up the instance of the mod
	@Instance(ModInfo.MODID)
	public static FillyCam instance;

	// Keybinds
	private KeyBinding keyGui = new KeyBinding("Opens the FillyCam GUI", Keyboard.KEY_F8, "FillyCam");

	// /////////////////////////////////////////////////////////////////////////////////////////
	// EVENTS // EVENTS // EVENTS // EVENTS // EVENTS // EVENTS // EVENTS // EVENTS // EVENTS //
	// /////////////////////////////////////////////////////////////////////////////////////////

	@SubscribeEvent
	public void onTick(ClientTickEvent event)
	{
		// Grab essentials
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer player = mc.thePlayer;
		if (player == null)
			return;

		// Open FillyCam gui if applicable
		if (keyGui.isPressed())
			mc.displayGuiScreen(new GuiScreenFillyCam());

		// Figure out if the mod should be doing anything at all or not
		if (!Config.getBoolean(Setting.MOD_ENABLED))
		{
			if (Config.playerHeight != -1.0f)
				player.eyeHeight = Config.playerHeight;
			setCameraDistance(4.0f);
			return;
		}

		// Handle the height of the player and camera distance to player
		if (Config.playerHeight == -1.0f)
			Config.playerHeight = player.eyeHeight;
		player.eyeHeight = Config.playerHeight + Config.getFloat(Setting.CAMERA_OFFSET_HEIGHT);
		setCameraDistance(4.0f + Config.getFloat(Setting.CAMERA_OFFSET_DISTANCE));
	}

	@SubscribeEvent
	public void onRenderTick(RenderTickEvent event)
	{
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		// Take care of the config, want the settings to actually work, eh? ^^
		Config.handleConfig(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		// Basic stuff that should be obvious
		FMLCommonHandler.instance().bus().register(this);

		ClientRegistry.registerKeyBinding(keyGui);
	}

	// /////////////////////////////////////////////////////////////////////////////////////////
	// HELPER METHODS // HELPER METHODS // HELPER METHODS // HELPER METHODS // HELPER METHODS //
	// /////////////////////////////////////////////////////////////////////////////////////////

	/** Handles the distance of the camera to the player */
	public void setCameraDistance(float distance)
	{
		try
		{
			Minecraft mc = Minecraft.getMinecraft();
			String field = Config.getBoolean(Setting.DEBUG_ENABLED) ? "thirdPersonDistance" : "field_78490_B";
			Field thirdPersonDistance = mc.entityRenderer.getClass().getDeclaredField(field);
			thirdPersonDistance.setAccessible(true);
			thirdPersonDistance.set(mc.entityRenderer, distance);
		}
		// Something went wrong? Bah, like we care, we'll just shove all the problem under the carpet!
		catch (NoSuchFieldException e)
		{
		}
		catch (SecurityException e)
		{
		}
		catch (IllegalArgumentException e)
		{
		}
		catch (IllegalAccessException e)
		{
		}
	}
}
