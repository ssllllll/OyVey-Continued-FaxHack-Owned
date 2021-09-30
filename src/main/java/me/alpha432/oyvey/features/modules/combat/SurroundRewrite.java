package me.alpha432.oyvey.features.modules.combat;

/**Not a real rewrite, this was made thanks to TrollGod
 * I just wanted to add a cool name lol
 */

import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.util.BlockUtil;
import me.alpha432.oyvey.util.ItemUtil;
import me.alpha432.oyvey.util.Timer;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.HashMap;
import java.util.Map;

public class SurroundRewrite extends Module {

    private final Setting<Integer> delay = this.register(new Setting<Integer>("Delay", 50, 0, 250));
    private final Setting<Integer> blocksPerTick = this.register(new Setting<Integer>("BPT", 8, 1, 20));
    private final Setting<Boolean> helpingBlocks = this.register(new Setting<Boolean>("HelpingBlocks", true));
    private final Setting<Boolean> intelligent = this.register(new Setting<Boolean>("Intelligent", false));
    private final Setting<Boolean> antiPedo = this.register(new Setting<Boolean>("Always Help", false));
    private final Setting<Boolean> floor = this.register(new Setting<Boolean>("Floor", false));
    private final Setting<Integer> retryer = this.register(new Setting<Integer>("Retries", 4, 1, 15));
    private final Setting<Integer> retryDelay = this.register(new Setting<Integer>("Retry Delay", 200, 1, 2500));

    private final Map<BlockPos, Integer> retries = new HashMap<>();
    private final Timer timer = new Timer();
    private final Timer retryTimer = new Timer();

    private boolean didPlace = false;
    private int placements = 0;
    private int obbySlot = -1;

    double posY;

    public SurroundRewrite() {
        super("SurroundRewrite", "Surrounds you with obsidian", Category.COMBAT, true, false, false);
    }

    @Override
    public void onEnable() {
        if (mc.player == null || mc.world == null) {
            setEnabled(false);
            return;
        }
        retries.clear();
        retryTimer.reset();
        posY = mc.player.posY;
    }

    @Override
    public void onToggle() {
        OyVey.INSTANCE.getPlayerManager().setSwitching(false);
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (check()) {
            return;
        }

        if (posY < mc.player.posY) {
            setEnabled(false);
            return;
        }

        boolean onEChest = mc.world.getBlockState(new BlockPos(mc.player.getPositionVector())).getBlock() == Blocks.ENDER_CHEST;
        if (mc.player.posY - (int)mc.player.posY < 0.7) {
            onEChest = false;
        }
        if (!BlockUtil.isSafe(mc.player, onEChest ? 1:0, floor.getValue())) {
            placeBlocks(mc.player.getPositionVector(), BlockUtil.getUnsafeBlockArray(mc.player, onEChest ? 1 : 0, floor.getValue()), helpingBlocks.getValue(), false);
        } else if (!BlockUtil.isSafe(mc.player, onEChest ? 0 : -1, false)) {
            if (antiPedo.getValue()) {
                placeBlocks(mc.player.getPositionVector(), BlockUtil.getUnsafeBlockArray(mc.player, onEChest ? 0 : -1, false), false, false);
            }
        }

        if (didPlace) {
            timer.reset();
        }
    }

    private boolean placeBlocks(Vec3d pos, Vec3d[] vec3ds, boolean hasHelpingBlocks, boolean isHelping) {
        int helpings = 0;
        boolean gotHelp;
        if (obbySlot == -1)
            return false;

        if (mc.player == null)
            return false;

        boolean switched = false;
        int lastSlot = mc.player.inventory.currentItem;
        for (final Vec3d vec3d : vec3ds) {
            if (!switched) {
                OyVey.INSTANCE.getPlayerManager().setSwitching(true);
                if (OyVey.INSTANCE.getPlayerManager().getSlot() != obbySlot) {
                    mc.getConnection().sendPacket(new CPacketHeldItemChange(obbySlot));
                }
                switched = true;
            }
            gotHelp = true;
            helpings++;
            if (isHelping && !intelligent.getValue() && helpings > 1) {
                return false;
            }
            final BlockPos position = new BlockPos(pos).add(vec3d.x, vec3d.y, vec3d.z);
            switch (BlockUtil.isPositionPlaceable(position, true)) {
                case 1:
                    if ((this.retries.get(position) == null || this.retries.get(position) < this.retryer.getValue())) {
                        this.placeBlock(position);
                        this.retries.put(position, this.retries.get(position) == null ? 1 : this.retries.get(position) + 1);
                        this.retryTimer.reset();
                        continue;
                    }

                    continue;
                case 2:
                    if (hasHelpingBlocks) {
                        gotHelp = placeBlocks(pos, BlockUtil.getHelpingBlocks(vec3d), false, true);
                    } else {
                        continue;
                    }
                case 3:
                    if (gotHelp) {
                        placeBlock(position);
                    }
                    if (isHelping) {
                        return true;
                    }
            }
        }
        if (switched && OyVey.INSTANCE.getPlayerManager().getSlot() != lastSlot) {
            mc.getConnection().sendPacket(new CPacketHeldItemChange(lastSlot));
        }
        OyVey.INSTANCE.getPlayerManager().setSwitching(false);
        return false;
    }
    private boolean check() {
        if (mc.player == null || mc.world == null) {
            return true;
        }

        didPlace = false;
        placements = 0;
        obbySlot = ItemUtil.getBlockFromHotbar(Blocks.OBSIDIAN);

        if (retryTimer.passed(retryDelay.getValue())) {
            retries.clear();
            retryTimer.reset();
        }

        if (obbySlot == -1) {
            obbySlot = ItemUtil.getBlockFromHotbar(Blocks.ENDER_CHEST);
            if (obbySlot == -1) {
                this.setEnabled(false);
                return true;
            }
        }

        return !timer.passed(delay.getValue());
    }

    private void placeBlock(BlockPos pos) {
        if (placements < blocksPerTick.getValue()) {
            BlockUtil.placeBlock(pos);
            didPlace = true;
            placements++;
        }
    }

}