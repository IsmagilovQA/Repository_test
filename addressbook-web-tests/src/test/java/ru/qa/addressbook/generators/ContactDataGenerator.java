package ru.qa.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.qa.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter (names = "-c", description = "Contact count")
  public int count;

  @Parameter (names = "-f", description = "Target file")
  public String file;


  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContactsCSV(count);
    saveCSV(contacts, new File(file));
  }

  private void saveCSV(List<ContactData> contacts, File file) throws IOException {
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

  private List<ContactData> generateContactsCSV(int count) {
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
