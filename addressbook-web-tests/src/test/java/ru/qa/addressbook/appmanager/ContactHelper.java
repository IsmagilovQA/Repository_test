package ru.qa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.qa.addressbook.model.ContactData;

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

  public void selectContact() {
    click(By.xpath("//input[@name='selected[]']"));
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
}
