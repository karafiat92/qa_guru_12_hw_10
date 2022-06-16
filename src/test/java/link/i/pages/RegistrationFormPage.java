package link.i.pages;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import link.i.pages.components.CalendarComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationFormPage {
    CalendarComponent calendar = new CalendarComponent();
    // locators
    SelenideElement pageTitle = $(".practice-form-wrapper"),
            firstName = $("#firstName"),
            lastName = $("#lastName"),
            email = $("#userEmail"),
            number = $("#userNumber"),
            address = $("#currentAddress"),
            photoInput = $("#uploadPicture"),
            hobby = $("#hobbiesWrapper"),
            gender = $("#genterWrapper"),
            birthDayInput = $("#dateOfBirthInput"),
            subjectInput = $("#subjectsInput"),
            state = $("#state"),
            city = $("#city"),
            submitButton = $("#submit"),
            table = $("tbody"),
            windowWithData = $(".modal-content"),
            closeButton = $("#closeLargeModal");
    String citySelection = "react-select-4-option-",
            subjectOption = "#react-select-2-option-",
            hobbyOption = "hobbies-checkbox-";
    // actions
    public RegistrationFormPage openPage() {
        open("/automation-practice-form");
        pageTitle.shouldHave(text("Student Registration Form"));
        return this;
    }

    public RegistrationFormPage setFirstname(String value) {
        firstName.setValue(value).shouldHave(value(value));
        return this;
    }

    public RegistrationFormPage setLastname(String value) {
        lastName.setValue(value).shouldHave(value(value));
        return this;
    }

    public RegistrationFormPage setEmail(String value) {
        email.setValue(value).shouldHave(value(value));
        return this;
    }

    public String getRandomGender() {
        int min = 0;
        int max = 2;
        int monthNumber = (min + (int) (Math.random() * (max - min)));
        switch (monthNumber) {
            case 0:
                return "Male";
            case 1:
                return "Female";
            case 2:
                return "Other";
        }
        return "Not a gender";
    }

    public RegistrationFormPage setGender(String value) {
        gender.find(byText(value)).click();
        gender.find(byValue(value)).shouldBe(checked);
        return this;
    }

    public RegistrationFormPage setPhoneNumber(String value) {
        number.setValue(value).shouldHave(value(value.substring(0, 10)));
        return this;
    }

    public String getRandomMonth() {
        int min = 0;
        int max = 11;
        int monthNumber = (min + (int) (Math.random() * (max - min)));
        switch (monthNumber) {
            case 0:
                return "January";
            case 1:
                return "February";
            case 2:
                return "March";
            case 3:
                return "April";
            case 4:
                return "May";
            case 5:
                return "June";
            case 6:
                return "July";
            case 7:
                return "August";
            case 8:
                return "September";
            case 9:
                return "October";
            case 10:
                return "November";
            case 11:
                return "December";
        }
        return "Not a month";
    }

    public RegistrationFormPage setBirthDate(Integer day, String month, String year) {
        birthDayInput.click();
        calendar.setDate(day, month, year);
        birthDayInput.shouldHave(
                value(day.toString()),
                value(month.substring(0, 3)),
                value(year));
        return this;
    }

    public String getRandomSubject(String value) {
        subjectInput.setValue(value);
        int min = 0;
        int max = 6;
        Integer subjectNumber = (min + (int) (Math.random() * (max - min)));
        String subjectText = $(subjectOption + subjectNumber).getText();
        subjectInput.clear();
        return subjectText;
    }

    public RegistrationFormPage setSubject(String sub) {
        subjectInput.setValue(sub).pressEnter();
        return this;
    }

    public String getRandomHobby() {
        int min = 0;
        int max = 2;
        int monthNumber = (min + (int) (Math.random() * (max - min)));
        switch (monthNumber) {
            case 0:
                return "Sport";
            case 1:
                return "Reading";
            case 2:
                return "Music";
        }
        return "Not a hobby";
    }

    public RegistrationFormPage setHobby(String value) {
        hobby.$$("label").find(text(value)).click();
        if (value.equals("Sport")) {
            $("input[id=" + hobbyOption + "1").shouldBe(checked);
        } else if (value.equals("Reading")) {
            $("input[id=" + hobbyOption + "2").shouldBe(checked);
        } else {
            $("input[id=" + hobbyOption + "3").shouldBe(checked);
        }
        return this;
    }

    public RegistrationFormPage setPhoto(String fileName) {
        photoInput.uploadFromClasspath(fileName);
        photoInput.shouldHave(value(fileName));
        return this;
    }

    public RegistrationFormPage setAddress(String value) {
        address.setValue(value).shouldHave(value(value));
        return this;
    }

    public String getRandomState() {
        int min = 0;
        int max = 3;
        int stateNumber = (min + (int) (Math.random() * (max - min)));
        switch (stateNumber) {
            case 0: {
                return "NCR";
            }
            case 1: {
                return "Uttar Pradesh";
            }
            case 2: {
                return "Haryana";
            }
            case 3: {
                return "Rajasthan";
            }
        }
        return "Unknown state";
    }

    public RegistrationFormPage setState(String stateValue) {
        state.click();
        state.find(byText(stateValue)).click();
        state.shouldHave(text(stateValue));
        return this;
    }

    public String setCity(String stateValue) {
        Faker faker = new Faker();
        city.click();
        Integer numberOfSelect;
        String val;
        if (stateValue.equals("NCR")) {
            numberOfSelect = faker.number().numberBetween(0, 2);
            val = city.find(byId(citySelection + numberOfSelect)).getText();
            city.find(byId(citySelection + numberOfSelect)).click();
        } else if (stateValue.equals("Uttar Pradesh")) {
            numberOfSelect = faker.number().numberBetween(0, 2);
            val = city.find(byId(citySelection + numberOfSelect)).getText();
            city.find(byId(citySelection + numberOfSelect)).click();
        } else if (stateValue.equals("Haryana")) {
            numberOfSelect = faker.number().numberBetween(0, 1);
            val = city.find(byId(citySelection + numberOfSelect)).getText();
            city.find(byId(citySelection + numberOfSelect)).click();
        } else if (stateValue.equals("Rajasthan")) {
            numberOfSelect = faker.number().numberBetween(0, 1);
            val = city.find(byId(citySelection + numberOfSelect)).getText();
            city.find(byId(citySelection + numberOfSelect)).click();
        } else {
            val = "We have a trouble";
        }
        return val;
    }

    public RegistrationFormPage submitForm() {
        submitButton.click();
        return this;
    }

    public RegistrationFormPage closeForm() {
        closeButton.click();
        windowWithData.shouldNot(exist);
        return this;
    }

    // ищем значение в таблице и возвращаем элемент селенида для дальнейшей проверки
    public RegistrationFormPage checkValueInTable(String rowTitle, String value) {
        table.find(byText(rowTitle)).sibling(0).shouldHave(text(value));
        return this;
    }

    public RegistrationFormPage checkWindowExist() {
        windowWithData.should(exist);
        $(".modal-header").shouldHave(text("Thanks for submitting the form"));
        return this;
    }
}
