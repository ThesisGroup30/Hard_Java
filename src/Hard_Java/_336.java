package Hard_Java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _336 {
	public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        
        // build a map of the reversed words to their indices in the array
        for (int i = 0; i < words.length; i++) {
            map.put(new StringBuilder(words[i]).reverse().toString(), i);
        }
        
        // check for palindromes that are the concatenation of two words
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (int j = 0; j <= word.length(); j++) {
                String left = word.substring(0, j);
                String right = word.substring(j);
                if (isPalindrome(left) && map.containsKey(right) && map.get(right) != i) {
                    result.add(Arrays.asList(map.get(right), i));
                }
                if (isPalindrome(right) && map.containsKey(left) && map.get(left) != i && !right.isEmpty()) {
                    result.add(Arrays.asList(i, map.get(left)));
                }
            }
        }
        
        return result;
    }
    
    private boolean isPalindrome(String word) {
        int i = 0, j = word.length() - 1;
        while (i < j) {
            if (word.charAt(i++) != word.charAt(j--)) {
                return false;
            }
        }
        return true;
    }
}
