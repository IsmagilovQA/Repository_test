package ru.qa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.qa.addressbook.model.ContactData;

import java.util.Set;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
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
  public void testContactDeletion() {
    Set<ContactData> before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);

    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedContact);
    Assert.assertEquals(before, after);
  }

}
