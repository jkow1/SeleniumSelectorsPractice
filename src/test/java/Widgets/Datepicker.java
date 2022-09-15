package Widgets;

import Base.TestBase;
import Logback.BasicLogger;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Datepicker extends TestBase {

    private Logger logger = LoggerFactory.getLogger(Datepicker.class);

    @BeforeEach
    public void getWebsite() {
        driver.get("https://seleniumui.moderntester.pl/datepicker.php");
    }

    @Test
    @Tag("Regression")
    public void datepickerCurrentDaySuccessfulTest() {

        String expectedDate;
        String actualDate;
        String currentDate = getCurrentDate();

        expectedDate = currentDate;
        setDate(expectedDate);
        actualDate = driver.findElement(By.cssSelector("#datepicker")).getAttribute("value");
        BasicLogger.logAssertedValues(logger, expectedDate, actualDate);
        Assertions.assertThat(actualDate).isEqualTo(expectedDate);

        expectedDate = getFirstDayOfNextMonthDate(currentDate);
        setDate(expectedDate);
        actualDate = driver.findElement(By.cssSelector("#datepicker")).getAttribute("value");
        BasicLogger.logAssertedValues(logger, expectedDate, actualDate);
        Assertions.assertThat(actualDate).isEqualTo(expectedDate);

        expectedDate = getFirstDayOfNextYear(currentDate);
        setDate(expectedDate);
        actualDate = driver.findElement(By.cssSelector("#datepicker")).getAttribute("value");
        BasicLogger.logAssertedValues(logger, expectedDate, actualDate);
        Assertions.assertThat(actualDate).isEqualTo(expectedDate);

        expectedDate = getFirstDayOfNextYear(currentDate);
        setDate(expectedDate);
        actualDate = driver.findElement(By.cssSelector("#datepicker")).getAttribute("value");
        BasicLogger.logAssertedValues(logger, expectedDate, actualDate);
        Assertions.assertThat(actualDate).isEqualTo(expectedDate);

        expectedDate = getRandomDayDate(currentDate);
        setDate(expectedDate);
        actualDate = driver.findElement(By.cssSelector("#datepicker")).getAttribute("value");
        BasicLogger.logAssertedValues(logger, expectedDate, actualDate);
        Assertions.assertThat(actualDate).isEqualTo(expectedDate);

        expectedDate = getRandomDatePrevYear(currentDate);
        setDate(expectedDate);
        actualDate = driver.findElement(By.cssSelector("#datepicker")).getAttribute("value");
        BasicLogger.logAssertedValues(logger, expectedDate, actualDate);
        System.out.println();
        Assertions.assertThat(actualDate).isEqualTo(expectedDate);
    }

    public void setDate(String date) {
        String day = getDayFromDate(date);
        String month = getMonthFromDate(date);
        String year = getYearFromDate(date);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement datePickerInput = driver.findElement(By.cssSelector("#datepicker"));
        datePickerInput.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ui-datepicker-next")));
        WebElement datePicker = driver.findElement(By.cssSelector("#ui-datepicker-div"));
        setExpectedMonth(month, year, datePicker);
        List<WebElement> dayList = datePicker.findElements(By.cssSelector("[data-month='" + (Integer.parseInt(month) - 1) + "'] a"));
        dayList.get(Integer.parseInt(day) - 1).click();

    }

    private void setExpectedMonth(String month, String year, WebElement datePicker) {
        if (Integer.parseInt(getYearFromDatepicker(datePicker)) < Integer.parseInt(year)) {
            moveNextToExpectedMonth(month, year, datePicker);
        } else if (Integer.parseInt(getYearFromDatepicker(datePicker)) > Integer.parseInt(year)) {
            movePrevToExpectedMonth(month, year, datePicker);
        } else if (Integer.parseInt(convertMonthNameToNumber(getMonthFromDatepicker(datePicker))) < Integer.parseInt(month)) {
            moveNextToExpectedMonth(month, year, datePicker);
        } else if (Integer.parseInt(convertMonthNameToNumber(getMonthFromDatepicker(datePicker))) > Integer.parseInt(month)) {
            movePrevToExpectedMonth(month, year, datePicker);
        }
    }

    private void moveNextToExpectedMonth(String month, String year, WebElement datePicker) {
        while (!getYearFromDatepicker(datePicker).equals(year) || !(Integer.parseInt(convertMonthNameToNumber(getMonthFromDatepicker(datePicker))) == Integer.parseInt(month))) {
            setNextMonth(datePicker);
        }
    }

    private void movePrevToExpectedMonth(String month, String year, WebElement datePicker) {
        while (!getYearFromDatepicker(datePicker).equals(year) || !(Integer.parseInt(convertMonthNameToNumber(getMonthFromDatepicker(datePicker))) == Integer.parseInt(month))) {
            setPreviousMonth(datePicker);
        }
    }

    private String getFirstDayOfNextMonthDate(String currentDate) {
        String nextMonth = String.valueOf(Integer.parseInt(getMonthFromDate(currentDate)) + 1);
        String year = getYearFromDate(currentDate);
        return nextMonth + "/01/" + year;
    }

    private String getFirstDayOfNextYear(String currentDate) {
        String nextYear = String.valueOf(Integer.parseInt(getYearFromDate(currentDate)) + 1);
        return "01/01/" + nextYear;
    }

    private String getRandomDayDate(String currentDate) {
        String year = getYearFromDate(currentDate);
        Month month = Month.of(Integer.parseInt(getMonthFromDate(currentDate)) - 1);
        YearMonth yearMonth = YearMonth.of(Integer.parseInt(year), month);
        int day = new Random(System.currentTimeMillis()).nextInt(1,yearMonth.lengthOfMonth());
        if( day < 10){
            return convertMonthNameToNumber(month.name()) + "/0" + day + "/" + (Integer.parseInt(year) - 1);
        }
        return convertMonthNameToNumber(month.name()) + "/" + day + "/" + (Integer.parseInt(year));
    }

    private String getRandomDatePrevYear(String currentDate) {
        String year = getYearFromDate(currentDate);
        Month month = Month.of((new Random(System.currentTimeMillis())).nextInt(0, 11));
        YearMonth yearMonth = YearMonth.of(Integer.parseInt(year) - 1, month);
        int day = new Random(System.currentTimeMillis()).nextInt(1,yearMonth.lengthOfMonth());
        if( day < 10){
            return convertMonthNameToNumber(month.name()) + "/0" + day + "/" + (Integer.parseInt(year) - 1);
        }
        return convertMonthNameToNumber(month.name()) + "/" + day + "/" + (Integer.parseInt(year) - 1);
    }

    private String getCurrentDate() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/YYYY");
        return dateTimeFormatter.format(LocalDateTime.now());
    }

    private String getDayFromDate(String dateToConvert) {
        String split[] = dateToConvert.split("/");
        return split[1];
    }

    private String getMonthFromDate(String dateToConvert) {
        String split[] = dateToConvert.split("/");
        return split[0];
    }

    private String getYearFromDate(String dateToConvert) {
        String split[] = dateToConvert.split("/");
        return split[2];
    }

    private void setNextMonth(WebElement webElement) {
        BasicLogger.logMethod(logger, "setNextMonth");
        webElement.findElement(By.cssSelector(".ui-datepicker-next")).click();
    }

    private void setPreviousMonth(WebElement webElement) {
        BasicLogger.logMethod(logger, "setPreviousMonth");
        webElement.findElement(By.cssSelector(".ui-datepicker-prev")).click();
    }

    private String getYearFromDatepicker(WebElement webElement) {
        return webElement.findElement(By.cssSelector(".ui-datepicker-year")).getText();
    }

    private String getMonthFromDatepicker(WebElement webElement) {
        return webElement.findElement(By.cssSelector(".ui-datepicker-month")).getText();
    }

    private String convertMonthNameToNumber(String monthName) {
        Date date = null;
        try {
            date = new SimpleDateFormat("MMMM", Locale.ENGLISH).parse(monthName);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return new SimpleDateFormat("MM").format(calendar.getTime());
    }

}
