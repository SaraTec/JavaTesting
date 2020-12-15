package service;

import lombok.SneakyThrows;
import model.Alert;
import org.openqa.selenium.WebDriver;
import page.GmailMainPage;
import page.GmailSideMenuPage;

public class SendMessageService {

  private GmailMainPage gmailMainPageObject;
  private GmailSideMenuPage gmailSideMenuPageObject;

  public void setWebDriver(WebDriver webDriver) {
    this.gmailMainPageObject = new GmailMainPage(webDriver);
    this.gmailSideMenuPageObject = new GmailSideMenuPage(webDriver);
  }

  @SneakyThrows
  public void sendMessage(String to, String subject, String content) {

    gmailSideMenuPageObject.clickComposeBtn();
    gmailMainPageObject.setMailReceiver(to);
    Thread.sleep(2000);
    gmailMainPageObject.setMailSubject(subject);
    gmailMainPageObject.setMailContent(content);

    Thread.sleep(2000);

    gmailMainPageObject.clickSendBtn();
  }

  @SneakyThrows
  public void sendDraftedMessage(int messageIndex) {

    gmailSideMenuPageObject.goToDraftsMessages();

    gmailMainPageObject.clickOnMessage(messageIndex);

    Thread.sleep(2000);

    gmailMainPageObject.clickSendBtn();
  }

  public Alert sendMessageWithInvalidEmail(String subject, String content) {
    sendMessage("wrongemail.com", subject, content);

    String alertDialogTitle = gmailMainPageObject.getAlertDialogTitle();
    String alertDialogContent = gmailMainPageObject.getAlertDialogContent();

    return new Alert(alertDialogTitle, alertDialogContent);
  }

  @SneakyThrows
  public void sendMessageAfterAlert(String correctEmail){
    gmailMainPageObject.clickOKonAlert();

    Thread.sleep(2000);

    gmailMainPageObject.resetMailReceiver(correctEmail);

    Thread.sleep(2000);

    gmailMainPageObject.clickSendBtn();
  }

  @SneakyThrows
  public void saveMessageAsDraft(String to, String subject, String content) {

    gmailSideMenuPageObject.clickComposeBtn();
    gmailMainPageObject.setMailReceiver(to);
    gmailMainPageObject.setMailSubject(subject);
    gmailMainPageObject.setMailContent(content);

    Thread.sleep(2000);

    gmailMainPageObject.closeMessageSendWindow();
  }
}
