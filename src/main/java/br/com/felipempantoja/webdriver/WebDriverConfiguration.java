package br.com.felipempantoja.webdriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
public class WebDriverConfiguration {

    @Bean(name = "webDriver")
    @Scope("prototype")
    @ConditionalOnProperty(name = "robot.driver", havingValue = "chrome")
    public WebDriver chrome() throws IOException {
        System.setProperty("webdriver.chrome.driver", new ClassPathResource("chromedriver").getFile().getAbsolutePath());
        return new ChromeDriver();
    }

    @Bean(name = "webDriver")
    @Scope("prototype")
    @ConditionalOnProperty(name = "robot.driver", havingValue = "htmlunit")
    public WebDriver htmlUnit() {
        return new HtmlUnitDriver(BrowserVersion.CHROME);
    }

    @Bean(name = "webDriver")
    @Scope("prototype")
    @ConditionalOnProperty(name = "robot.driver", havingValue = "phantomjs")
    public WebDriver phantomJS() throws IOException {
        String driverFileName;
        if(SystemUtils.IS_OS_LINUX) {
            driverFileName = "phantomjs";
        } else {
            driverFileName = "phantomjs.exe";
        }
        System.setProperty("phantomjs.binary.path", new ClassPathResource(driverFileName).getFile().getAbsolutePath());
        DesiredCapabilities dCaps = new DesiredCapabilities();
        dCaps.setJavascriptEnabled(true);
        dCaps.setCapability("takesScreenshot", false);
        return new PhantomJSDriver(dCaps);
    }
}
