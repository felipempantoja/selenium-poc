package br.com.felipempantoja.webdriver.robot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageElement {

    private final WebDriver driver;
    private By selector;
    private WebElement element;

    PageElement(WebDriver driver, By selector) {
        this.driver = driver;
        this.selector = selector;
    }

    WebElement element() {
        if(element != null) {
            return element;
        }
        return element = driver.findElement(selector);
    }

    By selector() {
        return selector;
    }

    public PageElement type(String value) {
        element().sendKeys(value);
        return this;
    }

    public PageElement submit() {
        element().submit();
        return this;
    }

    public PageElement parent() {
        return new PageElement(driver, By.xpath("./.."));
    }

    public PageElement closest(PageElement element) {
        PageElement parent = this.parent();
        //fazer o find correto (nao apenas by tagname)
        if(parent.element().getTagName().equals(element.element().getTagName())) {
            return parent;
        }
        return closest(parent);
    }
}
