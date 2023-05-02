package tests.account;

import org.testng.annotations.Test;
import pages.HomePage;
import pages.customerLogin.CustomerLoginPage;
import pages.customerLogin.account.AccountPage;
import pages.customerLogin.account.deposit.DepositPage;
import pages.customerLogin.account.enums.Accounts;
import pages.customerLogin.account.enums.Currency;
import pages.customerLogin.account.enums.Users;
import pages.customerLogin.account.transactions.TransactionsPage;
import tests.TestBase;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class DepositTestWithEnum extends TestBase {
    Random random = new Random();
    HomePage homePage;
    CustomerLoginPage customerLoginPage;
    AccountPage accountPage;
    DepositPage depositPage;
    TransactionsPage transactionsPage;
    StringBuilder expectedResult = new StringBuilder();
    // Сделать через стрингбилдер все!
    String userName = Users.DUMBLEDORE.getName();
    Integer amountRandom = random.nextInt(1, 500000);
    Integer amountRandom2 = random.nextInt(500001, 1000000);
    String amountRandomString = amountRandom.toString();
    String amountRandom2String = amountRandom2.toString();
    Integer amountRandomSum = amountRandom + amountRandom2;

    @Test
    public void DepositExistCustomerWithValidDataTest() {
        homePage = new HomePage(app.driver);
        homePage.waitForLoading();
        homePage.clickOnCustomerLoginButton();

        customerLoginPage = new CustomerLoginPage(app.driver);
        customerLoginPage.waitForLoading();
        customerLoginPage.selectExistingUser(userName);
        customerLoginPage.checkForVisibilityLoginButton();
        customerLoginPage.clickOnLoginButton();

        accountPage = new AccountPage(app.driver);
        accountPage.waitForLoading();

        LinkedHashMap<String, String> accountsAndCurrency = new LinkedHashMap<>();
        accountsAndCurrency.put(Accounts.DUMBLEDORE_DOLLAR.getNumberOfAccount(), Currency.DOLLAR.getCurrency());
        accountsAndCurrency.put(Accounts.DUMBLEDORE_POUND.getNumberOfAccount(), Currency.POUND.getCurrency());
        accountsAndCurrency.put(Accounts.DUMBLEDORE_RUPEE.getNumberOfAccount(), Currency.RUPEE.getCurrency());


        for (Map.Entry<String, String> pair : accountsAndCurrency.entrySet()) {
            String account = pair.getKey();
            String currency = pair.getValue();

            accountPage.selectCurrencyAccount(account);
            accountPage.clickDepositButton();

            Integer balance = Integer.parseInt(accountPage.getBalance());
            Integer balanceAndAmount = amountRandom + balance;
            String balanceAndAmountString = balanceAndAmount.toString();
            accountPage.checkAccountNumberBalanceCurrencyText(account, balance.toString(), currency);
            depositPage = new DepositPage(app.driver);
            depositPage.waitForLoading();
            depositPage.fillAmountField(amountRandomString);
            depositPage.clickOnDepositButtonConfirm();
            depositPage.checkForVisibilityDepositSuccessful();

            accountPage.checkAccountNumberBalanceCurrencyText(account, balanceAndAmountString, currency);

            depositPage.fillAmountField(amountRandom2String);
            depositPage.clickOnDepositButtonConfirm();
            depositPage.checkForVisibilityDepositSuccessful();

            Integer amountRandomSumAndBalance = amountRandomSum + balance;
            String amountRandomSumAndBalanceString = amountRandomSumAndBalance.toString();
            accountPage.checkAccountNumberBalanceCurrencyText(account, amountRandomSumAndBalanceString, currency);

            accountPage.clickTransactionsButton();

            transactionsPage = new TransactionsPage(app.driver);
            transactionsPage.waitForLoading();
            transactionsPage.clickOnResetButton();
            transactionsPage.clickOnBackButton();

            accountPage.checkAccountNumberBalanceCurrencyText(account, balance.toString(), currency);
        }
        homePage.clickOnLogoutButton();
    }

}