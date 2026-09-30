class Solution {
    public int sumDivisibleByK(int[] nums, int k) {
        HashMap<Integer, Integer> freq = new HashMap<>();

        for(int i: nums) freq.put(i, freq.getOrDefault(i, 0) + 1);

        int ans = 0;
        for(Map.Entry<Integer, Integer> entry : freq.entrySet()){
            int a = entry.getKey(), b = entry.getValue();
            if(b % k == 0) ans += a * b;
        }
        return ans;
    }
}