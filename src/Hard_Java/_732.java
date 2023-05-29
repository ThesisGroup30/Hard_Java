package Hard_Java;

import java.util.TreeMap;

public class _732 {
	private TreeMap<Integer, Integer> events;

    public _732() {
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
