package com.hepolite.fillycam;

import com.hepolite.fillycam.Config.Setting;
import com.mumfrey.liteloader.transformers.event.EventInfo;
import com.mumfrey.liteloader.transformers.event.ReturnEventInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

public class FillyEvents {

    private static Config config = Config.INSTANCE;
    private static Minecraft mc = Minecraft.getMinecraft();

    public static void onOrientCameraDistance(EventInfo<EntityRenderer> e, float ticks) {
        if (config.getBoolean(Setting.MOD_ENABLED)) {
            double original = getDistance(4, ticks);
            double changed = getDistance(4F + config.getFloat(Setting.CAMERA_OFFSET_DISTANCE), ticks);
            GlStateManager.translate(0, 0, (float)original);
            GlStateManager.translate(0, 0, (float)-changed);
        }
    }

    private static double getDistance(float distance, float ticks) {
        Entity entity = mc.getRenderViewEntity();
        if (entity == null) return distance;

        float f = entity.getEyeHeight();
        double posX = entity.prevPosX + (entity.posX - entity.prevPosX) * (double) ticks;
        double posY = entity.prevPosY + (entity.posY - entity.prevPosY) * (double) ticks + (double) f;
        double posZ = entity.prevPosZ + (entity.posZ - entity.prevPosZ) * (double) ticks;

        float yaw = entity.rotationYaw;
        float pitch = entity.rotationPitch;

        if (mc.gameSettings.thirdPersonView == 2) {
            pitch += 180.0F;
        }

        double dist = distance;

        double distX = (double) (-MathHelper.sin(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F)) * dist;
        double distZ = (double) (MathHelper.cos(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F)) * dist;
        double distY = (double) (-MathHelper.sin(pitch * 0.017453292F)) * dist;

        for (int i = 0; i < 8; ++i) {
            float f3 = (float) ((i & 1) * 2 - 1);
            float f4 = (float) ((i >> 1 & 1) * 2 - 1);
            float f5 = (float) ((i >> 2 & 1) * 2 - 1);
            f3 *= 0.1F;
            f4 *= 0.1F;
            f5 *= 0.1F;
            RayTraceResult raytraceresult = mc.world.rayTraceBlocks(
                    new Vec3d(posX + (double) f3, posY + (double) f4, posZ + (double) f5),
                    new Vec3d(posX - distX + (double) f3 + (double) f5, posY - distY + (double) f4, posZ - distZ + (double) f5));


            if (raytraceresult != null) {
                double dist2 = raytraceresult.hitVec.distanceTo(new Vec3d(posX, posY, posZ));

                if (dist2 < dist) {
                    dist = dist2;
                }
            }
        }
        return dist;
    }

    public static void onOrientCameraHeight(ReturnEventInfo<EntityPlayer, Float> e) {
        if (config.getBoolean(Setting.MOD_ENABLED) && !e.getSource().isPlayerSleeping() && !e.getSource().isElytraFlying()) {
            float height = e.getReturnValueF();
            height += config.getFloat(Setting.CAMERA_OFFSET_HEIGHT);
            e.setReturnValue(height);
        }
    }
}
