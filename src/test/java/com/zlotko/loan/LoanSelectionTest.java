package com.zlotko.loan;

import com.zlotko.BaseTest;
import com.zlotko.enums.LoanCategory;
import com.zlotko.enums.LoanPeriod;
import com.zlotko.models.UserDto;
import com.zlotko.pages.HomePage;
import com.zlotko.pages.LoanQuestionnairePage;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.zlotko.core.assertions.FluentAssertion.fluentAssert;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class LoanSelectionTest extends BaseTest {

    private HomePage homePage = new HomePage();
    private LoanQuestionnairePage loanQuestionnairePage = new LoanQuestionnairePage();

    private static Stream<Arguments> invalidCredentials() {
        return Stream.of(Arguments.of(new UserDto("test1", "test2"))
        );
    }

    private static Stream<Arguments> baseLoanParams() {
        return Stream.of(
                Arguments.of(LoanCategory.ACCOMODATION, 2750, LoanPeriod.TWENTY_FOUR_MONTHS)
        );
    }

    @Story("User can not log in under invalid credentials")
    @Description("Login error message is displayed during login attempt under invalid credentials")
    @ParameterizedTest
    @MethodSource("invalidCredentials")
    public void shouldNotLogInUnderInvalidCredentials(UserDto invalidUser) {
        homePage.open();
        homePage.logIn(invalidUser);

        fluentAssert(() -> assertThat("Invalid credentials error message not visible",
                homePage.isLoginErrorMessageDisplayed(),
                is(true)));
    }

    @Story("User can search for loan")
    @Description("Loan Questionnaire page is opened for comparing credits")
    @ParameterizedTest
    @MethodSource("baseLoanParams")
    public void shouldSearchForLoan(LoanCategory loanCategory, int netLoanAmount, LoanPeriod loanPeriod) {
        homePage.open();
        homePage.switchToLayoutFrame();
        homePage.selectLoanCategory(loanCategory);
        homePage.selectNetLoanAmount(netLoanAmount);
        homePage.selectLoanPeriod(loanPeriod);
        homePage.clickCompareCreditsButton();

        fluentAssert(() -> assertThat("Loan Questionnaire page is not loaded",
                loanQuestionnairePage.isOnPage(),
                is(true)));
    }
}
