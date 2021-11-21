package stepDefinitions;

import com.github.javafaker.Faker;
import com.google.gson.JsonObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import pojos.Users;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class LoginAPIStepDefinitions {

    RequestSpecification requestSpecification;
    Response response;
    String token;
    String path;
    Map<String, Object> map;
    RequestSpecification body;
    String firstName;
    String lastName;
    String email;
    String password;

    @Given("I add the headers {string} {string}")
    public void iAddTheHeaders(String accept, String appJson) {


        requestSpecification = given()
                .auth()
                .preemptive()
                .basic("email", "password")
                .header(accept, appJson);

    }
    @When("I POST {string} to {string} path")
    public void iPOSTToPath(String payLoadFile, String path) {

        payLoadFile = "src/test/java/apitests/payload.json";

        response = requestSpecification.when().body(new File(payLoadFile))
                .post(path);

    }
    @Then("I verify the status code should be {int}")
    public void iVerifyTheStatusCodeShouldBe(Integer expectedStatusCode) {

        response.then().extract().response();

        Integer actualStatusCode = response.getStatusCode();

        Assert.assertEquals(expectedStatusCode, actualStatusCode);

    }


    @When("I POST info from POJO class to {string} path")
    public void iPOSTInfoFromPOJOClassToPath(String path) {

        Users users = new Users();
        users.setEmail("duotechb5@gmail.com");
        users.setPassword("duotechb5");

        response = requestSpecification.when().body(users)
                .post(path);

    }



    @Given("I add the headers {string} {string} and {string} {string}")
    public void i_add_the_headers_and(String accept, String jasonApp, String authorization, String autToken) {

        path = "/login.php";

        token =
                given()
                        .auth()
                        .preemptive()
                        .basic("email", "password")
                        .header("Accept", ContentType.JSON.getAcceptHeader()) // "application/json"
                        .contentType(ContentType.JSON). //
                        when().body(new File("src/test/java/apitests/payload.json"))
                        .post(path).
                        then().
                        extract().path("token");

        requestSpecification = given().
                header(accept, jasonApp).
                header(authorization, token);
    }
    @When("I send a GET request to {string} endpoint")
    public void i_send_a_get_request_to_endpoint(String endPoint) {

        response = requestSpecification.when().log().all().
                get(endPoint);
    }
    @Then("the status code should be {int}")
    public void the_status_code_should_be(Integer statusCode) {
        response.then().log().all().
                statusCode(statusCode);
    }
    @Then("The list should contains firstname {string} and {string}")
    public void the_list_should_contains_firstname_and(String name1, String name2) {
        response.then().log().all().
                body("mortagage_applications.b_firstName", hasItems(name1, name2));
    }





    @Given("I add the headers {string}, {string} and {string}, {string}")
    public void iAddTheHeadersAnd(String contentType, String appJson, String accept, String appJson2) {


        requestSpecification = given().log().all().
                header(contentType, appJson).
                header(accept, appJson2);

    }
    @Given("I create a fake credentials and request in a map")
    public void iCreateAFakeCredentialsAndRequestInAMap() {

        map = new LinkedHashMap<>();

        Faker fake = new Faker();
        firstName = fake.name().firstName();
        lastName = fake.name().lastName();
        email = fake.internet().emailAddress();
        password = fake.internet().password();

        map.put("first_name" , firstName);
        map.put("last_name" , lastName);
        map.put("email" , email);
        map.put("password" , password);

        body = requestSpecification.body(map);
    }
    @When("I POST newly created user")
    public void iPOSTNewlyCreatedUser() {

        path = "register.php";

        response = body.
                when().log().all().
                post(path);
    }
    @Then("I verify the status code should be not greater than {int}")
    public void iVerifyTheStatusCodeShouldBeNotGreaterThan(Integer statusCode) {

        response.then().log().all().
                statusCode(not(greaterThan(statusCode)));

    }
    @Then("The success {string} should be {string}")
    public void theSuccessShouldBe(String message, String successMessage) {

        response.then().log().all().
                body(message, is(successMessage));
    }



    @Given("I create a fake credentials using POJO class")
    public void iCreateAFakeCredentialsUsingPOJOClass() {

        Faker fake = new Faker();
        firstName = fake.name().firstName();
        lastName = fake.name().lastName();
        email = fake.internet().emailAddress();
        password = fake.internet().password();

        Users users = new Users(firstName, lastName, email, password);

        body = requestSpecification.body(users);
    }


    @Given("I have new user {string} and {string}")
    public void iHaveNewUserAnd(String email, String password) {
        path = "/login.php";


       requestSpecification = given()
                .auth()
                .preemptive()
                .basic(email, password)
                .header("Accept", "application/json");
    }
    @When("I add body and POST endpoint as {string}")
    public void iAddBodyAndPOSTEndpointAs(String endpoint) {

        response = requestSpecification.when().body("{" +
                        "  \"email\": " + map.get("email") + "," +
                        "  \"password\": \"" + map.get("password") +
                        "}")
                .post(endpoint);
    }
    @Then("I should be able to login and the status code should be {int}")
    public void iShouldBeAbleToLoginAndTheStatusCodeShouldBe(int statusCode) {

        response.then().extract().response();

        Assert.assertEquals(statusCode, response.getStatusCode());
    }


    @Given("I create a wrong credentials using wrong {string}, {string}, {string} and {string}")
    public void iCreateAWrongCredentialsUsingWrongAnd(String firstName, String lastName, String email, String password) {

        JsonObject jo = new JsonObject();
        jo.addProperty("first_name", firstName);
        jo.addProperty("last_name", lastName);
        jo.addProperty("email", email);
        jo.addProperty("password", password);
        body = requestSpecification.body(jo);
    }


    @When("I POST using POJO to {string} path")
    public void iPOSTUsingPOJOToPath(String path) {

        Users users = new Users();
        users.setEmail("invalidEmail@gmail.com");
        users.setPassword("invalidPass");

        response = requestSpecification.when().body(users)
                .post(path);

    }


    @When("I POST using POJO with invalid pass to {string} path")
    public void iPOSTUsingPOJOWithInvalidPassToPath(String path) {
        Users users = new Users();
        users.setEmail("duotechb5@gmail.com");
        users.setPassword("");

        response = requestSpecification.when().body(users)
                .post(path);
    }
}
