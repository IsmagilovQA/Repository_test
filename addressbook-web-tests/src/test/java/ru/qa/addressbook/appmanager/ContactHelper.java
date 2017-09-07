package ru.qa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.qa.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactForm() {
    click(By.name("submit"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("middlename"), contactData.getMiddleName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("nickname"), contactData.getNickName());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("mobile"), contactData.getMobile());
  }

  public void selectContact(int index) {
    wd.findElements(By.xpath("//input[@name='selected[]']")).get(index).click();
  }

  public void editContact() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void deleteContact() {
    click(By.xpath("//input[@onclick='DeleteSel()']"));
  }

  public void deleteAlert() {
    wd.switchTo().alert().accept();
  }

  public void updateContact() {
    click(By.xpath("//input[@name='update']"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//input[@name='selected[]']"));
  }

  public void createContact(ContactData contact) {
    fillContactForm(contact);
    submitContactForm();
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.xpath(".//tr[@name='entry']"));
    for (WebElement element: elements){
      List<WebElement> cells1 = element.findElements(By.tagName("td"));
      String lastName = cells1.get(1).getText();
      List<WebElement> cells2 = element.findElements(By.tagName("td"));
      String firstName = cells2.get(2).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData(firstName, null, lastName,
              null, null, null, null, id);
      contacts.add(contact);
    }
    return contacts;
  }
}
