class Solution {
    public int[] twoSum(int[] nums, int target) {
        int l = 0, h = nums.length - 1;

        while(l < h){
            int num = nums[l] + nums[h];
            if(num == target) return new int[]{l + 1, h + 1};
            else if(num < target) l++;
            else h--;
        }
        return new int[]{0};
    }
}