package me.alpha432.oyvey.features.modules.combat;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.manager.EventManager;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.input.Keyboard;
import net.minecraft.item.ItemExpBottle;

import java.sql.Wrapper;

public class EXPFast extends Module {

    public
    EXPFast() {
        super("EXPFast", "Fast EXP idk", Category.COMBAT, false, false, false);
    }

    Minecraft mc = Minecraft.getMinecraft();

    public void onUpdate() {

        if (mc.player.inventory.getCurrentItem().getItem() instanceof ItemExpBottle) {
            mc.rightClickDelayTimer = 0;
        }

    }

    public void onEnable() {
        MinecraftForge.EVENT_BUS.register(this);
        super.onEnable();
    }

    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister(this);
        super.onDisable();
    }
}