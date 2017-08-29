package ru.qa.addressbook.tests;

import org.testng.annotations.Test;
import ru.qa.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

  @Test
  public  void testContactDeletion() {
    app.getNavigationHelper().goToHomePage();

    if (! app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().goToContactForm();
      app.getContactHelper().createContact(new ContactData(
              "my name 1",
              "my middle name 1",
              "my last name 1",
              "my nickname 1",
              "Facebook",
              "New York, Baseinaja str 4, flat 7",
              "+380001234567"));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteContact();
    app.getContactHelper().deleteAlert();
  }

}
