package com.iress.toyrobot.spring.domain.command;

import com.iress.toyrobot.spring.domain.robot.Robot;
import com.iress.toyrobot.spring.domain.types.CommandType;

import java.util.Optional;

public class ReportCommand implements Command{
    private String label;

    public ReportCommand() {
        this.label = CommandType.REPORT.getName();
    }

    @Override
    public void execute(Robot robot) {
        Optional.ofNullable(robot)
                .filter(Robot::isOperational)
                .ifPresent(r -> {
                    int xPosition = r.getPosition().x;
                    int yPosition = r.getPosition().y;
                    String cardinalDirection = r.getCardinalDirection().toString();

                    System.out.print("Output: " + xPosition + "," + yPosition + "," + cardinalDirection);
                });
    }

    @Override
    public String getLabel() {
        return label;
    }
}
