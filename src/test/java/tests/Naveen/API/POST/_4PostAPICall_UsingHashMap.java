package tests.Naveen.API.POST;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class _4PostAPICall_UsingHashMap {

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

    @DisplayName("Create User & Check Created User")
    @Test
    public void createUser_checkUser() throws IOException {
        System.out.println("Create user - POST Call");
        createUser();
        System.out.println("Check created user - GET Call");
        checkCreatedUser(id);
    }

    public void createUser() throws IOException {
        Map<String, String> dataPayload = new HashMap<>();
        dataPayload.put("name","AutomationTest_amit1");
        dataPayload.put("gender","male");
        dataPayload.put("email",getRandomEmail());
        dataPayload.put("status","active");

        APIResponse postResponse = apiRequestContext.post("https://gorest.co.in/public/v2/users",
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setHeader("Authorization", "Bearer d90c82d773e827d750c688e019166eb366130e0dec5dc94e9d5c82b091197791")
                        .setData(dataPayload)
        );

        System.out.println("Response status code :: "+ postResponse.status());
        System.out.println("Response status Text :: "+ postResponse.statusText());
        System.out.println("Response URL :: "+ postResponse.url());
        System.out.println("Response text :: "+postResponse.text());

        //extract id from post response
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode postJsonResponse = objectMapper.readTree(postResponse.body());

        id = postJsonResponse.get("id").asText();
    }

    public void checkCreatedUser(String id){
        APIResponse getResponse = apiRequestContext.get("https://gorest.co.in/public/v2/users",
                RequestOptions.create()
                        .setQueryParam("id", id)
                        .setHeader("Authorization", "Bearer d90c82d773e827d750c688e019166eb366130e0dec5dc94e9d5c82b091197791")
        );
        System.out.println("Response status code :: "+ getResponse.status());
        System.out.println("Response status Text :: "+ getResponse.statusText());
        System.out.println("Response URL :: "+ getResponse.url());
        System.out.println("Response text :: "+getResponse.text());

        //Assertions on values
    }

}
