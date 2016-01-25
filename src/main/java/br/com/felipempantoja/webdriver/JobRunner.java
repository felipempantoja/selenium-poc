package br.com.felipempantoja.webdriver;

import br.com.felipempantoja.webdriver.robot.dto.InboxEmail;
import br.com.felipempantoja.webdriver.robot.page.PageObject;
import br.com.felipempantoja.webdriver.robot.page.google.LoginGooglePage;
import br.com.felipempantoja.webdriver.robot.page.google.inbox.HomeInboxGooglePage;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class JobRunner implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobRunner.class);

    @Value("${robot.google.email}")
    private String defaultUserEmail;

    @Value("${robot.google.password}")
    private String defaultUserPassword;

    @Autowired
    private WebDriver webDriver;

    @Override
    public void run(String... strings) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Email (deixe em branco para logar como " + defaultUserEmail + "): ");
        String userEmail = reader.readLine();

        String userPassword;

        if(!userEmail.isEmpty()) {
            System.out.print("Password: ");
            userPassword = reader.readLine().toString();
        } else {
            userEmail = defaultUserEmail;
            userPassword = defaultUserPassword;
        }

        LOGGER.info("Initializing robot...");

        LoginGooglePage page = null;
        try {
            page = PageObject.init(webDriver, LoginGooglePage.class);
            page.loginAs(userEmail, userPassword);
            HomeInboxGooglePage inbox = page.go(HomeInboxGooglePage.class);

            for(InboxEmail email1 : inbox.emails()) {
                System.out.println(email1.getSender());
            }

        } catch (Exception e) {
            throw e;
        } finally {
            if(page != null) {
                page.close();
            }
        }
    }
}
