package com.dedale.app;

import picocli.CommandLine;

import java.util.List;
import java.util.Objects;

public class CoreExecutor {

    private final CoreCommand mainCommand;
    private final List<CoreCommand> subCommands;

    public CoreExecutor(CoreCommand mainCommand, List<CoreCommand> subCommands) {
        this.mainCommand = mainCommand;
        this.subCommands = subCommands;
    }

    public CoreResult execute(String... args) {
        CommandLine commandLine = getCommandLine();
        commandLine.execute(args);
        return parseResult(commandLine);
    }

    private CommandLine getCommandLine() {
        CommandLine commandLine = new CommandLine(mainCommand);
        subCommands.forEach(commandLine::addSubcommand);
        return commandLine;
    }

    private CoreResult parseResult(CommandLine commandLine) {
        return commandLine.getParseResult()
                .asCommandLineList()
                .stream()
                .map(CommandLine::<CoreResult>getExecutionResult)
                .filter(Objects::nonNull)
                .reduce(CoreResult.empty(), CoreResult::mergeWith);
    }
}
