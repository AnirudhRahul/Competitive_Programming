import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by user on 10/10/2018.
 */
public class walkToRemember{
    static class ConnectedComponents {
        ArrayList<ArrayList<Integer>> groups=new ArrayList<>();
        ArrayList<Integer> curGroup=new ArrayList<>();
        ArrayDeque<Integer> stack=new ArrayDeque<>();
        HashSet<Integer> visited=new HashSet<>();
        HashMap<Integer, HashSet<Integer>> edges;
        HashMap<Integer, HashSet<Integer>> reverse;
        public ConnectedComponents(HashMap<Integer, HashSet<Integer>> edges,HashMap<Integer, HashSet<Integer>> reverse){
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

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer=new StringTokenizer(br.readLine());
        int nodes=Integer.parseInt(stringTokenizer.nextToken());
        int edges=Integer.parseInt(stringTokenizer.nextToken());
        HashMap<Integer, HashSet<Integer>> edgeList=new HashMap<>();
        HashMap<Integer, HashSet<Integer>> reverseList=new HashMap<>();
//        for(int i=0;i<=10e5;i++){
//            edgeList.put(i, new HashSet<>());
//            reverseList.put(i, new HashSet<>());
//        }
        TreeSet<Integer> set=new TreeSet<>();
        for(int i=0;i<edges;i++){
            StringTokenizer tokenizer=new StringTokenizer(br.readLine());
            int start=Integer.parseInt(tokenizer.nextToken());
            int end=Integer.parseInt(tokenizer.nextToken());
            set.add(start);
            set.add(end);
            if(!edgeList.containsKey(start)) {
                edgeList.put(start, new HashSet<>());
            }
            if(!edgeList.containsKey(end)) {
                edgeList.put(end, new HashSet<>());
            }
            if(!reverseList.containsKey(end))
                reverseList.put(end,new HashSet<>());
            if(!reverseList.containsKey(start))
                reverseList.put(start,new HashSet<>());
            edgeList.get(start).add(end);
            reverseList.get(end).add(start);
        }
        ConnectedComponents components=new ConnectedComponents(edgeList,reverseList);
        HashMap<Integer,Integer> answers=new HashMap<>();
        for(ArrayList<Integer> e:components.groups){
            for(int cur:e)
                answers.put(cur,e.size());
        }
        for(int e:set){
            System.out.print((answers.get(e)==1?0:1)+" ");

        }



    }
}
