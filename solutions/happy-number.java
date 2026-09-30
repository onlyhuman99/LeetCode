class Solution {
    public boolean isHappy(int n) {
        
        int s = sod(n), f = sod(sod(n));

        while(s != f){
            s = sod(s);
            f = sod(sod(f));
        }
        return (s == 1);        
    }

    public static int sod(int n){
        int res = 0;
        while(n != 0){
            res += (n % 10) * (n % 10);
            n /= 10;
        }
        return res;
    }
}