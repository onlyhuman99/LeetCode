class Solution {
    public int[] decimalRepresentation(int n) {

        int dupN = n, m = 0;
        while(dupN > 0){
            if(dupN % 10 != 0){
                m++;
                dupN /= 10;
            }
            else{
                dupN /= 10;
            }
        }
        
        int i = m - 1, j = 1;
        int dr[] = new int[m];
        while(n > 0 && i >= 0){
            if(n % 10 != 0) dr[i--] = (n % 10) * j;
            n /= 10;
            j *= 10;
        }
        return dr;
    }
}