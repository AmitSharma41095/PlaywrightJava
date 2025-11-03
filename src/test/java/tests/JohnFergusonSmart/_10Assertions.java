package tests.JohnFergusonSmart;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.LoadState;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class _10Assertions {
    static Playwright playwright;
    static Browser browser;
    static Page page;
    static BrowserContext browserContext;

    @BeforeAll
    public static void setupBrowser(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
                        .setArgs(Arrays.asList("--start-maximized","--no-sandbox","--disable-extensions","--disable-gpu"))
        );
        browserContext = browser.newContext();
    }

    @AfterAll
    public static void tearDown(){
        browser.close();
        playwright.close();
    }

    @BeforeEach
    public void setup(){
        page = browserContext.newPage();
    }

    @DisplayName("Assertions on Field Values and State of Fields")
    @Test
    public void fieldAssertions(){
        page.navigate("https://practicesoftwaretesting.com/contact");
        var FirstNameField = page.getByLabel("First name");
        FirstNameField.fill("Amit");
        PlaywrightAssertions.assertThat(FirstNameField).hasValue("Amit");
        PlaywrightAssertions.assertThat(FirstNameField).isVisible();
        PlaywrightAssertions.assertThat(FirstNameField).isEditable();
        PlaywrightAssertions.assertThat(FirstNameField).not().isDisabled();
    }

    @DisplayName("Validate the prices of the products")
    @Test
    public void allProductPricesShouldBeCorrectValues() throws InterruptedException {
        page.navigate("https://practicesoftwaretesting.com/");
        playwright.selectors().setTestIdAttribute("data-test");
        Thread.sleep(3000);
        List<Double> prices = page.getByTestId("product-price")
                .allInnerTexts()
                .stream()
                .map(price -> Double.parseDouble(price.replace("$", "")))
                .toList();

        Assertions.assertThat(prices)
                .isNotEmpty()
                .allMatch(price -> price > 0)
                .doesNotContain(0.0)
                .allMatch(price -> price < 1000)
                .allSatisfy(price ->
                        Assertions.assertThat(price)
                                .isGreaterThan(0.0)
                                .isLessThan(1000.0)
                );
    }

    @DisplayName("Sort in alphabetical order")
    @Test
    public void sortInAlphabeticOrder(){
        page.navigate("https://practicesoftwaretesting.com/");
        page.locator(".form-select").selectOption("Name (A - Z)");
        page.waitForLoadState(LoadState.NETWORKIDLE);

        playwright.selectors().setTestIdAttribute("data-test");
        List<String> productName = page.getByTestId("product-name").allTextContents();
        Assertions.assertThat(productName).isSortedAccordingTo(Comparator.naturalOrder());
        Assertions.assertThat(productName).isSortedAccordingTo(String.CASE_INSENSITIVE_ORDER);
    }

    @DisplayName("Sort in reverse alphabetical order")
    @Test
    public void sortInReverseAlphabeticOrder(){
        page.navigate("https://practicesoftwaretesting.com/");
        page.locator(".form-select").selectOption("Name (Z - A)");
        page.waitForLoadState(LoadState.NETWORKIDLE);

        playwright.selectors().setTestIdAttribute("data-test");
        List<String> productName = page.getByTestId("product-name").allTextContents();
        Assertions.assertThat(productName).isSortedAccordingTo(Comparator.reverseOrder());
    }



}
