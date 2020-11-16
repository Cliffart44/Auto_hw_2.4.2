package ru.netology.web.page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.alfabank.alfatest.cucumber.annotations.Name;
import ru.alfabank.alfatest.cucumber.api.AkitaPage;

@Name("Дашбоард")
public class DashboardPage extends AkitaPage {
    @FindBy(css = "[data-test-id=dashboard]")
    private SelenideElement heading;
    @FindBy(css = "[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] .button__text")
    private SelenideElement addFundsCard1Button;
    @FindBy(css = "[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] .button__text")
    private SelenideElement addFundsCard2Button;

    public TransferPage moneyTransfer(boolean toCardOne) {
        if (toCardOne) addFundsCard1Button.click();
        else addFundsCard2Button.click();
        return Selenide.page(TransferPage.class);
    }

//    public void moneyTransferToCardTwo() {
//        addFundsCard2Button.click();
//    }
}