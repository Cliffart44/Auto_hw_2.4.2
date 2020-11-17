package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.google.common.base.CharMatcher;
import org.openqa.selenium.support.FindBy;
import ru.alfabank.alfatest.cucumber.annotations.Name;
import ru.alfabank.alfatest.cucumber.api.AkitaPage;

import static com.codeborne.selenide.Selenide.*;

@Name("Дашбоард")
public class DashboardPage extends AkitaPage {
    private String dataTestId;
    @FindBy(css = "[data-test-id=dashboard]")
    private SelenideElement heading;
    @FindBy(css = "[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] .button__text")
    private SelenideElement addFundsCard1Button;
    @FindBy(css = "[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] .button__text")
    private SelenideElement addFundsCard2Button;

    private void defineCard(String number) {
        ElementsCollection cards = $$("li div");
        int required = Integer.parseInt(CharMatcher.inRange('0', '9').retainFrom(number.substring(15, 19)));
        for (SelenideElement card : cards) {
            int found = Integer.parseInt(CharMatcher.inRange('0', '9').retainFrom(card.getText().substring(15, 19)));
            if (found == required) dataTestId = card.getAttribute("data-test-id");
        }
    }

    public String defineBalance(String number) {
        defineCard(number);
        String tmp = $("[data-test-id='" + dataTestId + "']").getOwnText().substring(20);
        return (CharMatcher.inRange('0', '9').retainFrom(tmp));
    }

    public TransferPage moneyTransfer(String number) {
        defineCard(number);
        $("[data-test-id='" + dataTestId + "'] .button__text").click();
        return Selenide.page(TransferPage.class);
    }
}