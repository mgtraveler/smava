package com.zlotko.pages;

import com.zlotko.core.annotations.DefaultUrl;
import com.zlotko.enums.LoanCategory;
import com.zlotko.enums.LoanPeriod;
import com.zlotko.models.UserDto;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.zlotko.utils.WebDriverUtil.*;

@Getter
@DefaultUrl("/v2?version=landingpage")
public class HomePage extends AnyPage {

    @FindBy(css = "[class*='login-button']")
    private WebElement loginLink;

    @FindBy(css = "input[name='email']")
    private WebElement emailInput;

    @FindBy(css = "input[name='password']")
    private WebElement passwordInput;

    @FindBy(css = "button[class*='button LoginForm']")
    private WebElement loginButton;

    @FindBy(css = "[class*='error-message']")
    private WebElement loginErrorMessage;

    @FindBy(css = "select[id*='loan.selection.category']")
    private WebElement loanCategoryDropdown;

    @FindBy(css = "select[id*='loan.selection.amount']")
    private WebElement netLoanAmountDropdown;

    @FindBy(xpath = ".//*[@id='react-select-2--value']//*[@class='Select-value']")
    private WebElement loanPeriodSelectComponent;

    @FindBy(css = "[id*='cta_btn']")
    private WebElement compareCreditsButton;

    @FindBy(css = "iframe[class='HomepageLayout__iframe']")
    private WebElement layoutFrame;

    @Step("Log in under email:{userDto.email} and password:{userDto.password}")
    public void logIn(UserDto userDto) {
        click(loginLink);
        type(emailInput, userDto.getEmail());
        type(passwordInput, userDto.getPassword());
        click(loginButton);
    }

    @Step
    public void switchToLayoutFrame() {
        switchToFrame(layoutFrame);
    }

    @Step("select loan category: {loanCategory.optionText}")
    public void selectLoanCategory(LoanCategory loanCategory) {
        selectOptionByValue(loanCategoryDropdown, loanCategory.optionValue());
    }

    @Step
    public void selectNetLoanAmount(int amount) {
        selectOptionByValue(netLoanAmountDropdown, amount);
    }

    @Step("select loan period: {loanPeriod.optionText}")
    public void selectLoanPeriod(LoanPeriod loanPeriod) {
        selectComponentByIndex(loanPeriodSelectComponent, loanPeriod.index());
    }

    @Step
    public void clickCompareCreditsButton() {
        click(compareCreditsButton);
    }
}
