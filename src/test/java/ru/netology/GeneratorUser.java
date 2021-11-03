package ru.netology;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class GeneratorUser {
    static Faker faker = new Faker(new Locale("en"));

    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    @BeforeAll
    static void setUpAll(RegistrationData regData) {
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(regData) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(200); // код 200 OK
    }

    public static RegistrationData dataUserActive() {
        RegistrationData regData = new RegistrationData(
                faker.name().firstName(),
                faker.internet().password(),
                DataStatus.active
        );
        setUpAll(regData);
        return regData;
    }

    public static RegistrationData dataUserBlocked() {
        RegistrationData regData = new RegistrationData(
                faker.name().firstName(),
                faker.internet().password(),
                DataStatus.blocked
        );
        setUpAll(regData);
        return regData;
    }

    public static RegistrationData dataWrongLogin() {
        RegistrationData regData = new RegistrationData(
                faker.name().fullName(),
                faker.internet().password(),
                DataStatus.active
        );
        setUpAll(regData);
        return regData;
    }

    public static RegistrationData dataWrongPassword() {
        RegistrationData regData = new RegistrationData(
                faker.name().fullName(),
                faker.internet().password(),
                DataStatus.active
        );
        setUpAll(regData);
        return regData;
    }
}