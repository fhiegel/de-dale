package com.dedale.app;

import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command
public class MainCommand implements Callable<MyResult> {

    @Override
    public MyResult call() {
        return new MyResult("wtf");
    }

    @Command(name = "me")
    public MyResult me() {
        System.out.println("toto");
        return new MyResult("helloYou");
    }
}
