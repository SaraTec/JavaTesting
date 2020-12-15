package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import lombok.Getter;

@Getter
public final class PropertyManager {

  private static PropertyManager instance;
  private String email;
  private String password;

  private String to1;
  private String subject1;
  private String content1;

  private String to2;
  private String subject2;
  private String content2;

  private String to4;
  private String subject4;
  private String content4;

  public static PropertyManager getInstance() {
    if (instance == null) {

      instance = new PropertyManager();
      instance.loadData();
    }

    return instance;
  }

  private void loadData() {
    Properties properties = new Properties();

    try {

      InputStream resourceInputStream = this.getClass().getClassLoader().getResourceAsStream("selenium-config.properties");

      if (resourceInputStream != null) {


        properties.load(resourceInputStream);
      } else {

        throw new IOException();
      }

    } catch (IOException e) {

      System.out.println("Configuration properties file cannot be found");
    }

    email = properties.getProperty("login.email");
    password = properties.getProperty("login.password");

    to1 = properties.getProperty("test1.to-email");
    subject1 = properties.getProperty("test1.subject");
    content1 = properties.getProperty("test1.content");

    to2 = properties.getProperty("test2.to-email");
    subject2 = properties.getProperty("test2.subject");
    content2 = properties.getProperty("test2.content");

    to4 = properties.getProperty("test4.to-email");
    subject4 = properties.getProperty("test4.subject");
    content4 = properties.getProperty("test4.content");
  }
}
