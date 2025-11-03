package tests.Naveen.API.POST;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.Naveen.API.POST.Data.User;
import tests.Naveen.API.POST.Data.UsersLombok;

import java.io.IOException;

public class _8PostAPICall_LombokClass {

    Playwright playwright;
    APIRequest request;
    APIRequestContext apiRequestContext;
    String id;

    @BeforeEach
    public void setup(){
        playwright = Playwright.create();
        request = playwright.request();
        apiRequestContext = request.newContext();
    }

    @AfterEach
    public void tearDown(){
        playwright.close();
    }

    public static String getRandomEmail(){
        String email = "AutomationTest_amit"+ System.currentTimeMillis() +"@test.com";
        return email;
    }

    @DisplayName("Create User and validate using POJO")
    @Test
    public void createUser_checkUser() throws IOException {
        createUser();
    }

    public void createUser() throws IOException {

        UsersLombok requestUser = UsersLombok.builder().
                name("Amit").email(getRandomEmail()).gender("male").status("active").build();

        System.out.println("Request User : "+requestUser);

        APIResponse postResponse = apiRequestContext.post("https://gorest.co.in/public/v2/users",
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setHeader("Authorization", "Bearer d90c82d773e827d750c688e019166eb366130e0dec5dc94e9d5c82b091197791")
                        .setData(requestUser)
        );

        System.out.println("Response status code :: "+ postResponse.status());
        System.out.println("Response status Text :: "+ postResponse.statusText());
        System.out.println("Response URL :: "+ postResponse.url());
        System.out.println("Response text :: "+postResponse.text());

        String responseText = postResponse.text();

        //deserialization
        ObjectMapper objectMapper = new ObjectMapper();
        User responseUser = objectMapper.readValue(responseText, User.class);
        System.out.println("Response user : "+responseUser);

        //Easy assertions on request and response users
        Assertions.assertThat(responseUser.getName()).isEqualTo(requestUser.getName());
        Assertions.assertThat(responseUser.getEmail()).isEqualTo(requestUser.getEmail());
        Assertions.assertThat(responseUser.getGender()).isEqualTo(requestUser.getGender());
        Assertions.assertThat(responseUser.getStatus()).isEqualTo(requestUser.getStatus());
    }
}
