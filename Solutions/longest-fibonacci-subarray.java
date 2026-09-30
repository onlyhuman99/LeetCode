class Solution {
    public int longestSubarray(int[] nums) {
        int len = 2, ans = 2;
        for(int i = 2; i < nums.length; i++){
            if(nums[i] == nums[i - 1] + nums[i - 2]) len++;
            else len = 2;
            ans = Math.max(len, ans);
        }
        return ans;
    }
}