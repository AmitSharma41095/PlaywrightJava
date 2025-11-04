package tests.JohnFergusonSmart;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.junit.jupiter.api.*;
import tests.JohnFergusonSmart.Resources.MockSearchResponses;

import java.util.Arrays;

public class _12MockingAPIResponse {

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

    @DisplayName("When a search returns a single product")
    @Test
    public void whenASearchReturnsASingleProduct() {

        //using mock data from Resources/MockSearchResponses.java
        page.route("**/products/search?q=Pliers", route -> {
            route.fulfill(
                    new Route.FulfillOptions()
                            .setStatus(200)
                            .setBody(MockSearchResponses.RESPONSE_WITH_A_SINGLE_ENTRY)
            );
        });

        page.getByPlaceholder("Search").fill("Pliers");
        page.getByPlaceholder("Search").press("Enter");

        playwright.selectors().setTestIdAttribute("data-test");
        PlaywrightAssertions.assertThat(page.getByTestId("product-name")).hasCount(1);
        PlaywrightAssertions.assertThat(page.getByTestId("product-name")).hasText("Testing Pliers");
    }

    @DisplayName("When a search returns zero product")
    @Test
    public void whenASearchReturnsZeroProduct() {

        //using mock data from Resources/MockSearchResponses.java
        page.route("**/products/search?q=Pliers", route -> {
            route.fulfill(
                    new Route.FulfillOptions()
                            .setStatus(200)
                            .setBody(MockSearchResponses.RESPONSE_WITH_NO_ENTRIES)
            );
        });

        page.getByPlaceholder("Search").fill("Pliers");
        page.getByPlaceholder("Search").press("Enter");

        playwright.selectors().setTestIdAttribute("data-test");
        PlaywrightAssertions.assertThat(page.getByTestId("product-name")).hasCount(0);
        PlaywrightAssertions.assertThat(page.getByTestId("search_completed")).hasText("There are no products found.");
    }

}
