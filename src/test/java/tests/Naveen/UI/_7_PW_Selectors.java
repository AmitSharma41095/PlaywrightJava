package tests.Naveen.UI;

import com.microsoft.playwright.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class _7_PW_Selectors {
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

    @DisplayName("Selecting elements that contain other elements")
    @Test
    public void selectingElementsThatContainOtherElements(){
        page.navigate("https://orangehrm.com/en/book-a-free-demo");
        Locator DDLlocator = page.locator("select#Form_getForm_Country:has(option[value='India'])");
        DDLlocator.allInnerTexts().forEach(e-> System.out.println(e));
    }

    @DisplayName("Comma seperated css list")
    @Test
    public void commaSeperatedCSSList(){
        page.navigate("https://academy.naveenautomationlabs.com/");
        page.locator("a:has-text('Login'), " +
                "a:has-text('login')," +
                "a:has-text('SignIn')," +
                "a:has-text('Sign In')").click();
        System.out.println(page.frameLocator("#microfe-popup-login").locator("h1.text-title6").textContent());
        Assertions.assertThat(page.frameLocator("#microfe-popup-login").locator("h1.text-title6").textContent())
                .isEqualTo("Create an Account");
    }

    @DisplayName("Count important links on page - comma seperated css list")
    @Test
    public void countImportantLinksOnPage(){
        page.navigate("https://academy.naveenautomationlabs.com/");
        Locator impLinks = page.locator("a:has-text('Courses'),a:has-text('Demo Site'),a:has-text('Webinars'),a:has-text('WebDriver APIs')");
        System.out.println("Important links count: "+ impLinks.count());
        impLinks.allInnerTexts().forEach(e-> System.out.println(e));
    }

    @DisplayName("Xpath union")
    @Test
    public void XPathUnion(){
        page.navigate("https://academy.naveenautomationlabs.com/");
        page.locator("//a[text()='Login'] | //a[text()='login'] | //a[text()='Sign in']").click();
        System.out.println(page.frameLocator("#microfe-popup-login").locator("h1.text-title6").textContent());
        Assertions.assertThat(page.frameLocator("#microfe-popup-login").locator("h1.text-title6").textContent())
                .isEqualTo("Create an Account");
    }

    @DisplayName("Nth Element Selector")
    @Test
    public void NthElementSelector(){
        page.navigate("https://www.bigbasket.com/");
        System.out.println("First element text : "+ page.locator("footer.footer .flex li a>>nth=0").textContent());
        System.out.println("Third element text : "+ page.locator("footer.footer .flex li a>>nth=2").textContent());
        System.out.println("Sixth element text : "+ page.locator("footer.footer .flex li a>>nth=5").textContent());
        System.out.println("Last element text : "+ page.locator("footer.footer .flex li a>>nth=-1").textContent());

    }

    @DisplayName("React Element Selector")
    @Test
    public void ReactElementSelector(){
        page.navigate("https://www.netflix.com/in/");
        Locator email = page.locator("_react=input[type='email']");
        email.first().click();
        email.first().fill("amit@gmail.com");

        page.locator("_react=select[name='LanguageSelect']").nth(1).click();

    }
}
