package ru.netology.web.page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import ru.alfabank.alfatest.cucumber.annotations.Name;
import ru.alfabank.alfatest.cucumber.api.AkitaPage;

@Name("Страница перевода")
public class TransferPage extends AkitaPage {
    @FindBy(css = ".money-input .input__control")
    private SelenideElement totField;
    @FindBy(css = "[data-test-id='from'] .input__control")
    private SelenideElement sourceCardField;
    @FindBy(css = "[data-test-id='action-transfer'] .button__text")
    private SelenideElement addFundsButton;
    @FindBy(css = "[data-test-id='action-cancel'] .button__text")
    private SelenideElement cancelButton;

    public DashboardPage transaction(String value, String source) {
        totField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE, value.trim());
        sourceCardField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE, source.trim());
        addFundsButton.click();
        return Selenide.page(DashboardPage.class);
    }
}