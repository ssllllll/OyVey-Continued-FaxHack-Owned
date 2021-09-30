package me.alpha432.oyvey.features.modules.render;

import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.event.events.Render3DEvent;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.util.EntityUtil;
import me.alpha432.oyvey.util.RenderUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;


public class BurrowESP
        extends Module {
    private final Setting <Integer> boxRed;
    private final Setting <Integer> outlineGreen;
    private final Setting <Integer> boxGreen;
    private final Setting <Boolean> box;
    private final Setting <Boolean> cOutline;
    private final Setting <Integer> outlineBlue;
    private final Setting <Boolean> name = this.register(new Setting <>("Name", false));
    private final Setting <Integer> boxAlpha;
    private final Setting <Float> outlineWidth;
    private final Setting <Integer> outlineRed;
    private final Setting <Boolean> outline;
    private final Setting <Integer> boxBlue;
    private final Map <EntityPlayer, BlockPos> burrowedPlayers;
    private final Setting <Integer> outlineAlpha;

    public
    BurrowESP() {
        super("BurrowESP", "Renders people burrowing", Module.Category.RENDER, true, false, false);
        this.box = new Setting<>( "Box", true);
        this.boxRed = this.register(new Setting <>("BoxRed", 255, 0, 255, v -> this.box.getValue()));
        this.boxGreen = this.register(new Setting <>("BoxGreen", 255, 0, 255, v -> this.box.getValue()));
        this.boxBlue = this.register(new Setting <>("BoxBlue", 255, 0, 255, v -> this.box.getValue()));
        this.boxAlpha = this.register(new Setting <>("BoxAlpha", 125, 0, 255, v -> this.box.getValue()));
        this.outline = this.register(new Setting <>("Outline", true));
        this.outlineWidth = this.register(new Setting <>("OutlineWidth", 1.0f, 0.0f, 5.0f, v -> this.outline.getValue()));
        this.cOutline = this.register(new Setting <>("CustomOutline", false, v -> this.outline.getValue()));
        this.outlineRed = this.register(new Setting <>("OutlineRed", 255, 0, 255, v -> this.cOutline.getValue()));
        this.outlineGreen = this.register(new Setting <>("OutlineGreen", 255, 0, 255, v -> this.cOutline.getValue()));
        this.outlineBlue = this.register(new Setting <>("OutlineBlue", 255, 0, 255, v -> this.cOutline.getValue()));
        this.outlineAlpha = this.register(new Setting <>("OutlineAlpha", 255, 0, 255, v -> this.cOutline.getValue()));
        this.burrowedPlayers = new HashMap <>();
    }

    private
    void getPlayers() {
        for (EntityPlayer entityPlayer : BurrowESP.mc.world.playerEntities) {
            if (entityPlayer == BurrowESP.mc.player || OyVey.friendManager.isFriend(entityPlayer.getName()) || ! EntityUtil.isLiving(entityPlayer) || ! this.isBurrowed(entityPlayer))
                continue;
            this.burrowedPlayers.put(entityPlayer, new BlockPos(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ));
        }
    }

    @Override
    public
    void onEnable() {
        this.burrowedPlayers.clear();
    }

    private
    void lambda$onRender3D$8(Map.Entry entry) {
        this.renderBurrowedBlock((BlockPos) entry.getValue());
        if (this.name.getValue()) {
            RenderUtil.drawText(new BlockPos((BlockPos) entry.getValue()), ((EntityPlayer) entry.getKey()).getGameProfile().getName());
        }
    }

    private
    boolean isBurrowed(EntityPlayer entityPlayer) {
        BlockPos blockPos = new BlockPos(Math.floor(entityPlayer.posX), Math.floor(entityPlayer.posY + 0.2), Math.floor(entityPlayer.posZ));
        return BurrowESP.mc.world.getBlockState(blockPos).getBlock() == Blocks.ENDER_CHEST || BurrowESP.mc.world.getBlockState(blockPos).getBlock() == Blocks.OBSIDIAN || BurrowESP.mc.world.getBlockState(blockPos).getBlock() == Blocks.CHEST || BurrowESP.mc.world.getBlockState(blockPos).getBlock() == Blocks.ANVIL;
    }

    @Override
    public
    void onUpdate() {
        if (BurrowESP.fullNullCheck()) {
            return;
        }
        this.burrowedPlayers.clear();
        this.getPlayers();
    }

    private
    void renderBurrowedBlock(BlockPos blockPos) {
        RenderUtil.drawBoxESP(blockPos, new Color(this.boxRed.getValue(), this.boxGreen.getValue(), this.boxBlue.getValue(), this.boxAlpha.getValue()), true, new Color(this.outlineRed.getValue(), this.outlineGreen.getValue(), this.outlineBlue.getValue(), this.outlineAlpha.getValue()), this.outlineWidth.getValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), true);
    }

    @Override
    public
    void onRender3D(Render3DEvent render3DEvent) {
        if (! this.burrowedPlayers.isEmpty()) {
            this.burrowedPlayers.entrySet().forEach(this::lambda$onRender3D$8);
        }
    }
}
