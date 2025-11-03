package tests.Naveen.API.E2E;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.Naveen.API.POST.Data.UsersLombok;

public class E2E_User_Creation_Updation_Deletion_Get {
    Playwright playwright;
    APIRequest request;
    APIRequestContext apiRequestContext;
    String userRequestId;
    UsersLombok userRequest;

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
    public void UpdateScenario() throws JsonProcessingException {
        //create -> get -> update -> get -> delete -> get
        createUser();
        getUser(userRequestId);
        updateUser(userRequestId);
        getUser(userRequestId);
        deleteUser(userRequestId);
        getUser(userRequestId, 404);
    }

    public void createUser() throws JsonProcessingException {
        System.out.println("********** Creating User **********");
        userRequest = UsersLombok.builder()
                .name("UserCreateTest").email(getRandomEmail()).gender("male").status("active").build();
        System.out.println("Request User : "+userRequest);

        APIResponse postResponse = apiRequestContext.post("https://gorest.co.in/public/v2/users",
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setHeader("Authorization", "Bearer d90c82d773e827d750c688e019166eb366130e0dec5dc94e9d5c82b091197791")
                        .setData(userRequest));

        System.out.println("Response status code :: "+ postResponse.status());
        System.out.println("Response status Text :: "+ postResponse.statusText());
        System.out.println("Response URL :: "+ postResponse.url());
        System.out.println("Response text :: "+postResponse.text());

        String responseText = postResponse.text();

        //deserialization
        ObjectMapper objectMapper = new ObjectMapper();
        UsersLombok responseUser = objectMapper.readValue(responseText, UsersLombok.class);
        System.out.println("Response user : "+responseUser);

        //Easy assertions on request and response users
        Assertions.assertThat(responseUser.getName()).isEqualTo(userRequest.getName());
        Assertions.assertThat(responseUser.getEmail()).isEqualTo(userRequest.getEmail());
        Assertions.assertThat(responseUser.getGender()).isEqualTo(userRequest.getGender());
        Assertions.assertThat(responseUser.getStatus()).isEqualTo(userRequest.getStatus());
        userRequestId = responseUser.getId();
        System.out.println("userRequestId :: "+ userRequestId);
    }

    public void updateUser(String id) throws JsonProcessingException {
        System.out.println("********** Updating User **********");

        userRequest.setName("UserUpdateTest");
        userRequest.setStatus("inactive");

        System.out.println("Request User : "+userRequest);

        APIResponse updateResponse = apiRequestContext.put("https://gorest.co.in/public/v2/users/" + id,
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setHeader("Accept", "application/json")
                        .setHeader("Authorization", "Bearer d90c82d773e827d750c688e019166eb366130e0dec5dc94e9d5c82b091197791")
                        .setData(userRequest));

        System.out.println("Response status code :: "+ updateResponse.status());
        System.out.println("Response status Text :: "+ updateResponse.statusText());
        System.out.println("Response URL :: "+ updateResponse.url());
        System.out.println("Response text :: "+updateResponse.text());

        String responseText = updateResponse.text();

        //deserialization
        ObjectMapper objectMapper = new ObjectMapper();
        UsersLombok responseUser = objectMapper.readValue(responseText, UsersLombok.class);
        System.out.println("Response user : "+responseUser);

        //Easy assertions on request and response users
        Assertions.assertThat(responseUser.getName()).isEqualTo(userRequest.getName());
        Assertions.assertThat(responseUser.getEmail()).isEqualTo(userRequest.getEmail());
        Assertions.assertThat(responseUser.getGender()).isEqualTo(userRequest.getGender());
        Assertions.assertThat(responseUser.getStatus()).isEqualTo(userRequest.getStatus());
        Assertions.assertThat(responseUser.getId()).isEqualTo(id);
    }

    public void getUser(String id) throws JsonProcessingException {
        System.out.println("********** Getting User **********");
        APIResponse apiResponse = apiRequestContext.get("https://gorest.co.in/public/v2/users/" + id,
                RequestOptions.create()
                        .setHeader("Authorization", "Bearer d90c82d773e827d750c688e019166eb366130e0dec5dc94e9d5c82b091197791"));

        System.out.println("Response status code :: "+ apiResponse.status());
        System.out.println("Response status Text :: "+ apiResponse.statusText());
        System.out.println("Response URL :: "+ apiResponse.url());
        System.out.println("Response text :: "+apiResponse.text());

        String responseText = apiResponse.text();

        //deserialization
        ObjectMapper objectMapper = new ObjectMapper();
        UsersLombok responseUser = objectMapper.readValue(responseText, UsersLombok.class);
        System.out.println("Response user : "+responseUser);

        //Easy assertions on request and response users
        Assertions.assertThat(responseUser.getName()).isEqualTo(userRequest.getName());
        Assertions.assertThat(responseUser.getEmail()).isEqualTo(userRequest.getEmail());
        Assertions.assertThat(responseUser.getGender()).isEqualTo(userRequest.getGender());
        Assertions.assertThat(responseUser.getStatus()).isEqualTo(userRequest.getStatus());
        Assertions.assertThat(responseUser.getId()).isEqualTo(id);
    }

    public void deleteUser(String id){
        System.out.println("********** Deleting User **********");
        APIResponse deleteResponse = apiRequestContext.delete("https://gorest.co.in/public/v2/users/" + id,
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setHeader("Authorization", "Bearer d90c82d773e827d750c688e019166eb366130e0dec5dc94e9d5c82b091197791"));

        System.out.println("Response status code :: "+ deleteResponse.status());
        System.out.println("Response status Text :: "+ deleteResponse.statusText());
        System.out.println("Response URL :: "+ deleteResponse.url());
        System.out.println("Response text :: "+deleteResponse.text());

        Assertions.assertThat(deleteResponse.status()).isEqualTo(204);
    }

    public void getUser(String id, int statusCode) throws JsonProcessingException {
        System.out.println("********** Getting User **********");
        APIResponse apiResponse = apiRequestContext.get("https://gorest.co.in/public/v2/users/" + id,
                RequestOptions.create()
                        .setHeader("Authorization", "Bearer d90c82d773e827d750c688e019166eb366130e0dec5dc94e9d5c82b091197791"));

        System.out.println("Response status code :: "+ apiResponse.status());
        System.out.println("Response status Text :: "+ apiResponse.statusText());
        System.out.println("Response URL :: "+ apiResponse.url());
        System.out.println("Response text :: "+apiResponse.text());

        String responseText = apiResponse.text();
        Assertions.assertThat(apiResponse.status()).isEqualTo(statusCode);
    }

    public static String getRandomEmail(){
        String email = "AutomationTest_amit"+ System.currentTimeMillis() +"@test.com";
        return email;
    }



}
