package br.com.felipempantoja.webdriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.IOException;

@Configuration
public class WebDriverConfiguration {

    @Bean(name = "webDriver")
    @Scope("prototype")
    @ConditionalOnProperty(name = "robot.driver", havingValue = "chrome")
    public WebDriver chrome() throws IOException {
        System.setProperty("webdriver.chrome.driver", this.getClass().getResource("/chromedriver").getPath());
        return new ChromeDriver();
    }

    @Bean(name = "webDriver")
    @Scope("prototype")
    @ConditionalOnProperty(name = "robot.driver", havingValue = "htmlunit")
    public WebDriver htmlUnit() {
        return new HtmlUnitDriver(BrowserVersion.CHROME);
    }
}
