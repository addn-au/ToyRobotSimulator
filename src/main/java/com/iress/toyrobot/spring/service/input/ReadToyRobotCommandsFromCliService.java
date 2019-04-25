package com.iress.toyrobot.spring.service.input;

import com.iress.toyrobot.spring.domain.command.Command;
import com.iress.toyrobot.spring.domain.command.Commands;
import com.iress.toyrobot.spring.domain.command.ToyRobotSimulatorCommands;
import com.iress.toyrobot.spring.domain.types.CommandType;
import com.iress.toyrobot.spring.service.command.CommandService;
import com.iress.toyrobot.spring.service.command.ToyRobotCommandService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service("readToyRobotCommandsFromCliService")
public class ReadToyRobotCommandsFromCliService implements ReadCommandsFromCliService {
    private CommandService toyRobotSimulatorCommandService;

    public ReadToyRobotCommandsFromCliService() {
        toyRobotSimulatorCommandService = new ToyRobotCommandService();
    }

    @Override
    public Commands read(Scanner scanner) {
        Commands commands = new ToyRobotSimulatorCommands();

        Optional.ofNullable(scanner).ifPresent(s -> {
                boolean running;
                do {
                    Optional<Command> command = toyRobotSimulatorCommandService.createCommand(s.nextLine());
                    command.ifPresent(commands::add);
                    running = !(command.isPresent()
                            && command.filter(c -> (c.getLabel().equals(CommandType.REPORT.getName()))).isPresent());
                } while (running);

                s.close();
            }
        );

        return commands;
    }
}
