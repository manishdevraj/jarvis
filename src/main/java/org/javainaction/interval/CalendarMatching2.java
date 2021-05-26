package org.javainaction.interval;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

import static java.time.temporal.ChronoUnit.MINUTES;

class CalendarMatching2 {
    public static String pattern = "k:m";
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
    public static NumberFormat numberFormat = new DecimalFormat("00");

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


    public static List<StringMeeting> calendarMatching(
            List<StringMeeting> calendar1,
            StringMeeting dailyBounds1,
            List<StringMeeting> calendar2,
            StringMeeting dailyBounds2,
            int meetingDuration) {
        List<Meeting> updateCalendar1 = updateCalendar(calendar1, dailyBounds1);
        List<Meeting> updateCalendar2 = updateCalendar(calendar2, dailyBounds2);
        //merge
        List<Meeting> mergedCalendar = mergeCalendars(updateCalendar1, updateCalendar2);
        //flatten
        List<Meeting> flattenedCalendar = flattenCalendar(mergedCalendar);
        //find availability

        return getAvailabilities(flattenedCalendar, meetingDuration);
    }

    public static List<StringMeeting> getAvailabilities(
            List<Meeting> calendar,int meetingDuration) {
        List<Meeting> matchingAvailabilities = new ArrayList<>();
        for (int i = 1; i < calendar.size(); i++) {
            int start = calendar.get(i - 1).end;
            int end = calendar.get(i).start;
            int availabilityDuration = end - start;
            if (availabilityDuration >= meetingDuration) {
                matchingAvailabilities.add(new Meeting(start, end));
            }
        }

        List<StringMeeting> matchingInHours = new ArrayList<>();
        for (int i = 0; i < matchingAvailabilities.size(); i++) {
            matchingInHours.add(new StringMeeting(
                    minuteToTime(matchingAvailabilities.get(i).start),
                    minuteToTime(matchingAvailabilities.get(i).end)));
        }
        return matchingInHours;
    }


    public static List<Meeting> flattenCalendar(List<Meeting> calendar) {
        List<Meeting> flattened = new ArrayList<>();
        flattened.add(calendar.get(0));
        for (int i = 1; i < calendar.size(); i++) {
            Meeting currentMeeting = calendar.get(i);
            Meeting previousMeeting = flattened.get(flattened.size() - 1);
            if (previousMeeting.end >=  currentMeeting.start) {
                Meeting newPrevMeeting = new Meeting(previousMeeting.start,
                        Math.max(previousMeeting.end, currentMeeting.end));
                flattened.set(flattened.size() - 1, newPrevMeeting);
            } else {
                flattened.add(currentMeeting);
            }
        }
        return flattened;
    }

    public static List<Meeting> mergeCalendars(List<Meeting> calendar1,
                                               List<Meeting> calendar2) {
        List<Meeting> merged = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < calendar1.size() && j < calendar2.size()) {
            Meeting meeting1 = calendar1.get(i);
            Meeting meeting2 = calendar2.get(j);
            if (meeting1.start < meeting2.start) {
                merged.add(meeting1);
                i++;
            } else {
                merged.add(meeting2);
                j++;
            }
        }
        //add remaining calendar times
        while (i > calendar1.size()) merged.add(calendar1.get(i++));
        while (j > calendar2.size()) merged.add(calendar2.get(j++));
        return merged;
    }

    public static List<Meeting> updateCalendar(List<StringMeeting> calendar,
                                               StringMeeting dailyBounds) {

        List<StringMeeting> updateCalendar = new ArrayList<>();
        //add all calendar upper and lower bounds out of the day
        updateCalendar.add(new StringMeeting("0:00", dailyBounds.start));
        updateCalendar.addAll(calendar);
        updateCalendar.add(new StringMeeting(dailyBounds.end, "23:59"));
        List<Meeting> calendarInMinutes = new ArrayList<>();
        for (int i = 0; i < updateCalendar.size(); i++) {
            calendarInMinutes.add(new Meeting(
                    timeToMinutes(updateCalendar.get(i).start),
                    timeToMinutes(updateCalendar.get(i).end)));
        }
        return calendarInMinutes;

    }

    public static int timeToMinutes(String time) {
        int delimiterPos = time.indexOf(":");
        int hours = Integer.parseInt(time.substring(0, delimiterPos));
        int minutes = Integer.parseInt(time.substring(delimiterPos +  1, time.length()));
        return hours * 60 + minutes;
    }

    public static String minuteToTime(int minutes) {
        int hours = minutes / 60;
        int mins = minutes % 60;
        String hourString = Integer.toString(hours);
        String minutesString = mins < 10 ? "0" + Integer.toString(mins)
                : Integer.toString(mins);
        return hourString + ":" + minutesString;
    }

    static class StringMeeting {
        public String start;
        public String end;

        public StringMeeting(String start, String end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "StringMeeting{" +
                    "start='" + start + '\'' +
                    ", end='" + end + '\'' +
                    '}';
        }
    }

    static class Meeting {
        public int start;
        public int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Meeting{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

}



