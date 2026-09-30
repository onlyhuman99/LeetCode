class Solution {
    public boolean scoreBalance(String s) {
        int TotalScore = 0;
        for(char k: s.toCharArray()) TotalScore += k - 'a' + 1;

        int Score = 0;
        for(int i = 0; i < s.length() - 1; i++){
            Score += s.charAt(i) - 'a' + 1;
            if(Score == TotalScore - Score) return true;
        }
        return false;
    }
}