package Hard_Java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class _936 {
	public int[] movesToStamp(String stamp, String target) {
        char[] sArr = stamp.toCharArray();
        char[] tArr = target.toCharArray();
        int m = sArr.length;
        int n = tArr.length;
        List<Integer> result = new ArrayList<>();
        boolean[] stamped = new boolean[n];
        int stampedCount = 0;

        while (stampedCount < n) {
            boolean hasStamped = false;

            for (int i = 0; i <= n - m; i++) {
                if (!stamped[i] && canStamp(sArr, tArr, i)) {
                    hasStamped = true;
                    stampedCount += stampString(sArr, tArr, i);
                    result.add(i);
                    if (stampedCount == n) {
                        break;
                    }
                }
            }

            if (!hasStamped) {
                return new int[0];
            }
        }

        Collections.reverse(result);
        return result.stream().mapToInt(i -> i).toArray();
    }

    private boolean canStamp(char[] stamp, char[] target, int start) {
        for (int i = 0; i < stamp.length; i++) {
            if (target[start + i] != '*' && target[start + i] != stamp[i]) {
                return false;
            }
        }
        return true;
    }

    private int stampString(char[] stamp, char[] target, int start) {
        int stampedCount = 0;
        for (int i = 0; i < stamp.length; i++) {
            if (target[start + i] != '*') {
                target[start + i] = '*';
                stampedCount++;
            }
        }
        return stampedCount;
    }
}
