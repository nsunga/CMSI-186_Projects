/**
 *  File name     :  CountTheDays.java
 *  Purpose       :  Contains the main method to run CalendarStuff.daysBetween
 *                   from command line.
 *  Author        :  Nick Sunga
 *  Date          :  2018-01-23
 *  Description   :  JVM runs code that determines the amount of days between
 *                   two given dates from a command line interface
 *  Notes         :  Enter the numbers in order -> month1, day1, year1, month2, day2, year2
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

import java.io.IOException;

public class CountTheDays {
  public static void main(String[] args) {
    try {
      long days_between_value = CalendarStuff.daysBetween(Long.parseLong(args[0]), Long.parseLong(args[1]), Long.parseLong(args[2]), Long.parseLong(args[3]), Long.parseLong(args[4]), Long.parseLong(args[5]));
      if (days_between_value == -1) {
        throw new IOException();
      } else {
        System.out.println( days_between_value );
      }
    } catch (Exception e) {
      System.out.println("BAD DATA " + e.getClass().getName());
    }
  }
}
