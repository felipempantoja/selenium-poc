package br.com.felipempantoja.webdriver.robot.page.google.inbox;

import br.com.felipempantoja.webdriver.robot.dto.InboxEmail;
import br.com.felipempantoja.webdriver.robot.page.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class HomeInboxGooglePage extends PageObject {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeInboxGooglePage.class);

    @FindBy(css = "span[email]")
    private List<WebElement> senders;

    @Override
    protected String getPage() {
        return "http://gmail.com";
    }

    @Override
    protected boolean isAuthenticated() {
        return driver.getCurrentUrl().contains("myaccount.google.com");
    }

    public List<InboxEmail> emails() {
        LOGGER.info("Retrieving emails from inbox");

        List<InboxEmail> emails = new ArrayList<>();
        for(WebElement sender : senders) {
            InboxEmail email = new InboxEmail();
            email.setSender(sender.getText());
            emails.add(email);
        }
//        driver.findElements(By.cssSelector("span[email]")).get(3).findElement(By.xpath("./../../..")).findElements(By.tagName("div")).get(9).getText();

        LOGGER.info("Emails found: {}", emails);
        return emails;
    }

    public int total() {
        return 0;
    }

    public InboxEmail openEmail(int index) {
        return new InboxEmail();
    }
}
