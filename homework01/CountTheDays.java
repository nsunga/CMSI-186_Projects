import java.lang.NumberFormatException;
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
