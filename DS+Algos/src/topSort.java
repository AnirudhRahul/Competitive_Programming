import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by user on 9/20/2018.
 */
public class topSort{
    HashMap<Integer,Integer> indegree=new HashMap<>();
    ArrayList<Integer> ordering=new ArrayList<>();
    //Assumes all vertices in the edges keyset
    public topSort(HashMap<Integer,HashSet<Integer>> edges){
        for(int key:edges.keySet())
            indegree.put(key,0);
        for(int key:edges.keySet())
            for(int out:edges.get(key))
                indegree.put(out,indegree.remove(out)+1);

        ArrayDeque<Integer> deque=new ArrayDeque<>();
        for(int key:edges.keySet())
            if(indegree.get(key)==0)
                deque.add(key);

        while(!deque.isEmpty()){
            int cur=deque.pollFirst();
            ordering.add(cur);
            for(int out:edges.get(cur)) {
                int val=indegree.get(out);
                indegree.put(out,val-1);
                if(val-1==0)
                    deque.add(out);
            }

        }
    }

}
