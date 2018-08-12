package com.zlotko.loan;

import com.zlotko.BaseTest;
import com.zlotko.enums.LoanCategory;
import com.zlotko.enums.LoanPeriod;
import com.zlotko.models.UserDto;
import com.zlotko.pages.HomePage;
import com.zlotko.pages.LoanQuestionnairePage;
import org.junit.jupiter.api.Test;

import static com.zlotko.core.assertions.FluentAssertion.fluentAssert;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class LoanSelectionTest extends BaseTest {

    private HomePage homePage = new HomePage();
    private LoanQuestionnairePage loanQuestionnairePage = new LoanQuestionnairePage();

    @Test
    public void shouldNotLogInUnderInvalidCredentials() {
        UserDto invalidUser = new UserDto();
        invalidUser.setEmail("test");
        invalidUser.setPassword("test");

        homePage.open();
        homePage.logIn(invalidUser);

        fluentAssert(() -> assertThat("Invalid credentials error message not visible",
                homePage.getLoginErrorMessage().isDisplayed(),
                is(true)));
    }

    @Test
    public void shouldSearchForLoan() {
        homePage.open();
        homePage.switchToLayoutFrame();
        homePage.selectLoanCategory(LoanCategory.ACCOMODATION);
        homePage.selectNetLoanAmount(2750);
        homePage.selectLoanPeriod(LoanPeriod.TWENTY_FOUR_MONTHS);
        homePage.clickCompareCreditsButton();

        fluentAssert(() -> assertThat("Loan Questionnaire page is not loaded",
                loanQuestionnairePage.isOnPage(),
                is(true)));
    }
}
