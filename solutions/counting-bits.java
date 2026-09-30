class Solution {
    public int[] countBits(int n) {
        int ans[] = new int[n + 1];
        for(int i = 0; i <= n; i++) ans[i] = CountBits(i);

        return ans;
    }
    public static int CountBits(int n){
        int ans = 0;
        while(n > 0){
            ans += (n & 1);
            n >>= 1;
        }
        return ans;
    }
}