package br.com.felipempantoja.webdriver.robot.page;

import br.com.felipempantoja.webdriver.robot.exception.AuthenticationException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class PageObject {

    private static final Logger LOGGER = LoggerFactory.getLogger(PageObject.class);

    protected WebDriver driver;
    protected WebDriverWait wait;

    public static <E extends PageObject> E init(WebDriver driver, Class<E> pageClass) {
        E pageObject = PageFactory.initElements(driver, pageClass);

        pageObject.driver = driver;
        pageObject.wait = new WebDriverWait(driver, 3);

        if(!pageObject.isAuthenticated()) {
            LOGGER.error("User needs to be authenticated to access {}", pageObject.getPage());
            throw new AuthenticationException("User needs to be authenticated to access " + pageObject.getPage());
        }

        LOGGER.info("Navigating to {}", pageObject.getPage());
        pageObject.driver.navigate().to(pageObject.getPage());

        return pageObject;
    }

    public <T extends PageObject>T go(Class<T> target) {
        return PageObject.init(driver, target);
    }

    public PageElement byName(String name) {
        return new PageElement(driver, By.name(name));
    }

    public PageElement byCss(String cssSelector) {
        return new PageElement(driver, By.cssSelector(cssSelector));
    }

    public WaitConditions forElement(PageElement element) {
        return new WaitConditions(ExpectedConditions.presenceOfElementLocated(element.selector()));
    }

    public WaitConditions forUrl(String url) {
        return new WaitConditions(ExpectedConditions.urlContains(url));
    }

    public void wait(WaitConditions waitConditions) {
        wait.until(waitConditions.get());
    }

    public String title() {
        return driver.getTitle();
    }

    public void close() {
        driver.quit();
    }

    protected abstract String getPage();

    protected abstract boolean isAuthenticated();
}
