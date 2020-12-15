package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GmailLoginPage {

  private static final By EMAIL_INPUT_LOCATOR = By.cssSelector("input[type='email']");
  private static final By PASSWORD_INPUT_LOCATOR = By.cssSelector("input[type='password']");
  private static final By EMAIL_NEXT_BUTTON_LOCATOR = By.cssSelector("div[id='identifierNext']");
  private static final By PASSWORD_NEXT_BUTTON_LOCATOR = By.cssSelector("div[id='passwordNext']");
  private final WebDriver webDriver;
  private WebDriverWait driverWait;

  public GmailLoginPage(WebDriver webDriver) {
    this.webDriver = webDriver;
    driverWait = new WebDriverWait(webDriver, 100);
  }

  @Step("Set email in field")
  public GmailLoginPage setEmail(String email) {
    webDriver.findElement(EMAIL_INPUT_LOCATOR).sendKeys(email);
    return this;
  }

  @Step("Set sassword in field")
  public GmailLoginPage setPassword(String pass) {
    driverWait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_INPUT_LOCATOR)).sendKeys(pass);
    return this;
  }

  @Step("Click NEXT after seting email")
  public GmailLoginPage clickNextAfterEmail() {
    webDriver.findElement(EMAIL_NEXT_BUTTON_LOCATOR).click();
    return this;
  }

  @Step("Click NEXT after seting password")
  public GmailLoginPage clickNextAfterPassword() {
    webDriver.findElement(PASSWORD_NEXT_BUTTON_LOCATOR).click();
    driverWait.until(ExpectedConditions.urlToBe("https://mail.google.com/mail/u/0/#inbox"));
    return this;
  }
}
