package alpha432.oyvey.features.modules.misc;

import alpha432.oyvey.features.modules.Module;
import com.mojang.realmsclient.gui.ChatFormatting;
import alpha432.oyvey.OyVey;
import alpha432.oyvey.features.setting.Setting;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.scoreboard.ScorePlayerTeam;

public class ExtraTab
        extends Module {
    private static ExtraTab INSTANCE = new ExtraTab();
    public Setting<Integer> size = this.register(new Setting<Integer>("Size", 250, 1, 1000));

    public ExtraTab() {
        super("ExtraTab", "Extends Tab.", Module.Category.MISC, false, false, false);
        this.setInstance();
    }

    public static String getPlayerName(NetworkPlayerInfo networkPlayerInfoIn) {
        String name;
        String string = name = networkPlayerInfoIn.getDisplayName() != null ? networkPlayerInfoIn.getDisplayName().getFormattedText() : ScorePlayerTeam.formatPlayerName(networkPlayerInfoIn.getPlayerTeam(), networkPlayerInfoIn.getGameProfile().getName());
        if (OyVey.friendManager.isFriend(name)) {
            return ChatFormatting.AQUA + name;
        }
        return name;
    }

    public static ExtraTab getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new ExtraTab();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
}

