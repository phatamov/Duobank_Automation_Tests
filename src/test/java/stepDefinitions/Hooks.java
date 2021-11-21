package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.DBUtility;
import utilities.Driver;
import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

public class Hooks {

    @Before ("not @api")
    public void setupScenario(){
        Driver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Driver.getDriver().manage().deleteAllCookies();
        Driver.getDriver().manage().window().maximize();


    }
    @Before ("@api")
    public void setupAPI(){

        baseURI = "http://duobank-env.eba-hjmrxg9a.us-east-2.elasticbeanstalk.com/api";


    }

    @Before ("@db")
    public void setupDb(){

        DBUtility.createConnection();
    }

    @After ("@db")
    public void tearDownDb(){
        DBUtility.close();
    }


    @After ("not @api")
    public void tearDownScenario(Scenario scenario){
         if(scenario.isFailed()){
             byte[] screenshotAs = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
             scenario.attach(screenshotAs, "image/png" , "failedScreenshot");
         }


        Driver.quitDriver();

    }
}
