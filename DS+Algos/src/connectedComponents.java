import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by user on 9/21/2018.
 */
public class connectedComponents {
    ArrayList<ArrayList<Integer>> groups=new ArrayList<>();
    ArrayList<Integer> curGroup=new ArrayList<>();
    ArrayDeque<Integer> stack=new ArrayDeque<>();
    HashSet<Integer> visited=new HashSet<>();
    HashMap<Integer, HashSet<Integer>> edges;
    HashMap<Integer, HashSet<Integer>> reverse;
    public connectedComponents(HashMap<Integer, HashSet<Integer>> edges,HashMap<Integer, HashSet<Integer>> reverse){
        this.edges=edges;
        this.reverse=reverse;
        for(int start: edges.keySet())
            DFSorder(start);
        visited=new HashSet<>();
//            System.out.println(stack);
        while(!stack.isEmpty()){
            DFSgroup(stack.pollFirst());
            if(curGroup.size()!=0) {
                groups.add(curGroup);
                curGroup=new ArrayList<>();
            }
        }
    }
    public void DFSorder(int start){
        if(visited.contains(start))
            return;
        visited.add(start);
        for(int e:edges.get(start))
            DFSorder(e);
        stack.addFirst(start);

    }

    public void DFSgroup(int start){
        if(visited.contains(start))
            return;
        visited.add(start);
        curGroup.add(start);
        for(int e:reverse.get(start))
            DFSgroup(e);
    }
}