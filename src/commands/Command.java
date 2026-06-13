package commands;

import program.IProgram;

public abstract class Command {
    private String mName;
    protected IProgram mProgram;

    public Command(String name, IProgram program) {
        mProgram = program;
        mName = name;
    }

    public abstract void execute();

    public String getName() {
        return mName;
    }
}
