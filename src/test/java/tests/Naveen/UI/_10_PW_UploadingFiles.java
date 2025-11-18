package tests.Naveen.UI;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.FilePayload;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public class _10_PW_UploadingFiles {
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

    @DisplayName("Uploading single and multiple files")
    @Test
    public void uploadingFilesTest() throws InterruptedException {
        System.out.println("Uploading Files Test");

        page.navigate("https://davidwalsh.name/demo/multiple-file-upload.php");

        //uploading single file
        page.setInputFiles("input#filesToUpload",
                Paths.get("src/test/resources/TestingDoc.txt"));
        System.out.println("Uploaded file name : "+ page.locator("#fileList").textContent());
        //Detaching the files
        Thread.sleep(5000);
        page.setInputFiles("input#filesToUpload", new Path[0]);
        System.out.println("Uploaded file name : "+ page.locator("#fileList").textContent());

        //upload multiple files
        page.setInputFiles("input#filesToUpload",
                new Path[]{
                        Paths.get("src/test/resources/TestingDoc.txt"),
                        Paths.get("src/test/resources/TestingFile.pdf"),
                });
        System.out.println("Uploaded file name : "+ page.locator("#fileList").allInnerTexts());

        //Detaching the files
        Thread.sleep(5000);
        page.setInputFiles("input#filesToUpload", new Path[0]);
        System.out.println("Uploaded file name : "+ page.locator("#fileList").textContent());
    }

    @DisplayName("Create file at runtime and upload")
    @Test
    public void uploadingFilesTest1() throws InterruptedException {
        System.out.println("Uploading Files Test");

        page.navigate("https://cgi-lib.berkeley.edu/ex/fup.html");

        //uploading single file
        page.setInputFiles("input[name='upfile']",
                new FilePayload("runTimeFile.txt",
                        "text/plain",
                        "This is the content of the file created at runtime".getBytes(StandardCharsets.UTF_8)));

        page.locator("input[value='Press']").click();
        System.out.println("Uploaded file content : "+ page.locator("pre").textContent());
    }

}
