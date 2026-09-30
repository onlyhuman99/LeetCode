class Solution {
    public int maximumWealth(int[][] accounts) {
        int Wealth[] = new int[accounts.length];

        for(int i = 0; i < Wealth.length; i++){
            for(int j = 0; j < accounts[0].length; j++){
                Wealth[i] += accounts[i][j];
            }
        }

        Arrays.sort(Wealth);
        return Wealth[Wealth.length - 1];
    }
}