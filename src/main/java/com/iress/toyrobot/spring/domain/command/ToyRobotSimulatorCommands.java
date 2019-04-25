package com.iress.toyrobot.spring.domain.command;

import com.iress.toyrobot.spring.domain.types.CommandType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component("toyRobotSimulatorCommands")
public class ToyRobotSimulatorCommands implements Commands {
    private List<Command> commands;

    public ToyRobotSimulatorCommands() {
        commands = new ArrayList<>();
    }

    @Override
    public void add(Command command) {
        Optional.ofNullable(command).ifPresent(c -> commands.add(c));
    }

    @Override
    public boolean isExecutable() {
        List<Command> placeCommands = Optional.of(commands).map(List::stream).orElseGet(Stream::empty)
                .filter(command -> command.getLabel().equals(CommandType.PLACE.getName()))
                .collect(Collectors.toList());
        return (placeCommands.size() > 0);
    }

    @Override
    public Stream<Command> stream() {
        return commands.stream();
    }

    @Override
    public Integer length() {
        return commands.size();
    }
}
