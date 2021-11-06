package alpha432.oyvey.features.command.commands;

import alpha432.oyvey.features.command.Command;
import alpha432.oyvey.OyVey;

public class ReloadCommand
        extends Command {
    public ReloadCommand() {
        super("reload", new String[0]);
    }

    @Override
    public void execute(String[] commands) {
        OyVey.reload();
    }
}

