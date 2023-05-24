import java.util.TreeMap;

class MyCalendarThree {
    private TreeMap<Integer, Integer> events;

    public MyCalendarThree() {
        events = new TreeMap<>();
    }

    public int book(int startTime, int endTime) {
        events.put(startTime, events.getOrDefault(startTime, 0) + 1);
        events.put(endTime, events.getOrDefault(endTime, 0) - 1);

        int maxBooking = 0;
        int ongoingBooking = 0;

        for (int count : events.values()) {
            ongoingBooking += count;
            maxBooking = Math.max(maxBooking, ongoingBooking);
        }

        return maxBooking;
    }
}
