package ru.qa.addressbook.tests;

import org.testng.annotations.Test;
import ru.qa.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().editContact();
    app.getContactHelper().fillContactForm(new ContactData("updated name 1", "updated middle name 1", "updated last name 1", "updated nickname 1", "updated Facebook", "updated - New York, Baseinaja str 4, flat 7", "updated +380001234567"));
    app.getContactHelper().updateContact();
  }
}
