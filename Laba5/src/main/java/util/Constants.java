package util;

public interface Constants {

  String ALERT_CONTENT = "The address \"wrongemail.com\" in the \"To\" field was not recognized. Please make sure that all addresses are properly formed.";
  String ALERT_TITLE = "Error";

  String TEST1_DESC = "1. Open gmail & login\n"
      + "2. Click “Compose” button\n"
      + "3. Fill “To”, “Subject” & “message” fields\n"
      + "4. Click “send” button\n"
      + "5. Verify that message is in “sent” folder\n"
      + "6. Remove message from the “sent” folder\n"
      + "\n";

  String TEST2_DESC = "1. Open gmail & login\n"
      + "2. Click “Compose” button\n"
      + "3. Fill “To”, “Subject” & “message” fields\n"
      + "4. Close “new message” window\n"
      + "5. Verify that message is saved as draft\n"
      + "6. Open message from the draft folder & send\n";

  String TEST3_DESC = "1. Open gmail & login\n"
      + "2. Mark 3 messages from inbox as important\n"
      + "3. Verify that messages are moved to “important” folder\n"
      + "4. Select those messages using checkboxes\n"
      + "5. Click on delete button\n"
      + "6. Verify that messages are deleted\n";

  String TEST4_DESC = "1. Open gmail & login\n"
      + "2. Click on compose button\n"
      + "3. Enter incorrect email in “to” field, fill “subject”/”message” fields & press “send” button \n"
      + "4. Verify that warning message appears\n"
      + "5. Click “OK” & enter correct email address & click send\n"
      + "6. Verify that message is moved to “Sent mail” folder\n";
}
