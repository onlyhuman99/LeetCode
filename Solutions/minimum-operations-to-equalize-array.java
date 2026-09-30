class Solution {
    public int minOperations(int[] nums) {
        boolean isAllEqual = true;
        for(int k: nums){
            if(k != nums[0]){
                isAllEqual = false;
                break;
            } 
        }
        if(isAllEqual) return 0;
        return 1;
    }
}