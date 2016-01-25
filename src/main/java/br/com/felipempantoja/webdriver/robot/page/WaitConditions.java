package br.com.felipempantoja.webdriver.robot.page;

import org.openqa.selenium.support.ui.ExpectedCondition;

public class WaitConditions<T> {

    private ExpectedCondition<T> expectedCondition;

    public WaitConditions(ExpectedCondition<T> expectedCondition) {
        this.expectedCondition = expectedCondition;
    }

    public ExpectedCondition<T> get() {
        return expectedCondition;
    }
}
