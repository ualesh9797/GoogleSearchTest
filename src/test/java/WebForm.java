import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WebForm extends PageObject {

    private final String SEARCH_TEXT_WITH_RESULTS = "Hello";

    private final String SEARCH_TEXT_WITHOUT_RESULTS = "SDJFKJDFKBKJDFHKJVKFDJKVHFDKJHBGJKFHDKJGHKFDHG";

    @FindBy(css = "input")
    private WebElement input;

    @FindBy(id = "result-stats")
    private WebElement resultStats;

    @FindBy(css = "div[aria-label=\"Очистить\"]")
    private WebElement clearButton;


    public WebForm(WebDriver driver) {
        super(driver);
    }

    public void enterSearchTextWithResults() {
        this.input.sendKeys(SEARCH_TEXT_WITH_RESULTS);
    }

    public void enterSearchTextWithoutResults() {
        this.input.sendKeys(SEARCH_TEXT_WITHOUT_RESULTS);
    }


    public void verifySearchSuccess() {
        this.resultStats.isDisplayed();
    }

    public boolean verifySearchFail() {
        try {
            this.resultStats.isDisplayed();
            return false;
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    public boolean verifyInputEmpty() {
        return this.input.getText().isEmpty();
    }

    public void pressEnterKey() {
        this.input.sendKeys(Keys.RETURN);
    }

    public void clickClearButton() {
        this.clearButton.click();
    }
}