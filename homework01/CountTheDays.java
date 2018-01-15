import java.io.IOException;

public class CountTheDays {
  public static void main(String[] args) {
      //check if the strings are longs first. else throw the exception
      System.out.println( daysBetween(Long.parseLong(args[0]), Long.parseLong(args[1]), Long.parseLong(args[2]), Long.parseLong(args[3]), Long.parseLong(args[4]), Long.parseLong(args[5])) );
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

  public static long daysInMonth(long month, long year) {
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
      System.out.println("BAD DATA");
      return -1;
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

  public static long daysBetween(long month0, long day0, long year0, long month1, long day1, long year1) {
    long amount_of_days = 0;
    long date_comparator_value = dateComparator(month0, day0, year0, month1, day1, year1);
    boolean both_dates_valid = false;

    try {
      both_dates_valid = isValidDate(month0, day0, year0) && isValidDate(month1, day1, year1);
    } catch (IOException e) {
      System.out.println("BAD DATA");
      return -1;
    }

    if (both_dates_valid) {
      if (date_comparator_value == 0) {
        while (year0 <= year1 || (month0 <= month1 && day0 <= day1)) {

          if (year0 == year1 && month0 == month1) {
            amount_of_days += day1;
            month0++;
            break;
          } else {
            amount_of_days += daysInMonth(month0, year0);
            month0++;
            if (month0 == 13) {
              month0 = 1;
              year0++;
            }
          }
          System.out.println(amount_of_days);
        }

        amount_of_days -= day0;
        return amount_of_days;
      } else {
        while (year1 <= year0 || (month1 <= month0 && day1 <= day0)) {

          if (year1 == year0 && month1 == month0) {
            amount_of_days += day0;
            month1++;
            break;
          } else {
            amount_of_days += daysInMonth(month1, year1);
            month1++;
            if (month1 == 13) {
              month1 = 1;
              year1++;
            }
          }
          System.out.println(amount_of_days);
        }

        amount_of_days -= day1;
        return amount_of_days;
      }
    }
    return 1;
  }

  public static long dateComparator(long month0, long day0, long year0, long month1, long day1, long year1) {
    if (year0 < year1) {
      return 0;
    }

    if (year0 == year1 && month0 < month1) {
      return 0;
    }

    if (year0 == year1 && month0 == month1 && day0 < day1) {
      return 0;
    } else {
      return 1;
    }
  }
}
