class Solution {
    public int[] bestTower(int[][] towers, int[] center, int radius) {
        int cx = center[0], cy = center[1];

        int mq = -1, rx = -1, ry = -1;

        for(int[] a: towers){
            int x = a[0], y = a[1], q = a[2];

            int d = Math.abs(x - cx) + Math.abs(y - cy);

            if(d <= radius){
                if(q > mq || (q == mq && (rx == -1 || x < rx || (x == rx && y < ry)))) {
                    mq = q;
                    rx = x;
                    ry = y;
                }
            }
        }

        return mq == -1 ? new int[]{-1, -1} : new int[]{rx, ry};
        
    }
}