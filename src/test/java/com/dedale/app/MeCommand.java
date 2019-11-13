package com.dedale.app;

import picocli.CommandLine.Command;

@Command(name = "me")
class MeCommand implements CoreCommand {

    @Override
    public CoreResult call() {
        return new CoreResult("helloYou");
    }
}
