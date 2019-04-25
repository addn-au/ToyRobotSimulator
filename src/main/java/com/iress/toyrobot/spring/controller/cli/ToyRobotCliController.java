package com.iress.toyrobot.spring.controller.cli;

import com.iress.toyrobot.spring.domain.command.Commands;
import com.iress.toyrobot.spring.service.input.ReadCommandsFromCliService;
import com.iress.toyrobot.spring.service.input.ReadToyRobotCommandsFromCliService;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller("toyRobotCliController")
public class ToyRobotCliController implements CliController {
    private ReadCommandsFromCliService readToyRobotCommandsFromCliService;
    private Scanner scanner;

    public ToyRobotCliController() {
        scanner = new Scanner(System.in);
        readToyRobotCommandsFromCliService = new ReadToyRobotCommandsFromCliService();
    }

    @Override
    public Commands readCommands(String[] args) {
        return readToyRobotCommandsFromCliService.read(scanner);
    }
}
