import java.util.Scanner;

public class Calendar_Arrays {
    public Calendar_Arrays() {
    }

    public static void main(String[] args) {
        String[] months = new String[]{"January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December"};

        Scanner input = new Scanner(System.in);

        //used this prompt instead of asking for the current month in order to provide flexibilty.
        System.out.print("What is the number of the month you want?: ");

        int numMonth;
        for(numMonth = input.nextInt(); numMonth > 12 || numMonth < 1; numMonth = input.nextInt()) {
            System.out.print("That is an invalid entry. Enter a value from 1 to 12: ");
        }
        /* could also ask for the current year and divide it by four in order to find out if it's a leap year,
        but this works as well. */
        System.out.print("Is it a leap year?(Y or N): ");
        String response = input.next();

        //conditions here were very long, so I had to split them into separte lines.
        for(char actualResponse = response.charAt(0);
            actualResponse != 'Y' && actualResponse != 'N' && actualResponse != 'y' && actualResponse != 'n';
            actualResponse = response.charAt(0))
        {
            System.out.print("Please input an appropriate response (Y or N): ");
            response = input.next();
        }

        System.out.print("The month you wanted is " + months[numMonth - 1] + " and the number of days in this " +
                "month are: " + DaysofMonth(response, numMonth));
        System.out.print("\nWhat is the date in this month?: ");

        //asks for the day of the month, which helps calculate many of the other functions of this program
        int Day;
        for(Day = input.nextInt(); Day > DaysofMonth(response, numMonth) || Day < 0; Day = input.nextInt()) {
            System.out.print("That is an invalid response for this month, please input the correct date of this " +
                    "month. (Please note that this month currently has " + DaysofMonth(response, numMonth) + " days. " +
                    "Enter no more than this.)");
        }

        System.out.print("The number of days left in the year are: " + DaysLeft(DaysPast(numMonth, Day, response),
                response) + "\nThe number of days that have passed are: " + DaysPast(numMonth, Day, response));
    }

    public static int DaysofMonth(String answer, int month) {
        int date = ArrayShortcut(answer, month);
        return date;
    }

    //shortcut for the number of days in each month of the year, again using an array to accompish this.
    public static int ArrayShortcut(String resp, int repeater) {
        char actualResp = resp.charAt(0);
        //index number one changes based on whether or not it's a leap year, since February gains an extra day.
        int[] numDaysLeap = new int[]{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] numDaysnLeap = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int days;
        if (actualResp != 'Y' && actualResp != 'y') {
            days = numDaysnLeap[repeater - 1];
        } else {
            days = numDaysLeap[repeater - 1];
        }

        return days;
    }

//calculates how many days have passed in the particular year.

    public static int DaysPast(int mnth, int daymnth, String reply) {
        int past = 0;

        for(int i = 1; i < mnth; ++i) {
            int dys = ArrayShortcut(reply, i);
            past += dys;
        }

        past += daymnth;
        return past;
    }

//calculates how many days are left in this particular year

    public static int DaysLeft(int passed, String ans) {
        char actualAns = ans.charAt(0);
        short daysOfYear;
        if (actualAns != 'Y' && actualAns != 'y') {
            daysOfYear = 365;
        } else {
            daysOfYear = 366;
        }

        return daysOfYear - passed;
    }
}
