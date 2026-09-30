class Solution {
    public int diagonalSum(int[][] mat) {
        int DiagSum = 0;
        for(int i = 0; i < mat.length; i++){
            for(int j = 0; j < mat.length; j++){
                if((i == j) || (i + j == mat.length - 1)){
                    DiagSum += mat[i][j];
                }
            }
        }
        return DiagSum;
    }
}