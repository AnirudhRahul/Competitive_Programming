/**
 * Created by user on 11/15/2019.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

//Incorrect solution
public class CHN15E {
    static class Edge{
        int a, b;
        public Edge(int a, int b){
            this.a=a;
            this.b=b;
        }
        public String toString(){return (a+1)+" "+(b+1);}
    }

    static ArrayList<Edge> treeEdges;
    static boolean[] inTree;
    static HashSet<Integer>[] edgeList;
    static void recurse(int root){
        inTree[root]=true;
//        System.out.println("Root: "+root);
        ArrayList<Integer> to = new ArrayList<>();

        outer:for(int child:edgeList[root]){
                if(inTree[child])
                    continue;

//            System.out.println("Child: "+child +"\\w Root:"+root);
                for(int child2:edgeList[root]) {
                    if (child == child2)
                        continue;
                    if (!edgeList[child].contains(child2))
                        continue outer;
                }
//            for(int outGoing:edgeList[child]){
//                    if(outGoing!=root && !edgeList[root].contains(outGoing))
//                        continue outer;
//                }
                to.add(child);
        }
//        for(int out:edgeList[root])
//            edgeList[out].remove(root);
//        for(int i=0;i<to.size();i++){
//            for(int j=i+1;j<to.size();j++){
//                edgeList[to.get(i)].remove(to.get(j));
//                edgeList[to.get(j)].remove(to.get(i));
//            }
//
//        }


        for(int child:to){
                treeEdges.add(new Edge(root, child));
                recurse(child);
        }


    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        int cases = Integer.parseInt(br.readLine());
        StringTokenizer tokenizer;
        while (cases-->0){
            tokenizer = new StringTokenizer(br.readLine());
            int vertices = Integer.parseInt(tokenizer.nextToken());
            int edges = Integer.parseInt(tokenizer.nextToken());
            treeEdges = new ArrayList<>(vertices);
            edgeList = new HashSet[vertices];
            inTree = new boolean[vertices];
            for(int i=0;i<vertices;i++)
                edgeList[i]=new HashSet<>();
            for(int i=0;i<edges;i++){
                tokenizer = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(tokenizer.nextToken())-1;
                int b = Integer.parseInt(tokenizer.nextToken())-1;
                edgeList[a].add(b);
                edgeList[b].add(a);
            }
            recurse(0);
//            System.out.println(Arrays.toString(edgeList));
            System.out.println(treeEdges);
            for(Edge e:treeEdges){
                wr.write(e.toString());
                wr.write("\n");
            }
//            wr.write("\n");
        }
        wr.flush();
    }
}
