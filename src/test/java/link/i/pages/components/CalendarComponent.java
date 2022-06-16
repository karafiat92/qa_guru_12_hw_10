package link.i.pages.components;

import com.codeborne.selenide.SelenideElement;
import link.i.pages.RegistrationFormPage;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {
    public void setDate(Integer day, String month, String year){
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        if (day < 10) {
            $(".react-datepicker__day--00" + day + ":not(.react-datepicker__day--outside-month)").click();
        } else {
            $(".react-datepicker__day--0" + day + ":not(.react-datepicker__day--outside-month)").click();
        }

    }
}
