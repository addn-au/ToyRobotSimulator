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
import static junit.framework.TestCase.assertNull;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class MoveCommandTest {
    private Robot robot;
    private Point point;
    private Command moveCommand;
    private Integer maxX;
    private Integer maxY;

    @Before
    public void setup() {
        robot = new ToyRobot(new SquareTable(DEFAULT_TOP_SIZE));
        point = new Point(1,2);
        maxX = maxY = DEFAULT_TOP_SIZE;
        robot.setPosition(point);
        robot.setCardinalDirection(CardinalDirection.NORTH);
        moveCommand = new MoveCommand();
    }

    @Test
    public void shouldReturnCorrectLabel() {
        assertThat(moveCommand.getLabel(), is(CommandType.MOVE.getName()));
    }

    @Test
    public void shouldNotMoveWhenRobotHasNoValidMap() {
        ToyRobot robotWithoutAValidMap = new ToyRobot();
        robotWithoutAValidMap.setPosition(point);
        robotWithoutAValidMap.setCardinalDirection(CardinalDirection.WEST);

        moveCommand.execute(robotWithoutAValidMap);

        assertNull(robotWithoutAValidMap.getPosition());
    }

    @Test
    public void shouldNotMoveWhenRobotHasNotBeenPlacedOnTheTable() {
        ToyRobot newRobot = new ToyRobot(new SquareTable(DEFAULT_TOP_SIZE));
        newRobot.setCardinalDirection(CardinalDirection.WEST);

        moveCommand.execute(newRobot);

        assertNull(newRobot.getPosition());
    }

    @Test
    public void shouldMoveOneUnitForwardWhileFacingNorth() {
        robot.setPosition(new Point(0,1));
        robot.setCardinalDirection(CardinalDirection.NORTH);

        moveCommand.execute(robot);

        assertThat(robot.getPosition().y, is(2));
    }

    @Test
    public void shouldMoveOneUnitForwardWhileFacingEast() {
        robot.setPosition(new Point(0,1));
        robot.setCardinalDirection(CardinalDirection.EAST);

        moveCommand.execute(robot);

        assertThat(robot.getPosition().x, is(1));
    }

    @Test
    public void shouldMoveOneUnitForwardWhileFacingSouth() {
        robot.setPosition(new Point(0,1));
        robot.setCardinalDirection(CardinalDirection.SOUTH);

        moveCommand.execute(robot);

        assertThat(robot.getPosition().y, is(0));
    }

    @Test
    public void shouldMoveOneUnitForwardWhileFacingWest() {
        robot.setPosition(new Point(2,1));
        robot.setCardinalDirection(CardinalDirection.WEST);

        moveCommand.execute(robot);

        assertThat(robot.getPosition().x, is(1));
    }

    @Test
    public void shouldNotMoveOffTheTableBoundaryWhileFacingNorth() {
        robot.setPosition(new Point(0,DEFAULT_TOP_SIZE));
        robot.setCardinalDirection(CardinalDirection.NORTH);

        moveCommand.execute(robot);

        assertThat(robot.getPosition().y, is(5));
    }

    @Test
    public void shouldNotMoveOffTheTableBoundaryWhileFacingEast() {
        robot.setPosition(new Point(maxX, DEFAULT_TOP_SIZE));
        robot.setCardinalDirection(CardinalDirection.EAST);

        moveCommand.execute(robot);

        assertThat(robot.getPosition().x, is(5));
    }

    @Test
    public void shouldNotMoveOffTheTableBoundaryWhileFacingSouth() {
        robot.setPosition(new Point(maxY, 0));
        robot.setCardinalDirection(CardinalDirection.SOUTH);

        moveCommand.execute(robot);

        assertThat(robot.getPosition().y, is(0));
        assertThat(robot.getPosition().y, is(0));
    }

    @Test
    public void shouldNotMoveOffTheTableBoundaryWhileFacingWest() {
        robot.setPosition(new Point(0, 0));
        robot.setCardinalDirection(CardinalDirection.WEST);

        moveCommand.execute(robot);

        assertThat(robot.getPosition().y, is(0));
        assertThat(robot.getPosition().y, is(0));
    }
}
