public class Date {
    // Each Date value is a past, present, or future date.

    // This date is represented by a year number y, a month number m
    // (1 <= m <= 12), and a day-in-month number d
    // (1 <= d <= month length <= 31).
    private int y;
    private int m;
    private int d;

    public Date (int y, int m, int d) {
    // Construct a date with year y, month m, and day-in-month d.
    // Throw an exception if they constitute an improper date.
        if (m < 1 || m > 12 ||
                d < 1 || d > length(m, y))
            throw new IllegalArgumentException("badly formed date");
        this.y = y;  this.m = m;  this.d = d;
    }
    
    public String toString () {
    // Return this date's ISO string representation.
        return String.format("%04d-%02d-%02d",y,m,d);
    }

    public boolean equals (Date that) {
    // Return true if this date equal to that, or false otherwise.
        return (this.y == that.y && this.m == that.m && this.d == that.d);
    }

    public int compareTo (Date other) {
    // Return -1 if this date comes before that,
    // or 0 if this date is equal to that,
    // or +1 if this date come after that.
        return  (y < other.y ? -1 :
                 y > other.y ? +1 :
                 m < other.m ? -1 :  //GL pour réfléter le livre
                 m > other.m ? +1 :  //GL
                 d < other.d ? -1 :
                 d > other.d ? +1 : 0);
    }

    public void advance (int n) {
    // Advance this date by n days (where n >= 0).
        d = d + n;
        int last;
        while (d > (last = length(m, y))) {
            d -= last;
            if (m < 12) m++; else { m = 1;  y++; }
        }
    }


    /// méthodes privées

    private static int length (int m, int y) {
    // Return the number of days in month m in year y.
        switch (m) {
            case 1: case 3: case 5: case 7:
            case 8: case 10: case 12:
                return 31;
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                return (isLeap(y) ? 29 : 28);
            default:
                return -1;
        }
    }

    private static boolean isLeap (int y) {
    // Return true if and only if y is a leapyear.
        return (y%4 == 0 && (y%100 != 0 || y%400 == 0));
    }
}