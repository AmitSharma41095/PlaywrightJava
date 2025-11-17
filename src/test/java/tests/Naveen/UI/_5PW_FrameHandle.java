package tests.Naveen.UI;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class _5PW_FrameHandle {
    Playwright playwright;
    Browser browser;
    Page page;

    @BeforeEach
    public void setup(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
    }

    @AfterEach
    public void tearDown(){
        browser.close();
        playwright.close();
    }

    @DisplayName("Handling frames <frame> - using locator and name")
    @Test
    public void frameHandleTest(){
        page.navigate("https://www.londonfreelance.org/courses/frames/index.html");

        //Using frame name
        String headerText = page.frame("main").locator("h2").textContent();
        System.out.println("header text is : "+ headerText);
        Assertions.assertThat(headerText).isEqualTo("Title bar (top.html)");

        //using frame locator
        String bodyTitle = page.frameLocator("frame[name='content']").locator("title").textContent();
        System.out.println("body title is : "+ bodyTitle);
        Assertions.assertThat(bodyTitle).isEqualTo("Example home page");

        String navTitle = page.frameLocator("//frame[@name='navbar']").locator("title").textContent();
        System.out.println("nav title is : "+ navTitle);
        Assertions.assertThat(navTitle).isEqualTo("Navigation");
    }

    @DisplayName("Handling frames <iframe> - using locator and name")
    @Test
    public void frameHandleTest1(){
        page.navigate("https://www.formsite.com/templates/registration-form-templates/vehicle-registration-form/");
        page.locator("img[title='Vehicle-Registration-Forms-and-Examples']").click();

        //using frame locator
        page.frameLocator("//iframe[contains(@id,'frame-one')]").locator("#RESULT_TextField-1").fill("Amit Sharma");
    }
}
