package stepDefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import pages.ApplicationPage;
import pages.LoginPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.SeleniumUtils;

public class preapprovalDetailsStepDefs {

    String email;
    String password;
    String purposeOfLoan;
    String estimatedPurchasePrice;
    String downPaymentAmount;
    String paymentSourceAnotherType;
    String realtorInfo;
    String expectedLoanAmount;
    String calculatedLoanAmount;


    @Given("I am in dashboard page and click on Mortgage App link")
    public void iAmInDashboardPageAndClickOnMortgageAppLink() {

        Driver.getDriver().get(ConfigReader.getProperty("url"));

        email = ConfigReader.getProperty("email");
        password = ConfigReader.getProperty("pass");

        LoginPage loginPage = new LoginPage();
        loginPage.login(email, password);

        ApplicationPage appPage = new ApplicationPage();
        SeleniumUtils.waitForClickablility(appPage.mortgageApplicationButton, 5);
        appPage.mortgageApplicationButton.click();

    }
    @When("I fill up all fields on the preapproval details page")
    public void iFillUpAllFieldsOnThePreapprovalDetailsPage() {

        purposeOfLoan = "Purchase A Home";
        estimatedPurchasePrice = "" + (500000 + (int)(Math.random() * 500000));
        downPaymentAmount = "" + (100000 + (int)(Math.random() * 400000));
        paymentSourceAnotherType = "Other type of Down Payment";
        realtorInfo = new Faker().name().fullName() + ", " + new Faker().internet().emailAddress();

        ApplicationPage appPage = new ApplicationPage();

        appPage.realtorInfoField.sendKeys(realtorInfo);
        appPage.workingWithLoanOfficerNO.click(); // ARE YOU WORKING WITH A REALTOR? - NO
        appPage.purposeOfLoanButton.click();
        appPage.purposeOfLoanField.sendKeys(purposeOfLoan, Keys.ENTER);
        appPage.estimatedPurchasePriceField.sendKeys(estimatedPurchasePrice);
        appPage.downPaymentAmountField.sendKeys(downPaymentAmount);
        appPage.downPaymentSourceButton.click();
        appPage.downPaymentSourceField.sendKeys(paymentSourceAnotherType, Keys.ENTER);
        appPage.nextButton.click();

    }
    @Then("I can pass to the personal information page")
    public void iCanPassToThePersonalInformationPage() {
        String personalInformationPageTextExpected = "PERSONAL INFORMATION";
        ApplicationPage appPage = new ApplicationPage();
        Assert.assertEquals(appPage.personalInformationPageText.getText(), personalInformationPageTextExpected);

    }


    @When("I fill up necessary fields on the preapproval details page")
    public void iFillUpNecessaryFieldsOnThePreapprovalDetailsPage() {

        purposeOfLoan = "Purchase A Home";
        estimatedPurchasePrice = "" + (500000 + (int)(Math.random() * 500000));
        downPaymentAmount = "" + (100000 + (int)(Math.random() * 400000));
        paymentSourceAnotherType = "Other type of Down Payment";
        realtorInfo = new Faker().name().fullName() + ", " + new Faker().internet().emailAddress();

        ApplicationPage appPage = new ApplicationPage();

        appPage.realtorInfoField.sendKeys(realtorInfo);
        appPage.workingWithLoanOfficerNO.click(); // ARE YOU WORKING WITH A REALTOR? - NO
        appPage.purposeOfLoanButton.click();
        appPage.purposeOfLoanField.sendKeys(purposeOfLoan, Keys.ENTER);
        appPage.estimatedPurchasePriceField.sendKeys(estimatedPurchasePrice);
        appPage.downPaymentAmountField.sendKeys(downPaymentAmount);
        appPage.downPaymentSourceButton.click();
        appPage.downPaymentSourceField.sendKeys(paymentSourceAnotherType, Keys.ENTER);
    }

