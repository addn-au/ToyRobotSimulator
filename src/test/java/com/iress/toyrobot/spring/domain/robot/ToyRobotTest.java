package com.iress.toyrobot.spring.domain.robot;

import com.iress.toyrobot.spring.domain.map.SquareTable;
import com.iress.toyrobot.spring.domain.types.CardinalDirection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.awt.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import static com.iress.toyrobot.spring.domain.map.SquareTable.DEFAULT_TOP_SIZE;

@RunWith(MockitoJUnitRunner.class)
public class ToyRobotTest {
    private Robot robot;
    private Point point;
    private Integer maxX;
    private Integer maxY;
    private Integer minX;
    private Integer minY;

    @Before
    public void setup() {
        robot = new ToyRobot(new SquareTable(DEFAULT_TOP_SIZE));
        point = new Point(3, 2);
        minX = minY = 0;
        maxX = maxY = DEFAULT_TOP_SIZE;
        robot.setPosition(point);
        robot.setCardinalDirection(CardinalDirection.NORTH);
    }

    @Test
    public void robotShouldBeOperable() {
        assertThat(robot.isOperational(), is(true));
    }

    @Test
    public void robotShouldNotBeOperableWhenRobotHasNoValidMap() {
        Robot newRobot = new ToyRobot();

        assertThat(newRobot.isOperational(), is(false));
    }

    @Test
    public void robotShouldNotBeOperableWhenRobotHasNotBeenPlacedOnTheTable() {
        Robot newRobot = new ToyRobot(new SquareTable(DEFAULT_TOP_SIZE));
        newRobot.setCardinalDirection(CardinalDirection.NORTH);

        assertThat(newRobot.isOperational(), is(false));
    }

    @Test
    public void robotShouldTurnLeftToWestWhileFacingNorth() {
        robot.setCardinalDirection(CardinalDirection.NORTH);

        robot.turnLeft();

        assertThat(robot.getCardinalDirection(), is(CardinalDirection.WEST));
    }

    @Test
    public void robotShouldTurnLeftToSouthWhileFacingWest() {
        robot.setCardinalDirection(CardinalDirection.WEST);

        robot.turnLeft();

        assertThat(robot.getCardinalDirection(), is(CardinalDirection.SOUTH));
    }

    @Test
    public void robotShouldTurnLeftToEastWhileFacingSouth() {
        robot.setCardinalDirection(CardinalDirection.SOUTH);

        robot.turnLeft();

        assertThat(robot.getCardinalDirection(), is(CardinalDirection.EAST));
    }

    @Test
    public void robotShouldTurnLeftToNorthWhileFacingSouth() {
        robot.setCardinalDirection(CardinalDirection.EAST);

        robot.turnLeft();

        assertThat(robot.getCardinalDirection(), is(CardinalDirection.NORTH));
    }

    @Test
    public void robotShouldTurnRightToEastWhileFacingNorth() {
        robot.setCardinalDirection(CardinalDirection.NORTH);

        robot.turnRight();

        assertThat(robot.getCardinalDirection(), is(CardinalDirection.EAST));
    }

    @Test
    public void robotShouldTurnRightToSouthWhileFacingEast() {
        robot.setCardinalDirection(CardinalDirection.EAST);

        robot.turnRight();

        assertThat(robot.getCardinalDirection(), is(CardinalDirection.SOUTH));
    }

    @Test
    public void robotShouldTurnRightToWestWhileFacingSouth() {
        robot.setCardinalDirection(CardinalDirection.SOUTH);

        robot.turnRight();

        assertThat(robot.getCardinalDirection(), is(CardinalDirection.WEST));
    }

    @Test
    public void robotShouldTurnRightToNorthWhileFacingWest() {
        robot.setCardinalDirection(CardinalDirection.WEST);

        robot.turnRight();

        assertThat(robot.getCardinalDirection(), is(CardinalDirection.NORTH));
    }

    @Test
    public void robotShouldMoveOneUnitForwardWhileFacingNorth() {
        robot.setPosition(new Point(0,1));
        robot.setCardinalDirection(CardinalDirection.NORTH);

        robot.move();

        assertThat(robot.getPosition().y, is(2));
    }

    @Test
    public void robotShouldMoveOneUnitForwardWhileFacingEast() {
        robot.setPosition(new Point(0,1));
        robot.setCardinalDirection(CardinalDirection.EAST);

        robot.move();

        assertThat(robot.getPosition().x, is(1));
    }

    @Test
    public void robotShouldMoveOneUnitForwardWhileFacingSouth() {
        robot.setPosition(new Point(0,1));
        robot.setCardinalDirection(CardinalDirection.SOUTH);

        robot.move();

        assertThat(robot.getPosition().y, is(0));
    }

    @Test
    public void robotShouldMoveOneUnitForwardWhileFacingWest() {
        robot.setPosition(new Point(2,1));
        robot.setCardinalDirection(CardinalDirection.WEST);

        robot.move();

        assertThat(robot.getPosition().x, is(1));
    }

