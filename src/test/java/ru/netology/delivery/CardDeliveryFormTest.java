package ru.netology.delivery;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryFormTest {

    @Test
    public void shouldSendOrderForm() {
        RegistrationInfo userInfo = RegistrationInfoGenerator.generateRegistrationInfo();
        String firstDate = RegistrationInfoGenerator.generateDate();
        String newDate = RegistrationInfoGenerator.generateDate();

        open("http://localhost:9999/");
        SelenideElement form = $("form");
        form.$("[data-test-id='city'] input").setValue(userInfo.getCity());
        form.$("[data-test-id='date'] input").doubleClick();
        form.$("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        form.$("[data-test-id='date'] input").setValue(firstDate);
        form.$("[data-test-id='name'] input").setValue(userInfo.getName());
        form.$("[data-test-id='phone'] input").setValue(userInfo.getPhone());
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $("[data-test-id='success-notification']").shouldHave(Condition.text("Встреча успешно запланирована на " + firstDate));
        $("[data-test-id='success-notification'] .icon-button").click();

        form.$("[data-test-id='date'] input").doubleClick();
        form.$("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        form.$("[data-test-id='date'] input").setValue(newDate);
        form.$(".button").click();
        $("[data-test-id='replan-notification']").shouldHave(Condition.text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $("[data-test-id='replan-notification'] .button").click();
        $("[data-test-id='success-notification']").shouldHave(Condition.text("Встреча успешно запланирована на " + newDate));
    }
}