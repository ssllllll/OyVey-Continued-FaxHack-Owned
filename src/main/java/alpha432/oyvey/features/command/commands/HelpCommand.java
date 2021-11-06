package alpha432.oyvey.features.command.commands;

import alpha432.oyvey.features.command.Command;
import com.mojang.realmsclient.gui.ChatFormatting;
import alpha432.oyvey.OyVey;

public class HelpCommand
        extends Command {
    public HelpCommand() {
        super("help");
    }

    @Override
    public void execute(String[] commands) {
        HelpCommand.sendMessage("Commands: ");
        for (Command command : OyVey.commandManager.getCommands()) {
            HelpCommand.sendMessage(ChatFormatting.GRAY + OyVey.commandManager.getPrefix() + command.getName());
        }
    }
}

