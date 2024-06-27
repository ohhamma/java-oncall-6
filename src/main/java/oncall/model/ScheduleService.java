package oncall.model;

public class ScheduleService {
    private WorkMonth workMonth;

    public void initWorkMonth(int month, int dayOfWeek) {
        this.workMonth = WorkMonth.of(month, dayOfWeek);
    }
}
