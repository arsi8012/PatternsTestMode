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
    static Faker faker = new Faker(new Locale("ru"));

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

    public static RegistrationData dataUser(String status) {
        RegistrationData regData = new RegistrationData(
                faker.name().firstName(),
                faker.internet().password(),
                status
        );
        setUpAll(regData);
        return regData;
    }

    public static String dataWrongLogin() {
        String loginFake = faker.name().fullName();
        return loginFake;
    }

    public static String dataWrongPassword() {
        String pasFake = faker.internet().password();
        return pasFake;
    }
}