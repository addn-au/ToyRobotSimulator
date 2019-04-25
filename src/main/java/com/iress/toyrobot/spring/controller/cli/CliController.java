package com.iress.toyrobot.spring.controller.cli;

import com.iress.toyrobot.spring.domain.command.Commands;

import java.util.Scanner;

public interface CliController {
    Commands readCommands(String[] args);
}
