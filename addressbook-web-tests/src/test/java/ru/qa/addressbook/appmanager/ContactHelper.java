package ru.qa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.qa.addressbook.model.ContactData;
import ru.qa.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submit() {
    click(By.name("submit"));
  }

  public void fillForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("middlename"), contactData.getMiddleName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("nickname"), contactData.getNickName());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("mobile"), contactData.getMobile());
    attach(By.name("photo"), contactData.getPhoto());

    if (creation) {
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
      } else {
        Assert.assertFalse(isElementPresent(By.name("new_group")));
      }
    }

  public void selectById(int id) {
    click(By.cssSelector("a[href='edit.php?id=" + id + "']"));
  }

  public void edit(int index) {
    click(By.xpath("//table[@id='maintable']/tbody/tr[" + index + "]/td[8]/a/img"));
  }

  public void delete() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void deleteAlert() {
    wd.switchTo().alert().accept();
  }

  public void update() {
    click(By.xpath("//input[@name='update']"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//input[@name='selected[]']"));
  }

  public void create(ContactData contact) {
    fillForm(contact, true);
    submit();
  }

  public void modify(ContactData contact) {
    selectById(contact.getId());
    fillForm(contact, false);
    update();
  }

  public void delete(ContactData contact) {
    selectById(contact.getId());
    delete();
    // deleteAlert();
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath(".//tr[@name='entry']"));
    for (WebElement element : elements) {
      List<WebElement> cells1 = element.findElements(By.tagName("td"));
      String lastName = cells1.get(1).getText();
      List<WebElement> cells2 = element.findElements(By.tagName("td"));
      String firstName = cells2.get(2).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactData().withFirstName(firstName).withLastName(lastName).withId(id));
    }
    return contacts;
  }
}
