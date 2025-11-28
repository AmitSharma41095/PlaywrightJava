package tests.RahulArora;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.Arrays;

public class _4_NonIncognitoModeTest {

    @DisplayName("Launcing browser in Non-Incognito/Normal mode")
    @Test
    public void nonIncognitoModeTest() throws InterruptedException {
        Playwright playwright = Playwright.create();
        BrowserContext browserContext = playwright.chromium().launchPersistentContext(Paths.get(""), new BrowserType.LaunchPersistentContextOptions().setHeadless(false).setChannel("chrome").setArgs(Arrays.asList("--start-maximized")));
        Page page = browserContext.newPage();
        page.navigate("https://www.way2automation.com/");
        System.out.println("Page title: " + page.title());
        Assertions.assertEquals(page.title(),"Get Online Selenium Certification Course | Way2Automation");
        Thread.sleep(5000);
        playwright.close();
    }
}
