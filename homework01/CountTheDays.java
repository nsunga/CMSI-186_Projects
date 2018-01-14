import java.io.IOException;

public class CountTheDays {
  public static void main(String[] args) {
    try {
      //check if the strings are longs first. else throw the exception
      System.out.println( isValidDate(Long.parseLong(args[0]), Long.parseLong(args[1]), Long.parseLong(args[2])) );
    } catch (IOException e) {
      System.out.println("BAD DATA");
    }
  }

  public static boolean isLeapYear(long year) {
    if (year % 4 == 0) {
      if (year % 100 == 0) {
        if (year % 400 == 0) {
          return true;
        } else {
          return false;
        }
      } else {
        return true;
      }
    } else {
      return false;
    }
  }

  public static long daysInMonth(long month, long year) throws IOException {
    if (month == 1) {
      return 31;
    } else if (month == 2) {
      if (isLeapYear(year)) {
        return 29;
      } else {
        return 28;
      }
    } else if (month == 3) {
      return 31;
    } else if (month == 4) {
      return 30;
    } else if (month == 5) {
      return 31;
    } else if (month == 6) {
      return 30;
    } else if (month == 7) {
      return 31;
    } else if (month == 8) {
      return 31;
    } else if (month == 9) {
      return 30;
    } else if (month == 10) {
      return 31;
    } else if (month == 11) {
      return 30;
    } else if (month == 12) {
      return 31;
    } else {
      throw new IOException();
    }
  }

  public static boolean isValidDate(long month, long day, long year) throws IOException {
    if (year > 0) {
      if (day > 0 && day <= daysInMonth(month, year)) {
        return true;
      } else {
        throw new IOException();
      }
    } else {
      throw new IOException();
    }
  }

  //TODO:
  public static long daysBetween(long month0, long day0, long year0, long month1, long day1, long year1) {
    return 1;
  }
}
