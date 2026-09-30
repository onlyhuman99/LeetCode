class Solution {
    public int[] recoverOrder(int[] order, int[] friends) {
        
        Set<Integer> frnds = new HashSet<>();
        for(int k: friends){
            frnds.add(k);
        }

        List<Integer> ans = new ArrayList<>();
        for(int k: order){
            if(frnds.contains(k)) ans.add(k);
        }

        int res[] = new int[ans.size()];
        for(int i = 0; i < ans.size(); i++){
            res[i] = ans.get(i);
        }
        return res;
    }
}