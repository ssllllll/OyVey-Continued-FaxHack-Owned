package alpha432.oyvey.features.modules.render;

import alpha432.oyvey.features.modules.Module;
import alpha432.oyvey.features.setting.Setting;
import alpha432.oyvey.api.events.Render3DEvent;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Cylinder;
import org.lwjgl.util.glu.Sphere;


public
class PenisESP
        extends Module {


    private final Setting<Float> penisSize = this.register(new Setting <Object>("PenisSize", 1.5f, 0.1f, 5.0f));

    public
    PenisESP() {
        super("PenisESP", "Renders ur dick", Module.Category.RENDER, false, false, false);
    }

    @Override
    public
    void onRender3D(Render3DEvent event) {
        for (final Object o : mc.world.loadedEntityList) {
            if (o instanceof EntityPlayer) {
                final EntityPlayer player = (EntityPlayer) o;
                final double n = player.lastTickPosX + (player.posX - player.lastTickPosX) * mc.timer.renderPartialTicks;
                mc.getRenderManager();
                final double x = n - mc.getRenderManager().renderPosX;
                final double n2 = player.lastTickPosY + (player.posY - player.lastTickPosY) * mc.timer.renderPartialTicks;
                mc.getRenderManager();
                final double y = n2 - mc.getRenderManager().renderPosY;
                final double n3 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * mc.timer.renderPartialTicks;
                mc.getRenderManager();
                final double z = n3 - mc.getRenderManager().renderPosZ;
                GL11.glPushMatrix();
                RenderHelper.disableStandardItemLighting();
                this.esp(player, x, y, z);
                RenderHelper.enableStandardItemLighting();
                GL11.glPopMatrix();
            }
        }
    }

    public
    void esp(final EntityPlayer player, final double x, final double y, final double z) {
        GL11.glDisable(2896);
        GL11.glDisable(3553);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(2929);
        GL11.glEnable(2848);
        GL11.glDepthMask(true);
        GL11.glLineWidth(1.0f);
        GL11.glTranslated(x, y, z);
        GL11.glRotatef(- player.rotationYaw, 0.0f, player.height, 0.0f);
        GL11.glTranslated(- x, - y, - z);
        GL11.glTranslated(x, y + player.height / 2.0f - 0.22499999403953552, z);
        GL11.glColor4f(1.38f, 0.55f, 2.38f, 1.0f);
        GL11.glRotated((player.isSneaking() ? 35 : 0), 1.0f, 0.0f, 0);
        GL11.glTranslated(0.0, 0.0, 0.07500000298023224);
        final Cylinder shaft = new Cylinder();
        shaft.setDrawStyle(100013);
        shaft.draw(0.1f * this.penisSize.getValue(), 0.11f, 0.4f, 25, 20);
        GL11.glTranslated(0.0, 0.0, - 0.12500000298023223);
        GL11.glTranslated(- 0.09000000074505805, 0.0, 0.0);
        final Sphere right = new Sphere();
        right.setDrawStyle(100013);
        right.draw(0.14f * this.penisSize.getValue(), 10, 20);
        GL11.glTranslated(0.16000000149011612, 0.0, 0.0);
        final Sphere left = new Sphere();
        left.setDrawStyle(100013);
        left.draw(0.14f * this.penisSize.getValue(), 10, 20);
        GL11.glColor4f(1.35f, 0.0f, 0.0f, 1.0f);
        GL11.glTranslated(- 0.07000000074505806, 0.0, 0.589999952316284);
        final Sphere tip = new Sphere();
        tip.setDrawStyle(100013);
        tip.draw(0.13f * this.penisSize.getValue(), 15, 20);
        GL11.glDepthMask(true);
        GL11.glDisable(2848);
        GL11.glEnable(2929);
        GL11.glDisable(3042);
        GL11.glEnable(2896);
        GL11.glEnable(3553);
    }
}