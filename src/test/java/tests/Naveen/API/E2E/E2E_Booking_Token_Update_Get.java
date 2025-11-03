package tests.Naveen.API.E2E;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

public class E2E_Booking_Token_Update_Get {
    Playwright playwright;
    APIRequest request;
    APIRequestContext apiRequestContext;
    String tokenId;

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

    @Test
    public void UpdateScenario() throws IOException {
        //generate token -> update booking -> get booking
        generateToken();
        updateBooking();
    }

    public void generateToken() throws IOException {
        System.out.println("********** Generating Token **********");
        HashMap<String, String> requestData = new HashMap<>();
        requestData.put("username", "admin");
        requestData.put("password", "password123");

        APIResponse tokenResponse = apiRequestContext.post("https://restful-booker.herokuapp.com/auth",
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setData(requestData));

        System.out.println("Response status code :: "+ tokenResponse.status());
        System.out.println("Response status Text :: "+ tokenResponse.statusText());
        System.out.println("Response URL :: "+ tokenResponse.url());
        System.out.println("Response text :: "+tokenResponse.text());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode tokenJsonNode = objectMapper.readTree(tokenResponse.body());
        tokenId = tokenJsonNode.get("token").asText();
        System.out.println("Generated Token ID : "+tokenId);
    }

    public void updateBooking() {
        System.out.println("********** Updating Booking **********");

        String updateJsonPayload = "{\n" +
                "    \"firstname\": \"salma\",\n" +
                "    \"lastname\": \"Wilson\",\n" +
                "    \"totalprice\": 638,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2021-05-14\",\n" +
                "        \"checkout\": \"2024-02-26\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Lunch\"\n" +
                "}";

        APIResponse putResponse = apiRequestContext.put("https://restful-booker.herokuapp.com/booking/4",
                RequestOptions.create().setHeader("Content-Type", "application/json")
                        .setHeader("Accept", "application/json")
                        .setHeader("Cookie", "token=" + tokenId)
                        .setData(updateJsonPayload));

        System.out.println("Response status code :: "+ putResponse.status());
        System.out.println("Response status Text :: "+ putResponse.statusText());
        System.out.println("Response URL :: "+ putResponse.url());
        System.out.println("Response text :: "+putResponse.text());
    }
}