    @Test
    public void robotShouldNotMoveOutOfTheTableBoundaryWhileFacingNorth() {
        robot.setPosition(new Point(0, maxY));
        robot.setCardinalDirection(CardinalDirection.NORTH);

        robot.move();

        assertThat(robot.getPosition().y, is(5));
    }

    @Test
    public void robotShouldNotMoveOutOfTheTableBoundaryWhileFacingEast() {
        robot.setPosition(new Point(maxX, maxY));
        robot.setCardinalDirection(CardinalDirection.EAST);

        robot.move();

        assertThat(robot.getPosition().x, is(5));
    }

    @Test
    public void robotShouldNotMoveOutOfTheTableBoundaryWhileFacingSouth() {
        robot.setPosition(new Point(maxX, 0));
        robot.setCardinalDirection(CardinalDirection.SOUTH);

        robot.move();

        assertThat(robot.getPosition().y, is(0));
        assertThat(robot.getPosition().y, is(0));
    }

    @Test
    public void robotShouldNotMoveOutOfTheTableBoundaryWhileFacingWest() {
        robot.setPosition(new Point(0, 0));
        robot.setCardinalDirection(CardinalDirection.WEST);

        robot.move();

        assertThat(robot.getPosition().y, is(0));
        assertThat(robot.getPosition().y, is(0));
    }

    @Test
    public void robotShouldTryNextMoveOneUnitForwardWhileFacingNorth() {
        robot.setPosition(new Point(0,1));
        robot.setCardinalDirection(CardinalDirection.NORTH);

        point = robot.tryNextMove();

        Point nextPoint = new Point(0,2);

        assertThat(point, is(nextPoint));
    }

    @Test
    public void robotShouldTryNextMoveOneUnitForwardWhileFacingEast() {
        robot.setPosition(new Point(0,1));
        robot.setCardinalDirection(CardinalDirection.EAST);

        point = robot.tryNextMove();

        Point nextPoint = new Point(1,1);

        assertThat(point, is(nextPoint));
    }

    @Test
    public void robotShouldTryNexMoveOneUnitForwardWhileFacingSouth() {
        robot.setPosition(new Point(0,1));
        robot.setCardinalDirection(CardinalDirection.SOUTH);

        point = robot.tryNextMove();

        Point nextPoint = new Point(0,0);

        assertThat(point, is(nextPoint));
    }

    @Test
    public void robotShouldTryNextMoveOneUnitForwardWhileFacingWest() {
        robot.setPosition(new Point(2,1));
        robot.setCardinalDirection(CardinalDirection.WEST);

        point = robot.tryNextMove();

        Point nextPoint = new Point(1,1);

        assertThat(point, is(nextPoint));
    }

    @Test
    public void robotPositionShouldBeSetToX5() {
        robot.setPosition(new Point(5,5));

        assertThat(robot.getPosition().x, is(5));
    }

    @Test
    public void robotPositionXShouldNotBeSetToGreaterThanMaxX() {
        Integer previousPositionX = robot.getPosition().x;

        robot.setPosition(new Point(maxX + 5,5));

        assertThat(robot.getPosition().x, is(previousPositionX));
    }

    @Test
    public void robotPositionXShouldNotBeSetToLessThanMinX() {
        Integer previousPositionX = robot.getPosition().x;

        robot.setPosition(new Point(minX - 5,5));

        assertThat(robot.getPosition().x, is(previousPositionX));
    }

    @Test
    public void robotPositionYShouldNotBeSetToGreaterThanMaxY() {
        Integer previousPositionY = robot.getPosition().y;

        robot.setPosition(new Point(5,maxY + 10));

        assertThat(robot.getPosition().y, is(previousPositionY));
    }


    @Test
    public void robotPositionYShouldNotBeSetToLessThanMinY() {
        Integer previousPositionY = robot.getPosition().y;

        robot.setPosition(new Point(5,minY - 10));

        assertThat(robot.getPosition().y, is(previousPositionY));
    }

    @Test
    public void robotShouldBeFacingNorth() {
        robot.setCardinalDirection(CardinalDirection.NORTH);

        assertThat(robot.getCardinalDirection(), is(CardinalDirection.NORTH));
    }

    @Test
    public void robotShouldBeFacingEast() {
        robot.setCardinalDirection(CardinalDirection.EAST);

        assertThat(robot.getCardinalDirection(), is(CardinalDirection.EAST));
    }

    @Test
    public void robotShouldBeFacingSouth() {
        robot.setCardinalDirection(CardinalDirection.SOUTH);

        assertThat(robot.getCardinalDirection(), is(CardinalDirection.SOUTH));
    }

    @Test
    public void robotShouldBeFacingWest() {
        robot.setCardinalDirection(CardinalDirection.WEST);

        assertThat(robot.getCardinalDirection(), is(CardinalDirection.WEST));
    }
}
