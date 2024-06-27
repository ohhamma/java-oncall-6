package oncall.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String INPUT_MONTH_MESSAGE = "비상 근무를 배정할 월과 시작 요일을 입력하세요> ";

    public String readMonth() {
        System.out.print(INPUT_MONTH_MESSAGE);
        return Console.readLine();
    }
}
