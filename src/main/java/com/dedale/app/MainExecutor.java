package com.dedale.app;

import picocli.CommandLine;

import java.util.Objects;

public class MainExecutor {

    static String execute(String ...args) {
        MainCommand mainCommand = new MainCommand();

        CommandLine cmd = new CommandLine(mainCommand);
        int exitCode = cmd.execute(args);

        MyResult result = cmd.getParseResult()
                .asCommandLineList()
                .stream()
                .map(cli -> (MyResult) cli.getExecutionResult())
                .filter(Objects::nonNull)
                .findFirst()
                .orElseGet(() -> new MyResult("default"));
        return result.toString();
    }
}
