package page;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GmailSideMenuPage {

  private static final By COMPOSE_BUTTON_LOCATOR = By.xpath("//div[contains(text(),'Compose')]");
  private static final By DRAFTS_BUTTON_LOCATOR = By.cssSelector("div[data-tooltip='Drafts']");
  private static final By INBOX_BUTTON_LOCATOR = By.cssSelector("div[data-tooltip='Inbox']");
  private static final By SENT_BUTTON_LOCATOR = By.cssSelector("div[data-tooltip='Sent']");
  private static final By MORE_BUTTON_LOCATOR = By.xpath("//span[contains(text(),'More')]");
  private static final By IMPORTANT_BUTTON_LOCATOR = By.cssSelector("div[data-tooltip='Important']");
  private static final By MAIN_NAV = By.cssSelector("div[id=':4']");

  private final WebDriver webDriver;
  private WebDriverWait driverWait;

  public GmailSideMenuPage(WebDriver webDriver) {
    this.webDriver = webDriver;
    driverWait = new WebDriverWait(webDriver, 100);
  }

  @Step("Click Compose button")
  public GmailSideMenuPage clickComposeBtn() {
    webDriver.findElement(COMPOSE_BUTTON_LOCATOR).click();
    return this;
  }

  @Step("Get Sent Messages")
  @SneakyThrows
  public GmailSideMenuPage goToSentMessages() {
    Thread.sleep(2000);

    driverWait.until(visibilityOfElementLocated(SENT_BUTTON_LOCATOR)).click();
    //webDriver.findElement(MAIN_NAV).click();
    driverWait.until(ExpectedConditions.urlToBe("https://mail.google.com/mail/u/0/#sent"));


    return this;
  }

  @Step("Go to Draft Messages")
  public GmailSideMenuPage goToDraftsMessages() {
    driverWait.until(visibilityOfElementLocated(DRAFTS_BUTTON_LOCATOR)).click();

    driverWait.until(ExpectedConditions.urlToBe("https://mail.google.com/mail/u/0/#drafts"));
    return this;
  }

  @Step("Go to Inbox Messages")
  public GmailSideMenuPage goToInboxMessages() {
    webDriver.findElement(INBOX_BUTTON_LOCATOR).click();

    driverWait.until(ExpectedConditions.urlToBe("https://mail.google.com/mail/u/0/#inbox"));
    return this;
  }

  @Step("Go to Important Messages")
  public GmailSideMenuPage goToImportantMessages() {

    try {

      webDriver.findElement(MORE_BUTTON_LOCATOR).click();
    } catch (Exception ex) {

      ex.printStackTrace();
    }

    driverWait.until(visibilityOfElementLocated(IMPORTANT_BUTTON_LOCATOR)).click();

    driverWait.until(ExpectedConditions.urlToBe("https://mail.google.com/mail/u/0/#imp"));
    return this;
  }
}
