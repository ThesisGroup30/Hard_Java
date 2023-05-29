package Hard_Java;

public class _810 {
	public boolean xorGame(int[] nums) {
        int xor = 0;
        for (int n : nums) {
            xor ^= n;
        }
        return xor == 0 || nums.length % 2 == 0;
    }
}
