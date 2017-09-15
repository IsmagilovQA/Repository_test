package ru.qa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.qa.addressbook.model.ContactData;

import java.util.ArrayList;
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

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void select(int index) {
    wd.findElements(By.xpath("//input[@name='selected[]']")).get(index).click();
  }

  public void edit(int index) {
    click(By.xpath("//table[@id='maintable']/tbody/tr[" + index + "]/td[8]/a/img"));
  }

  public void delete() {
    click(By.xpath("//input[@onclick='DeleteSel()']"));
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

  public void delete(int index) {
    select(index);
    delete();
    deleteAlert();
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.xpath(".//tr[@name='entry']"));
    for (WebElement element: elements){
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
