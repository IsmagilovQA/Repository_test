package ru.qa.sandbox.point;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CalculateDistanceTests {

  @Test
 public void testResult1() {
    Point p1 = new Point(8,2);
    Point p2 = new Point(6,1);
    Assert.assertEquals(p1.distance(p2), 2.23606797749979);
  }
}