    @Then("I can check calculated loan amount credibility")
    public void iCanCheckCalculatedLoanAmountCredibility() {

        expectedLoanAmount = "" + (Integer.parseInt(estimatedPurchasePrice) - Integer.parseInt(downPaymentAmount));
        calculatedLoanAmount = new ApplicationPage().calculatedLoanAmountValue.getAttribute("value");
        Assert.assertEquals(calculatedLoanAmount, expectedLoanAmount);
    }



    @When("I fill up necessary fields on the preapproval details page to check percentage")
    public void iFillUpNecessaryFieldsOnThePreapprovalDetailsPageToCheckPercentage() {

        purposeOfLoan = "Purchase A Home";
        estimatedPurchasePrice = "" + (500000 + (int)(Math.random() * 500000));
        downPaymentAmount = "" + (100000 + (int)(Math.random() * 400000));
        paymentSourceAnotherType = "Other type of Down Payment";
        realtorInfo = new Faker().name().fullName() + ", " + new Faker().internet().emailAddress();

        ApplicationPage appPage = new ApplicationPage();

        appPage.realtorInfoField.sendKeys(realtorInfo);
        appPage.workingWithLoanOfficerNO.click(); // ARE YOU WORKING WITH A REALTOR? - NO
        appPage.purposeOfLoanButton.click();
        appPage.purposeOfLoanField.sendKeys(purposeOfLoan, Keys.ENTER);
        appPage.estimatedPurchasePriceField.sendKeys(estimatedPurchasePrice);
        appPage.downPaymentAmountField.sendKeys(downPaymentAmount);
        appPage.downPaymentSourceButton.click();
        appPage.downPaymentSourceField.sendKeys(paymentSourceAnotherType, Keys.ENTER);
    }

    @Then("I can check calculated loan amount percentage")
    public void iCanCheckCalculatedLoanAmountPercentage() {

        String calculatedPercentage = new ApplicationPage().downPaymentPercentageField.getAttribute("value");
        String expectedDownPaymentPercentage = "" + (Integer.parseInt(downPaymentAmount) * 100
                / Integer.parseInt(estimatedPurchasePrice));
        org.testng.Assert.assertEquals(calculatedPercentage, expectedDownPaymentPercentage);
    }

    @When("I fill up all fields on the preapproval details page using {string} as a realtor information")
    public void iFillUpAllFieldsOnThePreapprovalDetailsPageUsingAsARealtorInformation(String emptySpace) {

        purposeOfLoan = "Purchase A Home";
        estimatedPurchasePrice = "" + (500000 + (int)(Math.random() * 500000));
        downPaymentAmount = "" + (100000 + (int)(Math.random() * 400000));
        paymentSourceAnotherType = "Other type of Down Payment";
        realtorInfo = emptySpace;

        ApplicationPage appPage = new ApplicationPage();

        appPage.realtorInfoField.sendKeys(realtorInfo);
        appPage.workingWithLoanOfficerNO.click(); // ARE YOU WORKING WITH A REALTOR? - NO
        appPage.purposeOfLoanButton.click();
        appPage.purposeOfLoanField.sendKeys(purposeOfLoan, Keys.ENTER);
        appPage.estimatedPurchasePriceField.sendKeys(estimatedPurchasePrice);
        appPage.downPaymentAmountField.sendKeys(downPaymentAmount);
        appPage.downPaymentSourceButton.click();
        appPage.downPaymentSourceField.sendKeys(paymentSourceAnotherType, Keys.ENTER);
        appPage.nextButton.click();
    }
    @Then("I can pass to the personal information page and verify it")
    public void iCanPassToThePersonalInformationPageAndVerifyIt() {

        String personalInformationPageTextExpected = "PERSONAL INFORMATION";
        ApplicationPage appPage = new ApplicationPage();
        Assert.assertEquals(appPage.personalInformationPageText.getText(), personalInformationPageTextExpected);

    }
}
