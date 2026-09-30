class Solution {
    public int alternatingSum(int[] nums) {
        int evenSum = 0, oddSum = 0;
        for(int i = 0; i < nums.length; i += 2) evenSum += nums[i];
        for(int i = 1; i < nums.length; i += 2) oddSum += nums[i];

        return evenSum - oddSum;
    }
}