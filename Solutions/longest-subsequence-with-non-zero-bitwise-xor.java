class Solution {
    public int longestSubsequence(int[] nums) {
        if(nums.length == 1) return nums[0] != 0 ? 1 : 0;

        int res = 0;
        for(int k: nums) res ^= k;
        if(res != 0) return nums.length;

        for(int k: nums) if(k != 0) return nums.length - 1;
        return 0;
    }
}