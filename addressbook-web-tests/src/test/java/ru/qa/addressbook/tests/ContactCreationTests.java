package ru.qa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.qa.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getNavigationHelper().goToContactForm();
    ContactData contact = new ContactData("my name 1",
            "my middle name 1",
            "my last name 1",
            "my nickname 1",
            "Facebook",
            "New York, Baseinaja str 4, flat 7",
            "+380001234567",
            "name 1");
    app.getContactHelper().createContact(contact);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    /*
    // Variant 1 - finding max ID
    int max = 0;
    for (ContactData g: after) {
      if (g.getId() > max) {
        max = g.getId();
      }
    }

    // Variant 2 -  - finding max ID
    contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    */

    before.add(contact);

    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
