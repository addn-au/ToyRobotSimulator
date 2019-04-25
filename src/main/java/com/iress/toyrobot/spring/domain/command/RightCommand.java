package com.iress.toyrobot.spring.domain.command;

import com.iress.toyrobot.spring.domain.robot.Robot;
import com.iress.toyrobot.spring.domain.types.CommandType;

import java.util.Optional;

public class RightCommand implements Command {
    private String label;

    public RightCommand() {
        this.label = CommandType.RIGHT.getName();
    }

    @Override
    public void execute(Robot robot) {
        Optional.ofNullable(robot)
                .filter(Robot::isOperational)
                .ifPresent(Robot::turnRight);
    }

    @Override
    public String getLabel() {
        return label;
    }
}
