package leetcode.bfs_dfs;

import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class M_Problem_0841_KeysAndRooms {

    // rooms.get(i) -> keys[]   rooms.get(i).get(j)
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        if(rooms == null || rooms.size() == 0) return true;

        HashSet<Integer> set = new HashSet<>();
        set.add(0);
        return process(rooms, 0, set);
    }

    public boolean process(List<List<Integer>> rooms, int index, HashSet<Integer> set){
        if(set.size() == rooms.size()) return true;

        boolean res = false;

        List<Integer> keys = rooms.get(index);
        for(int i = 0; i < keys.size(); i++){
            int nextRoom = keys.get(i);
            if(!set.contains(nextRoom)){
                set.add(nextRoom);
                res |= process(rooms, nextRoom, set);
            }
        }

        return res;
    }

    // iterative solution
    public boolean canVisitAllRooms2(List<List<Integer>> rooms) {
        if(rooms == null || rooms.size() == 0) return true;
        Stack<Integer> stack = new Stack<>();
        HashSet<Integer> seen = new HashSet<>();
        seen.add(0);
        stack.add(0);
        while(!stack.isEmpty()){
            int curRoom = stack.pop();
            for(int i = 0; i < rooms.get(curRoom).size(); i++){
                if(!seen.contains(rooms.get(curRoom).get(i))){
                    seen.add(rooms.get(curRoom).get(i));
                    stack.add(rooms.get(curRoom).get(i));
                }
            }

        }

        return seen.size() == rooms.size();
    }


}
