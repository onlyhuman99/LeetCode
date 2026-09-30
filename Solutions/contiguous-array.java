class Solution {
    public int findMaxLength(int[] nums) {
        
        for(int i = 0; i < nums.length; i++) nums[i] = (nums[i] == 0) ? -1 : 1;

        int PS[] = new int[nums.length];
        PS[0] = nums[0];
        for(int i = 1; i < nums.length; i++) PS[i] = PS[i - 1] + nums[i];

        int res = 0;
        HashMap<Integer, Integer> hm = new HashMap<>();
        hm.put(0, -1);
        for(int i = 0; i < nums.length; i++) {
            if(hm.containsKey(PS[i])) res = Math.max(res, i - hm.get(PS[i]));
            else hm.put(PS[i], i);
        }
        return res;    
    }
}