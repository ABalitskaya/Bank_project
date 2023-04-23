package pages.customerLogin.account.transactions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PageBase;
import wait.Wait;

public class TransactionsPage extends PageBase {
    public TransactionsPage(WebDriver driver) {
        super(driver);
    }

    Wait wait;

    @FindBy(xpath = " (//input[@id='start'])[1]")
    protected WebElement dropdownCalendar;
    /*@FindBy(xpath = "daytime")      //example
    protected WebElement dateBox;*/
    @FindBy(xpath = "//a[normalize-space()='Date-Time']")
    protected WebElement columnNameDataTime;
    @FindBy(xpath = "(//a[normalize-space()='Amount'])[1]")
    protected WebElement columnNameAmount;
    @FindBy(xpath = "(//a[normalize-space()='Transaction Type'])[1]")
    protected WebElement columnNameTransactionType;
    @FindBy(xpath = "(//button[normalize-space()='Back'])[1]")
    protected WebElement backButton;
    @FindBy(xpath = "(//button[normalize-space()='Reset'])[1]")
    protected WebElement resetButton;

    @FindBy(xpath = "//td[normalize-space()='Credit']")
    protected WebElement transactionTypeCredit;

    @FindBy(xpath = "//tbody//td[2]")
    protected WebElement amountTransactionPage;

    @FindBy(css = "tr[id='anchor0'] td:nth-child(2)")
    protected WebElement tableAmountRow;


   /* @FindBy(xpath = "//*[@ng-click='home()']")
    protected WebElement homeButton;

    @FindBy(xpath = "//*[@ng-click='byebye()']")
    protected WebElement logoutButton;*/

    public void waitForLoading() {
        wait = new Wait(driver);
        wait.forVisibility(columnNameDataTime);
        wait.forVisibility(dropdownCalendar);
        wait.forVisibility(backButton);
        wait.forVisibility(resetButton);
    }

    //you need to add a date picker method
 /*   public void changeDataTime() {
        click(dropdownCalendar);
        dateBox.sendKeys("Apr 5, 2023");
        dateBox.sendKeys(Keys.TAB);
        //click();
    }*/

    public String getAmount() {
        return tableAmountRow.getText();
    }

    public void clickOnColumnNameDataTime() {
        click(columnNameDataTime);
    }

    public void clickOnColumnNameAmount() {
        click(columnNameAmount);
    }

    public void clickOnColumnNameTransactionType() {
        click(columnNameTransactionType);
    }

    public void clickOnResetButton() {
        click(resetButton);
    }

    public void clickOnBackButton() {
        click(backButton);
    }

    public void checkTransactionType(String expectedResult) {
        checkItemText(transactionTypeCredit, expectedResult, "Error");
    }

    public void checkAmount(String expectedResult) {
        checkItemText(amountTransactionPage, expectedResult, "Error");
    }
}

