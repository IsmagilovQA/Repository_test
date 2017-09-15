package ru.qa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.qa.addressbook.model.ContactData;
import ru.qa.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size() - 1);

    assertThat(after, equalTo(before.without(deletedContact)));
  }

}