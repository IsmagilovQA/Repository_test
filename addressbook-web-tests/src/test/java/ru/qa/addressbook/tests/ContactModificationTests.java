package ru.qa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.qa.addressbook.model.ContactData;

import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
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
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().editContact();
    app.getContactHelper().fillContactForm(new ContactData(
            "updated name 1",
            "updated middle name 1",
            "updated last name 1",
            "updated nickname 1",
            "updated Facebook",
            "updated - New York, Baseinaja str 4, flat 7",
            "updated +380001234567"));
    app.getContactHelper().updateContact();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());
  }
}
