package com.iress.toyrobot.spring.domain.command;

import com.iress.toyrobot.spring.domain.robot.Robot;
import com.iress.toyrobot.spring.domain.types.CommandType;

import java.util.Optional;

public class MoveCommand implements Command {
    private String label;

    public MoveCommand() {
        this.label = CommandType.MOVE.getName();
    }

    @Override
    public void execute(Robot robot) {
        Optional.ofNullable(robot)
                .filter(Robot::isOperational)
                .ifPresent(Robot::move);
    }

    @Override
    public String getLabel() {
        return label;
    }
}
