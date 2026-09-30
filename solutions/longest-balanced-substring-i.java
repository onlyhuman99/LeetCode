class Solution {
    private static boolean isBalanced(int freq[]){
        int cf = 0;
        for(int f: freq){
            if(f > 0){
                if(cf == 0) cf = f;
                else if(f != cf) return false;
            }
        }
        return true;
    }
    public int longestBalanced(String s) {
        int ans = 0;
        for(int i = 0; i < s.length(); i++){
            int freq[] = new int[26];
            for(int j = i; j < s.length(); j++){
                freq[s.charAt(j) - 'a']++;
                if(isBalanced(freq)) ans = Math.max(ans, j - i + 1);
            }
        }
        return ans;
    }
}