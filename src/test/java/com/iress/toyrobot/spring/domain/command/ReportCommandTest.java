package com.iress.toyrobot.spring.domain.command;

import com.iress.toyrobot.spring.domain.command.Command;
import com.iress.toyrobot.spring.domain.command.PlaceCommand;
import com.iress.toyrobot.spring.domain.command.ReportCommand;
import com.iress.toyrobot.spring.domain.map.SquareTable;
import com.iress.toyrobot.spring.domain.robot.Robot;
import com.iress.toyrobot.spring.domain.robot.ToyRobot;
import com.iress.toyrobot.spring.domain.types.CardinalDirection;
import com.iress.toyrobot.spring.domain.types.CommandType;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.iress.toyrobot.spring.domain.map.SquareTable.DEFAULT_TOP_SIZE;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class ReportCommandTest {
    private Robot robot;
    private Point point;
    private Command reportCommand;

    @Before
    public void setup() {
        robot = new ToyRobot(new SquareTable(DEFAULT_TOP_SIZE));
        point = new Point(1,2);
        robot.setPosition(point);
        robot.setCardinalDirection(CardinalDirection.NORTH);
        reportCommand = new ReportCommand();
    }

    @Test
    public void shouldReturnCorrectLabel() {
        assertThat(reportCommand.getLabel(), CoreMatchers.is(CommandType.REPORT.getName()));
    }

    @Test
    public void shouldOutputTheCorrectPositionAndDirectionOfTheRobot() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        reportCommand.execute(robot);

        assertThat(outContent.toString(), containsString("Output: 1,2,NORTH"));
    }

    @Test
    public void shouldOutputEmptyStringWhenRobotHasNoValidMap() {
        ToyRobot robotWithoutAValidMap = new ToyRobot();
        robotWithoutAValidMap.setPosition(point);
        robotWithoutAValidMap.setCardinalDirection(CardinalDirection.NORTH);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        reportCommand.execute(robotWithoutAValidMap);

        assertThat(outContent.toString(), is(""));
    }

    @Test
    public void shouldNotOutputWhenRobotHasNotBeenPlacedOnTheTable() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Robot newRobot = new ToyRobot(new SquareTable(DEFAULT_TOP_SIZE));

        reportCommand.execute(newRobot);

        assertThat(outContent.toString(), is(""));
    }
}
