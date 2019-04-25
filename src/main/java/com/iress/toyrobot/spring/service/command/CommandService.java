package com.iress.toyrobot.spring.service.command;

import com.iress.toyrobot.spring.domain.command.Command;

import java.util.Optional;

public interface CommandService {
    Optional<Command> createCommand(String inputString);
}
