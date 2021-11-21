package stepDefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import pages.ApplicationPage;
import pages.LoginPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.SeleniumUtils;

public class personalInformationStepDefs {

    String firstName;
    String lastName;
    String email;
    String dateOfBirth;
    String ssn;
    String cellNumber;
    String homeNumber;
    String married;

    @Given("I am in preapproval details page and click on the next button to move to personal info page")
    public void iAmInPreapprovalDetailsPageAndClickOnTheNextButtonToMoveToPersonalInfoPage() {

        Driver.getDriver().get(ConfigReader.getProperty("url"));

        String email = ConfigReader.getProperty("email");
        String password = ConfigReader.getProperty("pass");

        new LoginPage().login(email, password);

        ApplicationPage appPage = new ApplicationPage();
        SeleniumUtils.waitForClickablility(appPage.mortgageApplicationButton, 10);
        appPage.mortgageApplicationButton.click();

        String purposeOfLoan = "Purchase A Home";
        String estimatedPurchasePrice = "" + (500000 + (int)(Math.random() * 500000));
        String downPaymentAmount = "" + (100000 + (int)(Math.random() * 400000));
        String paymentSourceAnotherType = "Other type of Down Payment";
        String realtorInfo = new Faker().name().fullName() + ", " + new Faker().internet().emailAddress();

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
    @When("I pass to personal info page")
    public void iPassToPersonalInfoPage() {

    }
    @Then("I verify personal info page text")
    public void iVerifyPersonalInfoPageText() {
        String personalInformationPageTextExpected = "PERSONAL INFORMATION";
        Assert.assertEquals(new ApplicationPage().personalInformationPageText.getText(), personalInformationPageTextExpected);
    }


    @When("I fill up all fields on the personal info page")
    public void iFillUpAllFieldsOnThePersonalInfoPage() {

        firstName = new Faker().name().firstName();
        lastName = new Faker().name().lastName();
        email = new Faker().internet().emailAddress();
        dateOfBirth = "05/05/1985";
        ssn = "123-45-6789";
        cellNumber = "121-100-0101";
        homeNumber = "222-100-0101";
        married = "Married";

        ApplicationPage appPage = new ApplicationPage();
        appPage.firstNameField.sendKeys(firstName);
        appPage.lastNameField.sendKeys(lastName);
        appPage.emailField.sendKeys(email);
        appPage.dateOfBirthField.sendKeys(dateOfBirth);
        appPage.ssnField.sendKeys(ssn);
        appPage.maritalStatusButton.click();
        appPage.maritalStatusField.sendKeys(married, Keys.ENTER);
        appPage.cellPhoneField.sendKeys(cellNumber);
        appPage.homePhoneField.sendKeys(homeNumber);
        appPage.nextButton.click();
    }
    @Then("I can pass to the expenses page")
    public void iCanPassToTheExpensesPage() {

        String expensesPageTextExpected = "EXPENSES";
        Assert.assertEquals(new ApplicationPage().expensesPageText.getText(), expensesPageTextExpected);

    }


    @When("I add empty space {string} for First Name and Last Name")
    public void iAddEmptySpaceForFirstNameAndLastName(String emptySpace) {

        firstName = emptySpace;
        lastName = emptySpace;
        email = new Faker().internet().emailAddress();
        dateOfBirth = "05/05/1985";
        ssn = "123-45-6789";
        cellNumber = "121-100-0101";
        homeNumber = "222-100-0101";
        married = "Married";

        ApplicationPage appPage = new ApplicationPage();
        appPage.firstNameField.sendKeys(firstName);
        appPage.lastNameField.sendKeys(lastName);
        appPage.emailField.sendKeys(email);
        appPage.dateOfBirthField.sendKeys(dateOfBirth);
        appPage.ssnField.sendKeys(ssn);
        appPage.maritalStatusButton.click();
        appPage.maritalStatusField.sendKeys(married, Keys.ENTER);
        appPage.cellPhoneField.sendKeys(cellNumber);
        appPage.homePhoneField.sendKeys(homeNumber);
        appPage.nextButton.click();
    }
    @Then("I can pass to the expenses page with a wrong name format")
    public void iCanPassToTheExpensesPageWithAWrongNameFormat() {

        String expensesPageTextExpected = "EXPENSES";
        Assert.assertEquals(new ApplicationPage().expensesPageText.getText(), expensesPageTextExpected);
    }


    @When("I fill up the application with a wrong date of birth {string}")
    public void iFillUpTheApplicationWithAWrongDateOfBirth(String wrongDateOfBirth) {

        firstName = new Faker().name().firstName();
        lastName = new Faker().name().lastName();
        email = new Faker().internet().emailAddress();
        dateOfBirth = wrongDateOfBirth;
        ssn = "123-45-6789";
        cellNumber = "121-100-0101";
        homeNumber = "222-100-0101";
        married = "Married";

        ApplicationPage appPage = new ApplicationPage();
        appPage.firstNameField.sendKeys(firstName);
        appPage.lastNameField.sendKeys(lastName);
        appPage.emailField.sendKeys(email);
        appPage.dateOfBirthField.sendKeys(dateOfBirth);
        appPage.ssnField.sendKeys(ssn);
        appPage.maritalStatusButton.click();
        appPage.maritalStatusField.sendKeys(married, Keys.ENTER);
        appPage.cellPhoneField.sendKeys(cellNumber);
        appPage.homePhoneField.sendKeys(homeNumber);
        appPage.nextButton.click();
    }
    @Then("I verify if I pass to the expenses page")
    public void iVerifyIfIPassToTheExpensesPage() {

        String expensesPageTextExpected = "EXPENSES";
        Assert.assertEquals(new ApplicationPage().expensesPageText.getText(), expensesPageTextExpected);
    }


    @When("I fill up the application with a wrong SSN {string}")
    public void iFillUpTheApplicationWithAWrongSSN(String wrongSsn) {

        firstName = new Faker().name().firstName();
        lastName = new Faker().name().lastName();
        email = new Faker().internet().emailAddress();
        dateOfBirth = "05/05/1985";
        ssn = wrongSsn;
        cellNumber = "121-100-0101";
        homeNumber = "222-100-0101";
        married = "Married";

        ApplicationPage appPage = new ApplicationPage();
        appPage.firstNameField.sendKeys(firstName);
        appPage.lastNameField.sendKeys(lastName);
        appPage.emailField.sendKeys(email);
        appPage.dateOfBirthField.sendKeys(dateOfBirth);
        appPage.ssnField.sendKeys(ssn);
        appPage.maritalStatusButton.click();
        appPage.maritalStatusField.sendKeys(married, Keys.ENTER);
        appPage.cellPhoneField.sendKeys(cellNumber);
        appPage.homePhoneField.sendKeys(homeNumber);
        appPage.nextButton.click();
    }
    @Then("I verify if I pass to the expenses page with wrong ssn")
    public void iVerifyIfIPassToTheExpensesPageWithWrongSsn() {

        String expensesPageTextExpected = "EXPENSES";
        Assert.assertEquals(new ApplicationPage().expensesPageText.getText(), expensesPageTextExpected);
    }



    @When("I fill up the application with a wrong cell number {string}")
    public void iFillUpTheApplicationWithAWrongCellNumber(String wrongCellNumber) {

        firstName = new Faker().name().firstName();
        lastName = new Faker().name().lastName();
        email = new Faker().internet().emailAddress();
        dateOfBirth = "05/05/1985";
        ssn = "123-45-6789";
        cellNumber = wrongCellNumber;
        homeNumber = "222-100-0101";
        married = "Married";

        ApplicationPage appPage = new ApplicationPage();
        appPage.firstNameField.sendKeys(firstName);
        appPage.lastNameField.sendKeys(lastName);
        appPage.emailField.sendKeys(email);
        appPage.dateOfBirthField.sendKeys(dateOfBirth);
        appPage.ssnField.sendKeys(ssn);
        appPage.maritalStatusButton.click();
        appPage.maritalStatusField.sendKeys(married, Keys.ENTER);
        appPage.cellPhoneField.sendKeys(cellNumber);
        appPage.homePhoneField.sendKeys(homeNumber);
        appPage.nextButton.click();

    }
    @Then("I verify if I pass to the expenses page with wrong number")
    public void iVerifyIfIPassToTheExpensesPageWithWrongNumber() {

        String expensesPageTextExpected = "EXPENSES";
        Assert.assertEquals(new ApplicationPage().expensesPageText.getText(), expensesPageTextExpected);
    }


    @When("I fill up the application with a wrong email {string}")
    public void iFillUpTheApplicationWithAWrongEmail(String wrongEmail) {

        firstName = new Faker().name().firstName();
        lastName = new Faker().name().lastName();
        email = wrongEmail;
        dateOfBirth = "05/05/1985";
        ssn = "123-45-6789";
        cellNumber = "121-100-0101";
        homeNumber = "222-100-0101";
        married = "Married";

        ApplicationPage appPage = new ApplicationPage();
        appPage.firstNameField.sendKeys(firstName);
        appPage.lastNameField.sendKeys(lastName);
        appPage.emailField.sendKeys(email);
        appPage.dateOfBirthField.sendKeys(dateOfBirth);
        appPage.ssnField.sendKeys(ssn);
        appPage.maritalStatusButton.click();
        appPage.maritalStatusField.sendKeys(married, Keys.ENTER);
        appPage.cellPhoneField.sendKeys(cellNumber);
        appPage.homePhoneField.sendKeys(homeNumber);
        appPage.nextButton.click();
    }
    @Then("I verify if I pass to the expenses page with wrong email")
    public void iVerifyIfIPassToTheExpensesPageWithWrongEmail() {

        String expensesPageTextExpected = "EXPENSES";
        Assert.assertEquals(new ApplicationPage().expensesPageText.getText(), expensesPageTextExpected);
    }



    @When("I fill up the application using {string}, {string} and {string}")
    public void iFillUpTheApplicationUsingAnd(String first_name, String last_name, String e_mail) {

        firstName = first_name;
        lastName = last_name;
        email = e_mail;
        dateOfBirth = "05/05/1985";
        ssn = "123-45-6789";
        cellNumber = "121-100-0101";
        homeNumber = "222-100-0101";
        married = "Married";

        ApplicationPage appPage = new ApplicationPage();
        appPage.firstNameField.sendKeys(firstName);
        appPage.lastNameField.sendKeys(lastName);
        appPage.emailField.sendKeys(email);
        appPage.dateOfBirthField.sendKeys(dateOfBirth);
        appPage.ssnField.sendKeys(ssn);
        appPage.maritalStatusButton.click();
        appPage.maritalStatusField.sendKeys(married, Keys.ENTER);
        appPage.cellPhoneField.sendKeys(cellNumber);
        appPage.homePhoneField.sendKeys(homeNumber);
        appPage.nextButton.click();
    }
    @Then("I verify if I pass to the expenses page with all data")
    public void iVerifyIfIPassToTheExpensesPageWithAllData() {

        String expensesPageTextExpected = "EXPENSES";
        Assert.assertEquals(new ApplicationPage().expensesPageText.getText(), expensesPageTextExpected);
    }

}


