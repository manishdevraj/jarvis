package org.javainaction.interval;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.*;
import java.text.*;

import static java.time.temporal.ChronoUnit.MINUTES;

class CalendarMatching {
    public static String pattern = "k:m";
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
    public static NumberFormat numberFormat = new DecimalFormat("00");

    public static List<StringMeeting> calendarMatching(
            List<StringMeeting> calendar1,
            StringMeeting dailyBounds1,
            List<StringMeeting> calendar2,
            StringMeeting dailyBounds2,
            int meetingDuration) {
        // Write your code here.
        BitSet bitSetCalOne = new BitSet(96);
        BitSet bitSetCalTwo = new BitSet(96);
        bitSetCalOne = calendarToBitSet(calendar1, bitSetCalOne);
        bitSetCalTwo = calendarToBitSet(calendar2, bitSetCalTwo);
        bitSetCalTwo.or(bitSetCalOne);

        LocalTime dailyBoundStartOneTime = strToLocalTime(dailyBounds1.start);
        LocalTime dailyBoundStartTwoTime = strToLocalTime(dailyBounds2.start);
        int startMax = -1;

        if (dailyBoundStartOneTime.compareTo(dailyBoundStartTwoTime) < 0) {
            startMax = convertMinToIndex(dailyBoundStartTwoTime);
        } else {
            startMax = convertMinToIndex(dailyBoundStartOneTime);
        }

        LocalTime dailyBoundEndOneTime = strToLocalTime(dailyBounds1.end);
        LocalTime dailyBoundEndTwoTime = strToLocalTime(dailyBounds2.end);
        int endMin = -1;
        if (dailyBoundEndOneTime.compareTo(dailyBoundEndTwoTime) < 0) {
            endMin = convertMinToIndex(dailyBoundEndOneTime);
        } else {
            endMin = convertMinToIndex(dailyBoundEndTwoTime);
        }

        int slotsNeeded = meetingDuration / 15;
        List<StringMeeting> output = new ArrayList<>();
        for(int i = startMax; i <= endMin;) {
            int start = i;
            int end = i;
            while (!bitSetCalTwo.get(end++) && end <= endMin) {
                continue;
            }

            if (end != start && (end - 1 - start) >= slotsNeeded) {
                int startTime = start * 15;
                int endTime = startTime + (end - start - 1) * 15 ;

                output.add(new StringMeeting(minuteToTime(startTime), minuteToTime(endTime)));
            }
            i = end;
        }

        return output;
    }

    public static LocalTime strToLocalTime(String dateStr) {
        return LocalTime.parse(dateStr, formatter);
    }

    public static String minuteToTime(int minutes) {
        int hours = minutes / 60;
        int mins = minutes % 60;
        String hourString = Integer.toString(hours);
        String minutesString = numberFormat.format(mins);
        return hourString + ":" + minutesString;
    }

    public static int convertMinToIndex(LocalTime localTime) {
        return localTime.get(ChronoField.MINUTE_OF_DAY) / 15;
    }

    public static BitSet calendarToBitSet(List<StringMeeting> calendar, BitSet bitSet) {
        for (StringMeeting stringMeeting : calendar) {
            LocalTime startLocalDateTime = LocalTime.parse(stringMeeting.start, formatter);
            LocalTime endLocalDateTime = LocalTime.parse(stringMeeting.end, formatter);

            long difference = MINUTES.between(startLocalDateTime, endLocalDateTime);
            int start = convertMinToIndex(startLocalDateTime);

            int duration = (int) difference / 15;
            while (duration > 0) {
                bitSet.set(start);
                start++;
                duration--;
            }
        }

        return bitSet;
    }

    static class StringMeeting {
        public String start;
        public String end;

        public StringMeeting(String start, String end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws ParseException {
        List<StringMeeting> calendar1 = Arrays.asList(new StringMeeting("9:00", "10:30"),
                new StringMeeting("12:00", "13:00"), new StringMeeting("16:00", "18:00"));

        List<StringMeeting> calendar2 = Arrays.asList(new StringMeeting("10:00", "11:30"),
                new StringMeeting("12:30", "14:30"), new StringMeeting("14:30", "15:00"),
                new StringMeeting("16:00", "17:00"));
        StringMeeting dailyBounds1 = new StringMeeting("9:00", "20:00");

        StringMeeting dailyBounds2 = new StringMeeting("10:30", "18:30");

        int meetingDuration = 30;

        List<StringMeeting> output = calendarMatching(calendar1, dailyBounds1, calendar2, dailyBounds2, meetingDuration);

        //11:30 12:00 & 15:00 16:00 18:00 & 18:30

        for (StringMeeting meeting : output) {
            System.out.println("[ " + meeting.start + " , " + meeting.end + " ]");
        }
    }

}



