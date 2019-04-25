package com.iress.toyrobot.spring.domain.command;

import com.iress.toyrobot.spring.domain.map.SquareTable;
import com.iress.toyrobot.spring.domain.robot.Robot;
import com.iress.toyrobot.spring.domain.robot.ToyRobot;
import com.iress.toyrobot.spring.domain.types.CardinalDirection;
import com.iress.toyrobot.spring.domain.types.CommandType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.awt.*;

import static com.iress.toyrobot.spring.domain.map.SquareTable.DEFAULT_TOP_SIZE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class PlaceCommandTest {
    private Robot robot;
    private Point point;
    private Command placeCommand;
    private Integer maxX;
    private Integer maxY;

    @Before
    public void setup() {
        robot = new ToyRobot(new SquareTable(DEFAULT_TOP_SIZE));
        point = new Point(1,2);
        maxX = maxY = DEFAULT_TOP_SIZE;
        placeCommand = new PlaceCommand(point, CardinalDirection.NORTH);
    }

    @Test
    public void shouldReturnCorrectLabel() {
        assertThat(placeCommand.getLabel(), is(CommandType.PLACE.getName()));
    }

    @Test
    public void shouldNotPlaceRobotOffTable() {
        whenPositionIsOffTable();

        placeCommand.execute(robot);

        assertNull(robot.getPosition());
    }

    @Test
    public void shouldNotPlaceRobotThatHasNoValidMap() {
        ToyRobot robotWithoutAValidMap = new ToyRobot();
        robotWithoutAValidMap.setPosition(point);
        robotWithoutAValidMap.setCardinalDirection(CardinalDirection.SOUTH);

        placeCommand.execute(robot);

        assertNull(robotWithoutAValidMap.getPosition());
    }

    @Test
    public void shouldSetRobotToTheCorrectPosition() {
        placeCommand.execute(robot);

        assertThat(robot.getPosition(), is(point));
    }

    @Test
    public void shouldSetRobotToNorthDirection() {
        placeCommand.execute(robot);

        assertThat(robot.getCardinalDirection(), is(CardinalDirection.NORTH));
    }

    @Test
    public void shouldSetRobotToEastDirection() {
        placeCommand = new PlaceCommand(point, CardinalDirection.EAST);
        placeCommand.execute(robot);

        assertThat(robot.getCardinalDirection(), is(CardinalDirection.EAST));
    }

    @Test
    public void shouldSetRobotToSouthDirection() {
        placeCommand = new PlaceCommand(point, CardinalDirection.SOUTH);
        placeCommand.execute(robot);

        assertThat(robot.getCardinalDirection(), is(CardinalDirection.SOUTH));
    }

    @Test
    public void shouldSetRobotToWestDirection() {
        placeCommand = new PlaceCommand(point, CardinalDirection.WEST);
        placeCommand.execute(robot);

        assertThat(robot.getCardinalDirection(), is(CardinalDirection.WEST));
    }

    private void whenPositionIsOffTable() {
        Point offTablePoint = new Point(maxX + 1, maxY + 2);
        placeCommand = new PlaceCommand(offTablePoint, CardinalDirection.NORTH);
    }
}
