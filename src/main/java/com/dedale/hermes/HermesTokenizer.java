package com.dedale.hermes;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class HermesTokenizer {

    final String author;
    Collection<HermesArgument> arguments;
    private Queue<HermesCommand> commands = new ArrayDeque<>();

    public HermesTokenizer(String commandSentence, String defaultAuthor) {
        this.author = defaultAuthor;
        commands(commandSentence);
    }

    private void commands(String commandSentence) {
        Queue<String> commandsTokenizer = tokenize(commandSentence);
        commands = parse(commandsTokenizer);
    }

    private Queue<HermesCommand> parse(Queue<String> commandsTokenizer) {
        return parseCommands(new LinkedList<>(), commandsTokenizer);
    }

    private Queue<HermesCommand> parseCommands(Queue<HermesCommand> commands, Queue<String> commandsTokenizer) {
        if (commandsTokenizer.isEmpty()) {
            return commands;
        }
        HermesCommand command = parceCommand(commandsTokenizer);
        commands.add(command);
        return parseCommands(commands, commandsTokenizer);
    }

    private HermesCommand parceCommand(Queue<String> commandsTokenizer) {
        String token = commandsTokenizer.peek();
        HermesCommand command = new HermesCommand(token, author);

        HermesArgument argument = arguments.stream()
                .filter(arg -> arg.handle(commandsTokenizer.poll())).findFirst()
                .orElseThrow(RuntimeException::new);
        return command;
    }

    private Queue<String> tokenize(String commandSentence) {
        Queue<String> inputQueue = new LinkedList<>();
        StringTokenizer commandsTokenizer = new StringTokenizer(commandSentence);
        while (commandsTokenizer.hasMoreTokens()) {
            inputQueue.add(commandsTokenizer.nextToken());
        }
        return inputQueue;
    }

    private void addCommand(Queue<HermesCommand> commands, String commandToken, StringTokenizer tokens) {
        HermesCommand command = new HermesCommand(commandToken, author);
        commands.add(command);

        if (tokens.hasMoreTokens()) {
            String commandToken2 = tokens.nextToken();
            if (commandToken2.startsWith("--author")) {
            } else {
                addCommand(commands, commandToken2, tokens);
            }
        }
    }

    public boolean hasNext() {
        return !commands.isEmpty();
    }

    public HermesCommand nextCommand() {
        return commands.poll();
    }

    //
    //
    //

    interface HermesArgument {
        boolean handle(String argument);

        HermesCommand inject(HermesCommand command, String argument);
    }
}
