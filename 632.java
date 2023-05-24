import java.util.*;

class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        // Create a priority queue to keep track of the smallest value in each list
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> nums.get(a[0]).get(a[1])));

        int maxVal = Integer.MIN_VALUE; // Track the maximum value seen so far
        for (int i = 0; i < nums.size(); i++) {
            List<Integer> list = nums.get(i);
            if (!list.isEmpty()) {
                pq.offer(new int[]{i, 0});
                maxVal = Math.max(maxVal, list.get(0));
            }
        }

        int rangeStart = -1, rangeEnd = -1, rangeSize = Integer.MAX_VALUE;
        while (pq.size() == nums.size()) { // While we have at least one element from each list
            int[] cur = pq.poll();
            int listIdx = cur[0], valIdx = cur[1], curVal = nums.get(listIdx).get(valIdx);

            if (maxVal - curVal < rangeSize || (maxVal - curVal == rangeSize && curVal < rangeStart)) {
                rangeStart = curVal;
                rangeEnd = maxVal;
                rangeSize = maxVal - curVal;
            }

            if (valIdx < nums.get(listIdx).size() - 1) {
                pq.offer(new int[]{listIdx, valIdx + 1});
                maxVal = Math.max(maxVal, nums.get(listIdx).get(valIdx + 1));
            }
        }

        return new int[]{rangeStart, rangeEnd};
    }
}
