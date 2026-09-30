class Solution {
    public int deleteGreatestValue(int[][] grid) {
        
        int Result = 0, rows = grid.length, cols = grid[0].length;
        int Max[] = new int[cols];

        for(int i = 0; i < rows; i++){
            Arrays.sort(grid[i]);
        }

        for(int j = cols - 1; j >= 0; j--){
            for(int i = 0; i < rows; i++){
                Max[j] = (Max[j] < grid[i][j]) ? grid[i][j] : Max[j];
            }
        }

        for(int i = 0; i < cols; i++){
            Result += Max[i];
        }
        return Result;
    }
}