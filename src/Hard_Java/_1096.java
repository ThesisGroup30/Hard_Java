package Hard_Java;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _1096 {
	public List<String> braceExpansionII(String expression) {
        Set<String> result = evaluateExpression(expression, 0, expression.length() - 1);
        return new ArrayList<>(result);
    }

    private Set<String> evaluateExpression(String expression, int start, int end) {
        Set<String> result = new HashSet<>();
        List<Set<String>> groups = new ArrayList<>();
        int level = 0;
        int startGroup = start;

        for (int i = start; i <= end; i++) {
            char ch = expression.charAt(i);

            if (ch == '{') {
                if (level == 0) {
                    startGroup = i + 1;
                }
                level++;
            } else if (ch == '}') {
                level--;
                if (level == 0) {
                    Set<String> groupResult = evaluateExpression(expression, startGroup, i - 1);
                    groups.add(groupResult);
                }
            } else if (ch == ',' && level == 0) {
                Set<String> groupResult = evaluateExpression(expression, startGroup, i - 1);
                groups.add(groupResult);
                startGroup = i + 1;
            } else if (level == 0) {
                Set<String> singleResult = new HashSet<>();
                singleResult.add(Character.toString(ch));
                groups.add(singleResult);
            }
        }

        if (groups.isEmpty()) {
            return result;
        }

        if (groups.size() == 1) {
            return groups.get(0);
        }

        Set<String> firstGroup = groups.get(0);
        for (int i = 1; i < groups.size(); i++) {
            firstGroup = concatenateSets(firstGroup, groups.get(i));
        }

        result.addAll(firstGroup);
        return result;
    }

    private Set<String> concatenateSets(Set<String> set1, Set<String> set2) {
        Set<String> result = new HashSet<>();
        for (String str1 : set1) {
            for (String str2 : set2) {
                result.add(str1 + str2);
            }
        }
        return result;
    }
}
