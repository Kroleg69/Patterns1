package ru.netology;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CardDeliveryTest {
    ru.netology.DataGenerator dataGenerator = new ru.netology.DataGenerator();


    @Test
    void shouldSubmitRequest() {
        String name = dataGenerator.makeName();
        String phone = dataGenerator.makePhone();
        String city = dataGenerator.makeCity();

        open("http://localhost:9999");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Город']").setValue(city);
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(dataGenerator.forwardDate(3));
        $("[name=name]").setValue(name);
        $("[name=phone]").setValue(phone);
        $(".checkbox__box").click();
        $(".button__text").click();
        $(withText("Успешно")).shouldBe(visible);
        $("input[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(dataGenerator.forwardDate(5));
        $(".button__text").click();
        $(withText("У вас уже запланирована встреча на другую дату. Перепланировать?")).waitUntil(visible, 15000);
        $("[data-test-id=replan-notification] button.button").click();
        $(withText("Успешно")).waitUntil(visible, 15000);
    }
}