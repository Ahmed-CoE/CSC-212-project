import java.time.DateTimeException;

public class DateTime implements IDateTime {
    private final int year; 
    private final int month;
    private final int day;
    private final int hour;
    private final int minute;

    public DateTime(int year, int month, int day, int hour, int minute) {
        if (month < 1 || month > 12)
            throw new IllegalArgumentException("Month must be between 1 and 12");
        if (day < 1 || day > 31)
            throw new IllegalArgumentException("Invalid day");
        if (hour < 0 || hour > 23)
            throw new IllegalArgumentException("Invalid hour");
        if (minute < 0 || minute > 59)
            throw new IllegalArgumentException("Invalid minute");

        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }
    public int getYear() {
        return year;
    }
    public int getMonth() {
        return month;
    }
    public int getDay() {
        return day;
    }
    public int getHour() {
        return hour;
    }
    public int getMinute() {
        return minute;
    }

    @Override
    public String format() {
        return String.format("%02d/%02d/%04d %02d:%02d", month, day, year, hour, minute);
    }
    @Override
    public int compareTo(IDateTime other) {
        if (year != other.getYear())
            return Integer.compare(year,other.getYear());

        if  (month != other.getMonth())
            return Integer.compare(month,other.getMonth());

        if  (day != other.getDay())
            return Integer.compare(day,other.getDay());

        if (hour != other.getHour())
            return Integer.compare(hour,other.getHour());

        return Integer.compare(minute,other.getMinute());
    }

 


}

