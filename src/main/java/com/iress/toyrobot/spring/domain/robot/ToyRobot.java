package com.iress.toyrobot.spring.domain.robot;

import com.iress.toyrobot.spring.domain.types.CardinalDirection;
import com.iress.toyrobot.spring.domain.map.TwoDimensionalMap;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.Optional;

@Component("toyRobot")
public class ToyRobot implements Robot {
    private Point position;
    private CardinalDirection cardinalDirection;
    private TwoDimensionalMap map;

    public ToyRobot() {}

    public ToyRobot(final TwoDimensionalMap map) {
        this.map = map;
    }

    @Override
    public void turnLeft() {
        if (isOperational()) {
            switch (cardinalDirection) {
                case NORTH:
                    cardinalDirection = CardinalDirection.WEST;
                    break;
                case SOUTH:
                    cardinalDirection = CardinalDirection.EAST;
                    break;
                case EAST:
                    cardinalDirection = CardinalDirection.NORTH;
                    break;
                case WEST:
                    cardinalDirection = CardinalDirection.SOUTH;
                    break;
            }
        }
    }

    @Override
    public void turnRight() {
        if (isOperational()) {
            switch (cardinalDirection) {
                case NORTH:
                    cardinalDirection = CardinalDirection.EAST;
                    break;
                case SOUTH:
                    cardinalDirection = CardinalDirection.WEST;
                    break;
                case EAST:
                    cardinalDirection = CardinalDirection.SOUTH;
                    break;
                case WEST:
                    cardinalDirection = CardinalDirection.NORTH;
                    break;
            }
        }
    }

    @Override
    public void move() {
        Point trialPosition = tryNextMove();
        if (map.isInBound(trialPosition)) {
            Optional.ofNullable(trialPosition).ifPresent(tP -> {
                position.x = tP.x;
                position.y = tP.y;
            });
        }
    }

    @Override
    public Point tryNextMove() {
        Point trialPosition = new Point();
        if (isOperational()) {
            trialPosition.x = position.x;
            trialPosition.y = position.y;

            switch (cardinalDirection) {
                case NORTH:
                    trialPosition.y += 1;
                    break;
                case SOUTH:
                    trialPosition.y -= 1;
                    break;
                case EAST:
                    trialPosition.x += 1;
                    break;
                case WEST:
                    trialPosition.x -= 1;
                    break;
            }
        }
        return trialPosition;
    }

    @Override
    public void setPosition(Point position) {
        Optional.ofNullable(position)
                .filter(p -> (Optional.ofNullable(map).filter(m -> m.isInBound(p)).isPresent()))
                .ifPresent(p -> this.position = p);
    }

    @Override
    public void setCardinalDirection(CardinalDirection cardinalDirection) {
        Optional.ofNullable(cardinalDirection).ifPresent(cd -> this.cardinalDirection = cd);
    }

    @Override
    public boolean isOperational() {
        return Optional.ofNullable(map).filter(TwoDimensionalMap::isValid).isPresent()
                && Optional.ofNullable(position).isPresent()
                && Optional.ofNullable(cardinalDirection).isPresent();
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public CardinalDirection getCardinalDirection() {
        return cardinalDirection;
    }
}
