import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by user on 9/20/2018.
 */
//References problem C from round 510 of CodeForces
public class letterOrdering {
    static class topSort{
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
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int len=Integer.parseInt(br.readLine());
        ArrayList<String> list=new ArrayList<>(len);
        for(int i=0;i<len;i++)
            list.add(br.readLine());
        HashMap<Integer,HashSet<Integer>> edges=new HashMap<>();
        int start=(int)'a';
        for(int i=start;i<start+26;i++){
            edges.put(i,new HashSet<>());
        }

        for(int i=0;i<len-1;i++){
            String cur=list.get(i);
            String next=list.get(i+1);
            int index=0;
            while(cur.charAt(index)==next.charAt(index)){
                index++;
                if(index==cur.length()||index==next.length()) {
                    if(next.length()<cur.length()){
                        System.out.println("Impossible");
                        return;
                    }
                    break;
                }
            }
            if(index<Math.min(cur.length(),next.length())){
                edges.get((int)cur.charAt(index)).add((int)next.charAt(index));
            }
        }
//        System.out.println(edges);
        topSort sort=new topSort(edges);
        String out="";
        for(int e:sort.ordering)
            out+=((char)e);
        System.out.println(out.length()==26?out:"Impossible");
    }
}
