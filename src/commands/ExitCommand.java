package commands;

import program.IProgram;

public class ExitCommand extends Command {
    public ExitCommand(IProgram program) {
        super("Exit", program);
    }

    @Override
    public void execute() {
        mProgram.exit();
    }
}
