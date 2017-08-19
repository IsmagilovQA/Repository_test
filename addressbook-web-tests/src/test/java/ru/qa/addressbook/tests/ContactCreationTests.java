package ru.qa.addressbook.tests;

import org.testng.annotations.Test;
import ru.qa.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().goToContactForm();
    app.getContactHelper().fillContactForm(new ContactData("my name 1", "my middle name 1", "my last name 1", "my nickname 1", "Facebook", "New York, Baseinaja str 4, flat 7", "+380001234567"));
    app.getContactHelper().submitContactForm();
  }

}
