package com.hepolite.fillycam;

import com.hepolite.fillycam.Config.Setting;
import com.mumfrey.liteloader.transformers.event.EventInfo;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;

public class FillyEvents {

    private static Config config = Config.INSTANCE;

    public static void onOrientCameraDistance(EventInfo<EntityRenderer> e, float arg1) {
        if (config.getBoolean(Setting.MOD_ENABLED)) {
            float distance = config.getFloat(Setting.CAMERA_OFFSET_DISTANCE);
            GlStateManager.translate(0, 0, -distance);
        }
    }

    public static void onOrientCameraHeight(EventInfo<EntityRenderer> e, float arg1) {
        if (config.getBoolean(Setting.MOD_ENABLED)) {
            float height = config.getFloat(Setting.CAMERA_OFFSET_HEIGHT);
            GlStateManager.translate(0, -height, 0);
        }
    }
}
