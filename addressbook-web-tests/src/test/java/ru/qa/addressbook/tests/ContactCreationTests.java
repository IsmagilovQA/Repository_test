package ru.qa.addressbook.tests;

import org.testng.annotations.Test;
import ru.qa.addressbook.model.ContactData;
import ru.qa.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    Contacts before = app.contact().all();
    app.goTo().contactPage();
    File photo = new File("src/test/resources/test_image.png");
    ContactData contact = new ContactData()
            .withFirstName("my name 1")
            .withMiddleName("my middle name 1")
            .withLastName("my last name 1")
            .withNickName("my nickname 1")
            .withCompany("Facebook")
            .withAddress("New York, Baseinaja str 4, flat 7")
            .withMobile("+380001234567")
            .withGroup("name 1")
            .withPhoto(photo);
    app.contact().create(contact);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));

    assertThat(after, equalTo
            (before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

  /*
  @Test
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/test_image.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }
  */

}
