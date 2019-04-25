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

@RunWith(MockitoJUnitRunner.class)
public class RightCommandTest {
    private Robot robot;
    private Point point;
    private Command rightCommand;

    @Before
    public void setup() {
        robot = new ToyRobot(new SquareTable(DEFAULT_TOP_SIZE));
        point = new Point(1,2);
        robot.setPosition(point);
        robot.setCardinalDirection(CardinalDirection.NORTH);
        rightCommand = new RightCommand();
    }

    @Test
    public void shouldReturnCorrectLabel() {
        assertThat(rightCommand.getLabel(), is(CommandType.RIGHT.getName()));
    }

    @Test
    public void shouldNotTurnRightWhenRobotHasNoValidMap() {
        ToyRobot robotWithoutAValidMap = new ToyRobot();
        robotWithoutAValidMap.setPosition(point);
        robotWithoutAValidMap.setCardinalDirection(CardinalDirection.WEST);

        rightCommand.execute(robotWithoutAValidMap);

        assertThat(robotWithoutAValidMap.getCardinalDirection(), is(CardinalDirection.WEST));
    }

    @Test
    public void shouldNotTurnRightWhenRobotHasNotBeenPlacedOnTheTable() {
        ToyRobot newRobot = new ToyRobot(new SquareTable(DEFAULT_TOP_SIZE));
        newRobot.setCardinalDirection(CardinalDirection.WEST);

        rightCommand.execute(newRobot);

        assertThat(newRobot.getCardinalDirection(), is(CardinalDirection.WEST));
    }

    @Test
    public void shouldTurnRightToEastWhileFacingNorth() {
        robot.setCardinalDirection(CardinalDirection.NORTH);

        rightCommand.execute(robot);

        assertThat(robot.getCardinalDirection(), is(CardinalDirection.EAST));
    }

    @Test
    public void shouldTurnRightToSouthWhileFacingEast() {
        robot.setCardinalDirection(CardinalDirection.EAST);

        rightCommand.execute(robot);

        assertThat(robot.getCardinalDirection(), is(CardinalDirection.SOUTH));
    }

    @Test
    public void shouldTurnRightToWestWhileFacingSouth() {
        robot.setCardinalDirection(CardinalDirection.SOUTH);

        rightCommand.execute(robot);

        assertThat(robot.getCardinalDirection(), is(CardinalDirection.WEST));
    }

    @Test
    public void shouldTurnRightToNorthWhileFacingWest() {
        robot.setCardinalDirection(CardinalDirection.WEST);

        rightCommand.execute(robot);

        assertThat(robot.getCardinalDirection(), is(CardinalDirection.NORTH));
    }
}
