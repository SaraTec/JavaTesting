package model;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
public class Message {

  @EqualsAndHashCode.Exclude
  private final String to;
  private final String subject;
  @EqualsAndHashCode.Exclude
  private final String content;
}
