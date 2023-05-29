package Hard_Java;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

public class _895 {
	// Map each element to its frequency
    Map<Integer, Integer> freq;
    // Map each frequency to a stack of elements with that frequency
    TreeMap<Integer, Stack<Integer>> freqStack;
    // Keep track of the maximum frequency
    int maxFreq;

    public _895() {
        freq = new HashMap<>();
        freqStack = new TreeMap<>();
        maxFreq = 0;
    }

    public void push(int val) {
        // Increase the frequency of the element
        int f = freq.getOrDefault(val, 0) + 1;
        freq.put(val, f);
        // Update the max frequency
        maxFreq = Math.max(maxFreq, f);
        // Add the element to the stack corresponding to its frequency
        freqStack.putIfAbsent(f, new Stack<>());
        freqStack.get(f).push(val);
    }

    public int pop() {
        // Get the element with the maximum frequency
        int val = freqStack.get(maxFreq).pop();
        // Decrease its frequency
        freq.put(val, freq.get(val) - 1);
        // If the stack for the max frequency is empty, update the max frequency
        if (freqStack.get(maxFreq).isEmpty()) {
            freqStack.remove(maxFreq);
            maxFreq--;
        }
        return val;
    }
}
