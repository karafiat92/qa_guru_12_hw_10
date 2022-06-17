package link.i;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
import link.i.pages.RegistrationFormPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

@Tag("linki")
public class RegistrationFormTests extends TestBase {
    RegistrationFormPage regForm = new RegistrationFormPage();
    Faker faker = new Faker();
    String firstname = faker.name().firstName(),
            lastname = faker.name().lastName(),
            userEmail = faker.internet().emailAddress(),
            pictureName = "7777.png",
            phoneNumber = faker.phoneNumber().phoneNumber()
                    .replace('.', '1')
                    .replace('-', '2')
                    .replace('(', '3')
                    .replace(')', '4')
                    .replace(' ', '5'),
            currentAddress = faker.address().fullAddress(),
            gender = regForm.getRandomGender(),
            hobby = regForm.getRandomHobby(),
            month = regForm.getRandomMonth(),
            state = regForm.getRandomState(),
            cityValue = "",
            subject = "";
    Integer day = faker.number().numberBetween(1, 30),
            year = faker.number().numberBetween(1900, 2022);

    @Test
    @DisplayName("Регистрация с позитивными рандомными данными")
    void positiveValuesRegistrationForm() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открытие формы", () -> {
            regForm.openPage();
        });
        step("Инициализация переменных, для последующей работы с тестом", () -> {
            subject = regForm.getRandomSubject("o");
            regForm.setFirstname(firstname)
                    .setLastname(lastname)
                    .setEmail(userEmail)
                    .setGender(gender)
                    .setPhoneNumber(phoneNumber)
                    .setBirthDate(day, month, year.toString())
                    .setSubject(subject)
                    .setHobby(hobby)
                    .setPhoto(pictureName)
                    .setAddress(currentAddress)
                    .setState(state);
            cityValue = regForm.setCity(state);
        });
        step("Подтверждение введённых данных", () -> {
            regForm.submitForm();
        });
        step("Проверка корректности отображения введённых данных", () -> {
            regForm.checkWindowExist()
                    .checkValueInTable("Student Name", (firstname + " " + lastname))
                    .checkValueInTable("Student Email", userEmail)
                    .checkValueInTable("Gender", gender)
                    .checkValueInTable("Mobile", phoneNumber.substring(0, 10))
                    .checkValueInTable("Date of Birth", (day + " " + month + "," + year))
                    .checkValueInTable("Subjects", subject)
                    .checkValueInTable("Hobbies", hobby)
                    .checkValueInTable("Picture", pictureName)
                    .checkValueInTable("Address", currentAddress)
                    .checkValueInTable("State and City", (state + " " + cityValue));
        });
        step("Закрытие формы", () -> {
            regForm.closeForm();
        });
    }
}
