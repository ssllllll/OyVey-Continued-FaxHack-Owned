package me.alpha432.oyvey.features.modules.render;

import com.google.common.collect.Maps;
import me.alpha432.oyvey.event.events.Render2DEvent;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.util.EntityUtil;
import me.alpha432.oyvey.util.RenderUtil;
import me.alpha432.oyvey.util.Util;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.Map;

public class ArrowESP
        extends Module {
    private final Setting<Integer> red = this.register(new Setting<Integer>("Red", 255, 0, 255));
    private final Setting<Integer> green = this.register(new Setting<Integer>("Green", 0, 0, 255));
    private final Setting<Integer> blue = this.register(new Setting<Integer>("Blue", 255, 0, 255));
    private final Setting<Integer> radius = this.register(new Setting<Integer>("Placement", 45, 10, 200));
    private final Setting<Float> size = this.register(new Setting<Float>("Size", Float.valueOf(10.0f), Float.valueOf(5.0f), Float.valueOf(25.0f)));
    private final Setting<Boolean> outline = this.register(new Setting<Boolean>("Outline", true));
    private final Setting<Float> outlineWidth = this.register(new Setting<Float>("Outline-Width", Float.valueOf(1.0f), Float.valueOf(0.1f), Float.valueOf(3.0f)));
    private final Setting<Integer> fadeDistance = this.register(new Setting<Integer>("Range", 100, 10, 200));
    private final Setting<Boolean> invisibles = this.register(new Setting<Boolean>("Invisibles", false));
    private final EntityListener entityListener = new EntityListener();

    public ArrowESP() {
        super("ArrowESP", "Arrow tracers ", Module.Category.RENDER, true, false, false);
    }

    @Override
    public void onRender2D(Render2DEvent event) {
        this.entityListener.render();
        ArrowESP.mc.world.loadedEntityList.forEach(o -> {
            if (o instanceof EntityPlayer && this.isValid((EntityPlayer) o)) {
                EntityPlayer entity = (EntityPlayer) o;
                Vec3d pos = this.entityListener.getEntityLowerBounds().get(entity);
                if (pos != null && !this.isOnScreen(pos) && !RenderUtil.isInViewFrustrum(entity)) {
                    Color color = EntityUtil.getColor(entity, this.red.getValue(), this.green.getValue(), this.blue.getValue(), (int) MathHelper.clamp(255.0f - 255.0f / (float) this.fadeDistance.getValue().intValue() * ArrowESP.mc.player.getDistance(entity), 100.0f, 255.0f), true);
                    int x = Display.getWidth() / 2 / (ArrowESP.mc.gameSettings.guiScale == 0 ? 1 : ArrowESP.mc.gameSettings.guiScale);
                    int y = Display.getHeight() / 2 / (ArrowESP.mc.gameSettings.guiScale == 0 ? 1 : ArrowESP.mc.gameSettings.guiScale);
                    float yaw = this.getRotations(entity) - ArrowESP.mc.player.rotationYaw;
                    GL11.glTranslatef((float) x, (float) y, 0.0f);
                    GL11.glRotatef(yaw, 0.0f, 0.0f, 1.0f);
                    GL11.glTranslatef((float) (-x), (float) (-y), 0.0f);
                    RenderUtil.drawTracerPointer(x, y - this.radius.getValue(), this.size.getValue().floatValue(), 2.0f, 1.0f, this.outline.getValue(), this.outlineWidth.getValue().floatValue(), color.getRGB());
                    GL11.glTranslatef((float) x, (float) y, 0.0f);
                    GL11.glRotatef(-yaw, 0.0f, 0.0f, 1.0f);
                    GL11.glTranslatef((float) (-x), (float) (-y), 0.0f);
                }
            }
        });
    }


    private boolean isOnScreen(Vec3d pos) {
        if (!(pos.x > -1.0)) return false;
        if (!(pos.y < 1.0)) return false;
        if (!(pos.x > -1.0)) return false;
        if (!(pos.z < 1.0)) return false;
        int n = ArrowESP.mc.gameSettings.guiScale == 0 ? 1 : ArrowESP.mc.gameSettings.guiScale;
        if (!(pos.x / (double) n >= 0.0)) return false;
        int n2 = ArrowESP.mc.gameSettings.guiScale == 0 ? 1 : ArrowESP.mc.gameSettings.guiScale;
        if (!(pos.x / (double) n2 <= (double) Display.getWidth())) return false;
        int n3 = ArrowESP.mc.gameSettings.guiScale == 0 ? 1 : ArrowESP.mc.gameSettings.guiScale;
        if (!(pos.y / (double) n3 >= 0.0)) return false;
        int n4 = ArrowESP.mc.gameSettings.guiScale == 0 ? 1 : ArrowESP.mc.gameSettings.guiScale;
        return pos.y / (double) n4 <= (double) Display.getHeight();
    }

    private boolean isValid(EntityPlayer entity) {
        return entity != ArrowESP.mc.player && (!entity.isInvisible() || this.invisibles.getValue() != false) && entity.isEntityAlive();
    }

    private float getRotations(EntityLivingBase ent) {
        double x = ent.posX - ArrowESP.mc.player.posX;
        double z = ent.posZ - ArrowESP.mc.player.posZ;
        return (float) (-(Math.atan2(x, z) * 57.29577951308232));
    }

    private static class EntityListener {
        private final Map<Entity, Vec3d> entityUpperBounds = Maps.newHashMap();
        private final Map<Entity, Vec3d> entityLowerBounds = Maps.newHashMap();

        private EntityListener() {
        }

        private void render() {
            if (!this.entityUpperBounds.isEmpty()) {
                this.entityUpperBounds.clear();
            }
            if (!this.entityLowerBounds.isEmpty()) {
                this.entityLowerBounds.clear();
            }
            for (Entity e : Util.mc.world.loadedEntityList) {
                Vec3d bound = this.getEntityRenderPosition(e);
                bound.add(new Vec3d(0.0, (double) e.height + 0.2, 0.0));
                Vec3d upperBounds = RenderUtil.to2D(bound.x, bound.y, bound.z);
                Vec3d lowerBounds = RenderUtil.to2D(bound.x, bound.y - 2.0, bound.z);
                if (upperBounds == null || lowerBounds == null) continue;
                this.entityUpperBounds.put(e, upperBounds);
                this.entityLowerBounds.put(e, lowerBounds);
            }
        }

        private Vec3d getEntityRenderPosition(Entity entity) {
            double partial = Util.mc.timer.renderPartialTicks;
            double x = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * partial - Util.mc.getRenderManager().viewerPosX;
            double y = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * partial - Util.mc.getRenderManager().viewerPosY;
            double z = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * partial - Util.mc.getRenderManager().viewerPosZ;
            return new Vec3d(x, y, z);
        }

        public Map<Entity, Vec3d> getEntityLowerBounds() {
            return this.entityLowerBounds;
        }
    }
}

