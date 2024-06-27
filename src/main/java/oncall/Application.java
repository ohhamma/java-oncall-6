package oncall;

import oncall.controller.ScheduleController;
import oncall.model.ScheduleService;
import oncall.view.InputView;
import oncall.view.OutputView;

public class Application {
    public static void main(String[] args) {
        ScheduleController scheduleController = new ScheduleController(new InputView(), new OutputView(), new ScheduleService());
        scheduleController.run();
    }
}
