package ru.qa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.qa.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().list().size() == 0) {
      app.goTo().contactPage();
      app.contact().createContact(new ContactData("my name 1", "my middle name 1",
              "my last name 1", "my nickname 1", "Facebook",
              "New York, Baseinaja str 4, flat 7", "+380001234567", null));
    }
  }

  @Test
  public void testContactModification() {
    List<ContactData> before = app.contact().list();
    app.contact().editContact(before.size() + 1);
    ContactData contact = new ContactData("updated name 1", "updated middle name 1",
            "updated last name 1", "updated nickname 1", "updated Facebook",
            "updated - New York, Baseinaja str 4, flat 7", "updated +380001234567",
            before.get(before.size() - 1).getId(), null);
    app.contact().fillContactForm(contact, false);
    app.contact().update();

    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());
    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
