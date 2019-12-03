package com.dedale.app;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.util.List;

@Command
public
class Echo implements CoreCommand {

    @Parameters(hidden = true)
    private List<String> parameters;

    @Override
    public CoreResult call() {
        return new CoreResult(String.join(" ", parameters));
    }

}
