class Solution {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        int N = word1.length, M = word2.length;

        String S1 = "", S2 = "";
        for(int i = 0; i < N; i++){
            S1 += word1[i];
        }

        for(int i = 0; i < M; i++){
            S2 += word2[i];
        }

        return S1.equals(S2);
    }
}