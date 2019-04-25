package com.iress.toyrobot.spring.service.command;

import com.iress.toyrobot.spring.domain.command.Command;
import com.iress.toyrobot.spring.domain.types.CommandType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

@RunWith(MockitoJUnitRunner.class)
public class ToyRobotCommandServiceTest {
    private ToyRobotCommandService toyRobotSimulatorCommandService;

    @Before
    public void setup() {
        toyRobotSimulatorCommandService = new ToyRobotCommandService();
    }

    @Test
    public void shouldCreatePlaceCommand() {
        Optional<Command> placeCommand = toyRobotSimulatorCommandService.createCommand("PLACE 0, 1, NORTH");

        assertThat(placeCommand.get().getLabel(), is(CommandType.PLACE.getName()));
    }

    @Test
    public void shouldNotCreatePlaceCommand() {
        Optional<Command> placeCommand = toyRobotSimulatorCommandService.createCommand("PLACE 0, , NORTH");

        assertThat(placeCommand.isPresent(), is(false));
    }

    @Test
    public void shouldCreateMoveCommand() {
        Optional<Command> moveCommand = toyRobotSimulatorCommandService.createCommand("MOVE");

        assertThat(moveCommand.get().getLabel(), is(CommandType.MOVE.getName()));
    }

    @Test
    public void shouldCreateLeftCommand() {
        Optional<Command> moveCommand = toyRobotSimulatorCommandService.createCommand("LEFT");

        assertThat(moveCommand.get().getLabel(), is(CommandType.LEFT.getName()));
    }

    @Test
    public void shouldCreateRightCommand() {
        Optional<Command> moveCommand = toyRobotSimulatorCommandService.createCommand("MOVE");

        assertThat(moveCommand.get().getLabel(), is(CommandType.MOVE.getName()));
    }

    @Test
    public void shouldCreateReportCommand() {
        Optional<Command> moveCommand = toyRobotSimulatorCommandService.createCommand("REPORT");

        assertThat(moveCommand.get().getLabel(), is(CommandType.REPORT.getName()));
    }
}
