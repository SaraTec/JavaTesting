package model;

import lombok.Value;

@Value
public class Alert {

  private final String title;
  private final String content;
}
