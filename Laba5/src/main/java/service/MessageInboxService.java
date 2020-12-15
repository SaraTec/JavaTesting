package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.SneakyThrows;
import model.Message;
import model.MessagesScope;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page.GmailMainPage;
import page.GmailSideMenuPage;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class MessageInboxService {
  private WebDriverWait driverWait;
  private GmailMainPage gmailMainPageObject;
  private GmailSideMenuPage gmailSideMenuPageObject;
  private Map<MessagesScope, Runnable> goToScopeMap;

  public void setWebDriver(WebDriver webDriver) {
    this.gmailMainPageObject = new GmailMainPage(webDriver);
    this.gmailSideMenuPageObject = new GmailSideMenuPage(webDriver);
    driverWait = new WebDriverWait(webDriver, 100);
    goToScopeMap = new HashMap<MessagesScope, Runnable>();
    goToScopeMap.put( MessagesScope.INBOX, gmailSideMenuPageObject::goToInboxMessages);
    goToScopeMap.put( MessagesScope.DRAFTS, gmailSideMenuPageObject::goToDraftsMessages);
    goToScopeMap.put( MessagesScope.SENT, gmailSideMenuPageObject::goToSentMessages);
    goToScopeMap.put( MessagesScope.IMPORTANT, gmailSideMenuPageObject::goToImportantMessages);
  }

  public Message getMessageInfo(int index, MessagesScope messagesScope) {

    goToScopeMap.get(messagesScope).run();
    if(!messagesScope.equals(MessagesScope.INBOX)) {
      System.out.println("ГО СУКА");
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    String recipient = gmailMainPageObject.getMessageRecipient(index, messagesScope.equals(MessagesScope.IMPORTANT));
    System.out.println("recipient");
    System.out.println(recipient);
    String subject = gmailMainPageObject.getMessageSubject(index, messagesScope.equals(MessagesScope.INBOX));
    System.out.println("subject");
    System.out.println(subject);
    String content = gmailMainPageObject.getMessageContent(index, messagesScope.equals(MessagesScope.INBOX));
    System.out.println("content");
    System.out.println(content);
    return new Message(recipient, subject, content);
  }

  @SneakyThrows
  public Message getDraftMessageInfo(int index) {

    goToScopeMap.get(MessagesScope.DRAFTS).run();

    gmailMainPageObject.clickOnMessage(index);

    Thread.sleep(2000);

    String recipient = gmailMainPageObject.getMailReceiverFromInput();
    String subject = gmailMainPageObject.getMailSubjectFromInput();
    String content = gmailMainPageObject.getMailContentFromInput();

    gmailMainPageObject.closeMessageSendWindow();

    return new Message(recipient, subject, content);
  }

  @SneakyThrows
  public void removeMessage(int messageIndex, MessagesScope messagesScope) {

    //goToScopeMap.get(messagesScope).run();

    gmailMainPageObject.checkMessage(messageIndex, messagesScope.equals(MessagesScope.INBOX));

    gmailMainPageObject.clickRemoveMessageButton();
  }

  public void removeMessageList(List<Integer> messagesIndexes, MessagesScope messagesScope) {

    goToScopeMap.get(messagesScope).run();

    messagesIndexes.forEach(index -> gmailMainPageObject.checkMessage(index, messagesScope.equals(MessagesScope.INBOX)));

    gmailMainPageObject.clickRemoveMessageButton();
  }

  public void markMessagesAsImportant(List<Integer> messagesIndexes, MessagesScope messagesScope) {

    goToScopeMap.get(messagesScope).run();

    messagesIndexes.forEach(index -> gmailMainPageObject.checkMessage(index, messagesScope.equals(MessagesScope.INBOX)));

    gmailMainPageObject.markMessagesAsImportant();
  }

  public int getMessagesCount(MessagesScope messagesScope) {

    goToScopeMap.get(messagesScope).run();

    return gmailMainPageObject.getMessagesCount();
  }
}
