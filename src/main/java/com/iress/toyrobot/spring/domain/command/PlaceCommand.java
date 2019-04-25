package com.iress.toyrobot.spring.domain.command;

import com.iress.toyrobot.spring.domain.types.CardinalDirection;
import com.iress.toyrobot.spring.domain.robot.Robot;
import com.iress.toyrobot.spring.domain.types.CommandType;

import java.awt.*;
import java.util.Optional;

public class PlaceCommand implements Command {
    private Point position;
    private CardinalDirection cardinalDirection;
    private String label;

    public PlaceCommand(Point position, CardinalDirection cardinalDirection) {
        this.position = position;
        this.cardinalDirection = cardinalDirection;
        this.label = CommandType.PLACE.getName();
    }

    @Override
    public void execute(Robot robot) {
        Optional.ofNullable(robot)
                .ifPresent(r -> {
                    r.setPosition(position);
                    r.setCardinalDirection(cardinalDirection);
                });
    }

    @Override
    public String getLabel() {
        return label;
    }
}
