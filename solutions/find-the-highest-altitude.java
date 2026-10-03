class Solution {
    public int largestAltitude(int[] gain) {
        int N = gain.length, max = 0, ps = 0;

        for(int i = 0; i < N; i++) {
            ps += gain[i];
            max = Math.max(max, ps);
        }
        

        return max;
    }
}