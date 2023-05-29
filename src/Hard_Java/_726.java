package Hard_Java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class _726 {
	public String countOfAtoms(String formula) {
        int n = formula.length();
        Deque<Map<String, Integer>> stack = new LinkedList<>();
        Map<String, Integer> currentCount = new HashMap<>();
        int i = 0;

        while (i < n) {
            char ch = formula.charAt(i);

            if (ch == '(') {
                stack.push(currentCount);
                currentCount = new HashMap<>();
                i++;
            } else if (ch == ')') {
                Map<String, Integer> countInParentheses = currentCount;
                currentCount = stack.pop();
                i++;

                int start = i;
                while (i < n && Character.isDigit(formula.charAt(i))) {
                    i++;
                }

                int multiplier = start < i ? Integer.parseInt(formula.substring(start, i)) : 1;
                for (String atom : countInParentheses.keySet()) {
                    int count = countInParentheses.get(atom);
                    currentCount.put(atom, currentCount.getOrDefault(atom, 0) + count * multiplier);
                }
            } else {
                int start = i++;
                while (i < n && Character.isLowerCase(formula.charAt(i))) {
                    i++;
                }

                String atom = formula.substring(start, i);
                start = i;
                while (i < n && Character.isDigit(formula.charAt(i))) {
                    i++;
                }

                int count = start < i ? Integer.parseInt(formula.substring(start, i)) : 1;
                currentCount.put(atom, currentCount.getOrDefault(atom, 0) + count);
            }
        }

        StringBuilder result = new StringBuilder();
        List<String> atoms = new ArrayList<>(currentCount.keySet());
        Collections.sort(atoms);

        for (String atom : atoms) {
            result.append(atom);
            int count = currentCount.get(atom);
            if (count > 1) {
                result.append(count);
            }
        }

        return result.toString();
    }
}
