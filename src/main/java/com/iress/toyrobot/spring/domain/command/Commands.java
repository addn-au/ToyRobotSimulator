package com.iress.toyrobot.spring.domain.command;

import java.util.stream.Stream;

public interface Commands {
    void add(Command command);
    boolean isExecutable();
    Stream<Command> stream();
    Integer length();
}
