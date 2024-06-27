package oncall.controller;

import oncall.model.ScheduleService;
import oncall.model.exception.ErrorMessage;
import oncall.view.InputView;
import oncall.view.OutputView;

import java.util.Arrays;
import java.util.List;

public class ScheduleController {
    private static final List<String> DAYS_OF_WEEK = List.of("월", "화", "수", "목", "금", "토", "일");
    private static final String SPLITERATOR = ",";

    private final InputView inputView;
    private final OutputView outputView;
    private final ScheduleService scheduleService;

    public ScheduleController(final InputView inputView, final OutputView outputView, final ScheduleService scheduleService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.scheduleService = scheduleService;
    }

    public void run() {
        inputMonth();
    }

    public void inputMonth() {
        try {
            String input = inputView.readMonth();
            validateMonthInput(input);
            List<String> result = Arrays.stream(input.split(SPLITERATOR)).toList();
            int month = parseMonth(result.get(0));
            int dayOfWeek = parseDayOfWeek(result.get(1));
            scheduleService.initWorkMonth(month, dayOfWeek);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            inputMonth();
        }
    }

    private void validateMonthInput(String input) {
        if (input.endsWith(SPLITERATOR)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getMessage());
        }
        List<String> result = Arrays.stream(input.split(SPLITERATOR)).toList();
        if (result.size() != 2) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getMessage());
        }
        validateMonth(result.get(0));
        validateDayOfWeek(result.get(1));
    }

    private void validateMonth(String month) {
        try {
            Integer.parseInt(month);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getMessage());
        }
    }

    private void validateDayOfWeek(String dayOfWeek) {
        boolean isInvalidDayOfWeek = DAYS_OF_WEEK.stream()
                .noneMatch(d -> d.equals(dayOfWeek));
        if (isInvalidDayOfWeek) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getMessage());
        }
    }

    private int parseMonth(String month) {
        return Integer.parseInt(month);
    }

    private int parseDayOfWeek(String dayOfWeek) {
        return DAYS_OF_WEEK.indexOf(dayOfWeek) + 1;
    }
}
