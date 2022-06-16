package link.i;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import link.i.pages.RegistrationFormPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RegistrationFormTests {
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
            state = regForm.getRandomState();
    Integer day = faker.number().numberBetween(1, 30),
            year = faker.number().numberBetween(1900, 2022);

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1500x1080";
        Configuration.holdBrowserOpen = false;
    }

    @Test
    @DisplayName("Регистрация с позитивными рандомными данными")
    void positiveValuesRegistrationForm() {
        regForm.openPage();
//        инициализация переменных, для последующей работы с тестом
        String subject = regForm.getRandomSubject("o");
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
        String cityValue = regForm.setCity(state);
        regForm.submitForm();
//        Проверка, что все данные ушли верно
        regForm.checkWindowExist()
                .checkValueInTable("Student Name", (firstname + " " + lastname))
                .checkValueInTable("Student Email", userEmail)
                .checkValueInTable("Gender", gender)
                .checkValueInTable("Mobile", phoneNumber.substring(0,10))
                .checkValueInTable("Date of Birth", (day + " " + month + "," + year))
                .checkValueInTable("Subjects", subject)
                .checkValueInTable("Hobbies", hobby)
                .checkValueInTable("Picture", pictureName)
                .checkValueInTable("Address", currentAddress)
                .checkValueInTable("State and City", (state + " " + cityValue))
                .closeForm();
    }
}
