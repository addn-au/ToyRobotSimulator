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
public class LeftCommandTest {
    private Robot robot;
    private Point point;
    private Command leftCommand;

    @Before
    public void setup() {
        robot = new ToyRobot(new SquareTable(DEFAULT_TOP_SIZE));
        point = new Point(1,2);
        robot.setPosition(point);
        robot.setCardinalDirection(CardinalDirection.NORTH);
        leftCommand = new LeftCommand();
    }

    @Test
    public void shouldReturnCorrectLabel() {
        assertThat(leftCommand.getLabel(), is(CommandType.LEFT.getName()));
    }

    @Test
    public void shouldNotTurnLeftWhenRobotHasNoValidMap() {
        ToyRobot robotWithoutAValidMap = new ToyRobot();
        robotWithoutAValidMap.setPosition(point);
        robotWithoutAValidMap.setCardinalDirection(CardinalDirection.WEST);

        leftCommand.execute(robotWithoutAValidMap);

        assertThat(robotWithoutAValidMap.getCardinalDirection(), is(CardinalDirection.WEST));
    }

    @Test
    public void shouldNotTurnLeftWhenRobotHasNotBeenPlacedOnTheTable() {
        ToyRobot newRobot = new ToyRobot(new SquareTable(DEFAULT_TOP_SIZE));
        newRobot.setCardinalDirection(CardinalDirection.WEST);

        leftCommand.execute(newRobot);

        assertThat(newRobot.getCardinalDirection(), is(CardinalDirection.WEST));
    }

    @Test
    public void shouldTurnLeftToWestWhileFacingNorth() {
        robot.setCardinalDirection(CardinalDirection.NORTH);

        leftCommand.execute(robot);

        assertThat(robot.getCardinalDirection(), is(CardinalDirection.WEST));
    }

    @Test
    public void shouldTurnLeftToSouthWhileFacingWest() {
        robot.setCardinalDirection(CardinalDirection.WEST);

        leftCommand.execute(robot);

        assertThat(robot.getCardinalDirection(), is(CardinalDirection.SOUTH));
    }

    @Test
    public void shouldTurnLeftToEastWhileFacingSouth() {
        robot.setCardinalDirection(CardinalDirection.SOUTH);

        leftCommand.execute(robot);

        assertThat(robot.getCardinalDirection(), is(CardinalDirection.EAST));
    }

    @Test
    public void shouldTurnLeftToNorthWhileFacingSouth() {
        robot.setCardinalDirection(CardinalDirection.EAST);

        leftCommand.execute(robot);

        assertThat(robot.getCardinalDirection(), is(CardinalDirection.NORTH));
    }
}
