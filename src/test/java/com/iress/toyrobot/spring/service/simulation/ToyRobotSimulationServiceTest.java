package com.iress.toyrobot.spring.service.simulation;

import com.iress.toyrobot.spring.domain.command.*;
import com.iress.toyrobot.spring.domain.map.SquareTable;
import com.iress.toyrobot.spring.domain.robot.Robot;
import com.iress.toyrobot.spring.domain.robot.ToyRobot;
import com.iress.toyrobot.spring.domain.types.CardinalDirection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

import static com.iress.toyrobot.spring.domain.map.SquareTable.DEFAULT_TOP_SIZE;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ToyRobotSimulationServiceTest {
    @Mock
    Robot robot;

    @Mock
    Commands commands;

    @InjectMocks
    ToyRobotSimulationService toyRobotSimulationService;

    @Test
    public void runShouldReturnTrue() {
        when(commands.length()).thenReturn(1);
        when(commands.isExecutable()).thenReturn(true);

        assertThat(toyRobotSimulationService.run(), is(true));
    }

    @Test
    public void runShouldReturnFalseWhenRobotIsNull() {
        Robot nullRobot = null;
        toyRobotSimulationService.setup(nullRobot, commands);

        assertThat(toyRobotSimulationService.run(), is(false));
    }

    @Test
    public void runShouldReturnFalseWhenCommandsListIsNull() {
        Commands nullCommands = null;
        toyRobotSimulationService.setup(robot, nullCommands);

        assertThat(toyRobotSimulationService.run(), is(false));
    }

    @Test
    public void runShouldReturnFalseWhenCommandsListIsEmpty() {
        when(commands.length()).thenReturn(0);

        assertThat(toyRobotSimulationService.run(), is(false));
    }

    @Test
    public void aCommandShouldExecuteOnceWhenSimulationIsReady() {
        whenSimulationIsReady();

        Command command = mock(PlaceCommand.class);

        Stream<Command> commandStream = Stream.of(command);
        when(commands.stream()).thenReturn(commandStream);

        toyRobotSimulationService.run();

        verify(command, times(1)).execute(robot);
    }

    @Test
    public void robotShouldBeFacingNorthAtX0Y1() {
        Robot realRobot = new ToyRobot(new SquareTable(DEFAULT_TOP_SIZE));
        Commands realCommands = new ToyRobotSimulatorCommands();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        realCommands.add(new PlaceCommand(new Point(0,0), CardinalDirection.NORTH));
        realCommands.add(new MoveCommand());
        realCommands.add(new ReportCommand());

        ToyRobotSimulationService realToyRobotSimulationService = new ToyRobotSimulationService();

        realToyRobotSimulationService.setup(realRobot, realCommands);
        realToyRobotSimulationService.run();

        assertThat(outContent.toString(), containsString("Output: 0,1,NORTH"));
    }

    @Test
    public void robotShouldBeFacingNorthAtX3Y3() {
        Robot realRobot = new ToyRobot(new SquareTable(DEFAULT_TOP_SIZE));
        Commands realCommands = new ToyRobotSimulatorCommands();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        realCommands.add(new PlaceCommand(new Point(1,2), CardinalDirection.EAST));
        realCommands.add(new MoveCommand());
        realCommands.add(new MoveCommand());
        realCommands.add(new LeftCommand());
        realCommands.add(new MoveCommand());
        realCommands.add(new ReportCommand());

        ToyRobotSimulationService realToyRobotSimulationService = new ToyRobotSimulationService();

        realToyRobotSimulationService.setup(realRobot, realCommands);
        realToyRobotSimulationService.run();

        assertThat(outContent.toString(), containsString("Output: 3,3,NORTH"));
    }

    @Test
    public void shouldNotOutputWhenRobotHasNotBeenPlacedOnTheTable() {
        Robot realRobot = new ToyRobot(new SquareTable(DEFAULT_TOP_SIZE));
        Commands realCommands = new ToyRobotSimulatorCommands();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        realCommands.add(new MoveCommand());
        realCommands.add(new MoveCommand());
        realCommands.add(new LeftCommand());
        realCommands.add(new MoveCommand());
        realCommands.add(new RightCommand());
        realCommands.add(new LeftCommand());
        realCommands.add(new ReportCommand());

        ToyRobotSimulationService realToyRobotSimulationService = new ToyRobotSimulationService();

        realToyRobotSimulationService.setup(realRobot, realCommands);
        realToyRobotSimulationService.run();

        assertThat(outContent.toString(), containsString(""));
    }

    @Test
    public void robotShouldIgnoreCommandsBeforeBeingPlacedOnTheTableAndFacingWestAtX0Y0() {
        Robot realRobot = new ToyRobot(new SquareTable(DEFAULT_TOP_SIZE));
        Commands realCommands = new ToyRobotSimulatorCommands();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        realCommands.add(new MoveCommand());
        realCommands.add(new MoveCommand());
        realCommands.add(new LeftCommand());
        realCommands.add(new MoveCommand());
        realCommands.add(new RightCommand());
        realCommands.add(new PlaceCommand(new Point(0,0), CardinalDirection.NORTH));
        realCommands.add(new LeftCommand());
        realCommands.add(new ReportCommand());

        ToyRobotSimulationService realToyRobotSimulationService = new ToyRobotSimulationService();

        realToyRobotSimulationService.setup(realRobot, realCommands);
        realToyRobotSimulationService.run();

        assertThat(outContent.toString(), containsString("Output: 0,0,WEST"));
    }

    private void whenSimulationIsReady() {
        when(commands.length()).thenReturn(1);
        when(commands.isExecutable()).thenReturn(true);
    }
}
