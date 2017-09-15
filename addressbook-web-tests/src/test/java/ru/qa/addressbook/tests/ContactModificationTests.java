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
      app.contact().create(new ContactData()
              .withFirstName("my name 1")
              .withMiddleName("my middle name 1")
              .withLastName("my last name 1")
              .withNickName("my nickname 1")
              .withCompany("Facebook")
              .withAddress("New York, Baseinaja str 4, flat 7")
              .withMobile("+380001234567"));
    }
  }


  @Test
  public void testContactModification() {
    List<ContactData> before = app.contact().list();
    app.contact().edit(before.size() + 1);
    ContactData contact = new ContactData()
            .withFirstName("my name 1")
            .withMiddleName("my middle name 1")
            .withLastName("my last name 1")
            .withNickName("my nickname 1")
            .withCompany("Facebook")
            .withAddress("New York, Baseinaja str 4, flat 7")
            .withMobile("+380001234567")
            .withId(before.get(before.size() - 1).getId());
    app.contact().fillForm(contact, false);
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
