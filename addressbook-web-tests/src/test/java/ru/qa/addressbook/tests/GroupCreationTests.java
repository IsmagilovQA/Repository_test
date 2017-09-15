package ru.qa.addressbook.tests;

import org.testng.annotations.Test;
import ru.qa.addressbook.model.GroupData;
import ru.qa.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("name 1").withHeader("header 1").withFooter("footer 1");
    app.group().create(group);
    Groups after = app.group().all();
    assertThat(after.size(), equalTo(before.size() + 1));

    assertThat(after, equalTo
            (before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
