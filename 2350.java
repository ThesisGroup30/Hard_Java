class Solution {
    public int shortestSequence(int[] rolls, int k) {
        Set<List<Integer>> sequences = new HashSet<>();
        List<Integer> currentSequence = new ArrayList<>();
        generateSequences(rolls, k, 0, currentSequence, sequences);
        for (int i = 1; i <= rolls.length + 1; i++) {
            for (List<Integer> sequence : sequences) {
                if (sequence.size() == i) {
                    boolean isPossible = true;
                    for (int j = 0; j < sequence.size(); j++) {
                        if (!isSubsequence(rolls, sequence.get(j), j)) {
                            isPossible = false;
                            break;
                        }
                    }
                    if (!isPossible) {
                        return i;
                    }
                }
            }
        }
        return rolls.length + 1;
    }
    
    private void generateSequences(int[] rolls, int k, int index, List<Integer> currentSequence, Set<List<Integer>> sequences) {
        if (index == rolls.length) {
            sequences.add(new ArrayList<>(currentSequence));
            return;
        }
        for (int i = 1; i <= k; i++) {
            currentSequence.add(i);
            generateSequences(rolls, k, index + 1, currentSequence, sequences);
            currentSequence.remove(currentSequence.size() - 1);
        }
    }
    
    private boolean isSubsequence(int[] rolls, int value, int index) {
        for (int i = index; i < rolls.length; i++) {
            if (rolls[i] == value) {
                return true;
            }
        }
        return false;
    }
}
