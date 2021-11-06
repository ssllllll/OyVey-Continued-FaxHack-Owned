package alpha432.oyvey.api.manager;

import alpha432.oyvey.api.events.PacketEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketHeldItemChange;

public class PlayerManager {

    private static final Minecraft mc = Minecraft.getMinecraft();

    private boolean shifting, switching;
    private int slot;

    public void onPacketSend(PacketEvent.Send event) {
        if (event.getPacket() instanceof CPacketEntityAction) {
            final CPacketEntityAction packet = event.getPacket();
            if (packet.getAction() == CPacketEntityAction.Action.START_SNEAKING) {
                shifting = true;
            } else if (packet.getAction() == CPacketEntityAction.Action.STOP_SNEAKING) {
                shifting = false;
            }
        }

        if (event.getPacket() instanceof CPacketHeldItemChange) {
            slot = ((CPacketHeldItemChange) event.getPacket()).getSlotId();
                mc.player.inventory.currentItem = slot;
            }
        }


    public void setSwitching(boolean switching) {
        this.switching = switching;
    }

    public boolean isShifting() {
        return shifting;
    }

    public int getSlot() {
        return slot;
    }

}