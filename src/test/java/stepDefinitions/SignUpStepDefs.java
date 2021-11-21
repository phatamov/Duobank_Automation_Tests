package stepDefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.SignUpPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.SeleniumUtils;

public class SignUpStepDefs {

    String firstName;
    String lastName;
    String email;
    String password;

    @Given("I am in homepage and click on sign up")
    public void i_am_in_homepage_and_click_on_sign_up() {
        Driver.getDriver().get(ConfigReader.getProperty("url"));
    }
    @When("I fill up the fields with the new user info")
    public void i_fill_up_the_fields_with_the_new_user_info() {
        Faker fake = new Faker();

        firstName = fake.name().firstName();
        lastName = fake.name().lastName();
        email = fake.internet().emailAddress();
        password = fake.internet().password();

        SignUpPage signUpPage = new SignUpPage();
        signUpPage.signUp(firstName, lastName, email, password);
    }
    @Then("I can pass to the mortgage application page")
    public void i_can_pass_to_the_mortgage_application_page() {

        SeleniumUtils.waitForUrlContains(ConfigReader.getProperty("url"), 5);
        Assert.assertEquals(Driver.getDriver().getCurrentUrl(), ConfigReader.getProperty("url"));

    }
}