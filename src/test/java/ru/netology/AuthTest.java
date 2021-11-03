package ru.netology;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class AuthTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldLoginAndPasswordCorrectStatusActive() {
        val data = GeneratorUser.dataUserActive();
        $("[data-test-id=login] [name=login]").setValue(data.getLogin());
        $("[data-test-id=password] [name=password]").setValue(data.getPassword());
        $("[data-test-id=active-login]").click();
        $(withText("Личный кабинет")).shouldBe(visible, Duration.ofSeconds(5));
    }

    @Test
    void shouldLoginAndPasswordCorrectStatusBlocked() {
        val data = GeneratorUser.dataUserBlocked();
        $("[data-test-id=login] [name=login]").setValue(data.getLogin());
        $("[data-test-id=password] [name=password]").setValue(data.getPassword());
        $("[data-test-id=active-login]").click();
        $(withText("Пользователь заблокирован")).shouldBe(visible, Duration.ofSeconds(5));
    }

    @Test
    void shouldIncorrectLogin() {
        val data = GeneratorUser.dataWrongLogin();
        $("[data-test-id=login] [name=login]").setValue(data.getLogin());
        $("[data-test-id=password] [name=password]").setValue(data.getPassword());
        $("[data-test-id=active-login]").click();
        $(withText("Неверно указан логин или пароль")).shouldBe(visible, Duration.ofSeconds(5));
    }

    @Test
    void shouldIncorrectPassword() {
        val data = GeneratorUser.dataWrongPassword();
        $("[data-test-id=login] [name=login]").setValue(data.getLogin());
        $("[data-test-id=password] [name=password]").setValue(data.getPassword());
        $("[data-test-id=active-login]").click();
        $(withText("Неверно указан логин или пароль")).shouldBe(visible, Duration.ofSeconds(5));
    }
}