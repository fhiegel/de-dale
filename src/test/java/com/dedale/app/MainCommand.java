package com.dedale.app;

import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.List;

@Command
class MainCommand implements CoreCommand {

    @CommandLine.Parameters(hidden = true)  // "hidden": don't show this parameter in usage help message
    private List<String> allParameters;

    @Override
    public CoreResult call() {
        return new CoreResult("Main Command is the best");
    }

}
