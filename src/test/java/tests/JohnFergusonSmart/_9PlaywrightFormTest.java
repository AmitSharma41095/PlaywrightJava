package tests.JohnFergusonSmart;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class _9PlaywrightFormTest {

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
        page.navigate("https://practicesoftwaretesting.com/contact");
    }

    @DisplayName("Form fill - complete")
    @Test
    public void fillForm() throws URISyntaxException {
        var FirstNameField = page.getByLabel("First name");
        var LastNameField = page.getByLabel("Last name");
        var EmailAddressField = page.getByLabel("Email address");
        var Messagefield = page.getByLabel("Message");
        var SubjectField = page.getByLabel("Subject");
        var AttachmentField = page.getByLabel("Attachment");

        FirstNameField.fill("Amit");
        LastNameField.fill("Sharma");
        EmailAddressField.fill("Amit.sharma@test.com");
        Messagefield.fill("qwertyuiopas \n this is a new line \n another line");
        SubjectField.selectOption("Webmaster");
        SubjectField.selectOption(new SelectOption().setLabel("Warranty"));

        System.out.println(Messagefield.inputValue()); //get the value of the text box

        Path fileToUpload = Paths.get(ClassLoader.getSystemResource("TestingDoc.txt").toURI());
        page.setInputFiles("#attachment",fileToUpload);

        PlaywrightAssertions.assertThat(FirstNameField).hasValue("Amit");
        PlaywrightAssertions.assertThat(LastNameField).hasValue("Sharma");
        PlaywrightAssertions.assertThat(EmailAddressField).hasValue("Amit.sharma@test.com");
        PlaywrightAssertions.assertThat(Messagefield).hasValue("qwertyuiopas \n this is a new line \n another line");
        PlaywrightAssertions.assertThat(SubjectField).hasValue("warranty"); //get the label and verify
        String uploadedFile = AttachmentField.inputValue();
        Assertions.assertThat(uploadedFile).endsWith("TestingDoc.txt");
    }

    @DisplayName("Testing for single Mandatory Fields")
    @Test
    public void testMandatoryFields(){
        var FirstNameField = page.getByLabel("First name");
        var LastNameField = page.getByLabel("Last name");
        var EmailAddressField = page.getByLabel("Email address");
        var Messagefield = page.getByLabel("Message");
        var SendButton = page.locator(".btnSubmit");

        SendButton.click();
        //check for first error message : First name is required
        var errorMessage = page.getByRole(AriaRole.ALERT).getByText("First name is required");
        PlaywrightAssertions.assertThat(errorMessage).isVisible();
    }

    @DisplayName("Testing for all Mandatory Fields - Parametrize test")
    @ParameterizedTest
    @ValueSource(strings = {"First name","Last name","Email","Message"})
    public void testAllMandatoryFields(String fieldNames){
        var FirstNameField = page.getByLabel("First name");
        var LastNameField = page.getByLabel("Last name");
        var EmailAddressField = page.getByLabel("Email address");
        var Messagefield = page.getByLabel("Message");
        var SendButton = page.locator(".btnSubmit");

        //fill in the field values
        FirstNameField.fill("Amit");
        LastNameField.fill("Sharma");
        EmailAddressField.fill("Amit.sharma@test.com");
        Messagefield.fill("qwertyuiopas \n this is a new line \n another line");

        //clear the fields
        page.getByLabel(fieldNames).clear();
        SendButton.click();

        //check the error message
        var errorMessage = page.getByRole(AriaRole.ALERT).getByText(fieldNames+" is required");
        PlaywrightAssertions.assertThat(errorMessage).isVisible();
    }



}
