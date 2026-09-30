class Solution {
    public void moveZeroes(int[] nums) {
        int p1 = 0, p2 = 0;
        while(p2 < nums.length && p1 < nums.length){
            if(nums[p1] == 0){ p1++;}
            else{
                int temp = nums[p1];
                nums[p1++] = nums[p2];
                nums[p2++] = temp;
            }
        }
    }

}