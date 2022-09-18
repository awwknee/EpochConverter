import java.util.Random;
import java.util.Scanner;

/**
 * This program displays the current date and then asks the user for a date (month, day, and year)
 * and computes what that date is in Unix Epoch time format. Then it will display one random date
 * from that same year.
 * 
 * @author Ani Lamichhane :D
 *
 */
public class EpochConverter {

  public static void main(String[] args) {
    int year;
    int day;
    int month;
    Scanner keyboard = new Scanner(System.in);

    System.out.println("Todays date is: ");
    System.out.println(dateToString(epochTimeToDate((System.currentTimeMillis() / 1000))));

    System.out.print("Enter a month: ");
    month = keyboard.nextInt();
    System.out.print("Enter a day: ");
    day = keyboard.nextInt();
    System.out.print("Enter a year: ");
    year = keyboard.nextInt();

    int[] date = {month, day, year};

    if (!isValidDate(date)) {
      System.out.println("Your date is invalid, Goodbye.");
      return;
    }

    System.out.println("Unix Epoch Time: ");
    System.out.println(dateToEpochTime(date));

    System.out.println("Random date from same year: ");
    System.out.println(dateToString(randomDate(year)));
  }

  /**
   * takes a year as input and returns boolean true if it is a leap year and false otherwise.
   * 
   * @param year user input of year
   * @return boolean, true if leap year false if not a leap year
   */
  public static boolean isLeapYear(int year) {
    if (year % 4 != 0) {
      return false;
    } else if (year % 100 != 0) {
      return true;
    } else if (year % 400 != 0) {
      return false;
    } else {
      return true;
    }
  }

