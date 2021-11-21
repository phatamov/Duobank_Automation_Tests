package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.ApplicationPage;
import pages.LoginPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.SeleniumUtils;

public class LogInStepDefs {

    @Given("I am on the homepage")
    public void iAmOnTheHomepage() {
        Driver.getDriver().get(ConfigReader.getProperty("url"));

    }
    @When("I log in with the valid credentials")
    public void iLogInWithTheValidCredentials() {

        String testerEmail = ConfigReader.getProperty("email");
        String testerPassword = ConfigReader.getProperty("pass");

        LoginPage loginPage = new LoginPage();
        loginPage.login(testerEmail, testerPassword);

    }
    @Then("I can pass to the Application page")
    public void iCanPassToTheApplicationPage() {

        String dashboardUrl = ConfigReader.getProperty("dashboardUrl");
        Assert.assertEquals(Driver.getDriver().getCurrentUrl(), dashboardUrl);

    }
}
