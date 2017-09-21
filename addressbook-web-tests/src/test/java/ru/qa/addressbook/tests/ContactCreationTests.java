package ru.qa.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.qa.addressbook.model.ContactData;
import ru.qa.addressbook.model.Contacts;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() {
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[]{new ContactData().withFirstName("name 1").withMiddleName("middle name 1")
            .withLastName("last name 1").withNickName("nickname 1").withCompany("Facebook")
            .withAddress("NY, Baseinaja str 1, flat 1").withMobile("+380001234567").withGroup("name 1")});
    list.add(new Object[]{new ContactData().withFirstName("name 2").withMiddleName("middle name 2")
            .withLastName("last name 2").withNickName("nickname 2").withCompany("VK")
            .withAddress("Boston, Baseinaja str 2, flat 2").withMobile("+380001234568").withGroup("name 2")});
    list.add(new Object[]{new ContactData().withFirstName("name 3").withMiddleName("middle name 3")
            .withLastName("last name 3").withNickName("nickname 3").withCompany("Fake")
            .withAddress("LA, Baseinaja str 3, flat 3").withMobile("+380001234569").withGroup("name 3")});
    return list.iterator();
  }

  @Test(dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) {
    Contacts before = app.contact().all();
    app.goTo().contactPage();
    File photo = new File("src/test/resources/test_image.png");
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
