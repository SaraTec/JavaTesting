package service;

import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import page.GmailLoginPage;

public class LoginService {

  private WebDriver webDriver;
  private GmailLoginPage gmailLoginPageObject;

  @SneakyThrows
  public void loginToGoogleAccount(String email, String password) {

    webDriver.get("http://www.gmail.com");

    gmailLoginPageObject
        .setEmail(email)
        .clickNextAfterEmail()
        .setPassword(password)
        .clickNextAfterPassword();
  }

  public void setWebDriver(WebDriver webDriver) {
    this.webDriver = webDriver;
    this.gmailLoginPageObject = new GmailLoginPage(webDriver);
  }
}
