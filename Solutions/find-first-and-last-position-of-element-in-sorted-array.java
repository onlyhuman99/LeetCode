class Solution {
    public int[] searchRange(int[] nums, int target) {
        int fo = -1, lo = -1, low = 0, high = nums.length - 1;
        while(low <= high){
            int mid = low + (high - low) / 2;
            if(nums[mid] == target){
                fo = mid;
                high = mid - 1;
            }
            else if(nums[mid] > target) high = mid - 1;
            else low = mid + 1;
        }
        low = 0;
        high = nums.length - 1;
        while(low <= high){
            int mid = low + (high - low) / 2;
            if(nums[mid] == target){
                lo = mid;
                low = mid + 1;
            }
            else if(nums[mid] > target) high = mid - 1;
            else low = mid + 1;
        }
        int ans[] = new int[2];
        ans[0] = fo;
        ans[1] = lo;
        return ans;
    }
}