package com.iress.toyrobot.spring.service.input;

import com.iress.toyrobot.spring.domain.command.Commands;

import java.util.Scanner;

public interface ReadCommandsFromCliService {
    Commands read(Scanner scanner);
}
