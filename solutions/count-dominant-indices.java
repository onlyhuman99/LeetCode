class Solution {
    public double avgSum(int[] nums, int i){
        int avg = 0, k = 0;
        for(int j = i + 1; j < nums.length; j++){
            avg += nums[j];
            k++;
        }
        return (k == 0) ? 0 : (double) avg/k;
    }
    public int dominantIndices(int[] nums) {
        int c = 0;
        for(int i = nums.length - 2; i >= 0; i--){
            if(nums[i] > avgSum(nums, i)) c++;
        }
        return c;
    }
}