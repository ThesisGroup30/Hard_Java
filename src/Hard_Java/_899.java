package Hard_Java;

import java.util.Arrays;

public class _899 {
	public String orderlyQueue(String s, int k) {
        if (k > 1) {
            // if k > 1, we can rearrange the letters in any order
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            return new String(chars);
        } else {
            // if k = 1, we need to consider all possible rotations of the string
            String min = s;
            for (int i = 0; i < s.length(); i++) {
                String rotated = s.substring(i) + s.substring(0, i);
                if (rotated.compareTo(min) < 0) {
                    min = rotated;
                }
            }
            return min;
        }
    }
}
