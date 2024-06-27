package oncall.model;

import oncall.model.exception.ErrorMessage;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.Month;

public class WorkMonth {
    private final Month month;
    private final DayOfWeek startDayOfWeek;

    private WorkMonth(final Month month, final DayOfWeek startDayOfWeek) {
        this.month = month;
        this.startDayOfWeek = startDayOfWeek;
    }

    public static WorkMonth of(final int month, final int startDayOfWeek) {
        try {
            return new WorkMonth(Month.of(month), DayOfWeek.of(startDayOfWeek));
        } catch (DateTimeException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getMessage());
        }
    }
}
