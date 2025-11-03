package tests.JohnFergusonSmart;

import com.microsoft.playwright.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

public class _11PlaywrightWaitstest {

    static Playwright playwright;
    static Browser browser;
    static Page page;

    @BeforeAll
    public static void setup(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
                        .setArgs(Arrays.asList("--start-maximized","--no-sandbox","--disable-extensions","--disable-gpu"))
        );
    }

    @BeforeEach
    void setUp() {
        page = browser.newPage();
        page.navigate("https://practicesoftwaretesting.com/");
    }

    @AfterAll
    public static void tearDown(){
        browser.close();
        playwright.close();
    }


    @DisplayName("Show all product names")
    @Test
    public void shouldShowAllProductNames() throws InterruptedException {
        page.waitForSelector("[data-test=product-name]"); // wait for all the products to load

        List<String> ProductNames = page.locator(".card-title").allInnerTexts();
        System.out.println(ProductNames);
        Assertions.assertThat(ProductNames).contains("Combination Pliers","Pliers","Bolt Cutters");
    }





}
