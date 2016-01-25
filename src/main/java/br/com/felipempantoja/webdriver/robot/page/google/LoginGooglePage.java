package br.com.felipempantoja.webdriver.robot.page.google;

import br.com.felipempantoja.webdriver.robot.exception.AuthenticationException;
import br.com.felipempantoja.webdriver.robot.page.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LoginGooglePage extends PageObject {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginGooglePage.class);

    @FindBy(name = "Email")
    private WebElement emailField;

    @FindBy(name = "Passwd")
    private WebElement passwordField;

    @Override
    protected String getPage() {
        return "https://accounts.google.com";
    }

    @Override
    protected boolean isAuthenticated() {
        return true;
    }

    public LoginGooglePage loginAs(String email, String password) {
        try {
            LOGGER.info("Trying to login as {}", email);

            byName("Email").type(email).submit();
            wait(forElement(byName("Passwd")));
            byName("Passwd").type(password).submit();
            wait(forUrl("myaccount.google.com"));

//            emailField.sendKeys(email);
//            emailField.submit();
//
//
//            wait.until(ExpectedConditions.presenceOfElementLocated(By.name("Passwd")));
//
//            passwordField.sendKeys(password);
//            passwordField.submit();
//
//            wait.until(ExpectedConditions.urlContains("myaccount.google.com"));

            LOGGER.info("Login as {} successful", email);
        } catch (Exception e) {
            String errorMessage = null;
            for(WebElement errorElement : driver.findElements(By.className("error-msg"))) {
                if(!errorElement.getText().isEmpty()) {
                    errorMessage = errorElement.getText();
                }
            }
            LOGGER.info("Failed to login as {}: {}", email, errorMessage);
            throw new AuthenticationException(errorMessage, e);
        }

        return this;
    }
}