import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by user on 3/9/2019.
 */
public class GCDgraphs{

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

    public static int gcd(int a, int b){
        if(a==0)
            return b;
        return gcd(b%a,a);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer1 = new StringTokenizer(br.readLine());
        int vertexCount = Integer.parseInt(tokenizer1.nextToken());
        int edgeCount = Integer.parseInt(tokenizer1.nextToken());
        int[] nums = new int[vertexCount+1];
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        for(int i=1;i<nums.length;i++){
            nums[i] = Integer.parseInt(tokenizer.nextToken());
        }
        HashMap<Integer, HashSet<Integer>> edges = new HashMap<>();
        HashMap<Integer, HashSet<Integer>> reverse = new HashMap<>();
        for(int i=1;i<=vertexCount;i++){
            edges.put(i, new HashSet<>());
            reverse.put(i, new HashSet<>());
        }
        for(int i=0;i<edgeCount;i++){
            StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());

            edges.get(a).add(b);
            reverse.get(b).add(a);

        }

        connectedComponents scc = new connectedComponents(edges, reverse);


        int[] groupNumber = new int[vertexCount+1];
        int[] connectedGCD = new int[scc.groups.size()];
        int groupIndex = 0;
        for(ArrayList<Integer> connectedList : scc.groups){
            int curGCD = nums[connectedList.get(0)];
            for(int i=1;i<connectedList.size();i++)
                curGCD=gcd(curGCD,nums[connectedList.get(i)]);
            connectedGCD[groupIndex]=curGCD;
            for(int vertex:connectedList)
                groupNumber[vertex]=groupIndex;
            groupIndex++;
        }
        HashMap<Integer,HashSet<Integer>> connectedEdges = new HashMap<>();
        for(int i=0;i<groupIndex;i++){
            connectedEdges.put(i, new HashSet<>());
        }
        for(int i=1;i<=vertexCount;i++){
            int groupNum_start = groupNumber[i];
            for(int out:edges.get(i)){
                int groupNum_out = groupNumber[out];
                connectedEdges.get(groupNum_start).add(groupNum_out);
            }
        }
        HashSet<Integer> visitedComponents = new HashSet<>();
        int min=Integer.MAX_VALUE;
        for(int start=0;start<groupIndex;start++){
            ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
            if(visitedComponents.contains(start))
                continue;
            arrayDeque.addLast(start);
            int curGCD = connectedGCD[start];
            while(!arrayDeque.isEmpty()){
                int curGroup = arrayDeque.pollFirst();
                visitedComponents.add(curGroup);
                curGCD=gcd(curGCD, connectedGCD[curGroup]);
                for(int next:connectedEdges.get(curGroup)){
                    if(!visitedComponents.contains(next))
                        arrayDeque.addLast(next);
                }

            }
            min=Math.min(curGCD,min);
//            System.out.println("Cur gcd "+curGCD);
        }
        System.out.println(min);



    }
}
