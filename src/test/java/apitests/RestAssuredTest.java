package apitests;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class RestAssuredTest {

    static String token;
    String path;


    @Test
    public void loginAndVerifyStatus(){

        baseURI = "http://duobank-env.eba-hjmrxg9a.us-east-2.elasticbeanstalk.com/api";

        path = "/login.php";

        Response response =

                given()
                        .auth()
                        .preemptive()
                        .basic("email", "password")
                        .header("Accept", ContentType.JSON.getAcceptHeader()) // "application/json"
                        .contentType(ContentType.JSON). //
                        when().body(new File("/Users/rafaelaziz/IdeaProjects/Duobank_cucumber/src/test/java/apitests/payload.json"))
                        .post(path).
                        then().extract().response();

        Assert.assertEquals(200, response.getStatusCode());

    }


    @Test
    public void loginAndVerifyUsers(){

        baseURI = "http://duobank-env.eba-hjmrxg9a.us-east-2.elasticbeanstalk.com/api";

        String path = "/login.php";

        token =
                given()
                        .auth()
                        .preemptive()
                        .basic("email", "password")
                        .header("Accept", ContentType.JSON.getAcceptHeader()) // "application/json"
                        .contentType(ContentType.JSON). //
                        when().body(new File("/Users/rafaelaziz/IdeaProjects/Duobank_cucumber/src/test/java/apitests/payload.json"))
                        .post(path).
                        then().
                        extract().path("token");

        given().
                header("Authorization", token).
                header("Accept", "application/json").

        when().log().all().
                get("getmortagage.php").
        then().log().all().
                statusCode(200).
                body("mortagage_applications[1].b_firstName", equalTo("Thomas"));
    }




    @Test
    public void serializationCreatingNewUser(){

        baseURI = "http://duobank-env.eba-hjmrxg9a.us-east-2.elasticbeanstalk.com/api";

        path = "register.php";

        Map<String, Object> map = new LinkedHashMap<>();

        Faker fake = new Faker();
        String firstName = fake.name().firstName();
        String lastName = fake.name().lastName();
        String email = fake.internet().emailAddress();
        String password = fake.internet().password();

        map.put("first_name" , firstName);
        map.put("last_name" , lastName);
        map.put("email" , email);
        map.put("password" , password);

        given().log().all().
                header("Content-Type", "application/json").
                header("Accept", "application/json").
                body(map).
        when().log().all().
                post(path).
        then().log().all().
                statusCode(not(greaterThan(300))).
                body("message", is("You have successfully registered."));



    }


    @Test
    public void createNewUserAndVerifyLogin(){

        baseURI = "http://duobank-env.eba-hjmrxg9a.us-east-2.elasticbeanstalk.com/api";

        path = "register.php";

        Map<String, Object> map = new LinkedHashMap<>();

        Faker fake = new Faker();
        String firstName = fake.name().firstName();
        String lastName = fake.name().lastName();
        String email = fake.internet().emailAddress();
        String password = fake.internet().password();

        map.put("first_name" , firstName);
        map.put("last_name" , lastName);
        map.put("email" , email);
        map.put("password" , password);

        given().log().all().
                header("Content-Type", "application/json").
                header("Accept", "application/json").
                body(map).
        when().log().all().
                post(path).
        then().log().all().
                statusCode(not(greaterThan(300))).
                body("message", is("You have successfully registered."));

        path = "/login.php";

        Response response =

                given()
                        .auth()
                        .preemptive()
                        .basic("email", "password")
                        .header("Accept", "application/json").
                when().body("{" +
                                "  \"email\": "+map.get("email")+"," +
                                "  \"password\": \"" + map.get("password") +
                                "}")
                        .post(path).
                then().extract().response();

        Assert.assertEquals(200, response.getStatusCode());


    }

}
