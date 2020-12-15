import static util.Constants.ALERT_CONTENT;
import static util.Constants.ALERT_TITLE;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import lombok.SneakyThrows;
import model.Alert;
import model.Message;
import model.MessagesScope;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.testng.Assert;
import org.testng.TestException;
import org.testng.annotations.*;
import service.LoginService;
import service.MessageInboxService;
import service.SendMessageService;
import util.Constants;
import util.PropertyManager;

@Listeners ({Allurelistener.class})
public class TestGmail extends BaseClass {

  private static PropertyManager propertyManager;
  private static ChromeDriverService service;
  public WebDriver driver;
  private LoginService loginService;
  private MessageInboxService messageInboxService;
  private SendMessageService sendMessageService;

  @BeforeSuite
  public void setup() {

    propertyManager = PropertyManager.getInstance();
    loginService = new LoginService();
    messageInboxService = new MessageInboxService();
    sendMessageService = new SendMessageService();
  }

  @BeforeTest
  @SneakyThrows
  public void doBeforeTest() {
    service = new ChromeDriverService.Builder()
        .usingDriverExecutable(new File("chromedriver.exe"))
        .usingAnyFreePort()
        .build();

    service.start();
  }

  @BeforeMethod
  public void before(){
    /*webDriver = new ChromeDriver();
    webDriver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
    webDriver.manage().window().maximize();*/
    BaseClass bs = new BaseClass();
    bs.initialize_driver();
    driver =bs.getDriver();
    loginService.setWebDriver(driver);
    messageInboxService.setWebDriver(driver);
    sendMessageService.setWebDriver(driver);
  }

  @Test(description = "TASK3")
  @Description(Constants.TEST3_DESC)
  @Epic("Laba 5")
  @Story("TASK3")
  public void Task1() throws InterruptedException {

    loginService.loginToGoogleAccount(propertyManager.getEmail(), propertyManager.getPassword());
    Assert.assertEquals(driver.getCurrentUrl(), "https://mail.google.com/mail/u/0/#inbox");

    Message messageInfoFromInbox1 = messageInboxService.getMessageInfo(1, MessagesScope.INBOX);
    Message messageInfoFromInbox2 = messageInboxService.getMessageInfo(2, MessagesScope.INBOX);
    Message messageInfoFromInbox3 = messageInboxService.getMessageInfo(3, MessagesScope.INBOX);
    List<Integer> list = Arrays.asList(1, 2, 3);
    messageInboxService.markMessagesAsImportant(list, MessagesScope.INBOX);



    Message messageInfoFromImportant1 = messageInboxService.getMessageInfo(1, MessagesScope.IMPORTANT);
    Message messageInfoFromImportant2 = messageInboxService.getMessageInfo(2, MessagesScope.IMPORTANT);
    Message messageInfoFromImportant3 = messageInboxService.getMessageInfo(3, MessagesScope.IMPORTANT);

    Assert.assertEquals(messageInfoFromInbox1, messageInfoFromImportant1);
    Assert.assertEquals(messageInfoFromInbox2, messageInfoFromImportant2);
    Assert.assertEquals(messageInfoFromInbox3, messageInfoFromImportant3);

    int sentMessagesCountBeforeDelete = messageInboxService.getMessagesCount(MessagesScope.IMPORTANT);
    messageInboxService.removeMessageList(list, MessagesScope.IMPORTANT);

    Thread.sleep(1500);
    System.out.println("ФУУх");
    int sentMessagesCountAfterDelete = messageInboxService.getMessagesCount(MessagesScope.IMPORTANT);

    Assert.assertEquals(sentMessagesCountBeforeDelete - sentMessagesCountAfterDelete, sentMessagesCountBeforeDelete == 3 ? 2 : 3);
  }

  @Test(description = "TASK1")
  @Description(Constants.TEST1_DESC)
  @Epic("Laba 5")
  @Story("TASK1")
  public void Task3() {
    this.loginService.loginToGoogleAccount(propertyManager.getEmail(), propertyManager.getPassword());
    Assert.assertEquals(this.driver.getCurrentUrl(), "https://mail.google.com/mail/u/0/#inbox");
    this.sendMessageService.sendMessage(propertyManager.getTo1(), propertyManager.getSubject1(), propertyManager.getContent1());
    int sentMessagesCountBeforeDelete = this.messageInboxService.getMessagesCount(MessagesScope.SENT);
    System.out.println(sentMessagesCountBeforeDelete);
    this.messageInboxService.removeMessage(1, MessagesScope.SENT);
    int sentMessagesCountAfterDelete = this.messageInboxService.getMessagesCount(MessagesScope.SENT);
    Assert.assertEquals(sentMessagesCountBeforeDelete - sentMessagesCountAfterDelete, sentMessagesCountBeforeDelete == 1 ? 0 : 1);
  }

  @Test(description = "TASK2")
  @Description(Constants.TEST2_DESC)
  @Epic("Laba 5")
  @Story("TASK2")
  public void Task2() {

    loginService.loginToGoogleAccount(propertyManager.getEmail(), propertyManager.getPassword());
    Assert.assertEquals(driver.getCurrentUrl(), "https://mail.google.com/mail/u/0/#inbox");

    sendMessageService.saveMessageAsDraft(propertyManager.getTo2(), propertyManager.getSubject2(), propertyManager.getContent2());

    Message messageInfo = messageInboxService.getDraftMessageInfo(1);

    Assert.assertEquals(messageInfo.getTo(), propertyManager.getTo2());
    Assert.assertEquals(messageInfo.getSubject(), propertyManager.getSubject2());
    Assert.assertEquals(messageInfo.getContent(), propertyManager.getContent2());

    sendMessageService.sendDraftedMessage(1);
  }

  @Test(description = "TASK4")
  @Description(Constants.TEST4_DESC)
  @Epic("Laba 5")
  @Story("TASK4")
  public void Task4() {
    this.loginService.loginToGoogleAccount(propertyManager.getEmail(), propertyManager.getPassword());
    Assert.assertEquals(this.driver.getCurrentUrl(), "https://mail.google.com/mail/u/0/#inbox");
    Alert alert = this.sendMessageService.sendMessageWithInvalidEmail(propertyManager.getSubject4(), propertyManager.getContent4());
    Assert.assertEquals(alert.getContent(), "The address \"wrongemail.com\" in the \"To\" field was not recognized. Please make sure that all addresses are properly formed.");
    Assert.assertEquals(alert.getTitle(), "Error");
    this.sendMessageService.sendMessageAfterAlert(propertyManager.getTo4());
    Message messageInfo = this.messageInboxService.getMessageInfo(1, MessagesScope.SENT);
    Assert.assertEquals(messageInfo.getTo(), propertyManager.getTo4());
    Assert.assertEquals(messageInfo.getSubject(), propertyManager.getSubject4());
    Assert.assertEquals(messageInfo.getContent(), propertyManager.getContent4());
  }

  @AfterMethod
  public void doAfterTest() {

    driver.close();
    driver.quit();
  }

  @AfterSuite
  public void doAfterSuite(){
    driver.quit();
    service.stop();
  }
}
