class Solution {
    private void reverse(int nums[], int i, int j){
        while(i < j){
            int temp = nums[i];
            nums[i++] = nums[j];
            nums[j--] = temp;
        }
    }
    public void rotate(int[] nums, int k) {
        int N = nums.length;
        k %= N; 
        if(k == 0) return;

        reverse(nums, 0, N - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, N - 1);
    }
}