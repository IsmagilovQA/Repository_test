package ru.qa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.qa.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    List<ContactData> before = app.contact().list();
    app.goTo().contactPage();
    ContactData contact = new ContactData()
            .withFirstName("my name 1")
            .withMiddleName("my middle name 1")
            .withLastName("my last name 1")
            .withNickName("my nickname 1")
            .withCompany("Facebook")
            .withAddress("New York, Baseinaja str 4, flat 7")
            .withMobile("+380001234567")
            .withGroup("name 1");
    app.contact().create(contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
