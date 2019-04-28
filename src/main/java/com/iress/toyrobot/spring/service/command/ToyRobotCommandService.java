package com.iress.toyrobot.spring.service.command;

import com.iress.toyrobot.spring.domain.command.*;
import com.iress.toyrobot.spring.domain.types.CardinalDirection;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("toyRobotCommandService")
public class ToyRobotCommandService implements CommandService {
    private final static String PLACE_COMMAND_PATTERN = "^(PLACE)[  ]+(\\d+)[ ]*,[ ]*(\\d+)[ ]*,[ ]*(NORTH|SOUTH|EAST|WEST)$";
    private final static String MOVE_COMMAND_PATTERN = "^MOVE$";
    private final static String LEFT_COMMAND_PATTERN = "^LEFT$";
    private final static String RIGHT_COMMAND_PATTERN = "^RIGHT$";
    private final static String REPORT_COMMAND_PATTERN = "^REPORT$";

    @Override
    public final Optional<Command> createCommand(String inputString) {
        try {
            if (isPlaceCommand(inputString)) {
                return makePlaceCommand(inputString);
            } else if (isMoveCommand(inputString)) {
                return makeMoveCommand();
            } else if (isLeftCommand(inputString)) {
                return makeLeftCommand();
            } else if (isRightCommand(inputString)) {
                return makeRightCommand();
            } else if (isReportCommand(inputString)) {
                return makeReportCommand();
            }
        } catch (IllegalStateException e) {
            //Ignore input string from cli for which command was not found.
        }

        return Optional.empty();
    }

    private boolean isPlaceCommand(String inputString) {
        return match(PLACE_COMMAND_PATTERN, inputString).find();
    }

    private boolean isMoveCommand(String inputString) {
        return match(MOVE_COMMAND_PATTERN, inputString).find();
    }

    private boolean isLeftCommand(String inputString) {
        return match(LEFT_COMMAND_PATTERN, inputString).find();
    }

    private boolean isRightCommand(String inputString) {
        return match(RIGHT_COMMAND_PATTERN, inputString).find();
    }

    private boolean isReportCommand(String inputString) {
        return match(REPORT_COMMAND_PATTERN, inputString).find();
    }

    private Optional<Command> makePlaceCommand(String inputString) throws IllegalStateException {
        Matcher matcher = match(PLACE_COMMAND_PATTERN, inputString);
        matcher.find();
        Point position = new Point(Integer.valueOf(matcher.group(2)), Integer.valueOf(matcher.group(3)));
        CardinalDirection cardinalDirection = CardinalDirection.valueOf(matcher.group(4));
        return Optional.of(new PlaceCommand(position, cardinalDirection));
    }

    private Optional<Command> makeMoveCommand() throws IllegalStateException {
        return Optional.of(new MoveCommand());
    }

    private Optional<Command> makeLeftCommand() throws IllegalStateException {
        return Optional.of(new LeftCommand());
    }

    private Optional<Command> makeRightCommand() throws IllegalStateException {
        return Optional.of(new RightCommand());
    }

    private Optional<Command> makeReportCommand() throws IllegalStateException {
        return Optional.of(new ReportCommand());
    }

    private Matcher match(String patternInString, String testAgainst) throws IllegalStateException {
        Pattern pattern =
                Pattern.compile(Optional.ofNullable(patternInString).orElse(""));

        return pattern.matcher(Optional.ofNullable(testAgainst).orElse(""));
    }
}