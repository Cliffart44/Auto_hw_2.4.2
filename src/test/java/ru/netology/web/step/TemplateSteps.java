package ru.netology.web.step;

import com.google.common.base.CharMatcher;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Пусть;
import cucumber.api.java.ru.Тогда;
import lombok.val;
import ru.alfabank.alfatest.cucumber.api.AkitaScenario;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.TransferPage;
import ru.netology.web.page.VerificationPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.codeborne.selenide.Selenide.*;
import static ru.alfabank.tests.core.helpers.PropertyLoader.loadProperty;

public class TemplateSteps {
    private final AkitaScenario scenario = AkitaScenario.getInstance();

    @Пусть("^пользователь залогинен с именем \"([^\"]*)\" и паролем \"([^\"]*)\";$")
    public void loginWithNameAndPassword(String login, String password) {
        val loginUrl = loadProperty("loginUrl");
        open(loginUrl);

        scenario.setCurrentPage(page(LoginPage.class));
        val loginPage = (LoginPage) scenario.getCurrentPage().appeared();
        val authInfo = new DataHelper.AuthInfo(login, password);
        scenario.setCurrentPage(loginPage.validLogin(authInfo));

        val verificationPage = (VerificationPage) scenario.getCurrentPage().appeared();
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        scenario.setCurrentPage(verificationPage.validVerify(verificationCode));

        scenario.getCurrentPage().appeared();
    }

//    @Когда("он переводит \"5 000\" рублей с карты с номером \"5559 0000 0000 0002\" на свою \"1\" карту с главной страницы")
//    public void moneyTransfer() {
//        boolean toCardOne = true;
//        String source;
//        if (toCardOne) source = DataHelper.cardNumber(2);
//        else source = DataHelper.cardNumber(1);
//        String value = "5000";
//        val dashboard = (DashboardPage) scenario.getCurrentPage().appeared();
//        scenario.setCurrentPage(page(DashboardPage.class));
//        scenario.setCurrentPage(dashboard.moneyTransfer(toCardOne).transaction(value, source));
//    }
//
//    @Тогда("баланс его \"1\" карты из списка на главной странице должен стать \"15 000\" рублей")
//    public void cardsBalance() {
//        boolean cardOne = true;
//        String tmp = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']").getOwnText().substring(20);
//        int balanceOne = Integer.parseInt(CharMatcher.inRange('0', '9').retainFrom(tmp));
//        String tmp2 = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']").getOwnText().substring(20);
//        int balanceTwo = Integer.parseInt(CharMatcher.inRange('0', '9').retainFrom(tmp2));
//        if (cardOne) {
//            assertEquals(15000, balanceOne);
//        } else {
//            assertEquals(5000, balanceTwo);
//        }

    @Когда("он переводит \"([^\"]*)\" рублей с карты с номером \"([^\"]*)\" на свою \"1\" карту с главной страницы")
    public void makeTransferToFirstCardFromSecond(String amountToTransfer, String numberOfCardFromTransfer) {
        val dashboardPage = (DashboardPage) scenario.getCurrentPage().appeared();
        scenario.setCurrentPage(dashboardPage.moneyTransferToCardOne());
        val transferPage = (TransferPage) scenario.getCurrentPage().appeared();
        scenario.setCurrentPage(transferPage.transaction(amountToTransfer, numberOfCardFromTransfer));
        scenario.getCurrentPage().appeared();
    }

    @Тогда("баланс его \"1\" карты из списка на главной странице должен стать \"([^\"]*)\" рублей")
    public void checkBalanceFirstCard(String balanceOfFirstCard) {
        val dashboardPage = (DashboardPage) scenario.getCurrentPage().appeared();
        String realBalanceOfFirstCard = dashboardPage.getCardOneBalance();
        assertEquals(balanceOfFirstCard, realBalanceOfFirstCard);
    }
}