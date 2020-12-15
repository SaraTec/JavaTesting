package page;

import static java.lang.String.format;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import io.qameta.allure.Step;
import org.openqa.selenium.interactions.Actions;

import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GmailMainPage {

  private static final By FIELD_TO = By.cssSelector("textarea[name='to']");
  private static final By TO_DRAFT_INPUT_LOCATOR = By.cssSelector("form span[data-hovercard-id]");
  private static final By FIELD_SUBJECT = By.cssSelector("input[name='subjectbox']");
  private static final By FIELD_MESSAGE = By.cssSelector("div[aria-label='Message Body']");
  private static final By SEND_BUTTON_LOCATOR = By.cssSelector("div[data-tooltip=\"Send \u202A(Ctrl-Enter)\u202C\"]");
  private static final By CLOSE_MESSAGE_WINDOW_BUTTON_LOCATOR = By.cssSelector("img[data-tooltip='Save & close']");
  private static final By ALERT_TITLE_LOCATOR = By.cssSelector("div[role='alertdialog'] div:nth-child(1)");
  private static final By ALERT_CONTENT_LOCATOR = By.cssSelector("div[role='alertdialog'] div:nth-child(2)");
  private static final By ALERT_OK_BUTTON_LOCATOR = By.cssSelector("div[role='alertdialog'] button[name='ok']");
  private static final By CLICK_TO_EMAIL_LABEL_LOCATOR = By.xpath("//span[contains(text(),'wrongemail.com')]");
  private static final By REMOVE_TO_EMAIL_LOCATOR = By.xpath("//div[contains(text(),'wrongemail.com')]/following-sibling::node()");

  private static final String MESSAGE_ROW_CSS_SELECTOR = "div[role='main'] tr:nth-child(%s)";
  private static final String INBOX_MESSAGE_ROW_CSS_SELECTOR = "table[role='grid'] tr:nth-child(%s)";
  private static final By MESSAGE_ROWS_LOCATOR = By.cssSelector("div[role='main'] tr");
  private static final By MESSAGE_ROW_CHECKBOX_LOCATOR = By.cssSelector("div[role='checkbox']");
  //private static final By REMOVE_MESSAGE_ICON_LOCATOR1 = By.xpath("(//*[normalize-space(text()) and normalize-space(.)='More'])[4]/preceding::div[26]");
  private static final By REMOVE_MESSAGE_ICON_LOCATOR1 = By.cssSelector("div[data-tooltip='Delete']");
  private static final By REMOVE_MESSAGE_ICON_LOCATOR2 = By.cssSelector("div[data-tooltip='Delete']");
  //private static final By REMOVE_MESSAGE_ICON_LOCATOR2 = By.xpath("(//*[normalize-space(text()) and normalize-space(.)='More'])[3]/preceding::div[27]");
  //private static final By MORE_MESSAGE_ACTIONS_BUTTON_LOCATOR = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Hangouts'])[3]/following::span[2]");
  private static final By MORE_MESSAGE_ACTIONS_BUTTON_LOCATOR = By.cssSelector("div[data-tooltip='More']");
  private static final By MARK_AS_IMPORTANT_BUTTON_LOCATOR = By.xpath("//div[contains(text(),'Mark as important')]");

  private final WebDriver webDriver;
  private WebDriverWait driverWait;

  public GmailMainPage(WebDriver webDriver) {
    this.webDriver = webDriver;
    driverWait = new WebDriverWait(webDriver, 100);
  }

  @Step("Set Mail Receiver")
  public GmailMainPage setMailReceiver(String to) {
    System.out.println("а це до");
    System.out.println(to);
    driverWait.until(visibilityOfElementLocated(FIELD_TO)).sendKeys(to);
    System.out.println("ну це після");
    System.out.println(to);
    return this;
  }

  @Step("Reset Mail Receiver")
  public GmailMainPage resetMailReceiver(String to){

    webDriver.findElement(CLICK_TO_EMAIL_LABEL_LOCATOR).click();
    webDriver.findElement(REMOVE_TO_EMAIL_LOCATOR).click();

    webDriver.findElement(FIELD_TO).sendKeys(to);
    return this;
  }

  @Step("Get mail receiver from input")
  public String getMailReceiverFromInput() {

    return webDriver.findElement(TO_DRAFT_INPUT_LOCATOR).getAttribute("email");
  }

  @Step("Set mail subject")
  public GmailMainPage setMailSubject(String subject) {
    webDriver.findElement(FIELD_SUBJECT).sendKeys(subject);
    return this;
  }

  @Step("Get mail subject from input")
  public String getMailSubjectFromInput() {

    return webDriver.findElement(FIELD_SUBJECT).getAttribute("value");
  }

  @Step("Set mail content")
  public GmailMainPage setMailContent(String content) {
    webDriver.findElement(FIELD_MESSAGE).sendKeys(content);
    return this;
  }

  @Step("Get mail content from input")
  public String getMailContentFromInput() {

    return webDriver.findElement(FIELD_MESSAGE).getText();
  }

  @Step("Click Send button")
  public GmailMainPage clickSendBtn() {
    webDriver.findElement(SEND_BUTTON_LOCATOR).click();
    return this;
  }

  @Step("Close Send Message window")
  public GmailMainPage closeMessageSendWindow() {
    webDriver.findElement(CLOSE_MESSAGE_WINDOW_BUTTON_LOCATOR).click();
    return this;
  }

  @Step("Get Alert Dialog Title")
  public String getAlertDialogTitle() {

    return driverWait.until(visibilityOfElementLocated(ALERT_TITLE_LOCATOR)).getText();
  }

  @Step("Get Alert Dialog Content")
  public String getAlertDialogContent() {

    return webDriver.findElement(ALERT_CONTENT_LOCATOR).getText();
  }

  @Step("Click OK on Alert")
  public GmailMainPage clickOKonAlert() {

    webDriver.findElement(ALERT_OK_BUTTON_LOCATOR).click();
    return this;
  }

  @Step("Get message recipient")
  @SneakyThrows
  public String getMessageRecipient(int messageIndex, boolean isInbox) {
    Thread.sleep(2000);

    String rowCssSelector = isInbox ? format(INBOX_MESSAGE_ROW_CSS_SELECTOR, messageIndex) : format(MESSAGE_ROW_CSS_SELECTOR, messageIndex);

    if(!isInbox) {
      return webDriver.findElement(By.cssSelector(rowCssSelector + " td:nth-child(4) div:first-child span:first-child span[email]")).getAttribute("email");
    }
    else{
      return webDriver.findElement(By.cssSelector(rowCssSelector + " td:nth-child(4) div:nth-child(2) span:first-child span[email]")).getAttribute("email");
    }
  }

  @Step("Get message subject")
  @SneakyThrows
  public String getMessageSubject(int messageIndex, boolean isInbox) {
    WebElement messageRowElement = getMessageRowWebElement(messageIndex, isInbox);

    return getMessageContentOrSubject(messageRowElement, false);
  }

  @Step("Get message content")
  @SneakyThrows
  public String getMessageContent(int messageIndex, boolean isInbox) {
    WebElement messageRowElement = getMessageRowWebElement(messageIndex, isInbox);

    return getMessageContentOrSubject(messageRowElement, true);
  }

  @Step("Get message content or subject")
  private String getMessageContentOrSubject(WebElement messageRowElement, boolean isContent) {
    String[] split = messageRowElement.getText().split("\n");

    int index = 0;
    for (int i = 0; i < split.length; i++) {

      if (split[i].equals(" - ")) {

        index = i;
      }
    }

    return split[index + (isContent ? +1 : -1)];
  }

  @Step("Get message row element")
  private WebElement getMessageRowWebElement(int messageIndex, boolean isInbox) throws InterruptedException {
    Thread.sleep(1000);

    WebElement messageRowElement;

    if (isInbox) {

      messageRowElement = webDriver.findElement(By.cssSelector(format(INBOX_MESSAGE_ROW_CSS_SELECTOR, messageIndex)));
    } else {

      messageRowElement = driverWait.until(visibilityOfElementLocated(By.cssSelector(format(MESSAGE_ROW_CSS_SELECTOR, messageIndex))));
    }
    return messageRowElement;
  }

  @Step("Check message")
  @SneakyThrows
  public GmailMainPage checkMessage(int messageIndex, boolean isInbox) {
    String rowCssSelector = isInbox ? format(INBOX_MESSAGE_ROW_CSS_SELECTOR, messageIndex) : format(MESSAGE_ROW_CSS_SELECTOR, messageIndex);

    WebElement messageRowElement = webDriver.findElement(By.cssSelector(format(rowCssSelector, messageIndex)));

    Thread.sleep(2000);

    messageRowElement.findElement(MESSAGE_ROW_CHECKBOX_LOCATOR).click();
    return this;
  }

  @Step("Click on message")
  public GmailMainPage clickOnMessage(int messageIndex) {
    WebElement messageRowElement = webDriver.findElement(By.cssSelector(format(MESSAGE_ROW_CSS_SELECTOR, messageIndex)));

    messageRowElement.click();
    return this;
  }

  @Step("Click on Remove Message Button")
  @SneakyThrows
  public GmailMainPage clickRemoveMessageButton() {
    
    Thread.sleep(2000);
    System.out.println("DELETE");
   // WebElement groupElement =  webDriver.findElement(By.xpath("//*[@id=\":4\"]"));//*[@id=":4"]/div[2]/div[1]/div[1]/div/div/div[2]/div[3]
    //WebElement groupElement =  webDriver.findElement(By.xpath("//*[@id=\":4\"]/div[2]/div[1]/div[1]/div/div/div[2]"));
    WebElement groupElement =  webDriver.findElement(By.xpath("//*[@id=\":4\"]/div[2]/div[1]/div[1]/div/div/div[2]/div[3]"));
    Actions action = new Actions(webDriver);
    Thread.sleep(4000);
    action.moveToElement(groupElement).build().perform();
    Thread.sleep(4000);
    System.out.println("HOVERNULU");
    //webDriver.findElement(By.xpath("//div[@aria-label='Delete']//div[@class='asa']")).click();
    webDriver.findElement(By.xpath("//*[@id=\":4\"]/div[2]/div[1]/div[1]/div/div/div[2]/div[3]/div")).click();
    System.out.println("OVER DELETE");
    Thread.sleep(2000);
    return this;
  }

  @Step("Mark messages as important")
  @SneakyThrows
  public GmailMainPage markMessagesAsImportant() {
    Thread.sleep(2000);
    WebElement groupElement =  webDriver.findElement(By.xpath("//*[@id=\":4\"]"));
    Actions action = new Actions(webDriver);
    action.moveToElement(groupElement).build().perform();
    driverWait.until(visibilityOfElementLocated(MORE_MESSAGE_ACTIONS_BUTTON_LOCATOR)).click();
    //webDriver.findElement(MORE_MESSAGE_ACTIONS_BUTTON_LOCATOR).click();

    Thread.sleep(2000);

    webDriver.findElement(MARK_AS_IMPORTANT_BUTTON_LOCATOR).click();
    return this;
  }

  @Step("Get messages count")
  @SneakyThrows
  public int getMessagesCount() {

    Thread.sleep(2000);

    return driverWait.until(visibilityOfAllElementsLocatedBy(MESSAGE_ROWS_LOCATOR)).size();
  }
}