  /**
   * takes a date as input and returns boolean true if the date is valid and false otherwise.
   * 
   * @param date user input of integer array size 3, (month,day,year)
   * @return boolean, true if date is valid and false if invalid date
   */
  public static boolean isValidDate(int[] date) {
    if (date[0] == 1 && date[1] <= 31) {
      return true;
    } else if (date[0] == 2 && isLeapYear(date[2]) == false && date[1] <= 28) {
      return true;
    } else if (date[0] == 2 && isLeapYear(date[2]) == true && date[1] <= 29) {
      return true;
    } else if (date[0] == 3 && date[1] <= 31) {
      return true;
    } else if (date[0] == 4 && date[1] <= 30) {
      return true;
    } else if (date[0] == 5 && date[1] <= 31) {
      return true;
    } else if (date[0] == 6 && date[1] <= 30) {
      return true;
    } else if (date[0] == 7 && date[1] <= 31) {
      return true;
    } else if (date[0] == 8 && date[1] <= 31) {
      return true;
    } else if (date[0] == 9 && date[1] <= 30) {
      return true;
    } else if (date[0] == 10 && date[1] <= 31) {
      return true;
    } else if (date[0] == 11 && date[1] <= 30) {
      return true;
    } else if (date[0] == 12 && date[1] <= 31) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * takes a date and returns the next valid date (i.e., the day after)
   * 
   * @param date user input of integer array size 3, (month,day,year)
   * @return the day after as an integer array size 3, (month,day,year)
   */
  public static int[] getDayAfter(int[] date) {

    int[] dayAfter = new int[3];

    if ((date[0] == 4 || date[0] == 6 || date[0] == 9 || date[0] == 11) && date[1] == 30) {
      dayAfter[0] = date[0] + 1;
      dayAfter[1] = 1;
      dayAfter[2] = date[2];
    } else if ((date[0] == 1 || date[0] == 3 || date[0] == 5 || date[0] == 7 || date[0] == 8
        || date[0] == 10) && date[1] == 31) {
      dayAfter[0] = date[0] + 1;
      dayAfter[1] = 1;
      dayAfter[2] = date[2];
    } else if (date[0] == 12 && date[1] == 31) {
      dayAfter[0] = 1;
      dayAfter[1] = 1;
      dayAfter[2] = date[2] + 1;
    } else if ((date[0] == 2) && (isLeapYear(date[2]) == true) && (date[1] == 29)) {
      dayAfter[0] = date[0] + 1;
      dayAfter[1] = 1;
      dayAfter[2] = date[2];
    } else if ((date[0] == 2) && (isLeapYear(date[2]) == false) && (date[1] == 28)) {
      dayAfter[0] = date[0] + 1;
      dayAfter[1] = 1;
      dayAfter[2] = date[2];
    } else {
      dayAfter[0] = date[0];
      dayAfter[1] = date[1] + 1;
      dayAfter[2] = date[2];
    }
    return dayAfter;
  }

  /**
   * takes two dates, date1 and date2, and returns -1 if date1 comes before date2, returns 1 if
   * date1 comes after date2, and returns 0 if date1 is the same date as date2.
   * 
   * @param date1 user input of integer array size 3, (month,day,year)
   * @param date2 user input of integer array size 3, (month,day,year)
   * @return -1 if date1 comes before date2, returns 1 if date1 comes after date2, and returns 0 if
   *         date1 is the same date as date2.
   */
  public static int compareDates(int[] date1, int[] date2) {
    if (date1 == date2) {
      return 0;
    } else if (date1[2] < date2[2]) {
      return -1;
    } else if (date1[2] > date2[2]) {
      return 1;
    } else if (date1[0] < date2[0]) {
      return -1;
    } else if (date1[0] > date2[0]) {
      return 1;
    } else if (date1[1] < date2[1]) {
      return -1;
    } else if (date1[1] > date2[1]) {
      return 1;
    }
    return 0;
  }

  /**
   * takes two dates as input and returns how many days elapse from the start of the first date to
   * the start of the second date.
   * 
   * @param date1 user input of integer array size 3, (month,day,year)
   * @param date2 user input of integer array size 3, (month,day,year)
   * @return how many days elapse from the start of the first date to the start of the second date
   */
  public static int daysBetweenDates(int[] date1, int[] date2) {
    int daysPast = 0;

    while ((date1[0] != date2[0]) && (date1[1] != date2[1]) && (date1[2] != date2[2])) {
      date1 = getDayAfter(date1);
      daysPast = daysPast + 1;
    }
    return daysPast;
  }

  /**
   * takes a date (integer array of size 3) as input and returns a long representing 00:00:00 UTC on
   * that date converted to epoch time.
   * 
   * @param date user input of integer array size 3, (month,day,year)
   * @return a long representing 00:00:00 UTC on that date converted to epoch time
   */
  public static long dateToEpochTime(int[] date) {
    long daysPast = 0;
    int[] epochdate = {1, 1, 1970};

    if (date[2] < 1970) {
      while (!dateToString(epochdate).equals(dateToString(date))) {
        date = getDayAfter(date);
        daysPast = daysPast + 1;
      }
      daysPast = daysPast * -1;
    } else {
      while (!dateToString(epochdate).equals(dateToString(date))) {
        epochdate = getDayAfter(epochdate);
        daysPast = daysPast + 1;
      }
    }
    daysPast = daysPast * 86400;
    return daysPast;
  }

  /**
   * takes an epoch time (long integer) and returns the corresponding date.
   * 
   * @param epochTime unix epoch time
   * @return date as an integer array size 3, (month,day,year)
   */
  public static int[] epochTimeToDate(long epochTime) {
    long days = epochTime / 86400;
    int i = 0;
    int[] date = {1, 1, 1970};

    while (i < days) {
      date = getDayAfter(date);
      i = i + 1;
    }
    return date;

  }

  /**
   * takes a date (array of size 3) and returns a string representation of the date in the form
   * month/day/year.
   * 
   * @param date user input of integer array size 3, (month,day,year)
   * @return string of the date in the form month/day/year.
   */
  public static String dateToString(int[] date) {
    return new String(date[0] + "/" + date[1] + "/" + date[2]);

  }

  /**
   * takes a year and a day number (1-365 or 1-366) and returns the date corresponding to that day
   * number in that year.
   * 
   * @param year user input of year.
   * @param day a number between 1-365 or 1-366 corresponding to the date
   * @return the date corresponding to that day number in that year.
   */
  public static int[] dayNumberToDate(int year, int day) {
    int i = 1;
    int[] date = {1, 1, year};

    while (i < day) {
      date = getDayAfter(date);
      i = i + 1;
    }
    return date;
  }

  public static int[] randomDate(int year) {
    int randomNumber;
    int i = 1;
    int[] date = {1, 1, year};
    Random generator = new Random();

    if (isLeapYear(year) == false) {
      randomNumber = generator.nextInt(365) + 1;
    } else {
      randomNumber = generator.nextInt(366) + 1;
    }

    while (i < randomNumber) {
      date = getDayAfter(date);
      i = i + 1;
    }
    return date;
  }
}


