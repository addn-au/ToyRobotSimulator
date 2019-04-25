package com.iress.toyrobot.spring.domain.command;

import com.iress.toyrobot.spring.domain.robot.Robot;
import com.iress.toyrobot.spring.domain.types.CommandType;

import java.util.Optional;

public class LeftCommand implements Command {
    private String label;

    public LeftCommand() {
        this.label = CommandType.LEFT.getName();
    }
    @Override
    public void execute(Robot robot) {
        Optional.ofNullable(robot)
                .filter(Robot::isOperational)
                .ifPresent(Robot::turnLeft);
    }

    @Override
    public String getLabel() {
        return label;
    }
}
