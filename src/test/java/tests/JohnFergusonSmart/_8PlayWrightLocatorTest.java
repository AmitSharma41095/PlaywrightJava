package tests.JohnFergusonSmart;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;


import java.util.Arrays;
import java.util.List;

public class _8PlayWrightLocatorTest {

    static Playwright playwright;
    static Browser browser;
    static Page page;
    static BrowserContext browserContext;

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
        browserContext = browser.newContext();
        page = browserContext.newPage();
    }

    @AfterEach
    void closeContext() {
        browserContext.close();
    }

    @AfterAll
    public static void tearDown(){
        browser.close();
        playwright.close();
    }

    private void openPage() {
        page.navigate("https://practicesoftwaretesting.com");
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    @DisplayName("Locating elements by text")
    @Nested
    class LocatingElementsByText {

        @BeforeEach
        public void openTheCatalogPage() {
            openPage();
        }

        @DisplayName("Locating elements by text content")
        @Test
        public void byText(){
            page.getByText("Bolt Cutters").click();
            PlaywrightAssertions.assertThat(page.getByText("MightyCraft Hardware")).isVisible();
        }

        @DisplayName("Locating elements by Images - alttext")
        @Test
        public void byAltText(){
            page.getByAltText("Combination Pliers").click();
            PlaywrightAssertions.assertThat(page.getByText("ForgeFlex Tools")).isVisible();
        }

        @DisplayName("Locating elements by Title - when hover the mouse on TOOLSHOP title appears")
        @Test
        public void byTitle(){
            page.getByAltText("Combination Pliers").click();
            page.getByTitle("Practice Software Testing - Toolshop").click();
        }
    }

    @DisplayName("Locating elements Using Css")
    @Nested
    class LocatingElementsUsingCss{

        @BeforeEach
        public void openContactPage(){
            page.navigate("https://practicesoftwaretesting.com/contact");
        }

        @Test
        public void fillForm() throws InterruptedException {
            page.locator("#first_name").fill("Amit");
            page.locator("input[placeholder='Your last name *']").fill("Sharma");
            page.locator(".btnSubmit").click();
            PlaywrightAssertions.assertThat(page.locator("#first_name")).hasValue("Amit");
            PlaywrightAssertions.assertThat(page.locator("input[placeholder='Your last name *']")).hasValue("Sharma");
            Thread.sleep(3000);
            List<String> alertMessages = page.locator(".alert").allTextContents();
            System.out.println(alertMessages.size());
            for(String s : alertMessages){
                System.out.println(s);
            }
        }
    }

    @DisplayName("Nested loctoars and Filtering")
    @Nested
    class Scenario_FilteringAndNestingLocators{

        @BeforeEach
        public void openContactPage(){
            openPage();
        }

        @Test
        public void filterText() throws InterruptedException {
            //Scenario : Search for Pliers -> get the number of results -> check all results contains word "pliers"
            // -> find product that is out of stock
            page.locator("#search-query").fill("Pliers");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Search")).click();

            playwright.selectors().setTestIdAttribute("data-test");
            PlaywrightAssertions.assertThat(page.locator(".card")).hasCount(4);
            List<String> productNames = page.getByTestId("product-name").allTextContents();
            System.out.println(productNames);
            Assertions.assertThat(productNames).allMatch(name -> name.contains("Pliers"));

            Locator OutOfStockproducts = page.locator(".card")
                    .filter(new Locator.FilterOptions().setHas(page.getByTestId("out-of-stock")))
                    .getByTestId("product-name");

            System.out.println("Product out of stock is : "+ OutOfStockproducts.allTextContents());
            PlaywrightAssertions.assertThat(OutOfStockproducts).hasCount(1);
            PlaywrightAssertions.assertThat(OutOfStockproducts).hasText("Long Nose Pliers");
        }
    }





}
