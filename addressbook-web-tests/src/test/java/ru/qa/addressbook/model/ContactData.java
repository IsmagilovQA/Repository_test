package ru.qa.addressbook.model;

public class ContactData {
  private final String firstName;
  private final String middleName;
  private final String lastName;
  private final String nickName;
  private final String company;
  private final String address;
  private final String mobile;
  private int id;
  private String group;


  public ContactData(String firstName, String middleName, String lastName,
                     String nickName, String company, String address,
                     String mobile) {
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.nickName = nickName;
    this.company = company;
    this.address = address;
    this.mobile = mobile;
    this.id = Integer.MAX_VALUE;

  }

  public ContactData(String firstName, String middleName, String lastName,
                     String nickName, String company, String address,
                     String mobile, int id) {
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.nickName = nickName;
    this.company = company;
    this.address = address;
    this.mobile = mobile;
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getNickName() {
    return nickName;
  }

  public String getCompany() {
    return company;
  }

  public String getAddress() {
    return address;
  }

  public String getMobile() {
    return mobile;
  }

  public String getGroup() {
    return group;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  @Override
  public String toString() {
    return "ContactData{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", id=" + id +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
    return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;
  }

  @Override
  public int hashCode() {
    int result = firstName != null ? firstName.hashCode() : 0;
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    return result;
  }

}
