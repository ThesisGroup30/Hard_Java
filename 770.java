import java.util.*;

class Solution {
    public List<String> basicCalculatorIV(String expression, String[] evalvars, int[] evalints) {
        // Map of variable names to their integer values
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < evalvars.length; i++) {
            map.put(evalvars[i], evalints[i]);
        }
        
        // Convert the expression to a list of tokens
        List<String> tokens = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                sb.append(c);
            } else if (c == '+' || c == '-' || c == '*' || c == '(' || c == ')') {
                if (sb.length() > 0) {
                    tokens.add(sb.toString());
                    sb.setLength(0);
                }
                tokens.add(Character.toString(c));
            }
        }
        if (sb.length() > 0) {
            tokens.add(sb.toString());
        }
        
        // Evaluate the expression and store the result in a map of terms
        Map<List<String>, Integer> termMap = evaluate(tokens, map);
        
        // Convert the map of terms to a list of strings, sorted by degree and lexicographic order
        List<String> result = new ArrayList<>();
        List<List<String>> sortedTerms = new ArrayList<>(termMap.keySet());
        Collections.sort(sortedTerms, new Comparator<List<String>>() {
            public int compare(List<String> t1, List<String> t2) {
                if (t1.size() != t2.size()) {
                    return t2.size() - t1.size();
                }
                for (int i = 0; i < t1.size(); i++) {
                    int cmp = t1.get(i).compareTo(t2.get(i));
                    if (cmp != 0) {
                        return cmp;
                    }
                }
                return 0;
            }
        });
        for (List<String> term : sortedTerms) {
            int coef = termMap.get(term);
            if (coef != 0) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(coef);
                for (String var : term) {
                    sb2.append('*');
                    sb2.append(var);
                }
                result.add(sb2.toString());
            }
        }
        
        return result;
    }
    
    // Recursive helper method to evaluate an expression and return a map of terms
    private Map<List<String>, Integer> evaluate(List<String> tokens, Map<String, Integer> map) {
        // Base case: a single variable or integer
        if (tokens.size() == 1) {
            String token = tokens.get(0);
            if (Character.isDigit(token.charAt(0))) {
                return Collections.singletonMap(Collections.emptyList(), Integer.parseInt(token));
            } else {
                List<String> term = new ArrayList<>();
                term.add(token);
                return Collections.singletonMap(term, 1);
            }
        }
        
        // Step 1: find the operator with the lowest precedence
        int minPrecedence = Integer.MAX_VALUE;
        int minIndex = -1;
        int depth = 0;
        for (int i = tokens.size() - 1; i >= 0; i--) {
            String token = tokens.get(i);
            if (token.equals(")")) {
                depth++;
            } else if (token.equals("(")) {
                depth--;
            } else if (depth == 0 && (token.equals("+") || token
