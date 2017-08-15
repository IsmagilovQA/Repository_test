package ru.qa.sandbox.point;

public class CalculateDistance {

  public static void main(String[] args) {

    Point p1 = new Point(4, 6);
    Point p2 = new Point(2, 8);
    System.out.println("Distance between points is " + p2.distance(p1));
  }
}
