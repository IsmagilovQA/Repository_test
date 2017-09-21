package ru.qa.addressbook.generators;

import ru.qa.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  public static void main(String[] args) throws IOException {
    int count = Integer.parseInt(args[0]);
    File file = new File(args[1]);

    List<ContactData> contacts = generateContactsCSV(count);
    saveCSV(contacts, file);
  }

  private static void saveCSV(List<ContactData> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for (ContactData contact: contacts) {
      writer.write(String.format("%s; %s; %s; %s; %s; %s; %s; %s\n",
              contact.getFirstName(),
              contact.getMiddleName(),
              contact.getLastName(),
              contact.getNickName(),
              contact.getCompany(),
              contact.getAddress(),
              contact.getMobile(),
              contact.getGroup()));
    }
    writer.close();
  }

  private static List<ContactData> generateContactsCSV(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData()
              .withFirstName(String.format("name %s", i))
              .withMiddleName(String.format("middle name %s", i))
              .withLastName(String.format("last name %s", i))
              .withNickName(String.format("nick name %s", i))
              .withCompany(String.format("Facebook %s", i))
              .withAddress(String.format("New York, Baseinaja str 4, flat 7 %s", i))
              .withMobile(String.format("+380001234567 %s", i))
              .withGroup(String.format("group 1", i)));
    }
    return contacts;
  }
}
