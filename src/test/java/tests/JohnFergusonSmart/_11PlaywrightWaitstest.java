package tests.JohnFergusonSmart;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.Comparator;
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

    @DisplayName("Show all product images")
    @Test
    public void shouldShowAllProductImages() throws InterruptedException {
        page.waitForSelector(".card-img-top"); // wait for all the images to load

        List<String> productImageTitles = page.locator(".card-img-top").all()
                .stream().map(img -> img.getAttribute("alt")).toList();
        System.out.println(productImageTitles);
        Assertions.assertThat(productImageTitles).contains("Combination Pliers","Pliers","Bolt Cutters");
    }

    @DisplayName("Should wait for the filter checkbox to appear before clicking")
    @Test
    public void shouldWaitForFilterCheckbox() {
        var screwdriver = page.getByLabel("Screwdriver");
        screwdriver.click(); //click has auto wait so no need to wait explicitly
        PlaywrightAssertions.assertThat(screwdriver).isChecked();
    }

    @DisplayName("Should filter products by Category")
    @Test
    public void shouldFilterProductsByCategory() {
        page.locator("[data-test='nav-categories']").click();
        page.locator("[data-test='nav-power-tools']").click();

        // wait for all the products to load - selector to appear, timout 5s
        page.waitForSelector("[data-test=product-name]",
               new Page.WaitForSelectorOptions()
                       .setState(WaitForSelectorState.VISIBLE).setTimeout(5000));

        var filteredProducts = page.locator(".card-title").allInnerTexts();
        System.out.println(filteredProducts);
        Assertions.assertThat(filteredProducts).contains("Sheet Sander","Belt Sander","Random Orbit Sander");
    }

    @DisplayName("Waiting for the element to appear and disappear")
    @Test
    public void shouldDisplayToasterMessage() {
        page.getByText("Combination Pliers").click();
        page.getByText("Add to Cart").click();

        //wait for the toaster message to appear
        PlaywrightAssertions.assertThat(page.getByRole(AriaRole.ALERT)).isVisible();
        PlaywrightAssertions.assertThat(page.getByRole(AriaRole.ALERT)).hasText("Product added to shopping cart.");

        //wait for the toaster message to disappear
        page.waitForCondition( () -> page.getByRole(AriaRole.ALERT).isHidden()); //waiting for toaster message to disappear
        PlaywrightAssertions.assertThat(page.getByRole(AriaRole.ALERT)).isHidden();
    }

    @DisplayName("Should update the item count")
    @Test
    public void shouldUpdateCartItemCount() {
        page.getByText("Combination Pliers").click();
        page.getByText("Add to Cart").click();

        //wait for the cart item to update
        page.waitForCondition( () -> page.locator("[data-test='cart-quantity']").textContent().equals("1"));
//        page.waitForSelector("[data-test='cart-quantity']:has-text('1')");
    }

    @DisplayName("Sort Prices and waiting for API Response")
//    @Test
    public void shouldWaitForAPIResponse() {
        playwright.selectors().setTestIdAttribute("data-test");

        page.waitForResponse("**/products?sort**",
                () -> {
                    page.getByTestId("sort").selectOption("Price (High - Low)");
                });

//        page.getByTestId("product-price").first().waitFor();
        var productPrices = page.getByTestId("product-price").allInnerTexts()
                .stream()
                .map(price -> Double.parseDouble(price.replace("$", "")))
                .toList();

        //check prices are in correct order
        System.out.println("Product Prices: " + productPrices);
        Assertions.assertThat(productPrices).isSortedAccordingTo(Comparator.reverseOrder()).isNotEmpty();
    }





}
