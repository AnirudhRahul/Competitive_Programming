import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by user on 9/22/2018.
 */
public class SCC{
    static class connectedComponents {
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
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer=new StringTokenizer(br.readLine());
        int nodes=Integer.parseInt(stringTokenizer.nextToken());
        int edges=Integer.parseInt(stringTokenizer.nextToken());
        HashMap<Integer,HashSet<Integer>> edgeList=new HashMap<>();
        HashMap<Integer,HashSet<Integer>> reversedList=new HashMap<>();
        for(int i=1;i<=nodes;i++){
            edgeList.put(i,new HashSet<>());
            reversedList.put(i,new HashSet<>());
        }
        for(int i=0;i<edges;i++){
            StringTokenizer tokenizer=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(tokenizer.nextToken());
            int b=Integer.parseInt(tokenizer.nextToken());
            edgeList.get(a).add(b);
            reversedList.get(b).add(a);
        }
        connectedComponents result=new connectedComponents(edgeList,reversedList);
        int even=0;
        int odd=0;
        for(ArrayList<Integer> cur:result.groups)
            if(cur.size()%2==0)
                even+=cur.size();
            else
                odd+=cur.size();
//        System.out.println(result.groups);
        System.out.println(odd-even);

    }
}
