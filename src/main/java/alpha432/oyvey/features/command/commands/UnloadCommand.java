package alpha432.oyvey.features.command.commands;

import alpha432.oyvey.features.command.Command;
import alpha432.oyvey.OyVey;

public class UnloadCommand
        extends Command {
    public UnloadCommand() {
        super("unload", new String[0]);
    }

    @Override
    public void execute(String[] commands) {
        OyVey.unload(true);
    }
}

